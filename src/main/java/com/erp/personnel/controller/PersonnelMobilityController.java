package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.service.IPersonnelMobilityService;
import com.erp.personnel.util.Jwutil;
import com.erp.personnel.util.WordUtils;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调岗记录 Controller
 *
 * @author qiufeng
 * @date 2021-09-25 08:56:16
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelMobilityController extends BaseController {

    private final IPersonnelMobilityService personnelMobilityService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "personnelMobility")
    public String personnelMobilityIndex(){
        return FebsUtil.view("personnelMobility/personnelMobility");
    }

    @GetMapping("personnelMobility")
    @ResponseBody
    @RequiresPermissions("personnelMobility:list")
    public FebsResponse getAllPersonnelMobilitys(PersonnelMobility personnelMobility) {
        return new FebsResponse().success().data(personnelMobilityService.findPersonnelMobilitys(personnelMobility));
    }

    @GetMapping("personnelMobility/list")
    @ResponseBody
    @RequiresPermissions("personnelMobility:list")
    public FebsResponse personnelMobilityList(QueryRequest request, PersonnelMobility personnelMobility) {
        Map<String, Object> dataTable = getDataTable(this.personnelMobilityService.findPersonnelMobilitys(request, personnelMobility));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelMobility", exceptionMessage = "新增PersonnelMobility失败")
    @PostMapping("personnelMobility/add")
    @ResponseBody
    @RequiresPermissions("personnelMobility:add")
    public FebsResponse addPersonnelMobility(@Valid PersonnelMobility personnelMobility) throws ParseException {
        this.personnelMobilityService.createPersonnelMobility(personnelMobility);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelMobility", exceptionMessage = "删除PersonnelMobility失败")
    @GetMapping("personnelMobility/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelMobility:delete")
    public FebsResponse deletePersonnelMobility(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelMobilityService.deletePersonnelMobility(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelMobility", exceptionMessage = "修改PersonnelMobility失败")
    @PostMapping("personnelMobility/update")
    @ResponseBody
    @RequiresPermissions("personnelMobility:update")
    public FebsResponse updatePersonnelMobility(PersonnelMobility personnelMobility) {
        this.personnelMobilityService.updatePersonnelMobility(personnelMobility);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelMobility", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelMobility/excel")
    @ResponseBody
    @RequiresPermissions("personnelMobility:export")
    public void export(QueryRequest queryRequest, PersonnelMobility personnelMobility, HttpServletResponse response) {
        List<PersonnelMobility> personnelMobilitys = this.personnelMobilityService.findPersonnelMobilitys(queryRequest, personnelMobility).getRecords();
        ExcelKit.$Export(PersonnelMobility.class, response).downXlsx(personnelMobilitys, false);
    }

    @GetMapping("personnelReceiveArchivesMobility/list")
    @ResponseBody
    @RequiresPermissions("personnelMobility:list")
    public FebsResponse personnelReceiveArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        return new FebsResponse().success().data(getDataTable(personnelMobilityService.findReceiveArchivesMobilityList(personnelArchives,request)));
    }

    @GetMapping("personnelMobilityUser/list/{userId}")
    @ResponseBody
    @RequiresPermissions("personnelMobility:list")
    public FebsResponse personnelMobilityUserList(QueryRequest request,@PathVariable String userId) {
        Map<String, Object> dataTable = getDataTable(this.personnelMobilityService.personnelMobilityUserList(request, userId));
        return new FebsResponse().success().data(dataTable);
    }

    //岗位移动导出文档
    @GetMapping(value = "personnelMobility/export/{id}")
    public void downloadWord(@PathVariable Long id,HttpServletResponse response) {
        String strDateFormat = "yyyy年MM月dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        PersonnelMobility mobility=  personnelMobilityService.personnelMobilityTransfer(id);
        Map<String, Object> dataMap =  new HashMap<String, Object>();
        dataMap.put("userName", mobility.getUserName());
        String gender = mobility.getGender();
        if (gender.equals("1")){
           gender= "男";
        }else {
            gender= "女";
        }
        dataMap.put("gender",gender);
        dataMap.put("birthdate",sdf.format(mobility.getBirthdate()).toString());
        dataMap.put("phone", mobility.getPhone());
        dataMap.put("identityArd", mobility.getIdentityArd());
        dataMap.put("departmentName", mobility.getDepartmentName());
        dataMap.put("positionName", mobility.getPositionName());
        dataMap.put("groupingName", mobility.getGroupingName());
        String departmentName = mobility.getToDepartmentName();
        if(departmentName==null){
            departmentName = "暂无调动";
        }
        dataMap.put("toDepartmentName",departmentName);
        String toPositionName = mobility.getToPositionName();
        if(toPositionName==null){
            toPositionName = "暂无调动";
        }
        dataMap.put("toPositionName",toPositionName);
        String toGroupingName = mobility.getToGroupingName();
        if(toGroupingName==null){
            toGroupingName = "暂无调动";
        }
        dataMap.put("toGroupingName",toGroupingName);
        dataMap.put("transferData",  sdf.format(mobility.getTransferData()).toString());


        String fileName=mobility.getUserName()+ Jwutil.getStringDate().toString()+".doc";
        WordUtils.get(response,dataMap,fileName);

    }
}

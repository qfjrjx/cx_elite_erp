package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.service.IPersonnelMobilityService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
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
    @GetMapping("personnelMobility/delete")
    @ResponseBody
    @RequiresPermissions("personnelMobility:delete")
    public FebsResponse deletePersonnelMobility(PersonnelMobility personnelMobility) {
        this.personnelMobilityService.deletePersonnelMobility(personnelMobility);
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
}

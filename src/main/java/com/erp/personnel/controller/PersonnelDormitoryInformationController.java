package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelDormitoryInformation;
import com.erp.personnel.service.IPersonnelDormitoryInformationService;
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
import java.util.List;
import java.util.Map;

/**
 * 宿舍人员入住信息表 Controller
 *
 * @author qiufeng
 * @date 2021-09-19 11:43:19
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelDormitoryInformationController extends BaseController {

    private final IPersonnelDormitoryInformationService personnelDormitoryInformationService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "personnelDormitoryInformation")
    public String personnelDormitoryInformationIndex(){
        return FebsUtil.view("personnelDormitoryInformation/personnelDormitoryInformation");
    }

    @GetMapping("personnelDormitoryInformation")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:list")
    public FebsResponse getAllPersonnelDormitoryInformations(PersonnelDormitoryInformation personnelDormitoryInformation) {
        return new FebsResponse().success().data(personnelDormitoryInformationService.findPersonnelDormitoryInformations(personnelDormitoryInformation));
    }

    @GetMapping("personnelDormitoryInformation/list")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:list")
    public FebsResponse personnelDormitoryInformationList(QueryRequest request, PersonnelDormitoryInformation personnelDormitoryInformation) {
        Map<String, Object> dataTable = getDataTable(this.personnelDormitoryInformationService.findPersonnelDormitoryInformations(request, personnelDormitoryInformation));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelDormitoryInformation", exceptionMessage = "新增PersonnelDormitoryInformation失败")
    @PostMapping("personnelDormitoryInformation/add")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:add")
    public FebsResponse addPersonnelDormitoryInformation(@Valid PersonnelDormitoryInformation personnelDormitoryInformation) {
        this.personnelDormitoryInformationService.createPersonnelDormitoryInformation(personnelDormitoryInformation);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelDormitoryInformation", exceptionMessage = "删除PersonnelDormitoryInformation失败")
    @GetMapping("personnelDormitoryInformation/delete")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:delete")
    public FebsResponse deletePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {
        this.personnelDormitoryInformationService.deletePersonnelDormitoryInformation(personnelDormitoryInformation);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelDormitoryInformation", exceptionMessage = "修改PersonnelDormitoryInformation失败")
    @PostMapping("personnelDormitoryInformation/update")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:update")
    public FebsResponse updatePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {
        this.personnelDormitoryInformationService.updatePersonnelDormitoryInformation(personnelDormitoryInformation);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelDormitoryInformation", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelDormitoryInformation/excel")
    @ResponseBody
    @RequiresPermissions("personnelDormitoryInformation:export")
    public void export(QueryRequest queryRequest, PersonnelDormitoryInformation personnelDormitoryInformation, HttpServletResponse response) {
        List<PersonnelDormitoryInformation> personnelDormitoryInformations = this.personnelDormitoryInformationService.findPersonnelDormitoryInformations(queryRequest, personnelDormitoryInformation).getRecords();
        ExcelKit.$Export(PersonnelDormitoryInformation.class, response).downXlsx(personnelDormitoryInformations, false);
    }

    @GetMapping("personneldormitoryBackfill/list")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:list")
    public FebsResponse personnelDormitoryList(QueryRequest request, PersonnelDormitory personnelDormitory) {
        Map<String, Object> dataTable = getDataTable(this.personnelDormitoryInformationService.findPersonnelDormitorys(request, personnelDormitory));
        return new FebsResponse().success().data(dataTable);
    }
}

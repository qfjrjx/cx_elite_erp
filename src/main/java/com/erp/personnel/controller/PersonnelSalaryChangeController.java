package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelSalaryChange;
import com.erp.personnel.service.IPersonnelSalaryChangeService;
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
 * 调薪记录 Controller
 *
 * @author qiufeng
 * @date 2021-09-25 10:52:51
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelSalaryChangeController extends BaseController {

    private final IPersonnelSalaryChangeService personnelSalaryChangeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "personnelSalaryChange")
    public String personnelSalaryChangeIndex(){
        return FebsUtil.view("personnelSalaryChange/personnelSalaryChange");
    }

    @GetMapping("personnelSalaryChange")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:list")
    public FebsResponse getAllPersonnelSalaryChanges(PersonnelSalaryChange personnelSalaryChange) {
        return new FebsResponse().success().data(personnelSalaryChangeService.findPersonnelSalaryChanges(personnelSalaryChange));
    }

    @GetMapping("personnelSalaryChange/list")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:list")
    public FebsResponse personnelSalaryChangeList(QueryRequest request, PersonnelSalaryChange personnelSalaryChange) {
        Map<String, Object> dataTable = getDataTable(this.personnelSalaryChangeService.findPersonnelSalaryChanges(request, personnelSalaryChange));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelSalaryChange", exceptionMessage = "新增PersonnelSalaryChange失败")
    @PostMapping("personnelSalaryChange")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:add")
    public FebsResponse addPersonnelSalaryChange(@Valid PersonnelSalaryChange personnelSalaryChange) {
        this.personnelSalaryChangeService.createPersonnelSalaryChange(personnelSalaryChange);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelSalaryChange", exceptionMessage = "删除PersonnelSalaryChange失败")
    @GetMapping("personnelSalaryChange/delete")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:delete")
    public FebsResponse deletePersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange) {
        this.personnelSalaryChangeService.deletePersonnelSalaryChange(personnelSalaryChange);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelSalaryChange", exceptionMessage = "修改PersonnelSalaryChange失败")
    @PostMapping("personnelSalaryChange/update")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:update")
    public FebsResponse updatePersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange) {
        this.personnelSalaryChangeService.updatePersonnelSalaryChange(personnelSalaryChange);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelSalaryChange", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelSalaryChange/excel")
    @ResponseBody
    @RequiresPermissions("personnelSalaryChange:export")
    public void export(QueryRequest queryRequest, PersonnelSalaryChange personnelSalaryChange, HttpServletResponse response) {
        List<PersonnelSalaryChange> personnelSalaryChanges = this.personnelSalaryChangeService.findPersonnelSalaryChanges(queryRequest, personnelSalaryChange).getRecords();
        ExcelKit.$Export(PersonnelSalaryChange.class, response).downXlsx(personnelSalaryChanges, false);
    }
}

package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.service.IPersonnelDormitoryService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 宿舍管理表 Controller
 *
 * @author qiufeng
 * @date 2021-09-19 09:35:28
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelDormitoryController extends BaseController {

    private final IPersonnelDormitoryService personnelDormitoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "personnelDormitory")
    public String personnelDormitoryIndex(){
        return FebsUtil.view("personnelDormitory/personnelDormitory");
    }

    @GetMapping("personnelDormitory")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:list")
    public FebsResponse getAllPersonnelDormitorys(PersonnelDormitory personnelDormitory) {
        return new FebsResponse().success().data(personnelDormitoryService.findPersonnelDormitorys(personnelDormitory));
    }

    @GetMapping("personnelDormitory/list")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:list")
    public FebsResponse personnelDormitoryList(QueryRequest request, PersonnelDormitory personnelDormitory) {
        Map<String, Object> dataTable = getDataTable(this.personnelDormitoryService.findPersonnelDormitorys(request, personnelDormitory));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelDormitory", exceptionMessage = "新增PersonnelDormitory失败")
    @PostMapping("personnelDormitory/add")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:add")
    public FebsResponse addPersonnelDormitory(@Valid PersonnelDormitory personnelDormitory) throws ParseException {
        this.personnelDormitoryService.createPersonnelDormitory(personnelDormitory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelDormitory", exceptionMessage = "删除PersonnelDormitory失败")
    @GetMapping("personnelDormitory/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:delete")
    public FebsResponse deletePersonnelDormitory(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelDormitoryService.deletePersonnelDormitory(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelDormitory", exceptionMessage = "修改PersonnelDormitory失败")
    @PostMapping("personnelDormitory/update")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:update")
    public FebsResponse updatePersonnelDormitory(PersonnelDormitory personnelDormitory) {
        this.personnelDormitoryService.updatePersonnelDormitory(personnelDormitory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelDormitory", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelDormitory/excel")
    @ResponseBody
    @RequiresPermissions("personnelDormitory:export")
    public void export(QueryRequest queryRequest, PersonnelDormitory personnelDormitory, HttpServletResponse response) {
        List<PersonnelDormitory> personnelDormitorys = this.personnelDormitoryService.findPersonnelDormitorys(queryRequest, personnelDormitory).getRecords();
        ExcelKit.$Export(PersonnelDormitory.class, response).downXlsx(personnelDormitorys, false);
    }
}

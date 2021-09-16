package com.erp.personnel.controller;
import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelReceive;
import com.erp.personnel.service.IPersonnelReceiveService;
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
import java.util.List;
import java.util.Map;

/**
 * 领取记录表 Controller
 *
 * @author qiufeng
 * @date 2021-09-14 15:45:00
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelReceiveController extends BaseController {

    private final IPersonnelReceiveService personnelReceiveService;

    @GetMapping("personnelReceive")
    @ResponseBody
    @RequiresPermissions("personnelReceive:list")
    public FebsResponse getAllPersonnelReceives(PersonnelReceive personnelReceive) {
        return new FebsResponse().success().data(personnelReceiveService.findPersonnelReceives(personnelReceive));
    }

    @GetMapping("personnelReceive/list")
    @ResponseBody
    @RequiresPermissions("personnelReceive:view")
    public FebsResponse personnelReceiveList(QueryRequest request, PersonnelReceive personnelReceive) {
        Map<String, Object> dataTable = getDataTable(this.personnelReceiveService.findPersonnelReceives(request, personnelReceive));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelReceive", exceptionMessage = "新增PersonnelReceive失败")
    @PostMapping("personnelReceive/add")
    @ResponseBody
    @RequiresPermissions("personnelReceive:add")
    public FebsResponse addPersonnelReceive(@Valid PersonnelReceive personnelReceive) {
        this.personnelReceiveService.createPersonnelReceive(personnelReceive);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelReceive", exceptionMessage = "删除PersonnelReceive失败")
    @GetMapping("personnelReceive/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelReceive:delete")
    public FebsResponse deletePersonnelReceive(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelReceiveService.deletePersonnelReceive(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelReceive", exceptionMessage = "修改PersonnelReceive失败")
    @PostMapping("personnelReceive/update")
    @ResponseBody
    @RequiresPermissions("personnelReceive:update")
    public FebsResponse updatePersonnelReceive(PersonnelReceive personnelReceive) {
        this.personnelReceiveService.updatePersonnelReceive(personnelReceive);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelReceive", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelReceive/excel")
    @ResponseBody
    @RequiresPermissions("personnelReceive:export")
    public void export(QueryRequest queryRequest, PersonnelReceive personnelReceive, HttpServletResponse response) {
        List<PersonnelReceive> personnelReceives = this.personnelReceiveService.findPersonnelReceives(queryRequest, personnelReceive).getRecords();
        ExcelKit.$Export(PersonnelReceive.class, response).downXlsx(personnelReceives, false);
    }
    @GetMapping("personnelReceiveArchives/list")
    @ResponseBody
    @RequiresPermissions("personnelArchives:view")
    public FebsResponse personnelReceiveArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        return new FebsResponse().success().data(getDataTable(personnelReceiveService.findReceiveArchivesList(personnelArchives,request)));
    }

}

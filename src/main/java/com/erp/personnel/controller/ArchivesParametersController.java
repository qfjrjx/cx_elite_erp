package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.ArchivesParameters;
import com.erp.personnel.service.IArchivesParametersService;
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
 *  Controller
 *
 * @author qiufeng
 * @date 2021-09-11 09:27:44
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class ArchivesParametersController extends BaseController {

    private final IArchivesParametersService archivesParametersService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "archivesParameters")
    public String archivesParametersIndex(){
        return FebsUtil.view("archivesParameters/archivesParameters");
    }

    @GetMapping("archivesParameters")
    @ResponseBody
    @RequiresPermissions("archivesParameters:list")
    public FebsResponse getAllArchivesParameterss(ArchivesParameters archivesParameters) {
        return new FebsResponse().success().data(archivesParametersService.findArchivesParameterss(archivesParameters));
    }

    @GetMapping("archivesParameters/list")
    @ResponseBody
    @RequiresPermissions("archivesParameters:list")
    public FebsResponse archivesParametersList(QueryRequest request, ArchivesParameters archivesParameters) {
        Map<String, Object> dataTable = getDataTable(this.archivesParametersService.findArchivesParameterss(request, archivesParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ArchivesParameters", exceptionMessage = "新增ArchivesParameters失败")
    @PostMapping("archivesParameters")
    @ResponseBody
    @RequiresPermissions("archivesParameters:add")
    public FebsResponse addArchivesParameters(@Valid ArchivesParameters archivesParameters) {
        this.archivesParametersService.createArchivesParameters(archivesParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ArchivesParameters", exceptionMessage = "删除ArchivesParameters失败")
    @GetMapping("archivesParameters/delete")
    @ResponseBody
    @RequiresPermissions("archivesParameters:delete")
    public FebsResponse deleteArchivesParameters(ArchivesParameters archivesParameters) {
        this.archivesParametersService.deleteArchivesParameters(archivesParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ArchivesParameters", exceptionMessage = "修改ArchivesParameters失败")
    @PostMapping("archivesParameters/update")
    @ResponseBody
    @RequiresPermissions("archivesParameters:update")
    public FebsResponse updateArchivesParameters(ArchivesParameters archivesParameters) {
        this.archivesParametersService.updateArchivesParameters(archivesParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ArchivesParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("archivesParameters/excel")
    @ResponseBody
    @RequiresPermissions("archivesParameters:export")
    public void export(QueryRequest queryRequest, ArchivesParameters archivesParameters, HttpServletResponse response) {
        List<ArchivesParameters> archivesParameterss = this.archivesParametersService.findArchivesParameterss(queryRequest, archivesParameters).getRecords();
        ExcelKit.$Export(ArchivesParameters.class, response).downXlsx(archivesParameterss, false);
    }
}

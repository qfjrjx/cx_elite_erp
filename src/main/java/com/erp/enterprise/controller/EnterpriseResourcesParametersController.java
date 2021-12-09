package com.erp.enterprise.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.erp.enterprise.service.IEnterpriseResourcesParametersService;
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
 * 参数设置表 Controller
 *
 * @author qiufeng
 * @date 2021-12-09 10:22:55
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class EnterpriseResourcesParametersController extends BaseController {

    private final IEnterpriseResourcesParametersService enterpriseResourcesParametersService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "enterpriseResourcesParameters")
    public String enterpriseResourcesParametersIndex(){
        return FebsUtil.view("enterpriseResourcesParameters/enterpriseResourcesParameters");
    }

    @GetMapping("enterpriseResourcesParameters")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:list")
    public FebsResponse getAllEnterpriseResourcesParameterss(EnterpriseResourcesParameters enterpriseResourcesParameters) {
        return new FebsResponse().success().data(enterpriseResourcesParametersService.findEnterpriseResourcesParameterss(enterpriseResourcesParameters));
    }

    @GetMapping("enterpriseResourcesParameters/list")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:view")
    public FebsResponse enterpriseResourcesParametersList(QueryRequest request, EnterpriseResourcesParameters enterpriseResourcesParameters) {
        Map<String, Object> dataTable = getDataTable(this.enterpriseResourcesParametersService.findEnterpriseResourcesParameterss(request, enterpriseResourcesParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增EnterpriseResourcesParameters", exceptionMessage = "新增EnterpriseResourcesParameters失败")
    @PostMapping("enterpriseResourcesParameters/add")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:add")
    public FebsResponse addEnterpriseResourcesParameters(@Valid EnterpriseResourcesParameters enterpriseResourcesParameters) {
        this.enterpriseResourcesParametersService.createEnterpriseResourcesParameters(enterpriseResourcesParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除EnterpriseResourcesParameters", exceptionMessage = "删除EnterpriseResourcesParameters失败")
    @GetMapping("enterpriseResourcesParameters/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:delete")
    public FebsResponse deleteEnterpriseResourcesParameters(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.enterpriseResourcesParametersService.deleteEnterpriseResourcesParameters(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterpriseResourcesParameters", exceptionMessage = "修改EnterpriseResourcesParameters失败")
    @PostMapping("enterpriseResourcesParameters/update")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:update")
    public FebsResponse updateEnterpriseResourcesParameters(EnterpriseResourcesParameters enterpriseResourcesParameters) {
        this.enterpriseResourcesParametersService.updateEnterpriseResourcesParameters(enterpriseResourcesParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterpriseResourcesParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("enterpriseResourcesParameters/excel")
    @ResponseBody
    @RequiresPermissions("enterpriseResourcesParameters:export")
    public void export(QueryRequest queryRequest, EnterpriseResourcesParameters enterpriseResourcesParameters, HttpServletResponse response) {
        List<EnterpriseResourcesParameters> enterpriseResourcesParameterss = this.enterpriseResourcesParametersService.findEnterpriseResourcesParameterss(queryRequest, enterpriseResourcesParameters).getRecords();
        ExcelKit.$Export(EnterpriseResourcesParameters.class, response).downXlsx(enterpriseResourcesParameterss, false);
    }
}

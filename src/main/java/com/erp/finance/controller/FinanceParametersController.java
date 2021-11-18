package com.erp.finance.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.service.IFinanceParametersService;
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
 * 财务参数表 Controller
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class FinanceParametersController extends BaseController {

    private final IFinanceParametersService financeParametersService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "financeParameters")
    public String financeParametersIndex(){
        return FebsUtil.view("financeParameters/financeParameters");
    }

    @GetMapping("financeParameters")
    @ResponseBody
    @RequiresPermissions("financeParameters:view")
    public FebsResponse getAllFinanceParameterss(FinanceParameters financeParameters) {
        return new FebsResponse().success().data(financeParametersService.findFinanceParameterss(financeParameters));
    }

    @GetMapping("financeParameters/list")
    @ResponseBody
    @RequiresPermissions("financeParameters:view")
    public FebsResponse financeParametersList(QueryRequest request, FinanceParameters financeParameters) {
        Map<String, Object> dataTable = getDataTable(this.financeParametersService.findFinanceParameterss(request, financeParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增FinanceParameters", exceptionMessage = "新增FinanceParameters失败")
    @PostMapping("financeParameters/add")
    @ResponseBody
    @RequiresPermissions("financeParameters:add")
    public FebsResponse addFinanceParameters(@Valid FinanceParameters financeParameters) throws ParseException {
        this.financeParametersService.createFinanceParameters(financeParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除FinanceParameters", exceptionMessage = "删除FinanceParameters失败")
    @GetMapping("financeParameters/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("financeParameters:delete")
    public FebsResponse deleteFinanceParameters(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.financeParametersService.deleteFinanceParameters(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改FinanceParameters", exceptionMessage = "修改FinanceParameters失败")
    @PostMapping("financeParameters/update")
    @ResponseBody
    @RequiresPermissions("financeParameters:update")
    public FebsResponse updateFinanceParameters(FinanceParameters financeParameters) {
        this.financeParametersService.updateFinanceParameters(financeParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改FinanceParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("financeParameters/excel")
    @ResponseBody
    @RequiresPermissions("financeParameters:export")
    public void export(QueryRequest queryRequest, FinanceParameters financeParameters, HttpServletResponse response) {
        List<FinanceParameters> financeParameterss = this.financeParametersService.findFinanceParameterss(queryRequest, financeParameters).getRecords();
        ExcelKit.$Export(FinanceParameters.class, response).downXlsx(financeParameterss, false);
    }
}

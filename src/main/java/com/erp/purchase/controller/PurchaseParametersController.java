package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.service.IPurchaseParametersService;
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
 * 采购参数表 Controller
 *
 * @author qiufeng
 * @date 2021-11-09 16:20:36
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseParametersController extends BaseController {

    private final IPurchaseParametersService purchaseParametersService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseParameters")
    public String purchaseParametersIndex(){
        return FebsUtil.view("purchaseParameters/purchaseParameters");
    }

    @GetMapping("purchaseParameters")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:list")
    public FebsResponse getAllPurchaseParameterss(PurchaseParameters purchaseParameters) {
        return new FebsResponse().success().data(purchaseParametersService.findPurchaseParameterss(purchaseParameters));
    }

    @GetMapping("purchaseParameters/list")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:view")
    public FebsResponse purchaseParametersList(QueryRequest request, PurchaseParameters purchaseParameters) {
        Map<String, Object> dataTable = getDataTable(this.purchaseParametersService.findPurchaseParameterss(request, purchaseParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseParameters", exceptionMessage = "新增PurchaseParameters失败")
    @PostMapping("purchaseParameters/add")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:add")
    public FebsResponse addPurchaseParameters(@Valid PurchaseParameters purchaseParameters) throws ParseException {
        this.purchaseParametersService.createPurchaseParameters(purchaseParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseParameters", exceptionMessage = "删除PurchaseParameters失败")
    @GetMapping("purchaseParameters/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:delete")
    public FebsResponse deletePurchaseParameters(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchaseParametersService.deletePurchaseParameters(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseParameters", exceptionMessage = "修改PurchaseParameters失败")
    @PostMapping("purchaseParameters/update")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:update")
    public FebsResponse updatePurchaseParameters(PurchaseParameters purchaseParameters) {
        this.purchaseParametersService.updatePurchaseParameters(purchaseParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseParameters/excel")
    @ResponseBody
    @RequiresPermissions("purchaseParameters:export")
    public void export(QueryRequest queryRequest, PurchaseParameters purchaseParameters, HttpServletResponse response) {
        List<PurchaseParameters> purchaseParameterss = this.purchaseParametersService.findPurchaseParameterss(queryRequest, purchaseParameters).getRecords();
        ExcelKit.$Export(PurchaseParameters.class, response).downXlsx(purchaseParameterss, false);
    }
}

package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseSupplier;
import com.erp.purchase.service.IPurchaseSupplierService;
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
 * 供货单位表 Controller
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:05
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseSupplierController extends BaseController {

    private final IPurchaseSupplierService purchaseSupplierService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseSupplier")
    public String purchaseSupplierIndex(){
        return FebsUtil.view("purchaseSupplier/purchaseSupplier");
    }

    @GetMapping("purchaseSupplier")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:list")
    public FebsResponse getAllPurchaseSuppliers(PurchaseSupplier purchaseSupplier) {
        return new FebsResponse().success().data(purchaseSupplierService.findPurchaseSuppliers(purchaseSupplier));
    }

    @GetMapping("purchaseSupplier/list")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:view")
    public FebsResponse purchaseSupplierList(QueryRequest request, PurchaseSupplier purchaseSupplier) {
        Map<String, Object> dataTable = getDataTable(this.purchaseSupplierService.findPurchaseSuppliers(request, purchaseSupplier));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseSupplier", exceptionMessage = "新增PurchaseSupplier失败")
    @PostMapping("purchaseSupplier/add")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:add")
    public FebsResponse addPurchaseSupplier(@Valid PurchaseSupplier purchaseSupplier) throws ParseException {
        this.purchaseSupplierService.createPurchaseSupplier(purchaseSupplier);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseSupplier", exceptionMessage = "删除PurchaseSupplier失败")
    @GetMapping("purchaseSupplier/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:delete")
    public FebsResponse deletePurchaseSupplier(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchaseSupplierService.deletePurchaseSupplier(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseSupplier", exceptionMessage = "修改PurchaseSupplier失败")
    @PostMapping("purchaseSupplier/update")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:update")
    public FebsResponse updatePurchaseSupplier(PurchaseSupplier purchaseSupplier) {
        this.purchaseSupplierService.updatePurchaseSupplier(purchaseSupplier);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseSupplier", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseSupplier/excel")
    @ResponseBody
    @RequiresPermissions("purchaseSupplier:export")
    public void export(QueryRequest queryRequest, PurchaseSupplier purchaseSupplier, HttpServletResponse response) {
        List<PurchaseSupplier> purchaseSuppliers = this.purchaseSupplierService.findPurchaseSuppliers(queryRequest, purchaseSupplier).getRecords();
        ExcelKit.$Export(PurchaseSupplier.class, response).downXlsx(purchaseSuppliers, false);
    }
}

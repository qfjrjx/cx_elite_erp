package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseInvoice;
import com.erp.purchase.entity.PurchaseInvoiceSchedule;
import com.erp.purchase.service.IPurchaseInvoiceService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购发票 Controller
 *
 * @author qiufeng
 * @date 2022-04-06 09:34:31
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseInvoiceController extends BaseController {

    private final IPurchaseInvoiceService purchaseInvoiceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseInvoice")
    public String purchaseInvoiceIndex(){
        return FebsUtil.view("purchaseInvoice/purchaseInvoice");
    }

    @GetMapping("purchaseInvoice")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:list")
    public FebsResponse getAllPurchaseInvoices(PurchaseInvoice purchaseInvoice) {
        return new FebsResponse().success().data(purchaseInvoiceService.findPurchaseInvoices(purchaseInvoice));
    }

    @GetMapping("purchaseInvoice/list")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:view")
    public FebsResponse purchaseInvoiceList(QueryRequest request, PurchaseInvoice purchaseInvoice) {
        Map<String, Object> dataTable = getDataTable(this.purchaseInvoiceService.findPurchaseInvoices(request, purchaseInvoice));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseInvoice", exceptionMessage = "新增PurchaseInvoice失败")
    @PostMapping("purchaseInvoice/add")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:add")
    public FebsResponse addPurchaseInvoice(@Valid String purchaseInvoice , @RequestBody String dataTable) throws ParseException {
        this.purchaseInvoiceService.createPurchaseInvoice(purchaseInvoice,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseInvoice", exceptionMessage = "删除PurchaseInvoice失败")
    @GetMapping("purchaseInvoice/delete/{invoiceNumbers}")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:delete")
    public FebsResponse deletePurchaseInvoice(@PathVariable String invoiceNumbers) {
        this.purchaseInvoiceService.deletePurchaseInvoice(invoiceNumbers);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseInvoice", exceptionMessage = "修改PurchaseInvoice失败")
    @PostMapping("purchaseInvoice/update")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:update")
    public FebsResponse updatePurchaseInvoice(@RequestBody String invoiceNumbers, @RequestBody String dataTable) throws ParseException {
        this.purchaseInvoiceService.updatePurchaseInvoice(invoiceNumbers,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseInvoice", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseInvoice/excel")
    @ResponseBody
    @RequiresPermissions("purchaseInvoice:export")
    public void export(QueryRequest queryRequest, PurchaseInvoice purchaseInvoice, HttpServletResponse response) {
        List<PurchaseInvoice> purchaseInvoices = this.purchaseInvoiceService.findPurchaseInvoices(queryRequest, purchaseInvoice).getRecords();
        ExcelKit.$Export(PurchaseInvoice.class, response).downXlsx(purchaseInvoices, false);
    }

    @GetMapping("purchaseInvoice/query")
    @ResponseBody
    public Map queryPurchaseInvoiceSchedule(@RequestParam String invoiceNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseInvoiceSchedule> purchaseInvoiceSchedules = this.purchaseInvoiceService.queryPurchaseInvoiceSchedule(invoiceNumbers);
            map.put("replies",purchaseInvoiceSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 采购付款，根据供应商进行查询
     * @param request
     * @param purchaseInvoiceSchedule
     * @return
     */
    @GetMapping("purchasePayment/listQuery")
    @ResponseBody
    @RequiresPermissions("purchasePayment:view")
    public FebsResponse purchaseSettlementAddQuery(QueryRequest request, PurchaseInvoiceSchedule purchaseInvoiceSchedule) {
        Map<String, Object> dataTable = getDataTable(this.purchaseInvoiceService.purchasePaymentAddQuery(request,purchaseInvoiceSchedule));
        return new FebsResponse().success().data(dataTable);
    }
}

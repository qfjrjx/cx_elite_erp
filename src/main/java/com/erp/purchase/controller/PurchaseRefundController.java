package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseRefund;
import com.erp.purchase.entity.PurchaseRefundSchedule;
import com.erp.purchase.entity.WarehouseStorageSchedule;
import com.erp.purchase.service.IPurchaseRefundService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购退货 Controller
 *
 * @author qiufeng
 * @date 2022-03-28 15:35:47
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseRefundController extends BaseController {

    private final IPurchaseRefundService purchaseRefundService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseRefund")
    public String purchaseRefundIndex(){
        return FebsUtil.view("purchaseRefund/purchaseRefund");
    }

    @GetMapping("purchaseRefund")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:list")
    public FebsResponse getAllPurchaseRefunds(PurchaseRefund purchaseRefund) {
        return new FebsResponse().success().data(purchaseRefundService.findPurchaseRefunds(purchaseRefund));
    }

    @GetMapping("purchaseRefund/list")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:view")
    public FebsResponse purchaseRefundList(QueryRequest request, PurchaseRefund purchaseRefund) {
        Map<String, Object> dataTable = getDataTable(this.purchaseRefundService.findPurchaseRefunds(request, purchaseRefund));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseRefund", exceptionMessage = "新增PurchaseRefund失败")
    @PostMapping("purchaseRefund/add")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:add")
    public FebsResponse addPurchaseRefund(@RequestBody String purchaseRefund, @RequestBody String dataTable) throws ParseException {
        this.purchaseRefundService.createPurchaseRefund(purchaseRefund,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseRefund", exceptionMessage = "删除PurchaseRefund失败")
    @GetMapping("purchaseRefund/delete/{refundNumber}")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:update")
    public FebsResponse deletePurchaseRefund(@NotBlank(message = "{required}") @PathVariable String refundNumber) {
        this.purchaseRefundService.deletePurchaseRefund(refundNumber);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseRefund", exceptionMessage = "修改PurchaseRefund失败")
    @PostMapping("purchaseRefund/update")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:update")
    public FebsResponse updatePurchaseRefund(@RequestBody String refundNumber, @RequestBody String dataTable) throws ParseException {
        this.purchaseRefundService.updatePurchaseRefund(refundNumber,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseRefund", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseRefund/excel")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:export")
    public void export(QueryRequest queryRequest, PurchaseRefund purchaseRefund, HttpServletResponse response) {
        List<PurchaseRefund> purchaseRefunds = this.purchaseRefundService.findPurchaseRefunds(queryRequest, purchaseRefund).getRecords();
        ExcelKit.$Export(PurchaseRefund.class, response).downXlsx(purchaseRefunds, false);
    }

    @GetMapping("purchaseRefund/query")
    @ResponseBody
    public Map queryPurchaseRefundSchedule(@RequestParam String oddNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseRefundSchedule> purchaseRefundSchedules = this.purchaseRefundService.queryPurchaseRefundSchedule(oddNumbers);
            map.put("replies",purchaseRefundSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("purchaseRefundAdd/list")
    @ResponseBody
    @RequiresPermissions("purchaseRefund:view")
    public FebsResponse purchaseRefundAddQuery(QueryRequest request, WarehouseStorageSchedule warehouseStorage) {
        Map<String, Object> dataTable = getDataTable(this.purchaseRefundService.findPurchaseRefundAddQueryPage(request,warehouseStorage));
        return new FebsResponse().success().data(dataTable);
    }
}

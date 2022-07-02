package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseOrder;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.service.IPurchaseOrderService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
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
 * 采购订单表 Controller
 *
 * @author qiufeng
 * @date 2022-02-20 14:25:03
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseOrderController extends BaseController {

    private final IPurchaseOrderService purchaseOrderService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseOrder")
    public String purchaseOrderIndex(){
        return FebsUtil.view("purchaseOrder/purchaseOrder");
    }

    @GetMapping("purchaseOrder")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:list")
    public FebsResponse getAllPurchaseOrders(PurchaseOrder purchaseOrder) {
        return new FebsResponse().success().data(purchaseOrderService.findPurchaseOrders(purchaseOrder));
    }

    @GetMapping("purchaseOrder/list")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:view")
    public FebsResponse purchaseOrderList(QueryRequest request, PurchaseOrder purchaseOrder) {
        Map<String, Object> dataTable = getDataTable(this.purchaseOrderService.findPurchaseOrders(request, purchaseOrder));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseOrder", exceptionMessage = "新增PurchaseOrder失败")
    @PostMapping("purchaseOrder/add")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:add")
    public FebsResponse addPurchaseOrder(@RequestBody String purchaseOrder,@RequestBody String dataTable) throws ParseException {
        this.purchaseOrderService.createPurchaseOrder(purchaseOrder,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseOrder", exceptionMessage = "删除PurchaseOrder失败")
    @GetMapping("purchaseOrder/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:delete")
    public FebsResponse deletePurchaseOrder(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchaseOrderService.deletePurchaseOrder(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseOrder", exceptionMessage = "修改PurchaseOrder失败")
    @PostMapping("purchaseOrder/update")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:update")
    public FebsResponse updatePurchaseOrder(@RequestBody String orderNumber,@RequestBody String dataTable) throws ParseException {
        this.purchaseOrderService.updatePurchaseOrder(orderNumber,dataTable);
        return new FebsResponse().success();
    }

    /**
     * 价格变动导出
     * @param
     * @param purchaseOrder
     * @param response
     */
    @ControllerEndpoint(operation = "导出purchasePriceChanges", exceptionMessage = "导出Excel失败")
    @GetMapping("purchasePriceChanges/excel")
    @RequiresPermissions("purchasePriceChanges:export")
    public void export(PurchaseOrder purchaseOrder, HttpServletResponse response) {
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderService.purchasePriceChangesExport(purchaseOrder);
        ExcelKit.$Export(PurchaseOrder.class, response).downXlsx(purchaseOrders, false);
    }

    @GetMapping("purchaseOrder/query")
    @ResponseBody
    public Map queryPurchaseOrder(@RequestParam String oddNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseOrderSchedule> purchaseOrderSchedules = this.purchaseOrderService.queryPurchaseOrderSchedule(oddNumbers);
            map.put("replies",purchaseOrderSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ControllerEndpoint(operation = "确认", exceptionMessage = "确认失败")
    @GetMapping("purchaseOrder/confirm/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:confirm")
    public FebsResponse confirmPurchaseOrder(@PathVariable String ids) {
        this.purchaseOrderService.confirmPurchaseOrder(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "反审", exceptionMessage = "反审失败")
    @GetMapping("purchaseOrder/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions(value = {"purchaseOrder:cancel", "purchaseOrder:umpire"}, logical = Logical.OR)
    public FebsResponse cancelPurchaseOrder(@PathVariable String ids) {
        this.purchaseOrderService.cancelPurchaseOrder(ids);
        return new FebsResponse().success();
    }


    //采购收货选择列表
    @GetMapping("purchaseClosedQuery/list")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:view")
    public FebsResponse purchaseClosedQuery(QueryRequest request, PurchaseOrder purchaseOrder) {
        Map<String, Object> dataTable = getDataTable(this.purchaseOrderService.findPurchaseClosedQueryPage(request, purchaseOrder));
        return new FebsResponse().success().data(dataTable);
    }

    //删除整单接口
    @ControllerEndpoint(operation = "删除PurchaseOrder", exceptionMessage = "删除PurchaseOrder失败")
    @GetMapping("purchaseOrder/delete/orderNumber/{orderNumber}")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:delete")
    public FebsResponse deletePurchaseRequisitionOrderNumber(@NotBlank(message = "{required}") @PathVariable String orderNumber) {
        this.purchaseOrderService.deletePurchaseRequisitionOrderNumber(orderNumber);
        return new FebsResponse().success();
    }

    //根据供应商名称查询订单
    @GetMapping("purchaseInspectionOrder/query")
    @ResponseBody
    public FebsResponse completionStatusList(QueryRequest request, String supplierName) {
        Map<String, Object> dataTable = getDataTable(this.purchaseOrderService.queryPurchaseInspectionOrder(request, supplierName));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("purchasePriceChanges/list")
    @ResponseBody
    public FebsResponse purchasePriceChangesList(QueryRequest request, PurchaseOrder purchaseOrder) {
        Map<String, Object> dataTable = getDataTable(this.purchaseOrderService.findPurchasePriceChanges(request, purchaseOrder));
        return new FebsResponse().success().data(dataTable);
    }

    /**
     * 订购在途查询
     */

    @GetMapping("purchaseOrderTransit/list")
    @ResponseBody
    @RequiresPermissions("purchaseOrderTransit:view")
    public FebsResponse purchaseOrderTransitList(QueryRequest request, PurchaseOrder purchaseOrder) throws ParseException {
        Map<String, Object> dataTable = getDataTable(this.purchaseOrderService.findPurchaseOrderTransit(request, purchaseOrder));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "核销", exceptionMessage = "核销失败")
    @GetMapping("purchaseOrderTransit/nuclear/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseOrderTransit:nuclear")
    public FebsResponse purchaseOrderTransitNuclear(@PathVariable String ids) {
        this.purchaseOrderService.purchaseOrderTransitNuclear(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "取消核销", exceptionMessage = "取消核销失败")
    @GetMapping("purchaseOrderTransit/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseOrderTransit:cancel")
    public FebsResponse purchaseOrderTransitCancel(@PathVariable String ids) {
        this.purchaseOrderService.purchaseOrderTransitCancel(ids);
        return new FebsResponse().success();
    }

}

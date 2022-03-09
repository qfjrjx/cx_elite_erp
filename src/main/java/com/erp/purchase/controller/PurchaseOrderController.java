package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseOrder;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.entity.PurchaseRequisition;
import com.erp.purchase.service.IPurchaseOrderService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    @GetMapping("purchaseOrder/delete")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:delete")
    public FebsResponse deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderService.deletePurchaseOrder(purchaseOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseOrder", exceptionMessage = "修改PurchaseOrder失败")
    @PostMapping("purchaseOrder/update")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:update")
    public FebsResponse updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderService.updatePurchaseOrder(purchaseOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseOrder", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseOrder/excel")
    @ResponseBody
    @RequiresPermissions("purchaseOrder:export")
    public void export(QueryRequest queryRequest, PurchaseOrder purchaseOrder, HttpServletResponse response) {
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderService.findPurchaseOrders(queryRequest, purchaseOrder).getRecords();
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


}

package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.service.ISaleOrderService;
import com.wuwenze.poi.ExcelKit;
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
import java.util.List;
import java.util.Map;

/**
 * 销售订单表 Controller
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleOrderController extends BaseController {

    private final ISaleOrderService saleOrderService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleOrder")
    public String saleOrderIndex(){
        return FebsUtil.view("saleOrder/saleOrder");
    }

    @GetMapping("saleOrder")
    @ResponseBody
    @RequiresPermissions("saleOrder:list")
    public FebsResponse getAllSaleOrders(SaleOrder saleOrder) {
        return new FebsResponse().success().data(saleOrderService.findSaleOrders(saleOrder));
    }

    @GetMapping("saleOrder/list")
    @ResponseBody
    @RequiresPermissions("saleOrder:view")
    public FebsResponse saleOrderList(QueryRequest request, SaleOrder saleOrder) {
        Map<String, Object> dataTable = getDataTable(this.saleOrderService.findSaleOrders(request, saleOrder));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleOrder", exceptionMessage = "新增SaleOrder失败")
    @PostMapping("saleOrder/add")
    @ResponseBody
    @RequiresPermissions("saleOrder:add")
    public FebsResponse addSaleOrder(@RequestParam String orderDate,
                                     @RequestParam String customerName,
                                     @RequestParam String salesmanName,
                                     @RequestParam String currencyName,
                                     @RequestParam String taxRate,
                                     @RequestParam String paymentMethod,
                                     @RequestParam String depositMoney,
                                     @RequestParam String invoiceNot,
                                     @RequestParam String contactsName,
                                     @RequestParam String mobilePhone,
                                     @RequestParam String orderType,
                                     @RequestParam String afterSalesClerk,
                                     @RequestParam String dataTable,
                                     @RequestParam String contImg) throws ParseException {
        this.saleOrderService.createSaleOrder(orderDate,customerName,
                salesmanName,currencyName,taxRate,paymentMethod,
                depositMoney,invoiceNot,contactsName,mobilePhone,orderType,afterSalesClerk,dataTable,contImg);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleOrder", exceptionMessage = "删除SaleOrder失败")
    @GetMapping("saleOrder/delete")
    @ResponseBody
    @RequiresPermissions("saleOrder:delete")
    public FebsResponse deleteSaleOrder(SaleOrder saleOrder) {
        this.saleOrderService.deleteSaleOrder(saleOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleOrder", exceptionMessage = "修改SaleOrder失败")
    @PostMapping("saleOrder/update")
    @ResponseBody
    @RequiresPermissions("saleOrder:update")
    public FebsResponse updateSaleOrder(SaleOrder saleOrder) {
        this.saleOrderService.updateSaleOrder(saleOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleOrder", exceptionMessage = "导出Excel失败")
    @PostMapping("saleOrder/excel")
    @ResponseBody
    @RequiresPermissions("saleOrder:export")
    public void export(QueryRequest queryRequest, SaleOrder saleOrder, HttpServletResponse response) {
        List<SaleOrder> saleOrders = this.saleOrderService.findSaleOrders(queryRequest, saleOrder).getRecords();
        ExcelKit.$Export(SaleOrder.class, response).downXlsx(saleOrders, false);
    }
}

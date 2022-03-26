package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseClosed;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.service.IPurchaseClosedService;
import com.erp.purchase.service.IPurchaseOrderService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购收货表 Controller
 *
 * @author qiufeng
 * @date 2022-03-19 09:44:35
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseClosedController extends BaseController {

    private final IPurchaseClosedService purchaseClosedService;

    private final IPurchaseOrderService purchaseOrderService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseClosed")
    public String purchaseClosedIndex(){
        return FebsUtil.view("purchaseClosed/purchaseClosed");
    }

    @GetMapping("purchaseClosed")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:list")
    public FebsResponse getAllPurchaseCloseds(PurchaseClosed purchaseClosed) {
        return new FebsResponse().success().data(purchaseClosedService.findPurchaseCloseds(purchaseClosed));
    }

    @GetMapping("purchaseClosed/list")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:view")
    public FebsResponse purchaseClosedList(QueryRequest request, PurchaseClosed purchaseClosed) {
        Map<String, Object> dataTable = getDataTable(this.purchaseClosedService.findPurchaseCloseds(request, purchaseClosed));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseClosed", exceptionMessage = "新增PurchaseClosed失败")
    @PostMapping("purchaseClosed/add")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:add")
    public FebsResponse addPurchaseClosed(@RequestBody String purchaseClosed, @RequestBody String dataTable) throws ParseException {
        this.purchaseClosedService.createPurchaseClosed(purchaseClosed,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseClosed", exceptionMessage = "删除PurchaseClosed失败")
    @GetMapping("purchaseClosed/delete")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:delete")
    public FebsResponse deletePurchaseClosed(PurchaseClosed purchaseClosed) {
        this.purchaseClosedService.deletePurchaseClosed(purchaseClosed);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseClosed", exceptionMessage = "修改PurchaseClosed失败")
    @PostMapping("purchaseClosed/update")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:update")
    public FebsResponse updatePurchaseClosed(@RequestBody String closedNumber,@RequestBody String dataTable) throws ParseException {
        this.purchaseClosedService.updatePurchaseClosed(closedNumber,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseClosed", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseClosed/excel")
    @ResponseBody
    @RequiresPermissions("purchaseClosed:export")
    public void export(QueryRequest queryRequest, PurchaseClosed purchaseClosed, HttpServletResponse response) {
        List<PurchaseClosed> purchaseCloseds = this.purchaseClosedService.findPurchaseCloseds(queryRequest, purchaseClosed).getRecords();
        ExcelKit.$Export(PurchaseClosed.class, response).downXlsx(purchaseCloseds, false);
    }

    @GetMapping("purchaseClosed/query")
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

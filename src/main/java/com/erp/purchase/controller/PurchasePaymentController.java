package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchasePayment;
import com.erp.purchase.entity.PurchasePaymentSchedule;
import com.erp.purchase.service.IPurchasePaymentService;
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
 * 采购付款 Controller
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:21
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchasePaymentController extends BaseController {

    private final IPurchasePaymentService purchasePaymentService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchasePayment")
    public String purchasePaymentIndex(){
        return FebsUtil.view("purchasePayment/purchasePayment");
    }

    @GetMapping("purchasePayment")
    @ResponseBody
    @RequiresPermissions("purchasePayment:list")
    public FebsResponse getAllPurchasePayments(PurchasePayment purchasePayment) {
        return new FebsResponse().success().data(purchasePaymentService.findPurchasePayments(purchasePayment));
    }

    @GetMapping("purchasePayment/list")
    @ResponseBody
    @RequiresPermissions("purchasePayment:view")
    public FebsResponse purchasePaymentList(QueryRequest request, PurchasePayment purchasePayment) {
        Map<String, Object> dataTable = getDataTable(this.purchasePaymentService.findPurchasePayments(request, purchasePayment));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchasePayment", exceptionMessage = "新增PurchasePayment失败")
    @PostMapping("purchasePayment/add")
    @ResponseBody
    @RequiresPermissions("purchasePayment:add")
    public FebsResponse addPurchasePayment(@RequestBody String purchasePayment , @RequestBody String dataTable) throws ParseException {
        this.purchasePaymentService.createPurchasePayment(purchasePayment,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchasePayment", exceptionMessage = "删除PurchasePayment失败")
    @GetMapping("purchasePayment/delete/{paymentNumber}")
    @ResponseBody
    @RequiresPermissions("purchasePayment:delete")
    public FebsResponse deletePurchasePayment(@NotBlank(message = "{required}") @PathVariable String paymentNumber) {
        this.purchasePaymentService.deletePurchasePayment(paymentNumber);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchasePayment", exceptionMessage = "修改PurchasePayment失败")
    @PostMapping("purchasePayment/update")
    @ResponseBody
    @RequiresPermissions("purchasePayment:update")
    public FebsResponse updatePurchasePayment(@RequestBody String paymentNumber , @RequestBody String dataTable) throws ParseException {
        this.purchasePaymentService.updatePurchasePayment(paymentNumber,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchasePayment", exceptionMessage = "导出Excel失败")
    @PostMapping("purchasePayment/excel")
    @ResponseBody
    @RequiresPermissions("purchasePayment:export")
    public void export(QueryRequest queryRequest, PurchasePayment purchasePayment, HttpServletResponse response) {
        List<PurchasePayment> purchasePayments = this.purchasePaymentService.findPurchasePayments(queryRequest, purchasePayment).getRecords();
        ExcelKit.$Export(PurchasePayment.class, response).downXlsx(purchasePayments, false);
    }

    @GetMapping("purchasePayment/query")
    @ResponseBody
    public Map purchasePayment(HttpServletResponse response,@RequestParam String paymentNumber) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> mappedMovies = new HashMap<String, Object>();
        try {
            List<PurchasePaymentSchedule> purchasePaymentSchedules = this.purchasePaymentService.queryPurchasePaymentSchedule(paymentNumber);
            /*for (PurchasePaymentSchedule movie : purchasePaymentSchedules) {
                mappedMovies.put("paymentNumber", movie.getPaymentNumber());
            }*/
            map.put("replies",purchasePaymentSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //确认
    @GetMapping("purchasePayment/confirm/{ids}")
    @ResponseBody
    @RequiresPermissions("purchasePayment:confirm")
    public FebsResponse confirmPurchasePayment(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchasePaymentService.confirmPurchasePayment(ids);
        return new FebsResponse().success();
    }

    //取消
    @GetMapping("purchasePayment/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("purchasePayment:cancel")
    public FebsResponse cancelPurchasePayment(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchasePaymentService.cancelPurchasePayment(ids);
        return new FebsResponse().success();
    }

    //已付款
    @GetMapping("purchasePayment/payment/{ids}")
    @ResponseBody
    @RequiresPermissions("purchasePayment:payment")
    public FebsResponse paymentPurchasePayment(@NotBlank(message = "{required}") @PathVariable String ids)  throws ParseException {
        this.purchasePaymentService.paymentPurchasePayment(ids);
        return new FebsResponse().success();
    }

    @GetMapping("purchasePriceChanges/query")
    @ResponseBody
    @RequiresPermissions("purchasePriceChanges:view")
    public FebsResponse purchasePriceChangesQuery(QueryRequest request, PurchasePaymentSchedule purchasePaymentSchedule) {
        Map<String, Object> dataTable = getDataTable(this.purchasePaymentService.purchasePriceChangesQuery(request, purchasePaymentSchedule));
        return new FebsResponse().success().data(dataTable);
    }

}

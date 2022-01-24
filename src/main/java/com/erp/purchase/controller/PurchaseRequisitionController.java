package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseRequisition;
import com.erp.purchase.service.IPurchaseRequisitionService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 采购申请表 Controller
 *
 * @author qiufeng
 * @date 2022-01-19 15:29:35
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseRequisitionController extends BaseController {

    private final IPurchaseRequisitionService purchaseRequisitionService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseRequisition")
    public String purchaseRequisitionIndex(){
        return FebsUtil.view("purchaseRequisition/purchaseRequisition");
    }

    @GetMapping("purchaseRequisition")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:list")
    public FebsResponse getAllPurchaseRequisitions(PurchaseRequisition purchaseRequisition) {
        return new FebsResponse().success().data(purchaseRequisitionService.findPurchaseRequisitions(purchaseRequisition));
    }

    @GetMapping("purchaseRequisition/list")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:view")
    public FebsResponse purchaseRequisitionList(QueryRequest request, PurchaseRequisition purchaseRequisition) {
        Map<String, Object> dataTable = getDataTable(this.purchaseRequisitionService.findPurchaseRequisitions(request, purchaseRequisition));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseRequisition", exceptionMessage = "新增PurchaseRequisition失败")
    @PostMapping("purchaseRequisition")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:add")
    public FebsResponse addPurchaseRequisition(@Valid PurchaseRequisition purchaseRequisition) {
        this.purchaseRequisitionService.createPurchaseRequisition(purchaseRequisition);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseRequisition", exceptionMessage = "删除PurchaseRequisition失败")
    @GetMapping("purchaseRequisition/delete")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:delete")
    public FebsResponse deletePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.purchaseRequisitionService.deletePurchaseRequisition(purchaseRequisition);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseRequisition", exceptionMessage = "修改PurchaseRequisition失败")
    @PostMapping("purchaseRequisition/update")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:update")
    public FebsResponse updatePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.purchaseRequisitionService.updatePurchaseRequisition(purchaseRequisition);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseRequisition", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseRequisition/excel")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:export")
    public void export(QueryRequest queryRequest, PurchaseRequisition purchaseRequisition, HttpServletResponse response) {
        List<PurchaseRequisition> purchaseRequisitions = this.purchaseRequisitionService.findPurchaseRequisitions(queryRequest, purchaseRequisition).getRecords();
        ExcelKit.$Export(PurchaseRequisition.class, response).downXlsx(purchaseRequisitions, false);
    }
}

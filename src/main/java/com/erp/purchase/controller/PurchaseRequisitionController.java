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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
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
    @PostMapping("purchaseRequisition/add")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:add")
    public FebsResponse addPurchaseRequisition(@RequestParam String applyPreparer,@RequestParam String transferData,@RequestParam Long departmentId,@RequestParam String dataTable) throws ParseException {
        this.purchaseRequisitionService.createPurchaseRequisition(applyPreparer,transferData,departmentId,dataTable);
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
   /* @ControllerEndpoint(operation = "新增PurchaseRequisition", exceptionMessage = "新增PurchaseRequisition失败")
    @PostMapping("purchaseRequisition/add")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:add")
    public FebsResponse addPurchaseRequisition(@RequestParam String applyPreparer,@RequestParam String transferData,@RequestParam Long departmentId,@RequestParam String dataTable) throws ParseException {
        this.purchaseRequisitionService.createPurchaseRequisition(applyPreparer,transferData,departmentId,dataTable);
        return new FebsResponse().success();
    }*/
    @ControllerEndpoint(operation = "修改PurchaseRequisition", exceptionMessage = "修改PurchaseRequisition失败")
    @PostMapping("purchaseRequisition/update")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:update")
    public FebsResponse updatePurchaseRequisition(@RequestParam String oddNumbers,@RequestParam String applyPreparer,@RequestParam String transferData,@RequestParam Long departmentId,@RequestParam String dataTable) throws ParseException {
        this.purchaseRequisitionService.updatePurchaseRequisition(oddNumbers,applyPreparer,transferData,departmentId,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "导出PurchaseRequisition", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseRequisition/excel")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:export")
    public void export(QueryRequest queryRequest, PurchaseRequisition purchaseRequisition, HttpServletResponse response) {
        List<PurchaseRequisition> purchaseRequisitions = this.purchaseRequisitionService.findPurchaseRequisitions(queryRequest, purchaseRequisition).getRecords();
        ExcelKit.$Export(PurchaseRequisition.class, response).downXlsx(purchaseRequisitions, false);
    }
    @GetMapping("purchaseRequisition/query")
    @ResponseBody
    public Map queryPurchaseRequisitions(@RequestParam String oddNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseRequisition> purchaseRequisitions = this.purchaseRequisitionService.queryPurchaseRequisitions(oddNumbers);
            map.put("replies",purchaseRequisitions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("purchaseRequisition/queryList/{oddNumbers}")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:view")
    public FebsResponse purchaseRequisitionQueryList(QueryRequest request,@PathVariable String oddNumbers) {
        Map<String, Object> dataTable = getDataTable(this.purchaseRequisitionService.queryPurchaseRequisitionsList(request,oddNumbers));
        return new FebsResponse().success().data(dataTable);
    }


    //采购申请选择列表
    @GetMapping("orderPurchaseRequisition/list")
    @ResponseBody
    @RequiresPermissions("purchaseRequisition:view")
    public FebsResponse orderPurchaseRequisition(QueryRequest request, PurchaseRequisition purchaseRequisition) {
        Map<String, Object> dataTable = getDataTable(this.purchaseRequisitionService.findOrderPurchaseRequisitionPage(request, purchaseRequisition));
        return new FebsResponse().success().data(dataTable);
    }
}

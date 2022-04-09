package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseSettlement;
import com.erp.purchase.entity.PurchaseSettlementSchedule;
import com.erp.purchase.service.IPurchaseSettlementService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购结算 Controller
 *
 * @author qiufeng
 * @date 2022-04-02 15:02:26
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseSettlementController extends BaseController {

    private final IPurchaseSettlementService purchaseSettlementService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseSettlement")
    public String purchaseSettlementIndex(){
        return FebsUtil.view("purchaseSettlement/purchaseSettlement");
    }

    @GetMapping("purchaseSettlement")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:list")
    public FebsResponse getAllPurchaseSettlements(PurchaseSettlement purchaseSettlement) {
        return new FebsResponse().success().data(purchaseSettlementService.findPurchaseSettlements(purchaseSettlement));
    }

    @GetMapping("purchaseSettlement/list")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:view")
    public FebsResponse purchaseSettlementList(QueryRequest request, PurchaseSettlement purchaseSettlement) {
        Map<String, Object> dataTable = getDataTable(this.purchaseSettlementService.findPurchaseSettlements(request, purchaseSettlement));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseSettlement", exceptionMessage = "新增PurchaseSettlement失败")
    @PostMapping("purchaseSettlement")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:add")
    public FebsResponse addPurchaseSettlement(@Valid PurchaseSettlement purchaseSettlement) {
        this.purchaseSettlementService.createPurchaseSettlement(purchaseSettlement);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseSettlement", exceptionMessage = "删除PurchaseSettlement失败")
    @GetMapping("purchaseSettlement/delete")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:delete")
    public FebsResponse deletePurchaseSettlement(PurchaseSettlement purchaseSettlement) {
        this.purchaseSettlementService.deletePurchaseSettlement(purchaseSettlement);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseSettlement", exceptionMessage = "修改PurchaseSettlement失败")
    @PostMapping("purchaseSettlement/update")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:update")
    public FebsResponse updatePurchaseSettlement(PurchaseSettlement purchaseSettlement) {
        this.purchaseSettlementService.updatePurchaseSettlement(purchaseSettlement);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseSettlement", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseSettlement/excel")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:export")
    public void export(QueryRequest queryRequest, PurchaseSettlement purchaseSettlement, HttpServletResponse response) {
        List<PurchaseSettlement> purchaseSettlements = this.purchaseSettlementService.findPurchaseSettlements(queryRequest, purchaseSettlement).getRecords();
        ExcelKit.$Export(PurchaseSettlement.class, response).downXlsx(purchaseSettlements, false);
    }

    @GetMapping("purchaseSettlement/query")
    @ResponseBody
    public Map queryPurchaseSettlementSchedule(@RequestParam String oddNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseSettlementSchedule> purchaseSettlementSchedules = this.purchaseSettlementService.queryPurchaseSettlementSchedule(oddNumbers);
            map.put("replies",purchaseSettlementSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ControllerEndpoint(operation = "结算", exceptionMessage = "结算失败")
    @GetMapping("purchaseSettlement/settlement/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:settlement")
    public FebsResponse settlementPurchaseSettlement(@PathVariable String ids) {
        this.purchaseSettlementService.settlementPurchaseSettlement(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "取消", exceptionMessage = "取消失败")
    @GetMapping("purchaseSettlement/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:cancel")
    public FebsResponse cancelPurchaseSettlement(@PathVariable String ids) {
        this.purchaseSettlementService.cancelPurchaseSettlement(ids);
        return new FebsResponse().success();
    }

    /**
     * 采购发票，根据供应商进行查询
     * @param request
     * @param purchaseSettlementSchedule
     * @return
     */
    @GetMapping("purchaseSettlement/listQuery")
    @ResponseBody
    @RequiresPermissions("purchaseSettlement:view")
    public FebsResponse purchaseSettlementAddQuery(QueryRequest request, PurchaseSettlementSchedule purchaseSettlementSchedule) {
        Map<String, Object> dataTable = getDataTable(this.purchaseSettlementService.purchaseSettlementAddQuery(request,purchaseSettlementSchedule));
        return new FebsResponse().success().data(dataTable);
    }
}

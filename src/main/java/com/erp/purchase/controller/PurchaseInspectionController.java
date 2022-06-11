package com.erp.purchase.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseInspection;
import com.erp.purchase.entity.PurchaseInspectionSchedule;
import com.erp.purchase.service.IPurchaseInspectionService;
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
 * 来货检验 Controller
 *
 * @author qiufeng
 * @date 2022-03-22 09:34:27
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseInspectionController extends BaseController {

    private final IPurchaseInspectionService purchaseInspectionService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseInspection")
    public String purchaseInspectionIndex(){
        return FebsUtil.view("purchaseInspection/purchaseInspection");
    }

    @GetMapping("purchaseInspection")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:list")
    public FebsResponse getAllPurchaseInspections(PurchaseInspection purchaseInspection) {
        return new FebsResponse().success().data(purchaseInspectionService.findPurchaseInspections(purchaseInspection));
    }

    @GetMapping("purchaseInspection/list")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:list")
    public FebsResponse purchaseInspectionList(QueryRequest request, PurchaseInspection purchaseInspection) {
        Map<String, Object> dataTable = getDataTable(this.purchaseInspectionService.findPurchaseInspections(request, purchaseInspection));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseInspection", exceptionMessage = "新增PurchaseInspection失败")
    @PostMapping("purchaseInspection/add")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:add")
    public FebsResponse addPurchaseInspection(@RequestBody String purchaseInspection, @RequestBody String dataTable) throws ParseException {
        this.purchaseInspectionService.createPurchaseInspection(purchaseInspection,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseInspection", exceptionMessage = "删除PurchaseInspection失败")
    @GetMapping("purchaseInspection/delete")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:delete")
    public FebsResponse deletePurchaseInspection(PurchaseInspection purchaseInspection) {
        this.purchaseInspectionService.deletePurchaseInspection(purchaseInspection);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseInspection", exceptionMessage = "修改PurchaseInspection失败")
    @PostMapping("purchaseInspection/update")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:update")
    public FebsResponse updatePurchaseInspection(@RequestBody String inspectionNumber, @RequestBody String dataTable) throws ParseException {
        this.purchaseInspectionService.updatePurchaseInspection(inspectionNumber,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseInspection", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseInspection/excel")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:export")
    public void export(QueryRequest queryRequest, PurchaseInspection purchaseInspection, HttpServletResponse response) {
        List<PurchaseInspection> purchaseInspections = this.purchaseInspectionService.findPurchaseInspections(queryRequest, purchaseInspection).getRecords();
        ExcelKit.$Export(PurchaseInspection.class, response).downXlsx(purchaseInspections, false);
    }

    @GetMapping("PurchaseInspectionSchedule/query")
    @ResponseBody
    public Map queryPurchaseInspectionSchedule(@RequestParam String oddNumbers) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PurchaseInspectionSchedule> purchaseInspectionSchedule = this.purchaseInspectionService.queryPurchaseInspectionSchedule(oddNumbers);
            map.put("replies",purchaseInspectionSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //删除整单接口
    @ControllerEndpoint(operation = "删除PurchaseOrder", exceptionMessage = "删除PurchaseOrder失败")
    @GetMapping("purchaseInspection/delete/{inspectionNumber}")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:update")
    public FebsResponse deletePurchaseInspectionNumber(@NotBlank(message = "{required}") @PathVariable String inspectionNumber) {
        this.purchaseInspectionService.deletePurchaseInspectionNumber(inspectionNumber);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "确认", exceptionMessage = "确认失败")
    @GetMapping("purchaseInspection/confirm/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:confirm")
    public FebsResponse confirmPurchaseInspection(@PathVariable String ids) {
        this.purchaseInspectionService.confirmPurchaseInspection(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "反审", exceptionMessage = "反审失败")
    @GetMapping("purchaseInspection/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseInspection:cancel")
    public FebsResponse cancelPurchaseInspection(@PathVariable String ids) {
        this.purchaseInspectionService.cancelPurchaseInspection(ids);
        return new FebsResponse().success();
    }

    /*品质外检检验*/
    @ControllerEndpoint(operation = "修改PurchaseInspection", exceptionMessage = "修改PurchaseInspection失败")
    @PostMapping("qualityInspection/inspection")
    @ResponseBody
    @RequiresPermissions("qualityInspection:inspection")
    public FebsResponse updateQualityInspection(PurchaseInspectionSchedule purchaseInspectionSchedule) throws ParseException {
        this.purchaseInspectionService.updateQualityInspection(purchaseInspectionSchedule);
        return new FebsResponse().success();
    }

    /*取消检验*/
    @ControllerEndpoint(operation = "取消检验", exceptionMessage = "取消检验失败")
    @GetMapping("qualityInspection/cancelInspection/{ids}")
    @ResponseBody
    @RequiresPermissions("qualityInspection:cancelInspection")
    public FebsResponse cancelInspection(@PathVariable String ids) {
        this.purchaseInspectionService.cancelInspection(ids);
        return new FebsResponse().success();
    }

    /*送库（外购）*/
    @ControllerEndpoint(operation = "送库（外购）", exceptionMessage = "送库（外购）失败")
    @GetMapping("qualityInspection/confirmOutsourcing/{ids}")
    @ResponseBody
    @RequiresPermissions("qualityInspection:outsourcing")
    public FebsResponse confirmOutsourcing(@PathVariable String ids) throws ParseException {
        this.purchaseInspectionService.confirmOutsourcing(ids);
        return new FebsResponse().success();
    }

    /*送库（资产）*/
    @ControllerEndpoint(operation = "送库（资产）", exceptionMessage = "送库（资产）失败")
    @GetMapping("qualityInspection/confirmAssets/{ids}")
    @ResponseBody
    @RequiresPermissions("qualityInspection:assets")
    public FebsResponse confirmAssets(@PathVariable String ids) throws ParseException {
        this.purchaseInspectionService.confirmAssets(ids);
        return new FebsResponse().success();
    }

    /*取消送库*/
    @ControllerEndpoint(operation = "取消送库", exceptionMessage = "取消送库失败")
    @GetMapping("qualityInspection/cancelLibrary/{ids}")
    @ResponseBody
    @RequiresPermissions("qualityInspection:cancelLibrary")
    public FebsResponse cancelLibrary(@PathVariable String ids) throws ParseException {
        this.purchaseInspectionService.cancelLibrary(ids);
        return new FebsResponse().success();
    }
}

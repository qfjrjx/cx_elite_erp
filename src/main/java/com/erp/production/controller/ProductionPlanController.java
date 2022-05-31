package com.erp.production.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.entity.SetupBom;
import com.erp.production.entity.SetupBomSchedule;
import com.erp.production.service.IProductionPlanService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产计划 Controller
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class ProductionPlanController extends BaseController {

    private final IProductionPlanService productionPlanService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "productionPlan")
    public String productionPlanIndex(){
        return FebsUtil.view("productionPlan/productionPlan");
    }

    @GetMapping("productionPlan")
    @ResponseBody
    @RequiresPermissions("productionPlan:list")
    public FebsResponse getAllProductionPlans(ProductionPlan productionPlan) {
        return new FebsResponse().success().data(productionPlanService.findProductionPlans(productionPlan));
    }

    @GetMapping("productionPlan/list")
    @ResponseBody
    @RequiresPermissions("productionPlan:view")
    public FebsResponse productionPlanList(QueryRequest request, ProductionPlan productionPlan) {
        Map<String, Object> dataTable = getDataTable(this.productionPlanService.findProductionPlans(request, productionPlan));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ProductionPlan", exceptionMessage = "新增ProductionPlan失败")
    @PostMapping("productionPlan/add")
    @ResponseBody
    @RequiresPermissions("productionPlan:add")
    public FebsResponse addProductionPlan(@Valid String userName, ProductionPlanSchedule productionPlanSchedule) throws ParseException {
        this.productionPlanService.createProductionPlan(userName,productionPlanSchedule);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ProductionPlan", exceptionMessage = "删除ProductionPlan失败")
    @GetMapping("productionPlan/delete/{planCode}")
    @ResponseBody
    @RequiresPermissions("productionPlan:delete")
    public FebsResponse deleteProductionPlan(@PathVariable String planCode) {
        this.productionPlanService.deleteProductionPlan(planCode);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ProductionPlan", exceptionMessage = "修改ProductionPlan失败")
    @PostMapping("productionPlan/update")
    @ResponseBody
    @RequiresPermissions("productionPlan:update")
    public FebsResponse updateProductionPlan(ProductionPlanSchedule productionPlanSchedule) {
        this.productionPlanService.updateProductionPlan(productionPlanSchedule);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ProductionPlan", exceptionMessage = "导出Excel失败")
    @PostMapping("productionPlan/excel")
    @ResponseBody
    @RequiresPermissions("productionPlan:export")
    public void export(QueryRequest queryRequest, ProductionPlan productionPlan, HttpServletResponse response) {
        List<ProductionPlan> productionPlans = this.productionPlanService.findProductionPlans(queryRequest, productionPlan).getRecords();
        ExcelKit.$Export(ProductionPlan.class, response).downXlsx(productionPlans, false);
    }

    //出厂编号
    @PostMapping("productionPlan/number")
    @ResponseBody
    @RequiresPermissions("productionPlan:number")
    public FebsResponse numberProductionPlan(@Valid ProductionPlan productionPlan){
        this.productionPlanService.numberProductionPlan(productionPlan);
        return new FebsResponse().success();
    }

    //附件上传
    @PostMapping("productionPlan/upload")
    @ResponseBody
    @RequiresPermissions("productionPlan:upload")
    public FebsResponse uploadProductionPlan(@Valid ProductionPlan productionPlan){
        this.productionPlanService.uploadProductionPlan(productionPlan);
        return new FebsResponse().success();
    }

    //设置BOM
    @ControllerEndpoint(operation = "新增BomNumber", exceptionMessage = "新增BomNumber失败")
    @PostMapping("setupBom/add")
    @ResponseBody
    @RequiresPermissions("productionPlan:bom")
    public FebsResponse addSetupBom(@RequestBody String setupBom,@RequestBody String dataTable) throws ParseException {
        this.productionPlanService.createSetupBom(setupBom,dataTable);
        return new FebsResponse().success();
    }

    @GetMapping("productionPlanUpdateBom/query")
    @ResponseBody
    public Map queryProductionPlanUpdateBom(@RequestParam String planCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SetupBom> setupBom = this.productionPlanService.queryProductionPlanUpdateBom(planCode);
            map.put("replies",setupBom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //修改BOM
    @ControllerEndpoint(operation = "修改BomNumber", exceptionMessage = "修改BomNumber失败")
    @PostMapping("setupBom/update")
    @ResponseBody
    @RequiresPermissions("productionPlan:bom")
    public FebsResponse updateSetupBom(@RequestBody String setupBom,@RequestBody String dataTable) throws ParseException {
        this.productionPlanService.updateSetupBom(setupBom,dataTable);
        return new FebsResponse().success();
    }

    @GetMapping("productionPlanViewBom/query")
    @ResponseBody
    public Map queryProductionPlanViewBom(@RequestParam String planCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SetupBomSchedule> setupBomSchedules = this.productionPlanService.queryProductionPlanViewBom(planCode);
            map.put("replies",setupBomSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 生产领用根据机器码查询
     * @param request
     * @param
     * @return
     */
    @GetMapping("productionRecipients/queryList")
    @ResponseBody
    public FebsResponse productionRecipientsAddQuery(QueryRequest request, SetupBomSchedule setupBomSchedule) {
        Map<String, Object> dataTable = getDataTable(this.productionPlanService.productionRecipientsAddQuery(request,setupBomSchedule));
        return new FebsResponse().success().data(dataTable);
    }

    /**
     * 安排生产
     * @param productionPlan
     * @return
     * @throws ParseException
     */
    @ControllerEndpoint(operation = "新增ProductionPlan", exceptionMessage = "新增ProductionPlan失败")
    @PostMapping("productionStatistical/arrange")
    @ResponseBody
    @RequiresPermissions("productionStatistical:arrange")
    public FebsResponse updateProductionStatistical(@Valid ProductionPlan productionPlan) throws ParseException {
        this.productionPlanService.updateProductionStatistical(productionPlan);
        return new FebsResponse().success();
    }

    /**
     * 生产统计导出
     * @param
     * @param productionPlan
     * @param response
     */
    @ControllerEndpoint(operation = "导出PersonnelDormitoryInformation", exceptionMessage = "导出Excel失败")
    @GetMapping("productionStatistical/excel")
    @RequiresPermissions("productionStatistical:export")
    public void productionStatistical(ProductionPlan productionPlan, HttpServletResponse response) {
        List<ProductionPlan> productionPlans = this.productionPlanService.productionStatisticalExport(productionPlan);
        ExcelKit.$Export(ProductionPlan.class, response).downXlsx(productionPlans, false);
    }
}

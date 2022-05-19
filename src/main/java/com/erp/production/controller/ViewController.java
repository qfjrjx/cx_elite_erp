package com.erp.production.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.service.IProductionPlanService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

/**
 * @author wangchuang
 */
@Controller("productionView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "production")
@RequiredArgsConstructor
public class ViewController {

    private final IProductionPlanService iProductionPlanService;

    /*生产计划模块开始*/

    //生产计划查询
    @GetMapping("productionPlan/list")
    @RequiresPermissions("productionPlan:view")
    public String productionPlanIndex(){
        return FebsUtil.view("productionPlan/productionPlanList");
    }

    //生产计划新增
    @GetMapping("productionPlan/add")
    @RequiresPermissions("productionPlan:add")
    public String productionPlanAdd(){
        return FebsUtil.view("productionPlan/productionPlanAdd");
    }

    //销售订单查询
    @GetMapping("saleOrderListQuery")
    @RequiresPermissions("productionPlan:add")
    public String saleOrderList(){
        return FebsUtil.view("productionPlan/saleOrderListQuery");
    }

    //生产计划修改
    @GetMapping("productionPlan/update/{id}")
    @RequiresPermissions("productionPlan:update")
    public String productionPlanUpdate(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanUpdate");
    }
    //生产计划修改回填
    private void productionPlanModel(Long id, Model model, Boolean transform) throws ParseException {
        ProductionPlanSchedule productionPlanSchedule = iProductionPlanService.productionPlanScheduleId(id);
        model.addAttribute("productionPlanSchedule", productionPlanSchedule);
        if (productionPlanSchedule.getPlanDate() != null) {
            model.addAttribute("planDate", DateUtil.getDateFormat(productionPlanSchedule.getPlanDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    @GetMapping("productionPlan/number/{id}")
    @RequiresPermissions("productionPlan:number")
    public String productionPlanNumber(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanNumber");
    }

    //附件上传
    @GetMapping("productionPlan/upload/{id}")
    @RequiresPermissions("productionPlan:upload")
    public String productionPlanUpload(@PathVariable Long id, Model model) throws ParseException {
        productionPlanUploadModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanUpload");
    }

    //生产计划配置查看
    @GetMapping("productionPlan/detail/{planNumber}")
    public String productionPlanDetail(@PathVariable String planNumber, Model model) throws ParseException {
        productionPlanDetail(planNumber, model, false);
        return FebsUtil.view("productionPlan/productionPlanDetail");
    }

    private void productionPlanUploadModel(Long id, Model model, Boolean transform) throws ParseException {
        ProductionPlan productionPlan = iProductionPlanService.productionPlanId(id);
        model.addAttribute("productionPlan", productionPlan);
    }

    private void productionPlanDetail(String planNumber, Model model, Boolean transform) throws ParseException {
        ProductionPlanSchedule productionPlanSchedule = iProductionPlanService.productionPlanPlanNumber(planNumber);
        model.addAttribute("productionPlanNumber", productionPlanSchedule);
    }

    //设置BOM
    @GetMapping("productionPlan/addBom/{id}")
    @RequiresPermissions("productionPlan:bom")
    public String productionPlanAddBOM(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanAddBom");
    }

    @GetMapping("productionPlanAddBomQueryList")
    @RequiresPermissions("productionPlan:bom")
    public String productionPlanAddBomQueryList(){
        return FebsUtil.view("productionPlan/productionPlanAddBomQueryList");
    }

    //修改BOM
    @GetMapping("productionPlan/updateBom/{id}")
    @RequiresPermissions("productionPlan:bom")
    public String productionPlanUpdateBom(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanUpdateBom");
    }

    @GetMapping("productionPlanUpdateBomQueryList")
    @RequiresPermissions("productionPlan:bom")
    public String productionPlanUpdateBomQueryList(){
        return FebsUtil.view("productionPlan/productionPlanUpdateBomQueryList");
    }

    //BOM查阅
    @GetMapping("productionPlan/viewBom/{planCode}")
    public String productionViewBom(@PathVariable String planCode, Model model){
        productionViewBomModel(planCode, model, false);
        return FebsUtil.view("productionPlan/productionPlanViewBom");
    }

    private void productionViewBomModel(String planCode, Model model, Boolean transform){
        model.addAttribute("planCodeViewBom", planCode);
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("productionPlanPlanCustomerNameList")
    @RequiresPermissions("productionPlan:view")
    public String purchaseSupplierSelectionList(Model model) {

        return FebsUtil.view("productionPlan/purchaseSupplierSelectionList");
    }

    /*生产计划模块结束*/

    /*生产领用开始*/

    //生产领用查询
    @GetMapping("productionRecipients/list")
    @RequiresPermissions("productionRecipients:view")
    public String productionRecipientsIndex(){
        return FebsUtil.view("productionRecipients/productionRecipientsList");
    }

    /*生产领用结束*/
}

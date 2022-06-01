package com.erp.production.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.production.entity.LackRecipients;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.service.ILackRecipientsService;
import com.erp.production.service.IProductionPlanService;
import com.erp.production.service.IProductionRecipientsService;
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

    //生产计划Service接口
    private final IProductionPlanService iProductionPlanService;
    //生产领用Service接口
    private final IProductionRecipientsService productionRecipientsService;
    //缺件领用Service接口
    private final ILackRecipientsService lackRecipientsService;

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

    //出厂编号
    @GetMapping("productionPlan/number/{id}")
    @RequiresPermissions("productionPlan:number")
    public String productionPlanNumber(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanNumber");
    }

    //关联出货
    @GetMapping("productionPlan/shipment/{id}")
    @RequiresPermissions("productionPlan:shipment")
    public String productionPlanShipment(@PathVariable Long id, Model model) throws ParseException {
        productionPlanModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanShipment");
    }

    //销售发货查询
    @GetMapping("saleProductionPlanShipmentList")
    public String saleProductionPlanShipmentList(){
        return FebsUtil.view("productionPlan/saleProductionPlanShipmentList");
    }

    //客户查询
    @GetMapping("productionPlanShipmentCustomerNameList")
    public String productionPlanShipmentCustomerNameList(){
        return FebsUtil.view("productionPlan/productionPlanShipmentCustomerNameList");
    }

    //附件上传
    @GetMapping("productionPlan/upload/{id}")
    @RequiresPermissions("productionPlan:upload")
    public String productionPlanUpload(@PathVariable Long id, Model model) throws ParseException {
        productionPlanUploadModel(id, model, false);
        return FebsUtil.view("productionPlan/productionPlanUpload");
    }

    //生产计划配置查看
    @GetMapping("productionPlan/detail/{planCode}")
    public String productionPlanDetail(@PathVariable String planCode, Model model) throws ParseException {
        productionPlanDetail(planCode, model, false);
        return FebsUtil.view("productionPlan/productionPlanDetail");
    }

    private void productionPlanUploadModel(Long id, Model model, Boolean transform) throws ParseException {
        ProductionPlan productionPlan = iProductionPlanService.productionPlanId(id);
        model.addAttribute("productionPlan", productionPlan);
    }

    private void productionPlanDetail(String planCode, Model model, Boolean transform) throws ParseException {
        ProductionPlanSchedule productionPlanSchedule = iProductionPlanService.productionPlanPlanNumber(planCode);
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

    //客户查询
    @GetMapping("saleCustomerProfileSelection")
    public String saleCustomerProfileSelectionList(){
        return FebsUtil.view("productionPlan/saleCustomerProfileSelectionList");
    }

    /*生产计划模块结束*/

    /*生产领用开始*/

    //生产领用查询
    @GetMapping("productionRecipients/list")
    @RequiresPermissions("productionRecipients:view")
    public String productionRecipientsIndex(){
        return FebsUtil.view("productionRecipients/productionRecipientsList");
    }

    //生产计划新增
    @GetMapping("productionRecipients/add")
    @RequiresPermissions("productionRecipients:add")
    public String productionRecipientsAdd(){
        return FebsUtil.view("productionRecipients/productionRecipientsAdd");
    }

    //查询生产计划
    @GetMapping("dblclickRecipientsMachineAdd")
    public String dblclickRecipientsMachineAdd(){
        return FebsUtil.view("productionRecipients/dblclickRecipientsMachineAdd");
    }

    //修改生产计划
    @GetMapping("productionRecipients/update/{id}")
    @RequiresPermissions("productionRecipients:update")
    public String productionRecipientsUpdate(@PathVariable Long id, Model model) throws ParseException {
        productionRecipientsModel(id, model, false);
        return FebsUtil.view("productionRecipients/productionRecipientsUpdate");
    }

    private void productionRecipientsModel(Long id, Model model, Boolean transform) throws ParseException {
        ProductionRecipients productionRecipients = productionRecipientsService.productionRecipientsId(id);
        model.addAttribute("productionRecipients", productionRecipients);
        if (productionRecipients.getRecipientsDate() != null) {
            model.addAttribute("productionRecipientsDate", DateUtil.getDateFormat(productionRecipients.getRecipientsDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //缺件查阅
    @GetMapping("productionRecipients/lack/{recipientsLackCode}")
    @RequiresPermissions("productionRecipients:lack")
    public String productionRecipientsLack(@PathVariable String recipientsLackCode, Model model){
        model.addAttribute("recipientsLackCode", recipientsLackCode);
        return FebsUtil.view("productionRecipients/productionRecipientsLack");
    }

    /*生产领用结束*/

    /*缺件领用结束*/

    //缺件领用查询
    @GetMapping("lackRecipients/list")
    @RequiresPermissions("lackRecipients:view")
    public String lackRecipientsIndex(){
        return FebsUtil.view("lackRecipients/lackRecipientsList");
    }

    //缺件领用新增
    @GetMapping("lackRecipients/add")
    @RequiresPermissions("lackRecipients:add")
    public String lackRecipientsAdd(){
        return FebsUtil.view("lackRecipients/lackRecipientsAdd");
    }

    //缺件领用列表查询
    @GetMapping("lackRecipientsQueryList")
    @RequiresPermissions("lackRecipients:add")
    public String lackRecipientsQueryList(){
        return FebsUtil.view("lackRecipients/lackRecipientsQueryList");
    }

    //缺件领用修改
    @GetMapping("lackRecipients/update/{id}")
    @RequiresPermissions("lackRecipients:update")
    public String lackRecipientsUpdate(@PathVariable Long id, Model model) throws ParseException {
        lackRecipientsModel(id, model, false);
        return FebsUtil.view("lackRecipients/lackRecipientsUpdate");
    }

    private void lackRecipientsModel(Long id, Model model, Boolean transform) throws ParseException {
        LackRecipients lackRecipients = lackRecipientsService.lackRecipientsId(id);
        model.addAttribute("lackRecipients", lackRecipients);
        if (lackRecipients.getLackDate() != null) {
            model.addAttribute("lackRecipientsDate", DateUtil.getDateFormat(lackRecipients.getLackDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //缺件领用列表查询
    @GetMapping("lackRecipientsUpdateQuery")
    @RequiresPermissions("lackRecipients:update")
    public String lackRecipientsUpdateQuery(){
        return FebsUtil.view("lackRecipients/lackRecipientsUpdateQuery");
    }

    /*缺件领用结束*/

    /*生产统计开始*/

    //生产统计查询
    @GetMapping("productionStatistical/list")
    @RequiresPermissions("productionStatistical:view")
    public String productionStatisticalIndex(){
        return FebsUtil.view("productionStatistical/productionStatisticalList");
    }

    //生产统计安排生产
    @GetMapping("productionStatistical/arrange/{id}")
    @RequiresPermissions("productionStatistical:arrange")
    public String productionStatisticalArrange(@PathVariable Long id, Model model) throws ParseException {
        productionStatisticalModel(id, model, false);
        return FebsUtil.view("productionStatistical/productionStatisticalArrange");
    }
    //安排生产回填
    private void productionStatisticalModel(Long id, Model model, Boolean transform) throws ParseException {
        ProductionPlan productionPlan = iProductionPlanService.productionPlanId(id);
        model.addAttribute("productionPlan", productionPlan);
        if (productionPlan.getPlanExpectDate() != null) {
            model.addAttribute("planExpectDate", DateUtil.getDateFormat(productionPlan.getPlanExpectDate(), DateUtil.FULL_TIME_SPLIT));
        }if (productionPlan.getPlanExpectDateOne() != null) {
            model.addAttribute("planExpectDateOne", DateUtil.getDateFormat(productionPlan.getPlanExpectDateOne(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //生产负责人选择列表
    @GetMapping("productionHead")
    @RequiresPermissions("productionStatistical:arrange")
    public String productionHead(){
        return FebsUtil.view("productionStatistical/productionHead");
    }

    @GetMapping("productionHeadOne")
    @RequiresPermissions("productionStatistical:arrange")
    public String productionHeadOne(){
        return FebsUtil.view("productionStatistical/productionHeadOne");
    }

    /*生产统计结束*/
}

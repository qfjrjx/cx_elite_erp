package com.erp.sale.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.sale.entity.SaleApplication;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.entity.SaleCustomerProfile;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.service.ISaleApplicationService;
import com.erp.sale.service.ISaleBusinessPersonnelService;
import com.erp.sale.service.ISaleCustomerProfileService;
import com.erp.sale.service.ISaleParametersService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qiufeng
 */
@Controller("saleParametersView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "sale")
@RequiredArgsConstructor
public class ViewController {
    //销售参数Service接口
    private final ISaleParametersService saleParametersService;
    //业务人员Service接口
    private final ISaleBusinessPersonnelService saleBusinessPersonnelService;
    //客户档案Service接口
    private final ISaleCustomerProfileService saleCustomerProfileService;
    //销售申请表 Service接口
    private final ISaleApplicationService saleApplicationService;

    /* 销售参数模块开始 */
    //跳转到销售参数页面
    @GetMapping("saleParameters")
    @RequiresPermissions("saleParameters:view")
    public String saleParametersIndex(){
        return FebsUtil.view("salesParameters/salesParametersList");
    }
    //销售参数添加
    @GetMapping("saleParameters/add")
    @RequiresPermissions("saleParameters:add")
    public String systemUserAdd() {
        return FebsUtil.view("salesParameters/salesParametersAdd");
    }
    //销售参数修改
    @GetMapping("saleParameters/update/{id}")
    @RequiresPermissions("saleParameters:update")
    public String systemUserUpdate(@PathVariable Long id, Model model) {
        resolveSalesParametersModel(id, model, false);
        return FebsUtil.view("salesParameters/salesParametersUpdate");
    }
    //销售参数修改回填
    private void resolveSalesParametersModel(Long id, Model model, Boolean transform) {
        SaleParameters saleParameters = saleParametersService.findSaleParametersById(id);
        model.addAttribute("saleParameters", saleParameters);
    }
    /* 销售参数模块结束 */


    /* 业务人员模块开始 */
    //跳转到业务人员页面
    @GetMapping("saleBusinessPersonnel")
    @RequiresPermissions("saleBusinessPersonnel:view")
    public String saleBusinessPersonnelIndex(){
        return FebsUtil.view("businessPersonnel/businessPersonnelList");
    }
    //业务人员新增
    @GetMapping("saleBusinessPersonnel/add")
    @RequiresPermissions("saleBusinessPersonnel:add")
    public String saleBusinessPersonnelAdd(Model model) {
        //查询区域信息
        List<SaleBusinessPersonnel> saleBusinessPersonnel  = saleParametersService.querySaleBusinessPersonnel();
        model.addAttribute("saleBusinessPersonnel",saleBusinessPersonnel);
        return FebsUtil.view("businessPersonnel/businessPersonnelAdd");
    }
    //业务人员修改
    @GetMapping("saleBusinessPersonnel/update/{id}")
    @RequiresPermissions("saleBusinessPersonnel:update")
    public String saleBusinessPersonnelUpdate(@PathVariable Long id, Model model) {
        saleBusinessPersonnelModel(id, model, false);
        return FebsUtil.view("businessPersonnel/businessPersonnelUpdate");
    }
    //业务人员修改回填
    private void saleBusinessPersonnelModel(Long id, Model model, Boolean transform) {
        //查询区域信息
        List<SaleBusinessPersonnel> saleBusiness  = saleParametersService.querySaleBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        SaleBusinessPersonnel saleBusinessPersonnel = saleBusinessPersonnelService.findSaleBusinessPersonnelById(id);
        model.addAttribute("saleBusinessPersonnel", saleBusinessPersonnel);
    }
    /* 业务人员模块结束 */

    /* 员工档案查询开始 */
    @GetMapping("employeeFileUpdate")
    public String employeeFileIndex(){
        return FebsUtil.view("businessPersonnel/employeeFileUpdate");
    }
    /* 员工档案结束 */

    /* 客户档案模块结束 */
    //跳转到客户档案页面
    @GetMapping("saleCustomerProfile")
    @RequiresPermissions("saleCustomerProfile:view")
    public String saleCustomerProfileIndex(Model model){
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileList");
    }
    //客户档案添加
    @GetMapping("saleCustomerProfile/add")
    @RequiresPermissions("saleCustomerProfile:add")
    public String saleCustomerProfileAdd() {
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileAdd");
    }
    //业务人员修改
    @GetMapping("saleCustomerProfile/update/{id}")
    @RequiresPermissions("saleCustomerProfile:update")
    public String saleCustomerProfileUpdate(@PathVariable Long id, Model model) {
        saleCustomerProfileModel(id, model, false);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileUpdate");
    }
    //业务人员修改回填
    private void saleCustomerProfileModel(Long id, Model model, Boolean transform) {

        SaleCustomerProfile saleCustomerProfile = saleCustomerProfileService.findSaleCustomerProfileById(id);
        model.addAttribute("saleCustomerProfile", saleCustomerProfile);
    }
    //业务人员查阅
    @GetMapping("saleCustomerProfileConsult/detail/{id}")
    @RequiresPermissions("saleCustomerProfile:view")
    public String saleCustomerProfileConsult(@PathVariable Long id, Model model) {
        saleCustomerProfileConsultModel(id, model, false);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileConsult");
    }
    //业务人员查阅回填
    private void saleCustomerProfileConsultModel(Long id, Model model, Boolean transform) {

        SaleCustomerProfile saleCustomerProfile = saleCustomerProfileService.findSaleCustomerProfileById(id);
        model.addAttribute("saleCustomerProfile", saleCustomerProfile);
    }
    /* 客户档案模块结束 */
    //双击跳到业务人员页面
    @GetMapping("saleCustomerPersonnelList")
    @RequiresPermissions("saleBusinessPersonnel:view")
    public String saleCustomerPersonnelList() {
        return FebsUtil.view("saleCustomerProfile/saleCustomerPersonnelList");
    }


    /* 销售业务开始 */
    //跳转到销售申请页面
    @GetMapping("saleApplicationList")
    @RequiresPermissions("saleApplication:view")
    public String saleApplicationIndex(Model model){
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        try{
            int count = saleApplicationService.quantityNameStatistics();
            model.addAttribute("quantityNameCount",count);

        }catch(Exception e){
            model.addAttribute("quantityNameCount","0");
        }
        return FebsUtil.view("saleApplication/saleApplicationList");
    }
    //销售申请添加
    @GetMapping("saleApplication/add")
    @RequiresPermissions("saleApplication:add")
    public String saleApplicationAdd(Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        return FebsUtil.view("saleApplication/saleApplicationAdd");
    }
    //双击跳到客户档案页面  条件查询时用到
    @GetMapping("jumpSaleCustomerProfile")
    @RequiresPermissions("saleCustomerProfile:view")
    public String jumpSaleCustomerProfileList(Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        return FebsUtil.view("saleApplication/saleCustomerProfile");
    }
    //双击跳到客户档案页面  添加时用到
    @GetMapping("saleCustomerProfileSelection")
    @RequiresPermissions("saleCustomerProfile:view")
    public String saleCustomerProfileSelectionList(Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        return FebsUtil.view("saleApplication/saleCustomerProfileSelection");
    }
    /* 销售业务结束 */



    //跳转到销售申请页面
    @GetMapping("saleProductList")
    public String saleProductIndex(Model model){
        return FebsUtil.view("saleProduct/saleProductList");
    }
    //跳转到销售申请页面
    @GetMapping("specificationList/")
    public String specificationIndex(){
        return FebsUtil.view("saleProduct/toConfigureAdd");
    }
    //配置查看
    @GetMapping("saleApplicationAllocation/detail/{id}")
    @RequiresPermissions("saleApplication:dispose")
    public String saleApplicationConfigureView(@PathVariable Long id, Model model) {
        saleApplicationConfigureViewModel(id, model, false);
        return FebsUtil.view("saleApplication/saleApplicationConfigureView");
    }
    //配置信息回填
    private void saleApplicationConfigureViewModel(Long id, Model model, Boolean transform) {

        SaleApplication saleApplication = saleApplicationService.findSaleApplicationConfigureViewById(id);
        model.addAttribute("saleApplicationConfigureView", saleApplication);
    }
    //跳转到销售订单页面
    @GetMapping("saleOrderList")
    @RequiresPermissions("saleOrder:view")
    public String saleOrderIndex(Model model){
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);

        return FebsUtil.view("saleOrder/saleOrderList");
    }

    //销售申请修改
    @GetMapping("saleApplication/update/{applicationNo}")
    @RequiresPermissions("saleApplication:update")
    public String saleApplicationUpdate(@PathVariable String applicationNo, Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusinessOne  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusinessOne",saleBusinessOne);
        saleApplicationModel(applicationNo, model, false);
        return FebsUtil.view("saleApplication/saleApplicationUpdate");
    }
    //销售申请修改回填
    private void saleApplicationModel(String applicationNo, Model model, Boolean transform) {
        SaleApplication saleApplicationOne = saleApplicationService.findSaleApplicationById(applicationNo);
        model.addAttribute("saleApplicationOne", saleApplicationOne);
        if (saleApplicationOne.getRequestedDeliveryDate() != null) {
            model.addAttribute("deliveryDate", DateUtil.getDateFormat(saleApplicationOne.getRequestedDeliveryDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //销售申请设计回复
    @GetMapping("saleApplication/designReply/{applicationNo}")
    @RequiresPermissions("saleApplication:update")
    public String saleApplicationDesignReply(@PathVariable String applicationNo, Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusinessOne  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusinessOne",saleBusinessOne);
        saleApplicationDesignReplyModel(applicationNo, model, false);
        return FebsUtil.view("saleApplication/designReply");
    }
    //销售申请设计回复回填
    private void saleApplicationDesignReplyModel(String applicationNo, Model model, Boolean transform) {
        SaleApplication designReply = saleApplicationService.findSaleApplicationById(applicationNo);
        model.addAttribute("designReply", designReply);
        if (designReply.getRequestedDeliveryDate() != null) {
            model.addAttribute("deliveryDate", DateUtil.getDateFormat(designReply.getRequestedDeliveryDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    //销售申请采购回复
    @GetMapping("saleApplication/purchaseReply/{applicationNo}")
    @RequiresPermissions("saleApplication:update")
    public String saleApplicationPurchaseReply(@PathVariable String applicationNo, Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusinessOne  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusinessOne",saleBusinessOne);
        saleApplicationPurchaseReplyModel(applicationNo, model, false);
        return FebsUtil.view("saleApplication/purchaseReply");
    }
    //销售申请采购回复回填
    private void saleApplicationPurchaseReplyModel(String applicationNo, Model model, Boolean transform) {
        SaleApplication purchaseReply = saleApplicationService.findSaleApplicationById(applicationNo);
        model.addAttribute("purchaseReply", purchaseReply);
        if (purchaseReply.getRequestedDeliveryDate() != null) {
            model.addAttribute("deliveryDate", DateUtil.getDateFormat(purchaseReply.getRequestedDeliveryDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    //销售申请生产回复
    @GetMapping("saleApplication/productionReply/{applicationNo}")
    @RequiresPermissions("saleApplication:update")
    public String saleApplicationProductionReply(@PathVariable String applicationNo, Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusinessOne  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusinessOne",saleBusinessOne);
        saleApplicationProductionReplyModel(applicationNo, model, false);
        return FebsUtil.view("saleApplication/productionReply");
    }
    //销售申请生产回复回填
    private void saleApplicationProductionReplyModel(String applicationNo, Model model, Boolean transform) {
        SaleApplication productionReply = saleApplicationService.findSaleApplicationById(applicationNo);
        model.addAttribute("productionReply", productionReply);
        if (productionReply.getRequestedDeliveryDate() != null) {
            model.addAttribute("deliveryDate", DateUtil.getDateFormat(productionReply.getRequestedDeliveryDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    //销售申请装配回复
    @GetMapping("saleApplication/assemblingReply/{applicationNo}")
    @RequiresPermissions("saleApplication:update")
    public String saleApplicationAssemblingReply(@PathVariable String applicationNo, Model model) {
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusinessOne  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusinessOne",saleBusinessOne);
        saleApplicationAssemblingReplyModel(applicationNo, model, false);
        return FebsUtil.view("saleApplication/assemblingReply");
    }
    //销售申请装配回复回填
    private void saleApplicationAssemblingReplyModel(String applicationNo, Model model, Boolean transform) {
        SaleApplication assemblingReply = saleApplicationService.findSaleApplicationById(applicationNo);
        model.addAttribute("assemblingReply", assemblingReply);
        if (assemblingReply.getRequestedDeliveryDate() != null) {
            model.addAttribute("deliveryDate", DateUtil.getDateFormat(assemblingReply.getRequestedDeliveryDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

}

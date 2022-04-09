package com.erp.purchase.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.service.IFinanceParametersService;
import com.erp.purchase.entity.*;
import com.erp.purchase.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qiufeng
 */
@Controller("purchaseView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "purchase")
@RequiredArgsConstructor
public class ViewController {
    //采购参数表 Service接口
    private final IPurchaseParametersService purchaseParametersService;
    //供货单位表 Service接口
    private final IPurchaseSupplierService purchaseSupplierService;
    //物料类别表 Service接口
    private final IPurchaseMaterialCategoryService purchaseMaterialCategoryService;
    //物料档案表 Service接口
    private final IPurchaseMaterialFileService purchaseMaterialFileService;
    //采购申请表 Service接口
    private final IPurchaseRequisitionService purchaseRequisitionService;
    //财务参数表 Service接口
    private final IFinanceParametersService financeParametersService;
    //采购订单表 Service接口
    private final IPurchaseOrderService purchaseOrderService;
    //采购收货表 Service接口
    private final IPurchaseClosedService purchaseClosedService;
    //来货检验表 Service接口
    private final IPurchaseInspectionService purchaseInspectionService;
    //采购退货表 Service接口
    private final IPurchaseRefundService purchaseRefundService;
    //采购发票表 Service接口
    private final IPurchaseInvoiceService purchaseInvoiceService;


    /*采购管理模块-采购档案开始*/
    /*采购参数列表*/
    @GetMapping("purchaseParameters/list")
    @RequiresPermissions("purchaseParameters:view")
    public String purchaseParametersIndex(){
        return FebsUtil.view("purchaseParameters/purchaseParametersList");
    }
    //采购参数添加
    @GetMapping("purchaseParameters/add")
    @RequiresPermissions("purchaseParameters:add")
    public String purchaseParametersAdd() {
        return FebsUtil.view("purchaseParameters/purchaseParametersAdd");
    }
    //采购参数修改
    @GetMapping("purchaseParameters/update/{id}")
    @RequiresPermissions("purchaseParameters:update")
    public String purchaseParametersUpdate(@PathVariable Long id, Model model) {
        purchaseParametersModel(id, model, false);
        return FebsUtil.view("purchaseParameters/purchaseParametersUpdate");
    }
    //采购参数修改回填
    private void purchaseParametersModel(Long id, Model model, Boolean transform) {
        PurchaseParameters purchaseParameters = purchaseParametersService.findPurchaseParametersById(id);
        model.addAttribute("purchaseParameters", purchaseParameters);
    }

    /*供货单位列表*/
    @GetMapping("purchaseSupplier/list")
    @RequiresPermissions("purchaseSupplier:view")
    public String purchaseSupplierIndex(){
        return FebsUtil.view("supplier/supplierList");
    }

    //供货单位添加
    @GetMapping("purchaseSupplier/add")
    @RequiresPermissions("purchaseSupplier:add")
    public String purchaseSupplierAdd() {
        return FebsUtil.view("supplier/supplierAdd");
    }
    //供货单位修改
    @GetMapping("purchaseSupplier/update/{id}")
    @RequiresPermissions("purchaseSupplier:update")
    public String purchaseSupplierUpdate(@PathVariable Long id, Model model) {
        purchaseSupplierModel(id, model, false);
        return FebsUtil.view("supplier/supplierUpdate");
    }
    //供货单位修改回填
    private void purchaseSupplierModel(Long id, Model model, Boolean transform) {
        PurchaseSupplier purchaseSupplier = purchaseSupplierService.findPurchaseSupplierById(id);
        model.addAttribute("purchaseSupplier", purchaseSupplier);
    }

    //供货单位查阅
    @GetMapping("purchaseSupplier/consult/{id}")
    @RequiresPermissions("purchaseSupplier:consult")
    public String purchaseSupplierConsult(@PathVariable Long id, Model model) {
        purchaseSupplierConsultModel(id, model, false);
        return FebsUtil.view("supplier/supplierConsult");
    }
    //供货单位查阅回填
    private void purchaseSupplierConsultModel(Long id, Model model, Boolean transform) {
        PurchaseSupplier purchaseSupplier = purchaseSupplierService.findPurchaseSupplierById(id);
        model.addAttribute("purchaseSupplier", purchaseSupplier);
    }

    /*物料类别列表*/
    @GetMapping("purchaseMaterialCategory/list")
    @RequiresPermissions("purchaseMaterialCategory:view")
    public String purchaseMaterialCategoryIndex(){
        return FebsUtil.view("materialCategory/materialCategoryList");
    }

    //物料类别添加
    @GetMapping("purchaseMaterialCategory/add")
    @RequiresPermissions("purchaseMaterialCategory:add")
    public String purchaseMaterialCategoryAdd(Model model) {
        //查询物料类别表
        List<PurchaseMaterialCategory> purchaseMaterialCategories  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("purchaseMaterialCategories",purchaseMaterialCategories);
        return FebsUtil.view("materialCategory/materialCategoryAdd");
    }

    //物料类别修改
    @GetMapping("purchaseMaterialCategory/update/{id}")
    @RequiresPermissions("purchaseMaterialCategory:update")
    public String purchaseMaterialCategoryUpdate(@PathVariable Long id, Model model) {
        purchaseMaterialCategoryModel(id, model, false);
        //查询物料类别表 大类
        List<PurchaseMaterialCategory> purchaseMaterialCategories  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("purchaseMaterialCategories",purchaseMaterialCategories);
        return FebsUtil.view("materialCategory/materialCategoryUpdate");
    }
    //物料类别修改回填
    private void purchaseMaterialCategoryModel(Long id, Model model, Boolean transform) {
        PurchaseMaterialCategory purchaseMaterialCategory = purchaseMaterialCategoryService.findPurchaseMaterialCategoryById(id);
        model.addAttribute("purchaseMaterialCategory", purchaseMaterialCategory);
    }

    /*物料档案列表*/
    @GetMapping("purchaseMaterialFile/list")
    @RequiresPermissions("purchaseMaterialFile:view")
    public String purchaseMaterialFileIndex(Model model){

        //查询产品材质信息
        List<PurchaseParameters> productMaterial  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterial);
        //查询物料类别表 大类
        List<PurchaseMaterialCategory> generalCategory  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("generalCategory",generalCategory);

        return FebsUtil.view("purchaseMaterialFile/purchaseMaterialFileList");
    }

    //物料档案添加
    @GetMapping("purchaseMaterialFile/add")
    @RequiresPermissions("purchaseMaterialFile:add")
    public String purchaseMaterialFileAdd(Model model) {
        //查询产品材质信息
        List<PurchaseParameters> productMaterial  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterial);
        //查询计量单位信息
        List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
        model.addAttribute("unitOfMeasure",unitOfMeasure);
        //查询物料类别表 大类
        List<PurchaseMaterialCategory> generalCategory  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("generalCategory",generalCategory);
        //查询物料类别表 小类
        List<PurchaseMaterialCategory> purchaseMaterialCategory  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.SUBCLASS);
        model.addAttribute("purchaseMaterialCategory",purchaseMaterialCategory);

        return FebsUtil.view("purchaseMaterialFile/purchaseMaterialFileAdd");
    }
    //物料档案修改
    @GetMapping("purchaseMaterialFile/update/{id}")
    @RequiresPermissions("purchaseMaterialFile:update")
    public String purchaseMaterialFileUpdate(@PathVariable Long id, Model model) {
        purchaseMaterialFileModel(id, model, false);
        //查询产品材质信息
        List<PurchaseParameters> productMaterial  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterial);
        //查询计量单位信息
        List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
        model.addAttribute("unitOfMeasure",unitOfMeasure);
        //查询物料类别表 大类
        List<PurchaseMaterialCategory> generalCategory  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("generalCategory",generalCategory);
        return FebsUtil.view("purchaseMaterialFile/purchaseMaterialFileUpdate");
    }
    //物料档案修改回填
    private void purchaseMaterialFileModel(Long id, Model model, Boolean transform) {
        PurchaseMaterialFile purchaseMaterialFile = purchaseMaterialFileService.findPurchaseMaterialFileById(id);
        model.addAttribute("purchaseMaterialFile", purchaseMaterialFile);

        Long generalCategoryId = purchaseMaterialFile.getGeneralCategoryId();
        //查询物料类别表 小类
        List<PurchaseMaterialCategory> purchaseMaterialCategory  = purchaseMaterialCategoryService.queryMaterialSubclass(generalCategoryId);
        model.addAttribute("purchaseMaterialCategory",purchaseMaterialCategory);
    }

    //物料档案复制
    @GetMapping("purchaseMaterialFile/copy/{id}")
    @RequiresPermissions("purchaseMaterialFile:copy")
    public String purchaseMaterialFileCopy(@PathVariable Long id, Model model) {
        purchaseMaterialFileCopyModel(id, model, false);
        //查询产品材质信息
        List<PurchaseParameters> productMaterial  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterial);
        //查询计量单位信息
        List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
        model.addAttribute("unitOfMeasure",unitOfMeasure);
        //查询物料类别表 大类
        List<PurchaseMaterialCategory> generalCategory  = purchaseMaterialCategoryService.queryPurchaseMaterialCategory(PurchaseMaterialCategory.GENERAL_CATEGORY);
        model.addAttribute("generalCategory",generalCategory);
        return FebsUtil.view("purchaseMaterialFile/purchaseMaterialFileCopy");
    }
    //物料档案复制回填
    private void purchaseMaterialFileCopyModel(Long id, Model model, Boolean transform) {
        PurchaseMaterialFile purchaseMaterialFile = purchaseMaterialFileService.findPurchaseMaterialFileCopyById(id);
        model.addAttribute("purchaseMaterialFile", purchaseMaterialFile);

        Long generalCategoryId = purchaseMaterialFile.getGeneralCategoryId();
        //查询物料类别表 小类
        List<PurchaseMaterialCategory> purchaseMaterialCategory  = purchaseMaterialCategoryService.queryMaterialSubclass(generalCategoryId);
        model.addAttribute("purchaseMaterialCategory",purchaseMaterialCategory);
    }
    /*采购管理模块-采购档案结束*/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/
    /*采购管理模块-采购业务开始*/

      /*采购申请列表*/
      @GetMapping("purchaseRequisition/list")
      @RequiresPermissions("purchaseRequisition:view")
      public String purchaseRequisitionIndex(){
          return FebsUtil.view("purchaseRequisition/purchaseRequisitionList");
      }
      //采购申请添加
      @GetMapping("purchaseRequisition/add")
      @RequiresPermissions("purchaseRequisition:add")
      public String purchaseRequisitionAdd() {
        return FebsUtil.view("purchaseRequisition/purchaseRequisitionAdd");
      }

    //添加页面 点击编码跳转到采购申请下物料编码选择列表页面
    @GetMapping("purchaseMaterialCodeList")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseMaterialCodeIndex(Model model){
        return FebsUtil.view("purchaseRequisition/purchaseMaterialCode");
    }

    //修改页面 点击编码跳转到采购申请下物料编码选择列表页面
    @GetMapping("purchaseMaterialCodeListUpdate")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseMaterialCodeUpdateIndex(Model model){
        return FebsUtil.view("purchaseRequisition/purchaseMaterialCodeUpdate");
    }


     //采购申请修改
     @GetMapping("purchaseRequisition/update/{id}")
     @RequiresPermissions("purchaseRequisition:update")
     public String purchaseRequisitionUpdate(@PathVariable Long id, Model model) {
         purchaseRequisitionModel(id, model, false);
        return FebsUtil.view("purchaseRequisition/purchaseRequisitionUpdate");
     }
    //采购申请修改回填
     private void purchaseRequisitionModel(Long id, Model model, Boolean transform) {
         PurchaseRequisitionPositive purchaseRequisitionPositive = purchaseRequisitionService.findPurchaseRequisitionPositiveById(id);
        model.addAttribute("purchaseRequisitionPositive", purchaseRequisitionPositive);
         if (purchaseRequisitionPositive.getPurchaseRequisitionDate() != null) {
             model.addAttribute("purchaseRequisitionDate", DateUtil.getDateFormat(purchaseRequisitionPositive.getPurchaseRequisitionDate(), DateUtil.FULL_TIME_SPLIT));
         }
     }

     /*采购订单列表*/
     @GetMapping("purchaseOrder/list")
     @RequiresPermissions("purchaseOrder:view")
     public String purchaseOrderIndex(){
         return FebsUtil.view("purchaseOrder/purchaseOrderRequisitionList");
     }
    //采购订单添加
    @GetMapping("purchaseOrder/add")
    @RequiresPermissions("purchaseOrder:add")
    public String purchaseOrderAdd(Model model) {
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        //查询供应商表
        /*List<PurchaseSupplier> purchaseSuppliers  = purchaseSupplierService.queryPurchaseSupplierList();
        model.addAttribute("purchaseSuppliers",purchaseSuppliers);*/

        return FebsUtil.view("purchaseOrder/purchaseOrderAdd");
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("purchaseSupplierSelectionList")
    @RequiresPermissions("purchaseSupplier:view")
    public String purchaseSupplierSelectionList(Model model) {

        return FebsUtil.view("purchaseOrder/purchaseSupplierSelectionList");
    }


    //添加页面 点击申请单号跳转到采购申请
    @GetMapping("purchaseRequisitionQueryList")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseRequisitionQueryList(Model model){
        return FebsUtil.view("purchaseOrder/orderPurchaseRequisitionList");
    }


    //采购订单修改
    @GetMapping("purchaseOrder/update/{id}")
    @RequiresPermissions("purchaseRequisition:update")
    public String purchaseOrderUpdate(@PathVariable Long id, Model model) {
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        purchaseOrderModel(id, model, false);
        return FebsUtil.view("purchaseOrder/purchaseOrderUpdate");
    }


    //采购订单修改回填
    private void purchaseOrderModel(Long id, Model model, Boolean transform) {
        PurchaseOrder purchaseOrderPositive = purchaseOrderService.queryPurchaseOrderList(id);
        model.addAttribute("purchaseOrderPositive", purchaseOrderPositive);
        if (purchaseOrderPositive.getPurchaseRequisitionDate() != null) {
            model.addAttribute("purchaseRequisitionDate", DateUtil.getDateFormat(purchaseOrderPositive.getPurchaseRequisitionDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //修改页面 点击编码跳转到采购申请下物料编码选择列表页面
    @GetMapping("purchaseOrderlCodeListUpdate")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseOrderCodeUpdateIndex(Model model){
        return FebsUtil.view("purchaseOrder/purchaseOrderCodeUpdate");
    }

    /*采购管理模块-采购业务结束*/

    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    /*采购管理模块-采购收货开始*/

    /*采购收货列表*/
    @GetMapping("purchaseClosed/list")
    @RequiresPermissions("purchaseClosed:view")
    public String purchaseClosedIndex(){
        return FebsUtil.view("purchaseClosed/purchaseClosedList");
    }

    /*采购收货添加*/
    @GetMapping("purchaseClosed/add")
    @RequiresPermissions("purchaseClosed:add")
    public String purchaseClosedAdd(Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        return FebsUtil.view("purchaseClosed/purchaseClosedAdd");
    }

    //添加页面 点击申请单号跳转到采购申请
    @GetMapping("purchaseClosedQueryList")
    @RequiresPermissions("purchaseClosed:view")
    public String purchaseClosedQueryList(Model model){
        return FebsUtil.view("purchaseClosed/purchaseClosedQueryAdd");
    }

    /*采购收货修改*/
    @GetMapping("purchaseClosed/update/{id}")
    @RequiresPermissions("purchaseClosed:update")
    public String purchaseClosedUpdate(@PathVariable Long id,Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        purchaseClosedModel(id, model, false);
        return FebsUtil.view("purchaseClosed/purchaseClosedUpdate");
    }

    //采购收货修改回填
    private void purchaseClosedModel(Long id, Model model, Boolean transform) {
        PurchaseClosed purchaseClosedPositive = purchaseClosedService.queryPurchaseClosedList(id);
        model.addAttribute("purchaseClosedPositive", purchaseClosedPositive);
        if (purchaseClosedPositive.getPurchaseRequisitionDate() != null) {
            model.addAttribute("purchaseRequisitionDate", DateUtil.getDateFormat(purchaseClosedPositive.getPurchaseRequisitionDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //修改页面 点击编码跳转到采购申请下物料编码选择列表页面
    @GetMapping("purchaseClodeListUpdate")
    @RequiresPermissions("purchaseClosed:view")
    public String purchaseClosedUpdateIndex(Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        return FebsUtil.view("purchaseClosed/purchaseClosedCodeUpdate");
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("purchaseClosedAddName")
    @RequiresPermissions("purchaseSupplier:view")
    public String purchaseClosedAddName(Model model) {

        return FebsUtil.view("purchaseClosed/purchaseSupplierSelectionList");
    }

    /*采购管理模块-采购收货结束*/

    /*采购管理模块-来货检验结束*/

    /*来货检验列表*/
    @GetMapping("purchaseInspection/list")
    @RequiresPermissions("purchaseInspection:view")
    public String purchaseInspectionIndex(){
        return FebsUtil.view("purchaseInspection/purchaseInspectionList");
    }

    /*来货检验添加*/
    @GetMapping("purchaseInspection/add")
    @RequiresPermissions("purchaseInspection:add")
    public String purchaseInspectionIndexAdd(Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        return FebsUtil.view("purchaseInspection/purchaseInspectionAdd");
    }

    //添加页面 点击申请单号跳转到采购申请
    @GetMapping("purchaseInspectionQueryList")
    @RequiresPermissions("purchaseInspection:view")
    public String purchaseInspectionIndexList(Model model){
        return FebsUtil.view("purchaseInspection/purchaseInspectionQueryAdd");
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("purchaseInspectionList")
    @RequiresPermissions("purchaseInspection:view")
    public String purchaseInspectionList(Model model) {

        return FebsUtil.view("purchaseInspection/purchaseInspectionAddList");
    }

    /*来货检验查阅*/
    @GetMapping("purchaseInspection/queryup/{inspectionNumber}")
    @RequiresPermissions("purchaseInspection:view")
    public String purchaseInspectionQueryupList(@PathVariable String inspectionNumber,Model model){
        purchaseClosedModel(inspectionNumber, model, false);
        return FebsUtil.view("purchaseInspection/purchaseInspectionQueryup");
    }

    //来货检验查阅回填
    private void purchaseClosedModel(String inspectionNumber, Model model, Boolean transform) {
        PurchaseInspectionSchedule purchaseInspection = this.purchaseInspectionService.findPurchaseInspectionQueryPage(inspectionNumber);
        model.addAttribute("purchaseInspection", purchaseInspection);
    }

    /*来货检验修改*/
    @GetMapping("purchaseInspection/update/{inspectionNumber}")
    @RequiresPermissions("purchaseInspection:view")
    public String purchaseInspectionUpdate(@PathVariable String inspectionNumber,Model model){
        purchaseInspectionModel(inspectionNumber, model, false);
        return FebsUtil.view("purchaseInspection/purchaseInspectionUpdate");
    }

    //来货检验查阅回填
    private void purchaseInspectionModel(String inspectionNumber, Model model, Boolean transform) {
        PurchaseInspectionSchedule purchaseInspection = this.purchaseInspectionService.findPurchaseInspectionQueryPage(inspectionNumber);
        model.addAttribute("purchaseInspection", purchaseInspection);
    }

    /*采购管理模块-来货检验结束*/

    /*采购管理模块-采购退货开始*/

    /*采购退货列表*/
    @GetMapping("purchaseRefund/list")
    @RequiresPermissions("purchaseRefund:view")
    public String purchaseRefundIndex(){
        return FebsUtil.view("purchaseRefund/purchaseRefundList");
    }

    /*采购退货新增*/
    @GetMapping("purchaseRefund/add")
    @RequiresPermissions("purchaseRefund:add")
    public String purchaseRefundAdd(Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        return FebsUtil.view("purchaseRefund/purchaseRefundAdd");
    }

    /*采购退货修改*/
    @GetMapping("purchaseRefund/update/{id}")
    @RequiresPermissions("purchaseRefund:update")
    public String purchaseRefundUpdate(@PathVariable Long id,Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        purchaseRefundModel(id, model, false);
        return FebsUtil.view("purchaseRefund/purchaseRefundUpdate");
    }

    //采购退货回填
    private void purchaseRefundModel(Long id, Model model, Boolean transform) {
        PurchaseRefund purchaseRefund = this.purchaseRefundService.findPurchaseRefundQueryPage(id);
        model.addAttribute("purchaseRefund", purchaseRefund);
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("purchaseRefundAddName")
    @RequiresPermissions("purchaseSupplier:view")
    public String purchaseRefundAddName(Model model) {

        return FebsUtil.view("purchaseRefund/purchaseSupplierSelectionList");
    }

    /*采购管理模块-采购退货结束*/

    /*采购管理模块-采购结算开始*/

    @GetMapping("purchaseSettlement/list")
    @RequiresPermissions("purchaseSettlement:view")
    public String purchaseSettlementIndex(){
        return FebsUtil.view("purchaseSettlement/purchaseSettlementList");
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("purchaseSettlementName")
    @RequiresPermissions("purchaseSettlement:view")
    public String purchaseSettlementName(Model model) {

        return FebsUtil.view("purchaseSettlement/purchaseSupplierSelectionList");
    }

    /*采购管理模块-采购结算结束*/

    /*采购管理模块-采购发票开始*/

    @GetMapping("purchaseInvoice/list")
    @RequiresPermissions("purchaseInvoice:view")
    public String purchaseInvoiceIndex(){
        return FebsUtil.view("purchaseInvoice/purchaseInvoiceList");
    }

    //供应商 双击跳到供应商选择列表页面  添加时用到
    @GetMapping("dblclickInvoiceSupplierList")
    @RequiresPermissions("purchaseSettlement:view")
    public String dblclickInvoiceSupplierList(Model model) {

        return FebsUtil.view("purchaseInvoice/purchaseSupplierSelectionList");
    }

    /*采购发票查阅*/
    @GetMapping("purchaseInvoice/queryup/{invoiceNumbers}")
    @RequiresPermissions("purchaseInvoice:reading")
    public String purchaseInvoiceQueryupList(@PathVariable String invoiceNumbers,Model model){
        purchaseInvoiceModel(invoiceNumbers, model, false);
        return FebsUtil.view("purchaseInvoice/purchaseInvoiceQueryup");
    }

    //采购发票查阅回填
    private void purchaseInvoiceModel(String invoiceNumbers, Model model, Boolean transform) {
        PurchaseInvoice purchaseInvoice = this.purchaseInvoiceService.findPurchaseInvoiceQueryPage(invoiceNumbers);
        model.addAttribute("purchaseInvoice", purchaseInvoice);
    }

    /*采购发票新增*/
    @GetMapping("purchaseInvoice/add")
    @RequiresPermissions("purchaseInvoice:add")
    public String purchaseInvoiceAdd(Model model){
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        return FebsUtil.view("purchaseInvoice/purchaseInvoiceAdd");
    }

    //采购发票修改
    @GetMapping("purchaseInvoice/update/{invoiceNumbers}")
    @RequiresPermissions("purchaseInvoice:update")
    public String purchaseInvoiceUpdate(@PathVariable String invoiceNumbers, Model model) {
        //查询币种信息
        List<FinanceParameters> currency  = financeParametersService.queryCurrencyInformation(FinanceParameters.CURRENCY);
        model.addAttribute("currency",currency);
        //查询税率信息
        List<FinanceParameters> taxRate  = financeParametersService.queryCurrencyInformation(FinanceParameters.TAX_RATE);
        model.addAttribute("taxRate",taxRate);
        purchaseInvoiceModel(invoiceNumbers, model, false);
        return FebsUtil.view("purchaseInvoice/purchaseInvoiceUpdate");
    }

    /*采购管理模块-采购发票结束*/
}

package com.erp.purchase.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.erp.purchase.entity.*;
import com.erp.purchase.service.*;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyProductCategoryService;
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


    /*采购管理模块-采购业务结束*/
}

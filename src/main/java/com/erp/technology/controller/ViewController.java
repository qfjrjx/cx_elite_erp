package com.erp.technology.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.service.IPurchaseParametersService;
import com.erp.technology.entity.TechnologyBomConfiguration;
import com.erp.technology.entity.TechnologyBomParameter;
import com.erp.technology.entity.TechnologyProduct;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyBomConfigurationService;
import com.erp.technology.service.ITechnologyBomParameterService;
import com.erp.technology.service.ITechnologyProductCategoryService;
import com.erp.technology.service.ITechnologyProductService;
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
@Controller("technologyView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "technology")
@RequiredArgsConstructor
public class ViewController {

    //产品类别表 Service接口
    private final ITechnologyProductCategoryService technologyProductCategoryService;
    //产品表 Service接口
    private final ITechnologyProductService technologyProductService;
    //采购参数表 Service接口
    private final IPurchaseParametersService purchaseParametersService;
    //BOM参数 Service接口
    private final ITechnologyBomParameterService iTechnologyBomParameterService;
    //BOM配置 Service接口
    private final ITechnologyBomConfigurationService technologyBomConfigurationService;

    /* 产品类别模块开始 */
    @GetMapping("technologyProductCategory/list")
    @RequiresPermissions("technologyProductCategory:view")
    public String technologyProductCategoryIndex(){
        return FebsUtil.view("technology/technologyList");
    }
    //产品类别添加
    @GetMapping("technologyProductCategory/add")
    @RequiresPermissions("technologyProductCategory:add")
    public String technologyProductCategoryAdd() {
        return FebsUtil.view("technology/technologyAdd");
    }
    //产品类别修改
    @GetMapping("technologyProductCategory/update/{id}")
    @RequiresPermissions("technologyProductCategory:update")
    public String technologyProductCategoryUpdate(@PathVariable Long id, Model model) {
        //查询产品类别  1-大类
        List<TechnologyProductCategory> productGeneralCategory  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.Product_general_category,TechnologyProductCategory.product_category_state);
        model.addAttribute("productGeneralCategory",productGeneralCategory);
        //回显产品类别
        TechnologyProductCategory technologyProductCategory = technologyProductCategoryService.findTechnologyById(id);
        model.addAttribute("technologyProductCategory", technologyProductCategory);
        String ss = technologyProductCategory.getProductCategory();
        if (ss.equals("1")){
            return FebsUtil.view("technology/technologyUpdate");
        }else {
            return FebsUtil.view("technology/technologyUpdateCategory");
        }
    }
    /* 产品类别模块结束 */

   /*技术管理模块开始*/


    /* 跳转到产品档案模块 */
    @GetMapping("technologyProduct/list")
    @RequiresPermissions("technologyProduct:view")
    public String technologyProductIndex(){
        return FebsUtil.view("productArchives/productArchivesList");
    }

     /*产品档案*/
     @GetMapping("technologyProduct/add")
     @RequiresPermissions("technologyProduct:add")
     public String technologyProductAdd(Model model) {
         //查询产品材质信息
         List<PurchaseParameters> productMaterial  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
         model.addAttribute("productMaterial",productMaterial);
         //查询产品类别  1-大类
         List<TechnologyProductCategory> productGeneralCategory  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.Product_general_category,TechnologyProductCategory.product_category_state);
         model.addAttribute("productGeneralCategory",productGeneralCategory);
         //查询产品类别  2-小类
         List<TechnologyProductCategory> productSubclass  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.product_subclass,TechnologyProductCategory.product_category_state);
         model.addAttribute("productSubclass",productSubclass);
         //查询计量单位信息
         List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
         model.addAttribute("unitOfMeasure",unitOfMeasure);
         return FebsUtil.view("productArchives/productArchivesAdd");
     }

    //产品档案修改
    @GetMapping("technologyProduct/update/{id}")
    @RequiresPermissions("technologyProduct:update")
    public String technologyProductUpdate(@PathVariable Long id, Model model) {
        technologyProductModel(id, model, false);
        //查询产品材质信息
        List<PurchaseParameters> productMaterialOne  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterialOne);
        //查询产品类别  1-大类
        List<TechnologyProductCategory> productGeneralCategory  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.Product_general_category,TechnologyProductCategory.product_category_state);
        model.addAttribute("productGeneralCategory",productGeneralCategory);
        //查询产品类别  2-小类
        List<TechnologyProductCategory> productSubclass  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.product_subclass,TechnologyProductCategory.product_category_state);
        model.addAttribute("productSubclass",productSubclass);
        //查询产品材质信息
        List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
        model.addAttribute("unitOfMeasure",unitOfMeasure);
        return FebsUtil.view("productArchives/productArchivesUpdate");
    }
    //产品档案修改回填
    private void technologyProductModel(Long id, Model model, Boolean transform) {
        TechnologyProduct technologyProduct = technologyProductService.findTechnologyProductById(id);
        model.addAttribute("technologyProduct", technologyProduct);
    }

    //产品档案复制
    @GetMapping("technologyProduct/copy/{id}")
    @RequiresPermissions("technologyProduct:copy")
    public String technologyProductCopy(@PathVariable Long id, Model model) {
        technologyProductCopyModel(id, model, false);
        //查询产品材质信息
        List<PurchaseParameters> productMaterialOne  = purchaseParametersService.queryProductMaterial(PurchaseParameters.product_material,PurchaseParameters.parameters_state);
        model.addAttribute("productMaterial",productMaterialOne);
        //查询产品类别  1-大类
        List<TechnologyProductCategory> productGeneralCategory  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.Product_general_category,TechnologyProductCategory.product_category_state);
        model.addAttribute("productGeneralCategory",productGeneralCategory);
        //查询产品类别  2-小类
        List<TechnologyProductCategory> productSubclass  = technologyProductCategoryService.queryProductGeneralCategory(TechnologyProductCategory.product_subclass,TechnologyProductCategory.product_category_state);
        model.addAttribute("productSubclass",productSubclass);
        //查询产品材质信息
        List<PurchaseParameters> unitOfMeasure  = purchaseParametersService.queryProductMaterial(PurchaseParameters.unit_of_measure,PurchaseParameters.parameters_state);
        model.addAttribute("unitOfMeasure",unitOfMeasure);
        return FebsUtil.view("productArchives/productArchivesCopy");
    }
    //产品档案复制回填
    private void technologyProductCopyModel(Long id, Model model, Boolean transform) {
        TechnologyProduct technologyProduct = technologyProductService.findTechnologyProductById(id);
        model.addAttribute("technologyProduct", technologyProduct);
    }

    //产品档案工序设置
    @GetMapping("technologyProduct/settings/{id}")
    @RequiresPermissions("technologyProduct:settings")
    public String technologyProductSettings(@PathVariable Long id, Model model) {
        technologyProductSettingsModel(id, model, false);
        return FebsUtil.view("productArchives/productArchivesSettings");
    }
    //产品档案工序设置回填
    private void technologyProductSettingsModel(Long id, Model model, Boolean transform) {
        TechnologyProduct technologyProduct = technologyProductService.findTechnologyProductById(id);
        model.addAttribute("technologyProduct", technologyProduct);
    }
    /*技术管理模块结束*/

    /*BOM参数开始*/

    //BOM参数查询
    @GetMapping("technologyBomParameter/list")
    @RequiresPermissions("technologyBomParameter:view")
    public String technologyConfigurationBOM(){
        return FebsUtil.view("technologyBom/technologyBomParameterList");
    }

    //BOM参数添加
    @GetMapping("technologyBomParameter/add")
    @RequiresPermissions("technologyBomParameter:add")
    public String technologyBomParameterAdd() {
        return FebsUtil.view("technologyBom/technologyBomParameterAdd");
    }

    //BOM参数修改
    @GetMapping("technologyBomParameter/update/{id}")
    @RequiresPermissions("technologyBomParameter:update")
    public String productionPlanUpdate(@PathVariable Long id, Model model){
        technologyBomParameterModel(id, model, false);
        return FebsUtil.view("technologyBom/technologyBomParameterUpdate");
    }
    //生产计划修改回填
    private void technologyBomParameterModel(Long id, Model model, Boolean transform){
        TechnologyBomParameter technologyBomParameter = iTechnologyBomParameterService.technologyBomParameterId(id);
        model.addAttribute("technologyBomParameterModel", technologyBomParameter);
    }

    /*BOM参数结束*/

    /*BOM配置开始*/

    //BOM配置查询
    @GetMapping("technologyBomConfiguration/list")
    @RequiresPermissions("technologyBomConfiguration:view")
    public String technologyBomConfiguration(Model model){
        //查询BOM参数
        List<TechnologyBomParameter> parameterName  = iTechnologyBomParameterService.findTechnologyBomParametersList();
        model.addAttribute("parameterName",parameterName);
        return FebsUtil.view("technologyBom/technologyBomConfigurationList");
    }

    //BOM配置新增
    @GetMapping("technologyBomConfiguration/add")
    @RequiresPermissions("technologyBomConfiguration:add")
    public String technologyBomConfigurationAdd(Model model){
        //查询BOM参数
        List<TechnologyBomParameter> parameterName  = iTechnologyBomParameterService.findTechnologyBomParametersList();
        model.addAttribute("parameterName",parameterName);
        return FebsUtil.view("technologyBom/technologyBomConfigurationAdd");
    }

    //点击新增跳转物料编码选择列表页面
    @GetMapping("purchaseRequisitionQueryList")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseMaterialCodeUpdateIndex(Model model){
        return FebsUtil.view("technologyBom/purchaseMaterialCode");
    }

    //点击新增跳转物料编码选择列表页面
    @GetMapping("purchaseRequisitionQuery")
    @RequiresPermissions("purchaseRequisition:view")
    public String purchaseRequisitionQueryIndex(Model model){
        return FebsUtil.view("technologyBom/purchaseRequisitionQuery");
    }

    /*BOM配置修改*/
    @GetMapping("technologyBomConfiguration/update/{id}")
    @RequiresPermissions("technologyBomConfiguration:update")
    public String technologyBomConfigurationUpdate(@PathVariable String id,Model model){
        technologyBomConfigurationModel(id, model, false);
        return FebsUtil.view("technologyBom/technologyBomConfigurationUpdate");
    }

    /*BOM配置查阅*/
    @GetMapping("technologyBomConfiguration/refer/{id}")
    @RequiresPermissions("technologyBomConfiguration:refer")
    public String technologyBomConfigurationRefer(@PathVariable String id,Model model){
        technologyBomConfigurationModel(id, model, false);
        return FebsUtil.view("technologyBom/technologyBomConfigurationRefer");
    }
    //BOM配置查阅
    private void technologyBomConfigurationModel(String id, Model model, Boolean transform) {
        TechnologyBomConfiguration technologyBomConfiguration = this.technologyBomConfigurationService.findTechnologyBomConfigurationModel(id);
        List<TechnologyBomParameter> parameterName  = iTechnologyBomParameterService.findTechnologyBomParametersList();
        model.addAttribute("parameterName",parameterName);
        model.addAttribute("technologyBomConfiguration", technologyBomConfiguration);
    }

    /*BOM配置结束*/

}

package com.erp.technology.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.service.IPurchaseParametersService;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.technology.entity.TechnologyProduct;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyProductCategoryService;
import com.erp.technology.service.ITechnologyProductService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
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
        technologyProductCategoryModel(id, model, false);
        return FebsUtil.view("technology/technologyUpdate");
    }
    //产品类别修改回填
    private void technologyProductCategoryModel(Long id, Model model, Boolean transform) {
        TechnologyProductCategory technologyProductCategory = technologyProductCategoryService.findTechnologyById(id);
        model.addAttribute("technologyProductCategory", technologyProductCategory);
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
         //查询产品材质信息
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
    /*技术管理模块结束*/

}

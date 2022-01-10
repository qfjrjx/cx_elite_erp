package com.erp.purchase.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.entity.PurchaseSupplier;
import com.erp.purchase.service.IPurchaseParametersService;
import com.erp.purchase.service.IPurchaseSupplierService;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /*采购管理模块开始*/
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



    /*采购管理模块结束*/
}

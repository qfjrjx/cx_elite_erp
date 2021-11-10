package com.erp.purchase.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.service.IPurchaseParametersService;
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

    private final IPurchaseParametersService purchaseParametersService;

    /*采购管理模块开始*/
    /* */
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
    /*采购管理模块结束*/
}

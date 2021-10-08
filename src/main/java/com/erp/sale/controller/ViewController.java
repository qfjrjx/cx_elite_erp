package com.erp.sale.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.service.ISaleParametersService;
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
@Controller("saleParametersView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "sale")
@RequiredArgsConstructor
public class ViewController {
    //销售参数Service接口
    private final ISaleParametersService saleParametersService;

    /* 销售参数模块开始 */
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
}

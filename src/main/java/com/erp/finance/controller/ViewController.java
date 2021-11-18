package com.erp.finance.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.service.IFinanceParametersService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiufeng
 */
@Controller("financeView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "finance")
@RequiredArgsConstructor
public class ViewController {

    private final IFinanceParametersService financeParametersService;

    /*财务管理模块开始*/
    /* */
    @GetMapping("financeParameters/list")
    @RequiresPermissions("financeParameters:view")
    public String purchaseParametersIndex(){
        return FebsUtil.view("finance/financialParametersList");
    }

    //财务参数添加
    @GetMapping("financeParameters/add")
    @RequiresPermissions("financeParameters:add")
    public String purchaseParametersAdd() {
        return FebsUtil.view("finance/financialParametersAdd");
    }
    //财务参数修改
    @GetMapping("financeParameters/update/{id}")
    @RequiresPermissions("financeParameters:update")
    public String financeParametersUpdate(@PathVariable Long id, Model model) {
        financeParametersModel(id, model, false);
        return FebsUtil.view("finance/financialParametersUpdate");
    }
    //财务参数修改回填
    private void financeParametersModel(Long id, Model model, Boolean transform) {
        FinanceParameters financeParameters = financeParametersService.financeParametersById(id);
        model.addAttribute("financeParameters", financeParameters);
    }
    //财务管理模块结束
}

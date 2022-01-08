package com.erp.expense.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.expense.entity.ExpenseReporting;
import com.erp.expense.service.IExpenseReportingService;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.service.IFinanceParametersService;
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
@Controller("expensesView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "expenses")
@RequiredArgsConstructor
public class ViewController {

    private final IExpenseReportingService expenseReportingService;
    //财务参数表 Service接口
    private final IFinanceParametersService financeParametersService;


    /*费用报支模块开始*/
    /* */
    @GetMapping("expenseReporting/list")
    @RequiresPermissions("expenseReporting:view")
    public String expenseReportingIndex(Model model){
        //查询费用类型信息
        List<FinanceParameters> expenseReportingCategory  = financeParametersService.queryCurrencyInformation(FinanceParameters.EXPENSE_REPORTING_CATEGORY);
        model.addAttribute("expenseReportingCategory",expenseReportingCategory);
        return FebsUtil.view("expenseReporting/expenseReportingList");
    }

    //费用报支添加
    @GetMapping("expenseReporting/add")
    @RequiresPermissions("expenseReporting:add")
    public String purchaseParametersAdd(Model model) {
        //查询费用类型信息
        List<FinanceParameters> expenseReportingCategory  = financeParametersService.queryCurrencyInformation(FinanceParameters.EXPENSE_REPORTING_CATEGORY);
        model.addAttribute("expenseReportingCategory",expenseReportingCategory);
        return FebsUtil.view("expenseReporting/expenseReportingAdd");
    }
    //费用报支修改
    @GetMapping("expenseReporting/update/{id}")
    @RequiresPermissions("expenseReporting:update")
    public String expenseReportingUpdate(@PathVariable Long id, Model model) {
        expenseReportingModel(id, model, false);
        //查询费用类型信息
        List<FinanceParameters> expenseReportingCategory  = financeParametersService.queryCurrencyInformation(FinanceParameters.EXPENSE_REPORTING_CATEGORY);
        model.addAttribute("expenseReportingCategory",expenseReportingCategory);
        return FebsUtil.view("expenseReporting/expenseReportingUpdate");
    }
    //费用报支修改回填
    private void expenseReportingModel(Long id, Model model, Boolean transform) {
        ExpenseReporting expenseReporting = expenseReportingService.expenseReportingById(id);
        model.addAttribute("expenseReporting", expenseReporting);
        if (expenseReporting.getExpenseReportingDate() != null) {
            model.addAttribute("expenseReportingTime", DateUtil.getDateFormat(expenseReporting.getExpenseReportingDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    //费用报支模块结束
}

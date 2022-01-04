package com.erp.expense.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.expense.entity.ExpenseReporting;
import com.erp.expense.service.IExpenseReportingService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 费用报支表 Controller
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class ExpenseReportingController extends BaseController {

    private final IExpenseReportingService expenseReportingService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "expenseReporting")
    public String expenseReportingIndex(){
        return FebsUtil.view("expenseReporting/expenseReporting");
    }

    @GetMapping("expenseReporting")
    @ResponseBody
    @RequiresPermissions("expenseReporting:list")
    public FebsResponse getAllExpenseReportings(ExpenseReporting expenseReporting) {
        return new FebsResponse().success().data(expenseReportingService.findExpenseReportings(expenseReporting));
    }

    @GetMapping("expenseReporting/list")
    @ResponseBody
    @RequiresPermissions("expenseReporting:view")
    public FebsResponse expenseReportingList(QueryRequest request, ExpenseReporting expenseReporting) {
        Map<String, Object> dataTable = getDataTable(this.expenseReportingService.findExpenseReportings(request, expenseReporting));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ExpenseReporting", exceptionMessage = "新增ExpenseReporting失败")
    @PostMapping("expenseReporting/add")
    @ResponseBody
    @RequiresPermissions("expenseReporting:add")
    public FebsResponse addExpenseReporting(@Valid ExpenseReporting expenseReporting) throws ParseException {
        this.expenseReportingService.createExpenseReporting(expenseReporting);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ExpenseReporting", exceptionMessage = "删除ExpenseReporting失败")
    @GetMapping("expenseReporting/delete")
    @ResponseBody
    @RequiresPermissions("expenseReporting:delete")
    public FebsResponse deleteExpenseReporting(ExpenseReporting expenseReporting) {
        this.expenseReportingService.deleteExpenseReporting(expenseReporting);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ExpenseReporting", exceptionMessage = "修改ExpenseReporting失败")
    @PostMapping("expenseReporting/update")
    @ResponseBody
    @RequiresPermissions("expenseReporting:update")
    public FebsResponse updateExpenseReporting(ExpenseReporting expenseReporting) {
        this.expenseReportingService.updateExpenseReporting(expenseReporting);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ExpenseReporting", exceptionMessage = "导出Excel失败")
    @PostMapping("expenseReporting/excel")
    @ResponseBody
    @RequiresPermissions("expenseReporting:export")
    public void export(QueryRequest queryRequest, ExpenseReporting expenseReporting, HttpServletResponse response) {
        List<ExpenseReporting> expenseReportings = this.expenseReportingService.findExpenseReportings(queryRequest, expenseReporting).getRecords();
        ExcelKit.$Export(ExpenseReporting.class, response).downXlsx(expenseReportings, false);
    }
}

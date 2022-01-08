package com.erp.expense.service;

import com.erp.common.entity.QueryRequest;
import com.erp.expense.entity.ExpenseReporting;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 费用报支表 Service接口
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
public interface IExpenseReportingService extends IService<ExpenseReporting> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param expenseReporting expenseReporting
     * @return IPage<ExpenseReporting>
     */
    IPage<ExpenseReporting> findExpenseReportings(QueryRequest request, ExpenseReporting expenseReporting);

    /**
     * 查询（所有）
     *
     * @param expenseReporting expenseReporting
     * @return List<ExpenseReporting>
     */
    List<ExpenseReporting> findExpenseReportings(ExpenseReporting expenseReporting);

    /**
     * 新增
     *
     * @param expenseReporting expenseReporting
     */
    void createExpenseReporting(ExpenseReporting expenseReporting) throws ParseException;

    /**
     * 修改
     *
     * @param expenseReporting expenseReporting
     */
    void updateExpenseReporting(ExpenseReporting expenseReporting);

    /**
     * 删除
     *
     * @param ids expenseReporting
     */
    void deleteExpenseReporting(String[] ids);

    ExpenseReporting expenseReportingById(Long id);

    void updateExpenseReportingState(Long id, String stateParam);
}

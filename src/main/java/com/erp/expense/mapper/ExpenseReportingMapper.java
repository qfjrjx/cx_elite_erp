package com.erp.expense.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.expense.entity.ExpenseReporting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 费用报支表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
@Mapper
public interface ExpenseReportingMapper extends BaseMapper<ExpenseReporting> {

    long countExpenseReporting(ExpenseReporting expenseReporting);

    IPage<ExpenseReporting> findExpenseReportingPage(Page<ExpenseReporting> page, ExpenseReporting expenseReporting);

    ExpenseReporting queryExpenseReporting();
}

package com.erp.expense.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.expense.entity.ExpenseReporting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 费用报支表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
@Mapper
public interface ExpenseReportingMapper extends BaseMapper<ExpenseReporting> {

    long countExpenseReporting(@Param("expenseReporting") ExpenseReporting expenseReporting);

    IPage<ExpenseReporting> findExpenseReportingPage(Page<ExpenseReporting> page,@Param("expenseReporting") ExpenseReporting expenseReporting);

    ExpenseReporting queryExpenseReporting();

    ExpenseReporting expenseReportingById(@Param("id") Long id);

    void saveOrUpdate(@Param("expenseReporting") ExpenseReporting expenseReporting);

    void updateExpenseReportingState(@Param("id") Long id,@Param("stateParam") String stateParam);
}

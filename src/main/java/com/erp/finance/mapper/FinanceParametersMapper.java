package com.erp.finance.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.finance.entity.FinanceParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 财务参数表 Mapper
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
@Mapper
public interface FinanceParametersMapper extends BaseMapper<FinanceParameters> {

    long countFinanceParameters(@Param("financeParameters") FinanceParameters financeParameters);

    IPage<FinanceParameters> findFinanceParametersPage(Page<FinanceParameters> page,@Param("financeParameters") FinanceParameters financeParameters);

    FinanceParameters financeParametersById(@Param("id") Long id);
}

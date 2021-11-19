package com.erp.finance.service;

import com.erp.common.entity.QueryRequest;
import com.erp.finance.entity.FinanceParameters;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 财务参数表 Service接口
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
public interface IFinanceParametersService extends IService<FinanceParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param financeParameters financeParameters
     * @return IPage<FinanceParameters>
     */
    IPage<FinanceParameters> findFinanceParameterss(QueryRequest request, FinanceParameters financeParameters);

    /**
     * 查询（所有）
     *
     * @param financeParameters financeParameters
     * @return List<FinanceParameters>
     */
    List<FinanceParameters> findFinanceParameterss(FinanceParameters financeParameters);

    /**
     * 新增
     *
     * @param financeParameters financeParameters
     */
    void createFinanceParameters(FinanceParameters financeParameters) throws ParseException;

    /**
     * 修改
     *
     * @param financeParameters financeParameters
     */
    void updateFinanceParameters(FinanceParameters financeParameters);

    /**
     * 删除
     *
     * @param ids financeParameters
     */
    void deleteFinanceParameters(String[] ids);

    FinanceParameters financeParametersById(Long id);

    List<FinanceParameters> queryCurrencyInformation(String parameterCategory);
}

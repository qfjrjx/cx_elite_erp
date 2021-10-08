package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleParameters;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 销售参数表

          数据库表名：                              对应java表名：

          jr_sale_parameters                     SaleParameters Service接口
 *
 * @author qiufeng
 * @date 2021-10-07 10:18:55
 */
public interface ISaleParametersService extends IService<SaleParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleParameters saleParameters
     * @return IPage<SaleParameters>
     */
    IPage<SaleParameters> findSaleParameterss(QueryRequest request, SaleParameters saleParameters);

    /**
     * 查询（所有）
     *
     * @param saleParameters saleParameters
     * @return List<SaleParameters>
     */
    List<SaleParameters> findSaleParameterss(SaleParameters saleParameters);

    /**
     * 新增
     *
     * @param saleParameters saleParameters
     */
    void createSaleParameters(SaleParameters saleParameters);

    /**
     * 修改
     *
     * @param saleParameters saleParameters
     */
    void updateSaleParameters(SaleParameters saleParameters);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deleteSaleParameters(String[] ids);
    //销售参数修改回填
    SaleParameters findSaleParametersById(Long id);
}

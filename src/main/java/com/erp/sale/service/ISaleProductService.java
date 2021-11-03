package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleProduct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.sale.entity.SaleProducts;

import java.util.List;
import java.util.Map;

/**
 * 产品表
             Service接口
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
public interface ISaleProductService extends IService<SaleProduct> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleProduct saleProduct
     * @return IPage<SaleProduct>
     */
    IPage<SaleProduct> findSaleProducts(QueryRequest request, SaleProduct saleProduct);

    /**
     * 查询（所有）
     *
     * @param saleProduct saleProduct
     * @return List<SaleProduct>
     */
    List<SaleProduct> findSaleProducts(SaleProduct saleProduct);

    /**
     * 新增
     *
     * @param saleProduct saleProduct
     */
    void createSaleProduct(SaleProduct saleProduct);

    /**
     * 修改
     *
     * @param saleProduct saleProduct
     */
    void updateSaleProduct(SaleProduct saleProduct);

    /**
     * 删除
     *
     * @param saleProduct saleProduct
     */
    void deleteSaleProduct(SaleProduct saleProduct);

    IPage<SaleProducts> findSaleProduct(QueryRequest request, SaleProducts saleProducts);

    void updateSaleProductState(Long id);

    void updateSaleProductStates(Long id);
}

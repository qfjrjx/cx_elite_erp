package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleApplicationAddStaging;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 销售申请暂存表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-20 17:12:25
 */
public interface ISaleApplicationAddStagingService extends IService<SaleApplicationAddStaging> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleApplicationAddStaging saleApplicationAddStaging
     * @return IPage<SaleApplicationAddStaging>
     */
    IPage<SaleApplicationAddStaging> findSaleApplicationAddStagings(QueryRequest request, SaleApplicationAddStaging saleApplicationAddStaging);

    /**
     * 查询（所有）
     *
     * @param saleApplicationAddStaging saleApplicationAddStaging
     * @return List<SaleApplicationAddStaging>
     */
    List<SaleApplicationAddStaging> findSaleApplicationAddStagings(SaleApplicationAddStaging saleApplicationAddStaging);

    /**
     * 新增
     *
     * @param saleApplicationAddStaging saleApplicationAddStaging
     */
    void createSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging);

    /**
     * 修改
     *
     * @param saleApplicationAddStaging saleApplicationAddStaging
     */
    void updateSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging);

    /**
     * 删除
     *
     * @param saleApplicationAddStaging saleApplicationAddStaging
     */
    void deleteSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging);
}

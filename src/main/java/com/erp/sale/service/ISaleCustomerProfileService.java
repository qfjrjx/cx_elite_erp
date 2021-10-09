package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleCustomerProfile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 客户档案表

             数据库表名：                                             对应java表名：

             jr_sale_customer_profile                            SaleCustomerProfile Service接口
 *
 * @author qiufeng
 * @date 2021-10-08 16:51:04
 */
public interface ISaleCustomerProfileService extends IService<SaleCustomerProfile> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleCustomerProfile saleCustomerProfile
     * @return IPage<SaleCustomerProfile>
     */
    IPage<SaleCustomerProfile> findSaleCustomerProfiles(QueryRequest request, SaleCustomerProfile saleCustomerProfile);

    /**
     * 查询（所有）
     *
     * @param saleCustomerProfile saleCustomerProfile
     * @return List<SaleCustomerProfile>
     */
    List<SaleCustomerProfile> findSaleCustomerProfiles(SaleCustomerProfile saleCustomerProfile);

    /**
     * 新增
     *
     * @param saleCustomerProfile saleCustomerProfile
     */
    void createSaleCustomerProfile(SaleCustomerProfile saleCustomerProfile);

    /**
     * 修改
     *
     * @param saleCustomerProfile saleCustomerProfile
     */
    void updateSaleCustomerProfile(SaleCustomerProfile saleCustomerProfile);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deleteSaleCustomerProfile(String[] ids);

    SaleCustomerProfile findSaleCustomerProfileById(Long id);
}

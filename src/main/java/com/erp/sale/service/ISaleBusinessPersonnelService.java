package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.finance.entity.FinanceParameters;
import com.erp.sale.entity.SaleBusinessPersonnel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 业务人员表

               数据库表名：                                         对应java表名：
    
               jr_sale_business_personnel                    SaleBusinessPersonnel Service接口
 *
 * @author qiufeng
 * @date 2021-10-08 13:34:13
 */
public interface ISaleBusinessPersonnelService extends IService<SaleBusinessPersonnel> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleBusinessPersonnel saleBusinessPersonnel
     * @return IPage<SaleBusinessPersonnel>
     */
    IPage<SaleBusinessPersonnel> findSaleBusinessPersonnels(QueryRequest request, SaleBusinessPersonnel saleBusinessPersonnel);

    /**
     * 查询（所有）
     *
     * @param saleBusinessPersonnel saleBusinessPersonnel
     * @return List<SaleBusinessPersonnel>
     */
    List<SaleBusinessPersonnel> findSaleBusinessPersonnels(SaleBusinessPersonnel saleBusinessPersonnel);

    /**
     * 新增
     *
     * @param saleBusinessPersonnel saleBusinessPersonnel
     */
    void createSaleBusinessPersonnel(SaleBusinessPersonnel saleBusinessPersonnel);

    /**
     * 修改
     *
     * @param saleBusinessPersonnel saleBusinessPersonnel
     */
    void updateSaleBusinessPersonnel(SaleBusinessPersonnel saleBusinessPersonnel);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deleteSaleBusinessPersonnel(String[] ids);
    /**
     * 修改回填
     *
     * @param id id
     */
    SaleBusinessPersonnel findSaleBusinessPersonnelById(Long id);

    List<SaleBusinessPersonnel> queryBusinessPersonnel();
}

package com.erp.production.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionRecipients;

import java.util.List;

/**
 * 生产领用 Service接口
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
public interface IProductionRecipientsService extends IService<ProductionRecipients> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param productionRecipients productionRecipients
     * @return IPage<ProductionRecipients>
     */
    IPage<ProductionRecipients> findProductionRecipientss(QueryRequest request, ProductionRecipients productionRecipients);

    /**
     * 查询（所有）
     *
     * @param productionRecipients productionRecipients
     * @return List<ProductionRecipients>
     */
    List<ProductionRecipients> findProductionRecipientss(ProductionRecipients productionRecipients);

    /**
     * 新增
     *
     * @param productionRecipients productionRecipients
     */
    void createProductionRecipients(ProductionRecipients productionRecipients);

    /**
     * 修改
     *
     * @param productionRecipients productionRecipients
     */
    void updateProductionRecipients(ProductionRecipients productionRecipients);

    /**
     * 删除
     *
     * @param productionRecipients productionRecipients
     */
    void deleteProductionRecipients(ProductionRecipients productionRecipients);
}

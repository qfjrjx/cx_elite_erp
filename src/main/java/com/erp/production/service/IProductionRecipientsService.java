package com.erp.production.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionLack;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.entity.ProductionRecipientsSchedule;

import java.text.ParseException;
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
     * @param recipients recipients
     */
    void createProductionRecipients(String recipients ,String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param recipients recipients
     */
    void updateProductionRecipients(String recipients, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param productionRecipients productionRecipients
     */
    void deleteProductionRecipients(ProductionRecipients productionRecipients);

    List<ProductionRecipientsSchedule> queryProductionRecipients(String recipientsCode);

    ProductionRecipients productionRecipientsId(Long id);

    void cancelProductionRecipients(String ids);

    void confirmProductionRecipients(String ids);

    List<ProductionLack> queryProductionRecipientsLack(String recipientsCode);

    IPage<ProductionLack> findProductionLackPage(QueryRequest request, ProductionLack productionLack);
}

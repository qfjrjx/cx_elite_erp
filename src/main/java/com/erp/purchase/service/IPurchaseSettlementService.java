package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseSettlement;
import com.erp.purchase.entity.PurchaseSettlementSchedule;

import java.util.List;

/**
 * 采购结算 Service接口
 *
 * @author qiufeng
 * @date 2022-04-02 15:02:26
 */
public interface IPurchaseSettlementService extends IService<PurchaseSettlement> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseSettlement purchaseSettlement
     * @return IPage<PurchaseSettlement>
     */
    IPage<PurchaseSettlement> findPurchaseSettlements(QueryRequest request, PurchaseSettlement purchaseSettlement);

    /**
     * 查询（所有）
     *
     * @param purchaseSettlement purchaseSettlement
     * @return List<PurchaseSettlement>
     */
    List<PurchaseSettlement> findPurchaseSettlements(PurchaseSettlement purchaseSettlement);

    /**
     * 新增
     *
     * @param purchaseSettlement purchaseSettlement
     */
    void createPurchaseSettlement(PurchaseSettlement purchaseSettlement);

    /**
     * 修改
     *
     * @param purchaseSettlement purchaseSettlement
     */
    void updatePurchaseSettlement(PurchaseSettlement purchaseSettlement);

    /**
     * 删除
     *
     * @param purchaseSettlement purchaseSettlement
     */
    void deletePurchaseSettlement(PurchaseSettlement purchaseSettlement);

    List<PurchaseSettlementSchedule> queryPurchaseSettlementSchedule(String oddNumbers);

    void settlementPurchaseSettlement(String ids);

    void cancelPurchaseSettlement(String ids);
}

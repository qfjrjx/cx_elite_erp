package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseRefund;
import com.erp.purchase.entity.PurchaseSettlement;
import com.erp.purchase.entity.PurchaseSettlementSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购结算 Mapper
 *
 * @author qiufeng
 * @date 2022-04-02 15:02:26
 */
public interface PurchaseSettlementMapper extends BaseMapper<PurchaseSettlement> {

    long countPurchaseSettlement(PurchaseSettlement purchaseSettlement);

    IPage<PurchaseSettlement> findPurchaseSettlement(Page<PurchaseRefund> page, @Param("purchaseSettlement") PurchaseSettlement purchaseSettlement);

    void savePurchaseSettlementSchedule(PurchaseSettlementSchedule purchaseSettlementSchedule);

    void savePurchaseSettlementDate(PurchaseSettlement purchaseSettlement);

    List<PurchaseSettlementSchedule> queryPurchaseSettlementSchedule(@Param("oddNumbers") String oddNumbers);

    void settlementPurchaseSettlement(String ids);

    void cancelPurchaseSettlement(String ids);
}

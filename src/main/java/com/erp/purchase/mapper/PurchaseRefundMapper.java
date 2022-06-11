package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseRefund;
import com.erp.purchase.entity.PurchaseRefundSchedule;
import com.erp.warehouse.entity.WarehouseStorage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购退货 Mapper
 *
 * @author qiufeng
 * @date 2022-03-28 15:35:47
 */
@Mapper
public interface PurchaseRefundMapper extends BaseMapper<PurchaseRefund> {

    long countPurchaseRefund(@Param("purchaseRefund") PurchaseRefund purchaseRefund);

    IPage<PurchaseRefund> findPurchaseRefundPage(Page<PurchaseRefund> page, @Param("purchaseRefund") PurchaseRefund purchaseRefund);

    List<PurchaseRefundSchedule> queryPurchaseRefundSchedule(@Param("oddNumbers") String oddNumbers);

    IPage<WarehouseStorage> findPurchaseRefundAddQueryPage(Page<WarehouseStorage> page , @Param("warehouseStorage") WarehouseStorage warehouseStorage);

    long countPurchaseRefundAddQuery(@Param("warehouseStorage") WarehouseStorage warehouseStorage);

    PurchaseRefund queryPurchaseRefund();

    void savePurchaseRefundSchedule(PurchaseRefundSchedule purchaseRefundSchedule);

    void savePurchaseRefundDate(PurchaseRefund purchaseRefundDate);

    PurchaseRefund findPurchaseRefundQueryPage(@Param("id") Long id);

    void deletePurchaseRefundSchedule(@Param("refundNumber") String refundNumber);

    void deletePurchaseRefund(@Param("refundNumber") String refundNumber);

    void otuPurchaseRefund(@Param("purchaseRefund") PurchaseRefund purchaseRefund);

    void cancelPurchaseRefund(@Param("ids") String id);

    void deletePurchaseSettlement(@Param("refundNumber") String refundNumber);

    void deletePurchaseSettlementSchedule(@Param("refundNumber") String refundNumber);
}

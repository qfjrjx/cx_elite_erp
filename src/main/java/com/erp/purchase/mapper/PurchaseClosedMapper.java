package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseClosed;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import org.apache.ibatis.annotations.Param;

/**
 * 采购收货表 Mapper
 *
 * @author qiufeng
 * @date 2022-03-19 09:44:35
 */
public interface PurchaseClosedMapper extends BaseMapper<PurchaseClosed> {

    long countPurchaseClosed(@Param("purchaseClosed") PurchaseClosed purchaseClosed);

    IPage<PurchaseClosed> findPurchaseClosedPage(Page<PurchaseClosed> page, @Param("purchaseClosed") PurchaseClosed purchaseClosed);

    PurchaseClosed queryPurchaseClosed();

    void savePurchaseOrderSchedule(PurchaseOrderSchedule purchaseOrderSchedule);

    void savePurchaseClosedDate (PurchaseClosed purchaseClosedDate);

    PurchaseClosed queryPurchaseClosedList(@Param("id") Long id);

    void deletePurchaseOrderSchedule (@Param("orderNumber") String orderNumber);
}

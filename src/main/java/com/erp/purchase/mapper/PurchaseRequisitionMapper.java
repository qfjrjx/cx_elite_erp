package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseRequisition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.purchase.entity.PurchaseRequisitionPositive;
import com.erp.purchase.entity.PurchaseRequisitionSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购申请表 Mapper
 *
 * @author qiufeng
 * @date 2022-01-19 15:29:35
 */
@Mapper
public interface PurchaseRequisitionMapper extends BaseMapper<PurchaseRequisition> {

    long countPurchaseRequisition(@Param("purchaseRequisition") PurchaseRequisition purchaseRequisition);

    IPage<PurchaseRequisition> findPurchaseRequisitionPage(Page<PurchaseRequisition> page, @Param("purchaseRequisition") PurchaseRequisition purchaseRequisition);

    PurchaseRequisition queryPurchaseRequisition();

    List<PurchaseRequisition> queryPurchaseRequisitions(@Param("oddNumbers") String oddNumbers);

    void savePurchaseRequisitionSchedule(PurchaseRequisitionSchedule purchaseRequisitionSchedule);

    void savePurchaseRequisitionPositive(PurchaseRequisitionPositive purchaseRequisitionPositive);

    PurchaseRequisitionPositive findPurchaseRequisitionPositiveById(@Param("id") Long id);

    IPage<PurchaseRequisitionSchedule> queryPurchaseRequisitionsList(Page<PurchaseRequisitionSchedule> page, String oddNumbers);

    void deletePurchaseRequisitionSchedule(@Param("oddNumbers") String oddNumbers);

    IPage<PurchaseRequisition> findOrderPurchaseRequisitionPage(Page<PurchaseRequisition> page, PurchaseRequisition purchaseRequisition);
}

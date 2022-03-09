package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.entity.PurchaseParameters;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购订单表 Mapper
 *
 * @author qiufeng
 * @date 2022-02-20 14:25:03
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {

    long countPurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

    IPage<PurchaseOrder> findPurchaseOrderPage(Page<PurchaseParameters> page,@Param("purchaseOrder") PurchaseOrder purchaseOrder);

    PurchaseOrder queryPurchaseOrder();

    void savePurchaseOrderSchedule(PurchaseOrderSchedule purchaseOrderSchedule);

    void savePurchaseOrderDate(PurchaseOrder purchaseOrderDate);

    List<PurchaseOrderSchedule> queryPurchaseOrderSchedule(@Param("oddNumbers") String oddNumbers);
}

package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseRequisition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 采购申请表 Mapper
 *
 * @author qiufeng
 * @date 2022-01-19 15:29:35
 */
@Mapper
public interface PurchaseRequisitionMapper extends BaseMapper<PurchaseRequisition> {

    long countPurchaseRequisition(@Param("purchaseRequisition") PurchaseRequisition purchaseRequisition);

    IPage<PurchaseRequisition> findPurchaseRequisitionPage(Page<PurchaseRequisition> page,@Param("purchaseRequisition") PurchaseRequisition purchaseRequisition);
}

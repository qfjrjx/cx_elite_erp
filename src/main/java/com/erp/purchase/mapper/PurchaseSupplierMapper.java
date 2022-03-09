package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供货单位表 Mapper
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:05
 */
@Mapper
public interface PurchaseSupplierMapper extends BaseMapper<PurchaseSupplier> {

    long countPurchaseSuppliers(@Param("purchaseSupplier") PurchaseSupplier purchaseSupplier);

    IPage<PurchaseSupplier> findPurchaseSuppliersPage(Page<PurchaseSupplier> page,@Param("purchaseSupplier") PurchaseSupplier purchaseSupplier);

    void savePurchaseSupplier(@Param("purchaseSupplier") PurchaseSupplier purchaseSupplier);

    PurchaseSupplier queryPurchaseSupplier();

    PurchaseSupplier findPurchaseSupplierById(@Param("id") Long id);

    List<PurchaseSupplier> queryPurchaseSupplierList();
}

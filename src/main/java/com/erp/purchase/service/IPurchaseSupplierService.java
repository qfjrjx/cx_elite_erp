package com.erp.purchase.service;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseSupplier;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 供货单位表 Service接口
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:05
 */
public interface IPurchaseSupplierService extends IService<PurchaseSupplier> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseSupplier purchaseSupplier
     * @return IPage<PurchaseSupplier>
     */
    IPage<PurchaseSupplier> findPurchaseSuppliers(QueryRequest request, PurchaseSupplier purchaseSupplier);

    /**
     * 查询（所有）
     *
     * @param purchaseSupplier purchaseSupplier
     * @return List<PurchaseSupplier>
     */
    List<PurchaseSupplier> findPurchaseSuppliers(PurchaseSupplier purchaseSupplier);

    /**
     * 新增
     *
     * @param purchaseSupplier purchaseSupplier
     */
    void createPurchaseSupplier(PurchaseSupplier purchaseSupplier) throws ParseException;

    /**
     * 修改
     *
     * @param purchaseSupplier purchaseSupplier
     */
    void updatePurchaseSupplier(PurchaseSupplier purchaseSupplier);

    /**
     * 删除
     *
     * @param ids purchaseSupplier
     */
    void deletePurchaseSupplier(String[] ids);

    PurchaseSupplier findPurchaseSupplierById(Long id);
}

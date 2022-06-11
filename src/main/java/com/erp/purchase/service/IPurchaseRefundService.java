package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseRefund;
import com.erp.purchase.entity.PurchaseRefundSchedule;
import com.erp.warehouse.entity.WarehouseStorage;

import java.text.ParseException;
import java.util.List;

/**
 * 采购退货 Service接口
 *
 * @author qiufeng
 * @date 2022-03-28 15:35:47
 */
public interface IPurchaseRefundService extends IService<PurchaseRefund> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseRefund purchaseRefund
     * @return IPage<PurchaseRefund>
     */
    IPage<PurchaseRefund> findPurchaseRefunds(QueryRequest request, PurchaseRefund purchaseRefund);

    /**
     * 查询（所有）
     *
     * @param purchaseRefund purchaseRefund
     * @return List<PurchaseRefund>
     */
    List<PurchaseRefund> findPurchaseRefunds(PurchaseRefund purchaseRefund);

    /**
     * 新增
     *
     * @param purchaseRefund purchaseRefund
     */
    void createPurchaseRefund(String purchaseRefund, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param refundNumber purchaseRefund
     */
    void updatePurchaseRefund(String refundNumber, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param refundNumber purchaseRefund
     */
    void deletePurchaseRefund(String refundNumber);

    List<PurchaseRefundSchedule> queryPurchaseRefundSchedule(String oddNumbers);

    IPage<WarehouseStorage> findPurchaseRefundAddQueryPage(QueryRequest request, WarehouseStorage warehouseStorage);

    PurchaseRefund findPurchaseRefundQueryPage(Long id);

    void otuPurchaseRefund(String ids) throws ParseException;

    void cancelPurchaseRefund(String ids);
}

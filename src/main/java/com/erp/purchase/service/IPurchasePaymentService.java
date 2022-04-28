package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchasePayment;
import com.erp.purchase.entity.PurchasePaymentSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 采购付款 Service接口
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:21
 */
public interface IPurchasePaymentService extends IService<PurchasePayment> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchasePayment purchasePayment
     * @return IPage<PurchasePayment>
     */
    IPage<PurchasePayment> findPurchasePayments(QueryRequest request, PurchasePayment purchasePayment);

    /**
     * 查询（所有）
     *
     * @param purchasePayment purchasePayment
     * @return List<PurchasePayment>
     */
    List<PurchasePayment> findPurchasePayments(PurchasePayment purchasePayment);

    /**
     * 新增
     *
     * @param purchasePayment purchasePayment
     */
    void createPurchasePayment(String purchasePayment, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param paymentNumber dataTable
     */
    void updatePurchasePayment(String paymentNumber ,String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param paymentNumber purchasePayment
     */
    void deletePurchasePayment(String paymentNumber);

    PurchasePayment findPurchasePaymentQueryPage(String id);

    List<PurchasePaymentSchedule> queryPurchasePaymentSchedule(String paymentNumber);

    void confirmPurchasePayment(String ids);

    void cancelPurchasePayment(String ids);

    void paymentPurchasePayment(String ids) throws ParseException ;

    IPage<PurchasePaymentSchedule> purchasePriceChangesQuery(QueryRequest request, PurchasePaymentSchedule purchasePaymentSchedule);
}

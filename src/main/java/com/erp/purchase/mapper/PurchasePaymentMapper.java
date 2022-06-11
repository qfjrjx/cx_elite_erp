package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseInvoice;
import com.erp.purchase.entity.PurchasePayment;
import com.erp.purchase.entity.PurchasePaymentSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购付款 Mapper
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:21
 */
public interface PurchasePaymentMapper extends BaseMapper<PurchasePayment> {

    long countPurchasePayment(@Param("purchasePayment") PurchasePayment purchasePayment);

    IPage<PurchasePayment> findPurchasePaymentPage(Page<PurchasePayment> page,@Param("purchasePayment") PurchasePayment purchasePayment);

    void savePurchasePaymentSchedule(PurchasePaymentSchedule purchasePaymentSchedule);

    void savePurchasePaymentData(PurchasePayment purchasePaymentDate);

    PurchasePayment queryPurchasePayment();

    PurchasePayment findPurchasePaymentQueryPage(@Param("id") String id);

    List<PurchasePaymentSchedule> queryPurchasePaymentSchedule(@Param("paymentNumber") String paymentNumber);

    void deletePurchasePaymentSchedule(@Param("paymentNumber") String paymentNumber);

    void deletePurchasePayment(@Param("paymentNumber") String paymentNumber);

    void confirmPurchasePayment(@Param("ids") String ids);

    void cancelPurchasePayment(@Param("ids") String ids);

    void paymentPurchasePayment(@Param("ids") String ids, @Param("purchasePayment") PurchasePayment purchasePayment);

    long countPurchasePaymentSchedule(PurchasePaymentSchedule purchasePaymentSchedule);

    IPage<PurchasePaymentSchedule> findPurchasePaymentSchedulePage(Page<PurchasePaymentSchedule> page,@Param("purchasePayment") PurchasePaymentSchedule purchasePaymentSchedule);

    void updatePaymentState(@Param("purchaseInvoice") PurchaseInvoice purchaseInvoice);

    void updateInvoiceNumbers(@Param("invoiceNumbers") String invoiceNumbers);
}

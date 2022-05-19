package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseInvoice;
import com.erp.purchase.entity.PurchaseInvoiceSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购发票 Mapper
 *
 * @author qiufeng
 * @date 2022-04-06 09:34:31
 */
public interface PurchaseInvoiceMapper extends BaseMapper<PurchaseInvoice> {

    long countPurchaseInvoice(@Param("purchaseInvoice")PurchaseInvoice purchaseInvoice);

    IPage<PurchaseInvoice> findPurchaseInvoicePage(Page<PurchaseInvoice> page,@Param("purchaseInvoice") PurchaseInvoice purchaseInvoice);

    PurchaseInvoice findPurchaseInvoiceQueryPage(@Param("invoiceNumbers") String invoiceNumbers);

    List<PurchaseInvoiceSchedule> queryPurchaseInvoiceSchedule(@Param("invoiceNumbers") String invoiceNumbers);

    void savePurchaseInvoiceSchedule(PurchaseInvoiceSchedule purchaseInvoiceSchedule);

    void savePurchaseInvoiceData(PurchaseInvoice purchaseInvoiceData);

    void deletePurchaseInvoiceSchedule(@Param("invoiceNumbers") String invoiceNumbers);

    void deletePurchaseInvoice(@Param("invoiceNumbers") String invoiceNumbers);

    long countPurchasePaymentAddQuery(@Param("purchaseInvoiceSchedule") PurchaseInvoiceSchedule purchaseInvoiceSchedule);

    IPage<PurchaseInvoiceSchedule> purchasePaymentAddQuery(Page<PurchaseInvoiceSchedule> page,@Param("purchaseInvoiceSchedule") PurchaseInvoiceSchedule purchaseInvoiceSchedule);

    void savePurchaseSettlementUpdate(@Param("invoiceCode") String invoiceCode);

    void confirmPurchaseInspection(@Param("ids") String ids);

    void cancelPurchaseInspection(@Param("ids") String ids);
}

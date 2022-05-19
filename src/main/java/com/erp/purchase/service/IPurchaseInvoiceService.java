package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseInvoice;
import com.erp.purchase.entity.PurchaseInvoiceSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 采购发票 Service接口
 *
 * @author qiufeng
 * @date 2022-04-06 09:34:31
 */
public interface IPurchaseInvoiceService extends IService<PurchaseInvoice> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseInvoice purchaseInvoice
     * @return IPage<PurchaseInvoice>
     */
    IPage<PurchaseInvoice> findPurchaseInvoices(QueryRequest request, PurchaseInvoice purchaseInvoice);

    /**
     * 查询（所有）
     *
     * @param purchaseInvoice purchaseInvoice
     * @return List<PurchaseInvoice>
     */
    List<PurchaseInvoice> findPurchaseInvoices(PurchaseInvoice purchaseInvoice);

    /**
     * 新增
     *
     * @param purchaseInvoice purchaseInvoice
     */
    void createPurchaseInvoice(String purchaseInvoice, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param invoiceNumbers purchaseInvoice
     */
    void updatePurchaseInvoice(String invoiceNumbers, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param invoiceNumbers purchaseInvoice
     */
    void deletePurchaseInvoice(String invoiceNumbers);

    PurchaseInvoice findPurchaseInvoiceQueryPage(String invoiceNumbers);

    List<PurchaseInvoiceSchedule> queryPurchaseInvoiceSchedule(String invoiceNumbers);

    IPage<PurchaseInvoiceSchedule> purchasePaymentAddQuery(QueryRequest request, PurchaseInvoiceSchedule purchaseInvoiceSchedule);

    void confirmPurchaseInspection(String ids);

    void cancelPurchaseInspection(String ids);
}

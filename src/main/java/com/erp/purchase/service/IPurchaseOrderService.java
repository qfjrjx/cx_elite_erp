package com.erp.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseOrder;
import com.erp.purchase.entity.PurchaseOrderSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 采购订单表 Service接口
 *
 * @author qiufeng
 * @date 2022-02-20 14:25:03
 */
public interface IPurchaseOrderService extends IService<PurchaseOrder> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseOrder purchaseOrder
     * @return IPage<PurchaseOrder>
     */
    IPage<PurchaseOrder> findPurchaseOrders(QueryRequest request, PurchaseOrder purchaseOrder);

    /**
     * 查询（所有）
     *
     * @param purchaseOrder purchaseOrder
     * @return List<PurchaseOrder>
     */
    List<PurchaseOrder> findPurchaseOrders(PurchaseOrder purchaseOrder);

    /**
     * 新增
     *
     * @param purchaseOrder purchaseOrder, dataTable
     */
    void createPurchaseOrder(String purchaseOrder, String dataTable) throws ParseException;
    /**
     * 修改
     *
     * @param orderNumber purchaseOrder
     */
    void updatePurchaseOrder(String orderNumber, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param
     */
    void deletePurchaseOrder(String[] ids);

    List<PurchaseOrderSchedule> queryPurchaseOrderSchedule(String oddNumbers);

    PurchaseOrder queryPurchaseOrderList(Long id);

    void confirmPurchaseOrder(String ids);

    void cancelPurchaseOrder(String ids);

    IPage<PurchaseOrder> findPurchaseClosedQueryPage(QueryRequest request, PurchaseOrder purchaseOrder);

    void deletePurchaseRequisitionOrderNumber(String orderNumber);

    IPage<PurchaseOrder> queryPurchaseInspectionOrder(QueryRequest request, String supplierName);

    IPage<PurchaseOrder> findPurchasePriceChanges(QueryRequest request, PurchaseOrder purchaseOrder);
}

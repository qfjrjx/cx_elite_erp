package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleOrder;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 销售订单表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
public interface ISaleOrderService extends IService<SaleOrder> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleOrder saleOrder
     * @return IPage<SaleOrder>
     */
    IPage<SaleOrder> findSaleOrders(QueryRequest request, SaleOrder saleOrder);

    /**
     * 查询（所有）
     *
     * @param saleOrder saleOrder
     * @return List<SaleOrder>
     */
    List<SaleOrder> findSaleOrders(SaleOrder saleOrder);

    /**
     * 新增
     *
     * @param
     */
    void createSaleOrder(String orderDate, String customerName, String salesmanName, String currencyName, String taxRate, String paymentMethod, String depositMoney, String invoiceNot, String contactsName, String mobilePhone, String orderType, String afterSalesClerk, String dataTable, String contImg) throws ParseException;

    /**
     * 修改
     *
     * @param saleOrder saleOrder
     */
    void updateSaleOrder(SaleOrder saleOrder);

    /**
     * 删除
     *
     * @param saleOrder saleOrder
     */
    void deleteSaleOrder(SaleOrder saleOrder);

    SaleOrder findSaleOrderConfigureViewById(Long id);
}

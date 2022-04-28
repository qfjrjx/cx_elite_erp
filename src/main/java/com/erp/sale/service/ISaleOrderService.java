package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.entity.SaleOrderAll;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.sale.entity.SaleOrderSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 销售订单表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
public interface ISaleOrderService extends IService<SaleOrderAll> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleOrder saleOrder
     * @return IPage<SaleOrder>
     */
    IPage<SaleOrderAll> findSaleOrders(QueryRequest request, SaleOrderAll saleOrder);

    /**
     * 查询（所有）
     *
     * @param saleOrder saleOrder
     * @return List<SaleOrder>
     */
    List<SaleOrderAll> findSaleOrders(SaleOrderAll saleOrder);

    /**
     * 新增
     *
     * @param
     */
    void createSaleOrder(String saleOrderData, String dataTable, String contImg) throws ParseException;
    /**
     * 修改
     *
     * @param saleOrder saleOrder
     */
    void updateSaleOrder(String saleApplicationData, String dataTable, String contImg);
    /**
     * 删除
     *
     * @param saleOrder saleOrder
     */
    void deleteSaleOrder(SaleOrderAll saleOrder);

    SaleOrderSchedule findSaleOrderConfigureViewById(Long id);


    SaleOrder findSaleOrderById(Long id);

    List<SaleOrderSchedule> saleOrderSchedulesList(String oddNumbersTwo);

}

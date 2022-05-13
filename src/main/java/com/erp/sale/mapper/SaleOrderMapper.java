package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.entity.SaleOrderAll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.sale.entity.SaleOrderSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售订单表 Mapper
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrderAll> {

    long countSaleOrder(@Param("saleOrder") SaleOrderAll saleOrder);

    IPage<SaleOrderAll> findSaleOrderPage(Page<SaleOrderAll> page, @Param("saleOrder") SaleOrderAll saleOrder);

    SaleOrder querySaleOrder();
    //添加到数据库主表
    void addSaleOrder(SaleOrder saleOrder);

    //添加到数据库附表
    void addSaleOrderSchedule(SaleOrderSchedule saleOrderSchedule);

    SaleOrderSchedule findSaleOrderConfigureViewById(@Param("id") Long id);

    SaleOrder findSaleOrderById(@Param("id") Long id);

    List<SaleOrderSchedule> saleOrderSchedulesList(@Param("oddNumbersTwo") String oddNumbersTwo);

    void deleteSaleOrderSchedule(@Param("oddNumbers") String oddNumbers);

    void updateSaleOrder(SaleOrder saleOrder);
}

package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.sale.entity.SaleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售订单表 Mapper
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

    long countSaleOrder(@Param("saleOrder") SaleOrder saleOrder);

    IPage<SaleOrder> findSaleOrderPage(Page<SaleOrder> page,@Param("saleOrder") SaleOrder saleOrder);

    SaleOrder querySaleOrder();

    void addSaleOrder(SaleOrder saleOrder);
}

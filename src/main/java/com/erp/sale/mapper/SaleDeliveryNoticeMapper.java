package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.sale.entity.SaleDeliveryNotice;
import com.erp.sale.entity.SaleDeliveryNoticeAll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.sale.entity.SaleDeliveryNoticeSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发货通知 Mapper
 *
 * @author qiufeng
 * @date 2022-05-13 10:13:33
 */
public interface SaleDeliveryNoticeMapper extends BaseMapper<SaleDeliveryNoticeAll> {

    long countSaleDeliveryNotice(@Param("saleDeliveryNotice") SaleDeliveryNoticeAll saleDeliveryNotice);

    IPage<SaleDeliveryNoticeAll> findSaleDeliveryNoticePage(Page<SaleDeliveryNoticeAll> page,@Param("saleDeliveryNotice") SaleDeliveryNoticeAll saleDeliveryNotice);

    List<SaleDeliveryNoticeAll> saleDeliveryNoticeList(@Param("customerData") Long customerData);

    SaleDeliveryNotice querySaleDeliveryNotice();
    //循环添加发货通知附表
    void addSaleDeliveryNoticeSchedule(SaleDeliveryNoticeSchedule saleDeliveryNoticeSchedule);
    //添加发货通知主表
    void addSaleDeliveryNotice(SaleDeliveryNotice saleDeliveryNotice);
}

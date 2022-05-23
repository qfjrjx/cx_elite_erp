package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleDeliveryNoticeAll;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 发货通知 Service接口
 *
 * @author qiufeng
 * @date 2022-05-13 10:13:33
 */
public interface ISaleDeliveryNoticeService extends IService<SaleDeliveryNoticeAll> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleDeliveryNotice saleDeliveryNotice
     * @return IPage<SaleDeliveryNotice>
     */
    IPage<SaleDeliveryNoticeAll> findSaleDeliveryNotices(QueryRequest request, SaleDeliveryNoticeAll saleDeliveryNotice);

    /**
     * 查询（所有）
     *
     * @param saleDeliveryNotice saleDeliveryNotice
     * @return List<SaleDeliveryNotice>
     */
    List<SaleDeliveryNoticeAll> findSaleDeliveryNotices(SaleDeliveryNoticeAll saleDeliveryNotice);

    /**
     * 新增
     *
     * @param saleDeliveryNoticeData dataTable
     */
    void createSaleDeliveryNotice(String saleDeliveryNoticeData, String dataTable) throws ParseException;
    /**
     * 修改
     *
     * @param saleDeliveryNotice saleDeliveryNotice
     */
    void updateSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice);

    /**
     * 删除
     *
     * @param saleDeliveryNotice saleDeliveryNotice
     */
    void deleteSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice);
    /**
    * 根据选择客户查询出可以发货的数据
    */
    List<SaleDeliveryNoticeAll> saleDeliveryNoticeList(Long customerData);

}

package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleApplication;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.sale.entity.SaleApplicationReply;

import java.text.ParseException;
import java.util.List;

/**
 * 销售申请表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
public interface ISaleApplicationService extends IService<SaleApplication> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleApplication saleApplication
     * @return IPage<SaleApplication>
     */
    IPage<SaleApplication> findSaleApplications(QueryRequest request, SaleApplication saleApplication);

    /**
     * 查询（所有）
     *
     * @param saleApplication saleApplication
     * @return List<SaleApplication>
     */
    List<SaleApplication> findSaleApplications(SaleApplication saleApplication);

    /**
     * 新增
     *
     * @param saleApplication saleApplication
     */
    void createSaleApplication(SaleApplication saleApplication);

    /**
     * 修改
     *
     * @param saleApplication saleApplication
     */
    void updateSaleApplication(SaleApplication saleApplication);

    /**
     * 删除
     *
     * @param saleApplication saleApplication
     */
    void deleteSaleApplication(SaleApplication saleApplication);

    void addSaleApplication(String requestedDeliveryDate, String customerName, String salesmanName, String dataTable,String contImg) throws ParseException;

    int quantityNameStatistics();

    SaleApplication findSaleApplicationConfigureViewById(Long id);

    SaleApplication findSaleApplicationById(Long id);

    IPage<SaleApplication> saleApplicationsList(QueryRequest request, String applicationNoTwo);

    SaleApplicationReply findSaleApplicationDesignViewById(Long id,String replyDepartment);

    void designReplySaleApplication(String designReplyParam,String userName) throws ParseException;

    void saleApplicationPurchaseReply(String purchaseReplyParam, String userName) throws ParseException;

    void saleApplicationProductionReply(String productionReplyParam, String userName) throws ParseException;

    void saleApplicationAssemblingReply(String assemblingReplyParam, String userName) throws ParseException;


}

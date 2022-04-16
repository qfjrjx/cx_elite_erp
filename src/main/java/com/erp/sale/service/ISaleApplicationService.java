package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleApplication;
import com.erp.sale.entity.SaleApplicationAll;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.sale.entity.SaleApplicationReply;
import com.erp.sale.entity.SaleApplicationSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 销售申请表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
public interface ISaleApplicationService extends IService<SaleApplicationAll> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleApplicationAll saleApplication
     * @return IPage<SaleApplication>
     */
    IPage<SaleApplicationAll> findSaleApplications(QueryRequest request, SaleApplicationAll saleApplicationAll);

    /**
     * 查询（所有）
     *
     * @param saleApplicationAll saleApplication
     * @return List<SaleApplication>
     */
    List<SaleApplicationAll> findSaleApplications(SaleApplicationAll saleApplicationAll);

    /**
     * 新增
     *
     * @param saleApplicationAll saleApplication
     */
    void createSaleApplication(SaleApplicationAll saleApplicationAll);

    /**
     * 修改
     *
     * @param saleApplicationData saleApplicationData
     */
    void updateSaleApplication(String saleApplicationData, String dataTable, String contImg) throws ParseException;
    /**
     * 删除
     *
     * @param saleApplicationAll saleApplication
     */
    void deleteSaleApplication(SaleApplicationAll saleApplicationAll);


    int quantityNameStatistics();


    SaleApplication findSaleApplicationById(Long id);

    void addSaleApplication(String saleApplication, String dataTable, String contImg) throws ParseException;

    SaleApplicationReply findSaleApplicationDesignViewById(Long id,String replyDepartment);

    void designReplySaleApplication(String designReplyParam,String userName) throws ParseException;

    void saleApplicationPurchaseReply(String purchaseReplyParam, String userName) throws ParseException;

    void saleApplicationProductionReply(String productionReplyParam, String userName) throws ParseException;

    void saleApplicationAssemblingReply(String assemblingReplyParam, String userName) throws ParseException;
    //修改回显
    List<SaleApplicationAll> saleApplicationsList(String applicationNoTwo);

    //销售申请页面查看配置信息
    SaleApplicationSchedule findSaleApplicationConfigureViewById(Long id);

}

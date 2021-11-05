package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.sale.entity.SaleApplicationReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售申请表 Mapper
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
@Mapper
public interface SaleApplicationMapper extends BaseMapper<SaleApplication> {

    long countSaleApplication(@Param("saleApplication") SaleApplication saleApplication);

    IPage<SaleApplication> findSaleApplicationPage(Page<SaleApplication> page,@Param("saleApplication") SaleApplication saleApplication);

    void addSaleApplication(SaleApplication saleApplication);

    int quantityNameStatistics();

    SaleApplication findSaleApplicationConfigureViewById(@Param("id") Long id);

    SaleApplication findSaleApplicationById(@Param("id") Long id);

    long countSaleApplicationsList(@Param("applicationNoTwo") String applicationNoTwo);

    IPage<SaleApplication> findSaleApplicationsPage(Page<SaleApplication> page,@Param("applicationNoTwo") String applicationNoTwo);

    void addSaleApplicationReply(SaleApplicationReply saleApplicationReply);

    SaleApplicationReply findSaleApplicationDesignViewById(@Param("id") Long id,@Param("replyDepartment") String replyDepartment);

    void updateSaleApplicationReply(SaleApplicationReply saleApplicationReply);

    int findSaleApplicationDesignById(@Param("id") Long id,@Param("replyDepartment") String replyDepartment);
    //根据得到的参数修改数据库表 设计回复
    void designReplySaleApplication(@Param("id") String id,@Param("designDate") String designDate,@Param("designReply") String designReply);
    //根据得到的参数修改数据库表 采购回复
    void saleApplicationPurchaseReply(@Param("id") String id,@Param("purchaseDate") String purchaseDate,@Param("purchaseReply") String purchaseReply);
    //根据得到的参数修改数据库表 生产回复
    void saleApplicationProductionReply(@Param("id")String id,@Param("productionDate") String productionDate,@Param("productionReply") String productionReply);
    //根据得到的参数修改数据库表 装配回复
    void saleApplicationAssemblingReply(@Param("id")String id,@Param("assemblingDate") String assemblingDate,@Param("assemblingReply") String assemblingReply);


}

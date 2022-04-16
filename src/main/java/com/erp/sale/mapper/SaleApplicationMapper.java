package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.sale.entity.SaleApplication;
import com.erp.sale.entity.SaleApplicationAll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.sale.entity.SaleApplicationReply;
import com.erp.sale.entity.SaleApplicationSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售申请表 Mapper
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
@Mapper
public interface SaleApplicationMapper extends BaseMapper<SaleApplicationAll> {

    long countSaleApplication(@Param("saleApplicationAll") SaleApplicationAll saleApplicationAll);

    IPage<SaleApplicationAll> findSaleApplicationPage(Page<SaleApplicationAll> page, @Param("saleApplicationAll") SaleApplicationAll saleApplicationAll);

    void addSaleApplicationSchedule(SaleApplicationSchedule saleApplicationSchedule);

    void addSaleApplication(SaleApplication saleApplicationDate);

    int quantityNameStatistics();

    SaleApplicationSchedule findSaleApplicationConfigureViewById(@Param("id") Long id);

    SaleApplication findSaleApplicationById(@Param("id") Long id);

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
    //修改回显
    List<SaleApplicationAll> saleApplicationsList(@Param("applicationNoTwo") String applicationNoTwo);
    //查询出最后一个销售申请单号
    SaleApplication querySaleApplication();
    //修改之前删除附表数据
    void deleteSaleApplication(@Param("applicationNo") String applicationNo);
    //删除之前的数据之后，点击修改添加新的数据
    void saveSaleApplicationSchedule(SaleApplicationSchedule saleApplicationSchedule);
    //修改销售申请数据库主表
    void updateSaleApplication(SaleApplication saleApplication);
}

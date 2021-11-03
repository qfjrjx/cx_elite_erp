package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    SaleApplication findSaleApplicationById(@Param("applicationNo") String applicationNo);

    long countSaleApplicationsList(@Param("applicationNoTwo") String applicationNoTwo);

    IPage<SaleApplication> findSaleApplicationsPage(Page<SaleApplication> page,@Param("applicationNoTwo") String applicationNoTwo);
}

package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleCustomerProfile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 客户档案表

             数据库表名：                                             对应java表名：

             jr_sale_customer_profile                            SaleCustomerProfile Mapper
 *
 * @author qiufeng
 * @date 2021-10-08 16:51:04
 */
@Mapper
public interface SaleCustomerProfileMapper extends BaseMapper<SaleCustomerProfile> {

    long countSaleCustomerProfiles(@Param("saleCustomerProfile") SaleCustomerProfile saleCustomerProfile);

    IPage<SaleCustomerProfile> findSaleCustomerProfilesPage(Page<PersonnelDormitory> page,@Param("saleCustomerProfile") SaleCustomerProfile saleCustomerProfile);

    SaleCustomerProfile querySaleCustomerProfile();

    SaleCustomerProfile findSaleCustomerProfileById(@Param("id") Long id);

    void saveOrUpdate(@Param("saleCustomerProfile")SaleCustomerProfile saleCustomerProfile);
}

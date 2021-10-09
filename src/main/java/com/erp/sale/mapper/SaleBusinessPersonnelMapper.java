package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务人员表

               数据库表名：                                         对应java表名：
    
               jr_sale_business_personnel                    SaleBusinessPersonnel Mapper
 *
 * @author qiufeng
 * @date 2021-10-08 13:34:13
 */
@Mapper
public interface SaleBusinessPersonnelMapper extends BaseMapper<SaleBusinessPersonnel> {

    long countSaleBusinessPersonnels(@Param("saleBusinessPersonnel") SaleBusinessPersonnel saleBusinessPersonnel);

    IPage<SaleBusinessPersonnel> findSaleBusinessPersonnelsPage(Page<PersonnelDormitory> page,@Param("saleBusinessPersonnel") SaleBusinessPersonnel saleBusinessPersonnel);

    SaleBusinessPersonnel findSaleBusinessPersonnelById(@Param("id") Long id);

    void saveOrUpdate(@Param("saleBusinessPersonnel") SaleBusinessPersonnel saleBusinessPersonnel);

    List<SaleBusinessPersonnel> queryBusinessPersonnel();
}

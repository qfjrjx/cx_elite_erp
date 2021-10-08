package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.entity.SaleParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售参数表

          数据库表名：                              对应java表名：

          jr_sale_parameters                     SaleParameters Mapper
 *
 * @author qiufeng
 * @date 2021-10-07 10:18:55
 */
@Mapper
public interface SaleParametersMapper extends BaseMapper<SaleParameters> {

    long countSaleParameterss(@Param("saleParameters") SaleParameters saleParameters);

    IPage<SaleParameters> findSaleParameterssPage(@Param("saleParameters") Page<PersonnelDormitory> page, SaleParameters saleParameters);
    //销售参数修改回填
    SaleParameters findSaleParametersById(@Param("id") Long id);

    List<SaleBusinessPersonnel> querySaleBusinessPersonnel();
}

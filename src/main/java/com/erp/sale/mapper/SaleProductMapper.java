package com.erp.sale.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.sale.entity.SaleProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.sale.entity.SaleProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品表
             Mapper
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
@Mapper
public interface SaleProductMapper extends BaseMapper<SaleProduct> {

    long countSaleProducts(@Param("saleProduct") SaleProduct saleProduct);

    IPage<SaleProduct> findSaleProductsPage(Page<SaleProduct> page,@Param("saleProduct") SaleProduct saleProduct);

    long countSaleProduct(SaleProducts saleProducts);

    IPage<SaleProducts> findSaleProductsPages(Page<SaleProducts> page, SaleProducts saleProducts);

    void updateSaleProductState(@Param("id") Long id);

    void updateSaleProductStates(@Param("id")Long id);
}

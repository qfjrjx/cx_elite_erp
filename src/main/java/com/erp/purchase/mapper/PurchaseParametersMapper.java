package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购参数表 Mapper
 *
 * @author qiufeng
 * @date 2021-11-09 16:20:36
 */
@Mapper
public interface PurchaseParametersMapper extends BaseMapper<PurchaseParameters> {

    long countPurchaseParameters(@Param("purchaseParameters") PurchaseParameters purchaseParameters);

    IPage<PurchaseParameters> findPurchaseParametersPage(Page<PurchaseParameters> page,@Param("purchaseParameters") PurchaseParameters purchaseParameters);

    PurchaseParameters findPurchaseParametersById(@Param("id") Long id);
    // <!--查询产品参数类别信息-->
    List<PurchaseParameters> queryProductMaterial(@Param("purchaseParametersCategory") String purchaseParametersCategory,@Param("parametersState") String parametersState);

}

package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseMaterialCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;
import java.util.List;

/**
 * 物料类别表 Mapper
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:14
 */
@Mapper
public interface PurchaseMaterialCategoryMapper extends BaseMapper<PurchaseMaterialCategory> {

    long countPurchaseMaterialCategory(@Param("purchaseMaterialCategory") PurchaseMaterialCategory purchaseMaterialCategory);

    IPage<PurchaseMaterialCategory> findPurchaseMaterialCategoryPage(Page<PurchaseMaterialCategory> page,@Param("purchaseMaterialCategory") PurchaseMaterialCategory purchaseMaterialCategory);

    List<PurchaseMaterialCategory> queryPurchaseMaterialCategory(@Param("generalCategory") String generalCategory);

    PurchaseMaterialCategory findPurchaseMaterialCategoryById(@Param("id") Long id);

    void updatePurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory);

    List<PurchaseMaterialCategory> queryMaterialSubclass(@Param("id") Long id);

}

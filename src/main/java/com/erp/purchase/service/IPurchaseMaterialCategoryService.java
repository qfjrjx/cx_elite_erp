package com.erp.purchase.service;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseMaterialCategory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 物料类别表 Service接口
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:14
 */
public interface IPurchaseMaterialCategoryService extends IService<PurchaseMaterialCategory> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseMaterialCategory purchaseMaterialCategory
     * @return IPage<PurchaseMaterialCategory>
     */
    IPage<PurchaseMaterialCategory> findPurchaseMaterialCategorys(QueryRequest request, PurchaseMaterialCategory purchaseMaterialCategory);

    /**
     * 查询（所有）
     *
     * @param purchaseMaterialCategory purchaseMaterialCategory
     * @return List<PurchaseMaterialCategory>
     */
    List<PurchaseMaterialCategory> findPurchaseMaterialCategorys(PurchaseMaterialCategory purchaseMaterialCategory);

    /**
     * 新增
     *
     * @param purchaseMaterialCategory purchaseMaterialCategory
     */
    void createPurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory);

    /**
     * 修改
     *
     * @param purchaseMaterialCategory purchaseMaterialCategory
     */
    void updatePurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory);

    /**
     * 删除
     *
     * @param ids purchaseMaterialCategory
     */
    void deletePurchaseMaterialCategory(String[] ids);

    List<PurchaseMaterialCategory> queryPurchaseMaterialCategory(String generalCategory);

    PurchaseMaterialCategory findPurchaseMaterialCategoryById(Long id);

    List<PurchaseMaterialCategory> queryMaterialSubclass(Long id);
}

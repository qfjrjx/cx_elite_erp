package com.erp.purchase.service;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseMaterialFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 物料档案表 Service接口
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:19
 */
public interface IPurchaseMaterialFileService extends IService<PurchaseMaterialFile> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseMaterialFile purchaseMaterialFile
     * @return IPage<PurchaseMaterialFile>
     */
    IPage<PurchaseMaterialFile> findPurchaseMaterialFiles(QueryRequest request, PurchaseMaterialFile purchaseMaterialFile);

    /**
     * 查询（所有）
     *
     * @param purchaseMaterialFile purchaseMaterialFile
     * @return List<PurchaseMaterialFile>
     */
    List<PurchaseMaterialFile> findPurchaseMaterialFiles(PurchaseMaterialFile purchaseMaterialFile);

    /**
     * 新增
     *
     * @param purchaseMaterialFile purchaseMaterialFile
     */
    void createPurchaseMaterialFile(PurchaseMaterialFile purchaseMaterialFile) throws ParseException;

    /**
     * 修改
     *
     * @param purchaseMaterialFile purchaseMaterialFile
     */
    void updatePurchaseMaterialFile(PurchaseMaterialFile purchaseMaterialFile);

    /**
     * 删除
     *
     * @param ids purchaseMaterialFile
     */
    void deletePurchaseMaterialFile(String[] ids);

    PurchaseMaterialFile findPurchaseMaterialFileById(Long id);

    PurchaseMaterialFile findPurchaseMaterialFileCopyById(Long id);
}

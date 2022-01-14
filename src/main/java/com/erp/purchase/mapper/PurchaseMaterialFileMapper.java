package com.erp.purchase.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseMaterialFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 物料档案表 Mapper
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:19
 */
@Mapper
public interface PurchaseMaterialFileMapper extends BaseMapper<PurchaseMaterialFile> {

    long countPurchaseMaterialFiles(@Param("purchaseMaterialFile") PurchaseMaterialFile purchaseMaterialFile);

    IPage<PurchaseMaterialFile> findPurchaseMaterialFilesPage(Page<PurchaseMaterialFile> page,@Param("purchaseMaterialFile") PurchaseMaterialFile purchaseMaterialFile);

    PurchaseMaterialFile queryPurchaseMaterialFile();

    PurchaseMaterialFile findPurchaseMaterialFileById(@Param("id") Long id);

    void updatePurchaseMaterialFile(@Param("purchaseMaterialFile") PurchaseMaterialFile purchaseMaterialFile);

    PurchaseMaterialFile findPurchaseMaterialFileCopyById(@Param("id") Long id);
}

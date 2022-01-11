package com.erp.purchase.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseMaterialCategory;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.mapper.PurchaseMaterialCategoryMapper;
import com.erp.purchase.service.IPurchaseMaterialCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 物料类别表 Service实现
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:14
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseMaterialCategoryServiceImpl extends ServiceImpl<PurchaseMaterialCategoryMapper, PurchaseMaterialCategory> implements IPurchaseMaterialCategoryService {

    private final PurchaseMaterialCategoryMapper purchaseMaterialCategoryMapper;

    @Override
    public IPage<PurchaseMaterialCategory> findPurchaseMaterialCategorys(QueryRequest request, PurchaseMaterialCategory purchaseMaterialCategory) {
        Page<PurchaseMaterialCategory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseMaterialCategory(purchaseMaterialCategory));
        return baseMapper.findPurchaseMaterialCategoryPage(page,purchaseMaterialCategory);
    }

    @Override
    public List<PurchaseMaterialCategory> findPurchaseMaterialCategorys(PurchaseMaterialCategory purchaseMaterialCategory) {
	    LambdaQueryWrapper<PurchaseMaterialCategory> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory) {
        this.save(purchaseMaterialCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory) {
        this.saveOrUpdate(purchaseMaterialCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseMaterialCategory(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public List<PurchaseMaterialCategory> queryPurchaseMaterialCategory(String generalCategory) {


        return baseMapper.queryPurchaseMaterialCategory(generalCategory);
    }

    @Override
    public PurchaseMaterialCategory findPurchaseMaterialCategoryById(Long id) {

        return baseMapper.findPurchaseMaterialCategoryById(id);
    }
}

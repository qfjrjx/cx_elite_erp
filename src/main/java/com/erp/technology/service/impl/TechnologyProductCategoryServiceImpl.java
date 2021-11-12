package com.erp.technology.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleApplication;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.mapper.TechnologyProductCategoryMapper;
import com.erp.technology.service.ITechnologyProductCategoryService;
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
 * 产品类别表 Service实现
 *
 * @author qiufeng
 * @date 2021-11-05 16:27:29
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TechnologyProductCategoryServiceImpl extends ServiceImpl<TechnologyProductCategoryMapper, TechnologyProductCategory> implements ITechnologyProductCategoryService {

    private final TechnologyProductCategoryMapper technologyProductCategoryMapper;

    @Override
    public IPage<TechnologyProductCategory> findTechnologyProductCategorys(QueryRequest request, TechnologyProductCategory technologyProductCategory) {
        Page<TechnologyProductCategory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countTechnologyProductCategory(technologyProductCategory));
        return baseMapper.findTechnologyProductCategoryPage(page,technologyProductCategory);
    }

    @Override
    public List<TechnologyProductCategory> findTechnologyProductCategorys(TechnologyProductCategory technologyProductCategory) {
	    LambdaQueryWrapper<TechnologyProductCategory> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTechnologyProductCategory(TechnologyProductCategory technologyProductCategory) {
        this.save(technologyProductCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTechnologyProductCategory(TechnologyProductCategory technologyProductCategory) {
        this.saveOrUpdate(technologyProductCategory);
    }

    @Override
    public void deleteTechnologyProductCategory(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }


    @Override
    public TechnologyProductCategory findTechnologyById(Long id) {

        return baseMapper.findTechnologyById(id);
    }
    //查询产品类别
    @Override
    public List<TechnologyProductCategory> queryProductGeneralCategory(String productGeneralCategory,String productCategoryState) {

        return baseMapper.queryProductGeneralCategory(productGeneralCategory,productCategoryState);
    }

}

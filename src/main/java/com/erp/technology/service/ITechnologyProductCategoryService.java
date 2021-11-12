package com.erp.technology.service;

import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyProductCategory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品类别表 Service接口
 *
 * @author qiufeng
 * @date 2021-11-05 16:27:29
 */
public interface ITechnologyProductCategoryService extends IService<TechnologyProductCategory> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param technologyProductCategory technologyProductCategory
     * @return IPage<TechnologyProductCategory>
     */
    IPage<TechnologyProductCategory> findTechnologyProductCategorys(QueryRequest request, TechnologyProductCategory technologyProductCategory);

    /**
     * 查询（所有）
     *
     * @param technologyProductCategory technologyProductCategory
     * @return List<TechnologyProductCategory>
     */
    List<TechnologyProductCategory> findTechnologyProductCategorys(TechnologyProductCategory technologyProductCategory);

    /**
     * 新增
     *
     * @param technologyProductCategory technologyProductCategory
     */
    void createTechnologyProductCategory(TechnologyProductCategory technologyProductCategory);

    /**
     * 修改
     *
     * @param technologyProductCategory technologyProductCategory
     */
    void updateTechnologyProductCategory(TechnologyProductCategory technologyProductCategory);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deleteTechnologyProductCategory(String[] ids);

    TechnologyProductCategory findTechnologyById(Long id);
    //查询产品类别
    List<TechnologyProductCategory> queryProductGeneralCategory(String productGeneralCategory,String productCategoryState);
}

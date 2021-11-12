package com.erp.technology.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.technology.entity.TechnologyProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品类别表 Mapper
 *
 * @author qiufeng
 * @date 2021-11-05 16:27:29
 */
@Mapper
public interface TechnologyProductCategoryMapper extends BaseMapper<TechnologyProductCategory> {

    long countTechnologyProductCategory(@Param("technologyProductCategory") TechnologyProductCategory technologyProductCategory);

    IPage<TechnologyProductCategory> findTechnologyProductCategoryPage(Page<TechnologyProductCategory> page,@Param("technologyProductCategory") TechnologyProductCategory technologyProductCategory);

    TechnologyProductCategory findTechnologyById(@Param("id") Long id);
    //查询产品类别
    List<TechnologyProductCategory> queryProductGeneralCategory(@Param("productGeneralCategory") String productGeneralCategory,@Param("productCategoryState") String productCategoryState);

}

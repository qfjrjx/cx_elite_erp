package com.erp.technology.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.technology.entity.TechnologyProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 产品表
             Mapper
 *
 * @author qiufeng
 * @date 2021-11-09 10:30:07
 */
@Mapper
public interface TechnologyProductMapper extends BaseMapper<TechnologyProduct> {

    long countTechnologyProduct(@Param("technologyProduct") TechnologyProduct technologyProduct);

    IPage<TechnologyProduct> findTechnologyProductPage(Page<TechnologyProduct> page,@Param("technologyProduct") TechnologyProduct technologyProduct);

    TechnologyProduct queryProductCode();

    void saveTechnologyProduct(@Param("technologyProduct") TechnologyProduct technologyProduct);

    TechnologyProduct findTechnologyProductById(@Param("id") Long id);

    void updateTechnologyProduct(@Param("technologyProduct") TechnologyProduct technologyProduct);
}

package com.erp.technology.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.technology.entity.TechnologyBomParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BOM参数 Mapper
 *
 * @author qiufeng
 * @date 2022-05-05 11:30:22
 */
public interface TechnologyBomParameterMapper extends BaseMapper<TechnologyBomParameter> {

    long countTechnologyBomParameters(@Param("technologyBomParameter") TechnologyBomParameter technologyBomParameter);

    IPage<TechnologyBomParameter> findTechnologyBomParametersPage(Page<TechnologyBomParameter> page,@Param("technologyBomParameter") TechnologyBomParameter technologyBomParameter);

    TechnologyBomParameter technologyBomParameterId(@Param("id") Long id);

    List<TechnologyBomParameter> findTechnologyBomParametersList();
}

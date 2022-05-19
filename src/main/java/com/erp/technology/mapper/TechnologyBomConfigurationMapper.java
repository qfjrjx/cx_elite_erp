package com.erp.technology.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.technology.entity.TechnologyBomConfiguration;
import com.erp.technology.entity.TechnologyBomConfigurationSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BOM配置 Mapper
 *
 * @author qiufeng
 * @date 2022-05-09 09:43:35
 */
public interface TechnologyBomConfigurationMapper extends BaseMapper<TechnologyBomConfiguration> {

    long countTechnologyBomConfiguration(@Param("technologyBomConfiguration") TechnologyBomConfiguration technologyBomConfiguration);

    IPage<TechnologyBomConfiguration> findTechnologyBomConfigurationPage(Page<TechnologyBomConfiguration> page,@Param("technologyBomConfiguration") TechnologyBomConfiguration technologyBomConfiguration);

    TechnologyBomConfiguration queryTechnologyBomConfiguration();

    void saveTechnologyBomConfigurationSchedule(TechnologyBomConfigurationSchedule technologyBomConfigurationSchedule);

    void saveTechnologyBomConfigurationDate(TechnologyBomConfiguration technologyBomConfigurationDate);

    TechnologyBomConfiguration findTechnologyBomConfigurationModel(@Param("id") String id);

    List<TechnologyBomConfigurationSchedule> queryConfigurationRefer(@Param("parameterCode") String parameterCode);

    void deleteTechnologyBomConfiguration(@Param("parameterCode") String parameterCode);

    void deleteTechnologyBomConfigurationSchedule(@Param("parameterCode") String parameterCode);

    void confirmTechnologyBomConfiguration(@Param("ids") String ids);

    void cancelTechnologyBomConfiguration(@Param("ids") String ids);
}

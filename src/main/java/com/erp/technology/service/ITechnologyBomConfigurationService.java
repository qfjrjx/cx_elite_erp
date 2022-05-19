package com.erp.technology.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyBomConfiguration;
import com.erp.technology.entity.TechnologyBomConfigurationSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * BOM配置 Service接口
 *
 * @author qiufeng
 * @date 2022-05-09 09:43:35
 */
public interface ITechnologyBomConfigurationService extends IService<TechnologyBomConfiguration> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param technologyBomConfiguration technologyBomConfiguration
     * @return IPage<TechnologyBomConfiguration>
     */
    IPage<TechnologyBomConfiguration> findTechnologyBomConfigurations(QueryRequest request, TechnologyBomConfiguration technologyBomConfiguration);

    /**
     * 查询（所有）
     *
     * @param technologyBomConfiguration technologyBomConfiguration
     * @return List<TechnologyBomConfiguration>
     */
    List<TechnologyBomConfiguration> findTechnologyBomConfigurations(TechnologyBomConfiguration technologyBomConfiguration);

    /**
     * 新增
     *
     * @param technologyBomConfiguration technologyBomConfiguration
     */
    void createTechnologyBomConfiguration(String technologyBomConfiguration,String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param technologyBomConfiguration technologyBomConfiguration
     */
    void updateTechnologyBomConfiguration(String technologyBomConfiguration,String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param parameterCode technologyBomConfiguration
     */
    void deleteTechnologyBomConfiguration(String parameterCode);

    TechnologyBomConfiguration findTechnologyBomConfigurationModel(String id);

    List<TechnologyBomConfigurationSchedule> queryConfigurationRefer(String parameterCode);

    void confirmTechnologyBomConfiguration(String ids);

    void cancelTechnologyBomConfiguration(String ids);
}

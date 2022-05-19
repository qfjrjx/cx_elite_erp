package com.erp.technology.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyBomParameter;

import java.util.List;

/**
 * BOM参数 Service接口
 *
 * @author qiufeng
 * @date 2022-05-05 11:30:22
 */
public interface ITechnologyBomParameterService extends IService<TechnologyBomParameter> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param technologyBomParameter technologyBomParameter
     * @return IPage<TechnologyBomParameter>
     */
    IPage<TechnologyBomParameter> findTechnologyBomParameters(QueryRequest request, TechnologyBomParameter technologyBomParameter);

    /**
     * 查询（所有）
     *
     * @param technologyBomParameter technologyBomParameter
     * @return List<TechnologyBomParameter>
     */
    List<TechnologyBomParameter> findTechnologyBomParameters(TechnologyBomParameter technologyBomParameter);

    /**
     * 新增
     *
     * @param technologyBomParameter technologyBomParameter
     */
    void createTechnologyBomParameter(TechnologyBomParameter technologyBomParameter);

    /**
     * 修改
     *
     * @param technologyBomParameter technologyBomParameter
     */
    void updateTechnologyBomParameter(TechnologyBomParameter technologyBomParameter);

    /**
     * 删除
     *
     * @param
     */
    void deleteTechnologyBomParameter(String[] ids);

    TechnologyBomParameter technologyBomParameterId(Long id);

    List<TechnologyBomParameter> findTechnologyBomParametersList();
}

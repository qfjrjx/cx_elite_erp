package com.erp.enterprise.service;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 参数设置表 Service接口
 *
 * @author qiufeng
 * @date 2021-12-09 10:22:55
 */
public interface IEnterpriseResourcesParametersService extends IService<EnterpriseResourcesParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param enterpriseResourcesParameters enterpriseResourcesParameters
     * @return IPage<EnterpriseResourcesParameters>
     */
    IPage<EnterpriseResourcesParameters> findEnterpriseResourcesParameterss(QueryRequest request, EnterpriseResourcesParameters enterpriseResourcesParameters);

    /**
     * 查询（所有）
     *
     * @param enterpriseResourcesParameters enterpriseResourcesParameters
     * @return List<EnterpriseResourcesParameters>
     */
    List<EnterpriseResourcesParameters> findEnterpriseResourcesParameterss(EnterpriseResourcesParameters enterpriseResourcesParameters);

    /**
     * 新增
     *
     * @param enterpriseResourcesParameters enterpriseResourcesParameters
     */
    void createEnterpriseResourcesParameters(EnterpriseResourcesParameters enterpriseResourcesParameters);

    /**
     * 修改
     *
     * @param enterpriseResourcesParameters enterpriseResourcesParameters
     */
    void updateEnterpriseResourcesParameters(EnterpriseResourcesParameters enterpriseResourcesParameters);

    /**
     * 删除
     *
     * @param ids enterpriseResourcesParameters
     */
    void deleteEnterpriseResourcesParameters(String[] ids);

    EnterpriseResourcesParameters resourcesParameterById(Long id);
    //查询参数设置里的公文类型信息
    List<EnterpriseResourcesParameters> queryEnterpriseResourcesParameters(String parametersType);
}

package com.erp.quality.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.quality.entity.QualityParameter;

import java.util.List;

/**
 * 品质参数 Service接口
 *
 * @author qiufeng
 * @date 2022-06-02 10:08:22
 */
public interface IQualityParameterService extends IService<QualityParameter> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param qualityParameter qualityParameter
     * @return IPage<QualityParameter>
     */
    IPage<QualityParameter> findQualityParameters(QueryRequest request, QualityParameter qualityParameter);

    /**
     * 查询（所有）
     *
     * @param qualityParameter qualityParameter
     * @return List<QualityParameter>
     */
    List<QualityParameter> findQualityParameters(QualityParameter qualityParameter);

    /**
     * 新增
     *
     * @param qualityParameter qualityParameter
     */
    void createQualityParameter(QualityParameter qualityParameter);

    /**
     * 修改
     *
     * @param qualityParameter qualityParameter
     */
    void updateQualityParameter(QualityParameter qualityParameter);

    /**
     * 删除
     *
     * @param qualityParameter qualityParameter
     */
    void deleteQualityParameter(String ids);

    QualityParameter qualityParameterId(Long id);
}

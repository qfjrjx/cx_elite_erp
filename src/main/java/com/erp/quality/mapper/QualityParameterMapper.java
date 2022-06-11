package com.erp.quality.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.quality.entity.QualityParameter;
import org.apache.ibatis.annotations.Param;

/**
 * 品质参数 Mapper
 *
 * @author qiufeng
 * @date 2022-06-02 10:08:22
 */
public interface QualityParameterMapper extends BaseMapper<QualityParameter> {

    long countQualityParameters(@Param("qualityParameter") QualityParameter qualityParameter);

    IPage<QualityParameter> findQualityParametersPage(@Param("qualityParameter") Page<QualityParameter> page, QualityParameter qualityParameter);

    QualityParameter qualityParameterId(@Param("id") Long id);

    void deleteQualityParameter(@Param("id") String ids);
}

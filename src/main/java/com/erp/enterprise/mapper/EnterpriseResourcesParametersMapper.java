package com.erp.enterprise.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.finance.entity.FinanceParameters;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数设置表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-09 10:22:55
 */
@Mapper
public interface EnterpriseResourcesParametersMapper extends BaseMapper<EnterpriseResourcesParameters> {

    long countEnterpriseResourcesParameters(@Param("enterpriseResourcesParameters") EnterpriseResourcesParameters enterpriseResourcesParameters);

    IPage<EnterpriseResourcesParameters> findEnterpriseResourcesParametersPage(Page<EnterpriseResourcesParameters> page,@Param("enterpriseResourcesParameters") EnterpriseResourcesParameters enterpriseResourcesParameters);

    EnterpriseResourcesParameters resourcesParameterById(@Param("id") Long id);
    //查询参数设置里的公文类型信息
    List<EnterpriseResourcesParameters> queryEnterpriseResourcesParameters(@Param("parametersType") String parametersType);
}

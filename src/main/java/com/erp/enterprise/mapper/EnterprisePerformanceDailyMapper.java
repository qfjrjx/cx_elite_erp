package com.erp.enterprise.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 业绩日报表  Mapper
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
@Mapper
public interface EnterprisePerformanceDailyMapper extends BaseMapper<EnterprisePerformanceDaily> {

    long countEnterprisePerformanceDaily(@Param("enterprisePerformanceDaily") EnterprisePerformanceDaily enterprisePerformanceDaily);

    IPage<EnterprisePerformanceDaily> findEnterprisePerformanceDailyPage(Page<EnterprisePerformanceDaily> page,@Param("enterprisePerformanceDaily") EnterprisePerformanceDaily enterprisePerformanceDaily);

    EnterprisePerformanceDaily queryEnterprisePerformanceDaily();

    EnterprisePerformanceDaily enterprisePerformanceDailyById(@Param("id") Long id);
}

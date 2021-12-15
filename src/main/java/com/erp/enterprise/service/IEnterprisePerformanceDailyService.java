package com.erp.enterprise.service;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 业绩日报表  Service接口
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
public interface IEnterprisePerformanceDailyService extends IService<EnterprisePerformanceDaily> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param enterprisePerformanceDaily enterprisePerformanceDaily
     * @return IPage<EnterprisePerformanceDaily>
     */
    IPage<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(QueryRequest request, EnterprisePerformanceDaily enterprisePerformanceDaily);

    /**
     * 查询（所有）
     *
     * @param enterprisePerformanceDaily enterprisePerformanceDaily
     * @return List<EnterprisePerformanceDaily>
     */
    List<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(EnterprisePerformanceDaily enterprisePerformanceDaily);

    /**
     * 新增
     *
     * @param enterprisePerformanceDaily enterprisePerformanceDaily
     */
    void createEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) throws ParseException;

    /**
     * 修改
     *
     * @param enterprisePerformanceDaily enterprisePerformanceDaily
     */
    void updateEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily);

    /**
     * 删除
     *
     * @param ids enterprisePerformanceDaily
     */
    void deleteEnterprisePerformanceDaily(String[] ids);

    EnterprisePerformanceDaily enterprisePerformanceDailyById(Long id);
}

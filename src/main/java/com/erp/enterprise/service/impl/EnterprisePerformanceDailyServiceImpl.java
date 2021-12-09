package com.erp.enterprise.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;
import com.erp.enterprise.mapper.EnterprisePerformanceDailyMapper;
import com.erp.enterprise.service.IEnterprisePerformanceDailyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 业绩日报表  Service实现
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EnterprisePerformanceDailyServiceImpl extends ServiceImpl<EnterprisePerformanceDailyMapper, EnterprisePerformanceDaily> implements IEnterprisePerformanceDailyService {

    private final EnterprisePerformanceDailyMapper enterprisePerformanceDailyMapper;

    @Override
    public IPage<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(QueryRequest request, EnterprisePerformanceDaily enterprisePerformanceDaily) {
        LambdaQueryWrapper<EnterprisePerformanceDaily> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<EnterprisePerformanceDaily> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(EnterprisePerformanceDaily enterprisePerformanceDaily) {
	    LambdaQueryWrapper<EnterprisePerformanceDaily> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.save(enterprisePerformanceDaily);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.saveOrUpdate(enterprisePerformanceDaily);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        LambdaQueryWrapper<EnterprisePerformanceDaily> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

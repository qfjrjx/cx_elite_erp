package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleApplicationAddStaging;
import com.erp.sale.mapper.SaleApplicationAddStagingMapper;
import com.erp.sale.service.ISaleApplicationAddStagingService;
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
 * 销售申请暂存表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-20 17:12:25
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleApplicationAddStagingServiceImpl extends ServiceImpl<SaleApplicationAddStagingMapper, SaleApplicationAddStaging> implements ISaleApplicationAddStagingService {

    private final SaleApplicationAddStagingMapper saleApplicationAddStagingMapper;

    @Override
    public IPage<SaleApplicationAddStaging> findSaleApplicationAddStagings(QueryRequest request, SaleApplicationAddStaging saleApplicationAddStaging) {
        LambdaQueryWrapper<SaleApplicationAddStaging> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<SaleApplicationAddStaging> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<SaleApplicationAddStaging> findSaleApplicationAddStagings(SaleApplicationAddStaging saleApplicationAddStaging) {
	    LambdaQueryWrapper<SaleApplicationAddStaging> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging) {
        this.save(saleApplicationAddStaging);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging) {
        this.saveOrUpdate(saleApplicationAddStaging);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging) {
        LambdaQueryWrapper<SaleApplicationAddStaging> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

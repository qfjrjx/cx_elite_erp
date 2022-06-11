package com.erp.warehouse.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.warehouse.entity.WarehouseParameter;
import com.erp.warehouse.mapper.WarehouseParameterMapper;
import com.erp.warehouse.service.IWarehouseParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 仓库参数 Service实现
 *
 * @author qiufeng
 * @date 2022-06-01 15:53:35
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WarehouseParameterServiceImpl extends ServiceImpl<WarehouseParameterMapper, WarehouseParameter> implements IWarehouseParameterService {

    private final WarehouseParameterMapper warehouseParameterMapper;

    @Override
    public IPage<WarehouseParameter> findWarehouseParameters(QueryRequest request, WarehouseParameter warehouseParameter) {
        Page<WarehouseParameter> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countWarehouseParameters(warehouseParameter));
        return baseMapper.findWarehouseParametersPage(page,warehouseParameter);
    }

    @Override
    public List<WarehouseParameter> findWarehouseParameters(WarehouseParameter warehouseParameter) {
	    LambdaQueryWrapper<WarehouseParameter> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWarehouseParameter(WarehouseParameter warehouseParameter) {
        this.save(warehouseParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWarehouseParameter(WarehouseParameter warehouseParameter) {
        this.saveOrUpdate(warehouseParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWarehouseParameter(String ids) {
        baseMapper.deleteWarehouseParameter(ids);
	}

    @Override
    public WarehouseParameter warehouseParameterId(Long id) {
        return baseMapper.warehouseParameterId(id);
    }

    @Override
    public List<WarehouseParameter> queryParameterValue() {
        return baseMapper.queryParameterValue();
    }
}

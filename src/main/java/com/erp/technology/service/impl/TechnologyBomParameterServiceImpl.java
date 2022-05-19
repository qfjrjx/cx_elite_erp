package com.erp.technology.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyBomParameter;
import com.erp.technology.mapper.TechnologyBomParameterMapper;
import com.erp.technology.service.ITechnologyBomParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * BOM参数 Service实现
 *
 * @author qiufeng
 * @date 2022-05-05 11:30:22
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TechnologyBomParameterServiceImpl extends ServiceImpl<TechnologyBomParameterMapper, TechnologyBomParameter> implements ITechnologyBomParameterService {

    private final TechnologyBomParameterMapper technologyBomParameterMapper;

    @Override
    public IPage<TechnologyBomParameter> findTechnologyBomParameters(QueryRequest request, TechnologyBomParameter technologyBomParameter) {
        Page<TechnologyBomParameter> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countTechnologyBomParameters(technologyBomParameter));
        return baseMapper.findTechnologyBomParametersPage(page,technologyBomParameter);
    }

    @Override
    public List<TechnologyBomParameter> findTechnologyBomParameters(TechnologyBomParameter technologyBomParameter) {
	    LambdaQueryWrapper<TechnologyBomParameter> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTechnologyBomParameter(TechnologyBomParameter technologyBomParameter) {
        this.save(technologyBomParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTechnologyBomParameter(TechnologyBomParameter technologyBomParameter) {
        this.saveOrUpdate(technologyBomParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTechnologyBomParameter(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public TechnologyBomParameter technologyBomParameterId(Long id) {
        return baseMapper.technologyBomParameterId(id);
    }

    @Override
    public List<TechnologyBomParameter> findTechnologyBomParametersList() {
        return baseMapper.findTechnologyBomParametersList();
    }
}

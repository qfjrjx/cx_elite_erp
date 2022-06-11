package com.erp.quality.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.quality.entity.QualityParameter;
import com.erp.quality.mapper.QualityParameterMapper;
import com.erp.quality.service.IQualityParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品质参数 Service实现
 *
 * @author qiufeng
 * @date 2022-06-02 10:08:22
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QualityParameterServiceImpl extends ServiceImpl<QualityParameterMapper, QualityParameter> implements IQualityParameterService {

    private final QualityParameterMapper qualityParameterMapper;

    @Override
    public IPage<QualityParameter> findQualityParameters(QueryRequest request, QualityParameter qualityParameter) {
        Page<QualityParameter> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countQualityParameters(qualityParameter));
        return baseMapper.findQualityParametersPage(page,qualityParameter);
    }

    @Override
    public List<QualityParameter> findQualityParameters(QualityParameter qualityParameter) {
	    LambdaQueryWrapper<QualityParameter> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createQualityParameter(QualityParameter qualityParameter) {
        this.save(qualityParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQualityParameter(QualityParameter qualityParameter) {
        this.saveOrUpdate(qualityParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQualityParameter(String ids) {
        baseMapper.deleteQualityParameter(ids);
	}

    @Override
    public QualityParameter qualityParameterId(Long id) {
        return baseMapper.qualityParameterId(id);
    }
}

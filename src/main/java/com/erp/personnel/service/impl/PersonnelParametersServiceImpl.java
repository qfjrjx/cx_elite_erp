package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.job.entity.Job;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.mapper.PersonnelParametersMapper;
import com.erp.personnel.service.IPersonnelParametersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 人事参数表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-03 15:19:47
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelParametersServiceImpl extends ServiceImpl<PersonnelParametersMapper, PersonnelParameters> implements IPersonnelParametersService {

    private final PersonnelParametersMapper personnelParametersMapper;

    @Override
    public IPage<PersonnelParameters> findPersonnelParameterss(QueryRequest request, PersonnelParameters personnelParameters) {
        LambdaQueryWrapper<PersonnelParameters> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if (StringUtils.isNotBlank(personnelParameters.getParameterName())) {
            queryWrapper.eq(PersonnelParameters::getParameterName, personnelParameters.getParameterName());
        }
        if (StringUtils.isNotBlank(personnelParameters.getParameterCategory())) {
            queryWrapper.eq(PersonnelParameters::getParameterCategory, personnelParameters.getParameterCategory());
        }
        if (StringUtils.isNotBlank(personnelParameters.getParameterState())) {
            queryWrapper.eq(PersonnelParameters::getParameterState, personnelParameters.getParameterState());
        }
        Page<PersonnelParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<PersonnelParameters> queryDuties() {
		return baseMapper.queryDuties();
    }

    @Override
    public List<PersonnelParameters> queryPosition() {
        return baseMapper.queryPosition();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelParameters(PersonnelParameters personnelParameters) {
        this.save(personnelParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelParameters(PersonnelParameters personnelParameters) {
        this.saveOrUpdate(personnelParameters);
    }

    @Override
    public void deletePersonnel(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }

    @Override
    public PersonnelParameters findById(Long id) {
        return baseMapper.findById(id);
    }



}

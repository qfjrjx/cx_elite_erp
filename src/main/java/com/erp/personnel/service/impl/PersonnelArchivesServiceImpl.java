package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.mapper.PersonnelArchivesMapper;
import com.erp.personnel.service.IPersonnelArchivesService;
import com.erp.system.entity.User;
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
 * 用户表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelArchivesServiceImpl extends ServiceImpl<PersonnelArchivesMapper, PersonnelArchives> implements IPersonnelArchivesService {

    private final PersonnelArchivesMapper personnelArchivesMapper;

    @Override
    public IPage<PersonnelArchives> findPersonnelArchivess(QueryRequest request, PersonnelArchives personnelArchives) {
        LambdaQueryWrapper<PersonnelArchives> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if (StringUtils.isNotBlank(personnelArchives.getJobNumber())) {
            queryWrapper.eq(PersonnelArchives::getJobNumber, personnelArchives.getJobNumber());
        }
        if (StringUtils.isNotBlank(personnelArchives.getUserName())) {
            queryWrapper.eq(PersonnelArchives::getUserName, personnelArchives.getUserName());
        }
        if (StringUtils.isNotBlank(personnelArchives.getUserState())) {
            queryWrapper.eq(PersonnelArchives::getUserState, personnelArchives.getUserState());
        }
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<PersonnelArchives> findPersonnelArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelArchives(personnelArchives));
        return baseMapper.findPersonnelArchivesPage(page,personnelArchives);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelArchives(PersonnelArchives personnelArchives) {
        this.save(personnelArchives);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelArchives(PersonnelArchives personnelArchives) {
        this.saveOrUpdate(personnelArchives);
    }

    @Override
    public void deletePersonnelArchives(String[] ids) {
        List<String> list = Arrays.asList(ids);
	    // TODO 设置删除条件
        baseMapper.deleteBatchIds(list);
	}
}

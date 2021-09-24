package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelDormitoryInformation;
import com.erp.personnel.mapper.PersonnelDormitoryInformationMapper;
import com.erp.personnel.service.IPersonnelDormitoryInformationService;
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
 * 宿舍人员入住信息表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-19 11:43:19
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelDormitoryInformationServiceImpl extends ServiceImpl<PersonnelDormitoryInformationMapper, PersonnelDormitoryInformation> implements IPersonnelDormitoryInformationService {

    private final PersonnelDormitoryInformationMapper personnelDormitoryInformationMapper;

    @Override
    public IPage<PersonnelDormitoryInformation> findPersonnelDormitoryInformations(QueryRequest request, PersonnelDormitoryInformation personnelDormitoryInformation) {
        Page<PersonnelDormitoryInformation> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelDormitoryInformations(personnelDormitoryInformation));
        return baseMapper.findPersonnelDormitoryInformationsPage(page,personnelDormitoryInformation);
    }

    @Override
    public List<PersonnelDormitoryInformation> findPersonnelDormitoryInformations(PersonnelDormitoryInformation personnelDormitoryInformation) {
	    LambdaQueryWrapper<PersonnelDormitoryInformation> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {
        this.save(personnelDormitoryInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {
        this.saveOrUpdate(personnelDormitoryInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {
        LambdaQueryWrapper<PersonnelDormitoryInformation> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

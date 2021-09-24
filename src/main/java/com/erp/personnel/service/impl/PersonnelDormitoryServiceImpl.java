package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelContract;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.mapper.PersonnelDormitoryMapper;
import com.erp.personnel.service.IPersonnelDormitoryService;
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
 * 宿舍管理表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-19 09:35:28
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelDormitoryServiceImpl extends ServiceImpl<PersonnelDormitoryMapper, PersonnelDormitory> implements IPersonnelDormitoryService {

    private final PersonnelDormitoryMapper personnelDormitoryMapper;

    @Override
    public IPage<PersonnelDormitory> findPersonnelDormitorys(QueryRequest request, PersonnelDormitory personnelDormitory) {
        Page<PersonnelDormitory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelDormitorys(personnelDormitory));
        return baseMapper.findPersonnelDormitorysPage(page,personnelDormitory);
    }

    @Override
    public List<PersonnelDormitory> findPersonnelDormitorys(PersonnelDormitory personnelDormitory) {
	    LambdaQueryWrapper<PersonnelDormitory> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelDormitory(PersonnelDormitory personnelDormitory) {
        this.save(personnelDormitory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelDormitory(PersonnelDormitory personnelDormitory) {
        this.saveOrUpdate(personnelDormitory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonnelDormitory(PersonnelDormitory personnelDormitory) {
        LambdaQueryWrapper<PersonnelDormitory> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelContract;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.mapper.PersonnelDormitoryInformationMapper;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    private final PersonnelDormitoryInformationMapper personnelDormitoryInformationMapper;

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
    public void createPersonnelDormitory(PersonnelDormitory personnelDormitory) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        personnelDormitory.setCreateDate(today);
        this.save(personnelDormitory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelDormitory(PersonnelDormitory personnelDormitory) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        personnelDormitory.setCreateDate(today);
        this.saveOrUpdate(personnelDormitory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonnelDormitory(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public PersonnelDormitory findDormitoryById(Long id) {
        return baseMapper.findDormitoryById(id);
    }
}

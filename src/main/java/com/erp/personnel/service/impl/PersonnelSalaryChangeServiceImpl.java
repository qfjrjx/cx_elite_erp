package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.entity.PersonnelSalaryChange;
import com.erp.personnel.mapper.PersonnelSalaryChangeMapper;
import com.erp.personnel.service.IPersonnelSalaryChangeService;
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
 * 调薪记录 Service实现
 *
 * @author qiufeng
 * @date 2021-09-25 10:52:51
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelSalaryChangeServiceImpl extends ServiceImpl<PersonnelSalaryChangeMapper, PersonnelSalaryChange> implements IPersonnelSalaryChangeService {

    private final PersonnelSalaryChangeMapper personnelSalaryChangeMapper;

    @Override
    public IPage<PersonnelSalaryChange> findPersonnelSalaryChanges(QueryRequest request, PersonnelSalaryChange personnelSalaryChange) {
        Page<PersonnelSalaryChange> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelSalaryChange(personnelSalaryChange));
        return baseMapper.findPersonnelSalaryChangePage(page,personnelSalaryChange);
    }

    @Override
    public List<PersonnelSalaryChange> findPersonnelSalaryChanges(PersonnelSalaryChange personnelSalaryChange) {
	    LambdaQueryWrapper<PersonnelSalaryChange> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        personnelSalaryChange.setCreateDate(today);
        this.save(personnelSalaryChange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange) {
        this.saveOrUpdate(personnelSalaryChange);
    }

    @Override
    public void deletePersonnelSalaryChange(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }


}

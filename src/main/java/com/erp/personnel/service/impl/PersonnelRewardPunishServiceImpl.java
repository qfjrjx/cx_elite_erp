package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.entity.PersonnelRewardPunish;
import com.erp.personnel.mapper.PersonnelRewardPunishMapper;
import com.erp.personnel.service.IPersonnelRewardPunishService;
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
 * 奖罚记录 Service实现
 *
 * @author qiufeng
 * @date 2021-09-25 14:06:53
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelRewardPunishServiceImpl extends ServiceImpl<PersonnelRewardPunishMapper, PersonnelRewardPunish> implements IPersonnelRewardPunishService {

    private final PersonnelRewardPunishMapper personnelRewardPunishMapper;

    @Override
    public IPage<PersonnelRewardPunish> findPersonnelRewardPunishs(QueryRequest request, PersonnelRewardPunish personnelRewardPunish) {
        Page<PersonnelRewardPunish> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelRewardPunishs(personnelRewardPunish));
        return baseMapper.findPersonnelRewardPunishsPage(page,personnelRewardPunish);
    }

    @Override
    public List<PersonnelRewardPunish> findPersonnelRewardPunishs(PersonnelRewardPunish personnelRewardPunish) {
	    LambdaQueryWrapper<PersonnelRewardPunish> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        personnelRewardPunish.setCreateDate(today);
        this.save(personnelRewardPunish);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) {
        this.saveOrUpdate(personnelRewardPunish);
    }

    @Override
    public void deletePersonnelRewardPunish(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public IPage<PersonnelRewardPunish> personnelRewardPunishUserList(QueryRequest request, String userId) {

        Page<PersonnelRewardPunish> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelRewardPunishUser(userId));
        return baseMapper.findPersonnelRewardPunishUserPage(page,userId);
    }
}

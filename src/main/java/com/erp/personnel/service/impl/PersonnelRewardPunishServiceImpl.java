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
    public void createPersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) {
        this.save(personnelRewardPunish);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) {
        this.saveOrUpdate(personnelRewardPunish);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) {
        LambdaQueryWrapper<PersonnelRewardPunish> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

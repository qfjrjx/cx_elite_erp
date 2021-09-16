package com.erp.personnel.service.impl;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.SortUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelReceive;
import com.erp.personnel.mapper.PersonnelReceiveMapper;
import com.erp.personnel.service.IPersonnelReceiveService;
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
 * 领取记录表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-14 15:45:00
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelReceiveServiceImpl extends ServiceImpl<PersonnelReceiveMapper, PersonnelReceive> implements IPersonnelReceiveService {

    private final PersonnelReceiveMapper personnelReceiveMapper;

    @Override
    public IPage<PersonnelReceive> findPersonnelReceives(QueryRequest request, PersonnelReceive personnelReceive) {
        Page<PersonnelReceive> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelReceive(personnelReceive));
        return baseMapper.findPersonnelReceivePage(page,personnelReceive);
    }

    @Override
    public List<PersonnelReceive> findPersonnelReceives(PersonnelReceive personnelReceive) {
	    LambdaQueryWrapper<PersonnelReceive> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelReceive(PersonnelReceive personnelReceive) {
        baseMapper.createPersonnelReceive(personnelReceive);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelReceive(PersonnelReceive personnelReceive) {
        baseMapper.saveOrUpdate(personnelReceive);
    }

    @Override
    public void deletePersonnelReceive(String[] ids) {
        List<String> list = Arrays.asList(ids);

        baseMapper.deleteBatchIds(list);
    }


    @Override
    public IPage<PersonnelArchives> findReceiveArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countReceiveArchives(personnelArchives));
        SortUtil.handlePageSort(request, page, "entryDate", FebsConstant.ORDER_DESC, true);
        return baseMapper.findReceiveArchivesPage(page,personnelArchives);
    }

    @Override
    public PersonnelReceive findById(Long id) {

        return baseMapper.findById(id);
    }
}

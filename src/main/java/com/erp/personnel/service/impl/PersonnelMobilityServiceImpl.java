package com.erp.personnel.service.impl;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.SortUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.mapper.PersonnelMobilityMapper;
import com.erp.personnel.service.IPersonnelMobilityService;
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
 * 调岗记录 Service实现
 *
 * @author qiufeng
 * @date 2021-09-25 08:56:16
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelMobilityServiceImpl extends ServiceImpl<PersonnelMobilityMapper, PersonnelMobility> implements IPersonnelMobilityService {

    private final PersonnelMobilityMapper personnelMobilityMapper;

    @Override
    public IPage<PersonnelMobility> findPersonnelMobilitys(QueryRequest request, PersonnelMobility personnelMobility) {
        Page<PersonnelMobility> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelMobility(personnelMobility));
        return baseMapper.findPersonnelMobilityPage(page,personnelMobility);
    }

    @Override
    public List<PersonnelMobility> findPersonnelMobilitys(PersonnelMobility personnelMobility) {
	    LambdaQueryWrapper<PersonnelMobility> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelMobility(PersonnelMobility personnelMobility) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        personnelMobility.setCreateDate(today);
        this.save(personnelMobility);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelMobility(PersonnelMobility personnelMobility) {
        this.saveOrUpdate(personnelMobility);
    }

    @Override
    public void deletePersonnelMobility(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }


    @Override
    public IPage<PersonnelArchives> findReceiveArchivesMobilityList(PersonnelArchives personnelArchives, QueryRequest request) {
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countReceiveArchivesMobility(personnelArchives));
        SortUtil.handlePageSort(request, page, "entryDate", FebsConstant.ORDER_DESC, true);
        return baseMapper.findReceiveArchivesMobilityPage(page,personnelArchives);
    }

    @Override
    public IPage<PersonnelMobility> personnelMobilityUserList(QueryRequest request, String userId) {

        Page<PersonnelMobility> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelMobilityUser(userId));
        return baseMapper.findPersonnelMobilityUserPage(page,userId);
    }


}

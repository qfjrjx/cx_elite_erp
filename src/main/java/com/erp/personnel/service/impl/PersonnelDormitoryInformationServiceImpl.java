package com.erp.personnel.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelDormitoryInformation;
import com.erp.personnel.mapper.PersonnelDormitoryInformationMapper;
import com.erp.personnel.mapper.PersonnelDormitoryMapper;
import com.erp.personnel.service.IPersonnelDormitoryInformationService;
import com.erp.personnel.service.IPersonnelDormitoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
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

    private final PersonnelDormitoryMapper personnelDormitoryMapper;

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

        personnelDormitoryInformation.setUsedBeds(personnelDormitoryInformation.getUsedBeds()+1);
        String dormitoryLocation = personnelDormitoryInformation.getDormitoryLocation();
        if (dormitoryLocation.equals("东宿舍")){
            personnelDormitoryInformation.setDormitoryLocation("1");
        }if (dormitoryLocation.equals("西宿舍")){
            personnelDormitoryInformation.setDormitoryLocation("2");
        }
        Long dormitoryId = personnelDormitoryInformation.getDormitoryId();

        String dormitoryNo = personnelDormitoryInformation.getDormitoryNo();
        String dormitoryPlace  = personnelDormitoryInformation.getDormitoryLocation();
        //根据宿舍编号和宿舍地址查询出当前宿舍已入住人数。
        List<PersonnelDormitoryInformation> dormitoryCount = personnelDormitoryInformationMapper.countNormitoryNoDormitoryPlace(dormitoryNo,dormitoryPlace);
        if (dormitoryCount.size()>0){
            for (PersonnelDormitoryInformation personnelDormitoryCount: dormitoryCount) {
                Long id = personnelDormitoryCount.getId();
                int usedBedsCount = personnelDormitoryCount.getUsedBeds()+1;
                //循环修改员工信息表已住宿舍人数
                personnelDormitoryInformationMapper.updateDormitoryUsedBeds(id,usedBedsCount);
            }
        }
        //修改员工管理表宿舍已用数量
        personnelDormitoryMapper.updateDormitory(dormitoryId,personnelDormitoryInformation.getUsedBeds());
        baseMapper.saveDormitoryInformation(personnelDormitoryInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation) {

        String dormitoryLocation = personnelDormitoryInformation.getDormitoryLocation();
        if (dormitoryLocation.equals("东宿舍")){
            personnelDormitoryInformation.setDormitoryLocation("1");
        }if (dormitoryLocation.equals("西宿舍")){
            personnelDormitoryInformation.setDormitoryLocation("2");
        }
        Long dormitoryId = personnelDormitoryInformation.getDormitoryId();
        Long informationId = personnelDormitoryInformation.getId();
        PersonnelDormitoryInformation dormitoryInformation = baseMapper.findDormitoryInformationById(informationId);
        String dormitoryNo = dormitoryInformation.getDormitoryNo();
        String dormitoryPlace = dormitoryInformation.getDormitoryLocation();
        int usedBedsCounts = baseMapper.queryDormitoryInformationCount(dormitoryNo,dormitoryPlace);
        List<PersonnelDormitoryInformation> dormitoryCount = personnelDormitoryInformationMapper.countNormitoryNoDormitoryPlace(dormitoryNo,dormitoryPlace);
        int userBedsCount = 0;
        if (dormitoryCount.size()>0){
            for (PersonnelDormitoryInformation personnelDormitoryCount: dormitoryCount) {
                Long id = personnelDormitoryCount.getId();
                userBedsCount = usedBedsCounts-1;
                //循环修改员工信息表已住宿舍人数
                personnelDormitoryInformationMapper.updateDormitoryUsedBeds(id,userBedsCount);
            }
            personnelDormitoryMapper.updateDormitoryPresentNnt(dormitoryNo,dormitoryPlace,userBedsCount);
        }

        baseMapper.saveOrUpdate(personnelDormitoryInformation);
        String dormitoryNos = personnelDormitoryInformation.getDormitoryNo();
        String locations = personnelDormitoryInformation.getDormitoryLocation();
        List<PersonnelDormitoryInformation> dormitoryCountOne = personnelDormitoryInformationMapper.countNormitoryNoDormitoryPlace(dormitoryNos,locations);
        int usedBedsCount = 0;
        if (dormitoryCountOne.size()>0){
            for (PersonnelDormitoryInformation personnelDormitoryInformationCount: dormitoryCountOne) {
                Long id = personnelDormitoryInformationCount.getId();
                int dormitoryInformationCount = baseMapper.queryDormitoryInformationCount(personnelDormitoryInformationCount.getDormitoryNo(),personnelDormitoryInformationCount.getDormitoryLocation());
                usedBedsCount = dormitoryInformationCount;
                //循环修改员工信息表已住宿舍人数
                personnelDormitoryInformationMapper.updateDormitoryUsedBeds(id,usedBedsCount);
            }
        }
        personnelDormitoryMapper.updateDormitory(dormitoryId,usedBedsCount);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonnelDormitoryInformation(String[] ids) {
        //定义一个list对象用来存放循环出来的宿舍人员信息
        ArrayList<PersonnelDormitoryInformation> personnelDormitoryInformations =new ArrayList<>();
        //把拿到的id存放近list里面去
        List<String> list = Arrays.asList(ids);
        //循环拿到的所有id，并且存放到定义的新的list里
        for (String informationId:list) {
            PersonnelDormitoryInformation personnelDormitoryInformation = baseMapper.queryDormitoryInformationId(informationId);
            personnelDormitoryInformations.add(personnelDormitoryInformation);
        }
        //根据id删除所有的宿舍人员信息
        baseMapper.deleteBatchIds(list);
        //循环list获取所有宿舍人员信息
        for (PersonnelDormitoryInformation informationId:personnelDormitoryInformations) {
            //获取宿舍编号
            String dormitoryNo = informationId.getDormitoryNo();
            //获取宿舍地址
            String dormitoryPlace = informationId.getDormitoryLocation();
            //根据宿舍编号和宿舍地址统计出所有人员信息
            int usedBedsCount = baseMapper.queryDormitoryInformationCount(dormitoryNo,dormitoryPlace);
            //循环修改员工信息表已住宿舍人数
            personnelDormitoryInformationMapper.updateDormitoryusedBedsCount(dormitoryNo,dormitoryPlace,usedBedsCount);
            //修改员工管理表宿舍已用数量
            personnelDormitoryMapper.updateDormitoryPresentNnt(dormitoryNo,dormitoryPlace,usedBedsCount);
        }
	}

    @Override
    public IPage<PersonnelDormitory> findPersonnelDormitorys(QueryRequest request, PersonnelDormitory personnelDormitory) {
        Page<PersonnelDormitory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelDormitorys(personnelDormitory));
        return baseMapper.findPersonnelDormitorysPage(page,personnelDormitory);
    }

    @Override
    public PersonnelDormitoryInformation findDormitoryInformationById(Long id) {

        return baseMapper.findDormitoryInformationById(id);
    }
}

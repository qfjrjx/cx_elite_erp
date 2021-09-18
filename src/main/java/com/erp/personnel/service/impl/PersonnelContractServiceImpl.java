package com.erp.personnel.service.impl;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelContract;
import com.erp.personnel.entity.PersonnelReceive;
import com.erp.personnel.mapper.PersonnelContractMapper;
import com.erp.personnel.service.IPersonnelContractService;
import org.apache.commons.lang3.StringUtils;
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
 * 员工合同表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-16 10:46:36
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelContractServiceImpl extends ServiceImpl<PersonnelContractMapper, PersonnelContract> implements IPersonnelContractService {

    private final PersonnelContractMapper personnelContractMapper;

    @Override
    public IPage<PersonnelContract> findPersonnelContracts(QueryRequest request, PersonnelContract personnelContract) {
        if (StringUtils.isNotBlank(personnelContract.getSignedDateFrom()) &&
                StringUtils.equals(personnelContract.getSignedDateFrom(), personnelContract.getSignedDateTo())) {
            personnelContract.setSignedDateFrom(personnelContract.getSignedDateFrom() + FebsConstant.DAY_START_PATTERN_SUFFIX);
            personnelContract.setSignedDateTo(personnelContract.getSignedDateFrom() + FebsConstant.DAY_END_PATTERN_SUFFIX);
        }
        if (StringUtils.isNotBlank(personnelContract.getExpireDateFrom()) &&
                StringUtils.equals(personnelContract.getExpireDateFrom(), personnelContract.getExpireDateTo())) {
            personnelContract.setExpireDateFrom(personnelContract.getExpireDateFrom() + FebsConstant.DAY_START_PATTERN_SUFFIX);
            personnelContract.setExpireDateTo(personnelContract.getExpireDateTo() + FebsConstant.DAY_END_PATTERN_SUFFIX);
        }

        Page<PersonnelContract> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelContract(personnelContract));
        return baseMapper.findPersonnelContractPage(page,personnelContract);
    }

    @Override
    public List<PersonnelContract> findPersonnelContracts(PersonnelContract personnelContract) {
	    LambdaQueryWrapper<PersonnelContract> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelContract(PersonnelContract personnelContract){
        this.save(personnelContract);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelContract(PersonnelContract personnelContract) throws ParseException{


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //System.out.println(simpleDateFormat.format(new Date()));// new Date()为获取当前系统时间
        String date = simpleDateFormat.format(new Date());//系统当前时间
        //System.out.println("系统当前时间："+date);
        long timeStart = simpleDateFormat.parse(date).getTime();//转化系统当前时间为毫秒值
        //System.out.println("转化系统当前时间为毫秒值："+timeStart);
        String expireDate = simpleDateFormat.format(personnelContract.getExpireDate());//获取到期时间
        //System.out.println("获取到期时间："+expireDate);
        long timeEnd = simpleDateFormat.parse(expireDate).getTime();//转化到期时间为毫秒值
        //System.out.println("转化到期时间为毫秒值："+timeEnd);
        long dayCount= (timeEnd-timeStart)/(24*3600*1000);////两个日期想减得到天数
        //System.out.println("两个日期想减得到天数："+dayCount);
        Long contractId = personnelContract.getContractId();//获取合同id
        if(dayCount>0){
            personnelContract.setContractState(1);
            /*int contractState = 1;
            baseMapper.contractStateUpdate(contractId,contractState);*/
        }else if (dayCount<0){
            personnelContract.setContractState(2);
           /* int contractState = 2;
            baseMapper.contractStateUpdate(contractId,contractState);*/
        }if (dayCount>30){
            personnelContract.setContractTipsState(1);
          /*  int contractTipsState = 1;
            baseMapper.contractTipsStateUpdate(contractId,contractTipsState);*/
        }
        baseMapper.saveOrUpdate(personnelContract);
    }

    @Override
    public void deletePersonnelContract(String[] ids) {

        List<String> list = Arrays.asList(ids);

        baseMapper.deleteBatchIds(list);
    }


    @Override
    public List<PersonnelContract> queryContractList() {


        return baseMapper.queryContractList();
    }

    @Override
    public void contractStateUpdate(Long contractId, int contractState) {
        baseMapper.contractStateUpdate(contractId,contractState);
    }

    @Override
    public PersonnelContract findContractById(Long id) {


        return baseMapper.findContractById(id);
    }

    @Override
    public List<PersonnelContract> queryContractTipsList() {
        return baseMapper.queryContractTipsList();
    }

    @Override
    public void updateContractTipsState(String[] name) {
        baseMapper.updateContractTipsState(name);
    }

}

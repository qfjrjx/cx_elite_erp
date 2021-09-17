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
    public void updatePersonnelContract(PersonnelContract personnelContract) {
        baseMapper.saveOrContractUpdate(personnelContract);
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
    public void contractStateUpdate(Long contractId) {
        baseMapper.contractStateUpdate(contractId);
    }

    @Override
    public PersonnelContract findContractById(Long id) {


        return baseMapper.findContractById(id);
    }
}

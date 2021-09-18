package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelContract;

import java.text.ParseException;
import java.util.List;

/**
 * 员工合同表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-16 10:46:36
 */
public interface IPersonnelContractService extends IService<PersonnelContract> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelContract personnelContract
     * @return IPage<PersonnelContract>
     */
    IPage<PersonnelContract> findPersonnelContracts(QueryRequest request, PersonnelContract personnelContract);

    /**
     * 查询（所有）
     *
     * @param personnelContract personnelContract
     * @return List<PersonnelContract>
     */
    List<PersonnelContract> findPersonnelContracts(PersonnelContract personnelContract);

    /**
     * 新增
     *
     * @param personnelContract personnelContract
     */
    void createPersonnelContract(PersonnelContract personnelContract);

    /**
     * 修改
     *
     * @param personnelContract personnelContract
     */
    void updatePersonnelContract(PersonnelContract personnelContract) throws ParseException;

    /**
     * 删除
     *
     * @param ids ids
     */
    void deletePersonnelContract(String[] ids);

    List<PersonnelContract> queryContractList();

    void contractStateUpdate(Long contractId, int contractState);

    PersonnelContract findContractById(Long id);


    List<PersonnelContract> queryContractTipsList();

    void updateContractTipsState(String[] name);
}

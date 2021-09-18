package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelContract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工合同表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-16 10:46:36
 */
@Mapper
public interface PersonnelContractMapper extends BaseMapper<PersonnelContract> {

    long countPersonnelContract(@Param("personnelContract") PersonnelContract personnelContract);

    IPage<PersonnelContract> findPersonnelContractPage(Page<PersonnelContract> page,@Param("personnelContract") PersonnelContract personnelContract);

    List<PersonnelContract> queryContractList();

    void contractStateUpdate(@Param("contractId") Long contractId,@Param("contractState")int contractState);

    PersonnelContract findContractById(@Param("id") Long id);

    void saveOrUpdate(PersonnelContract personnelContract);

    List<PersonnelContract> queryContractTipsList();

    void updateContractTipsState(@Param("name")String[] name);

    void contractTipsStateUpdate(@Param("contractId") Long contractId,@Param("contractTipsState") int contractTipsState);
}

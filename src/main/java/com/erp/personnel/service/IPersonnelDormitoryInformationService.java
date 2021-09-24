package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitoryInformation;

import java.util.List;

/**
 * 宿舍人员入住信息表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-19 11:43:19
 */
public interface IPersonnelDormitoryInformationService extends IService<PersonnelDormitoryInformation> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelDormitoryInformation personnelDormitoryInformation
     * @return IPage<PersonnelDormitoryInformation>
     */
    IPage<PersonnelDormitoryInformation> findPersonnelDormitoryInformations(QueryRequest request, PersonnelDormitoryInformation personnelDormitoryInformation);

    /**
     * 查询（所有）
     *
     * @param personnelDormitoryInformation personnelDormitoryInformation
     * @return List<PersonnelDormitoryInformation>
     */
    List<PersonnelDormitoryInformation> findPersonnelDormitoryInformations(PersonnelDormitoryInformation personnelDormitoryInformation);

    /**
     * 新增
     *
     * @param personnelDormitoryInformation personnelDormitoryInformation
     */
    void createPersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation);

    /**
     * 修改
     *
     * @param personnelDormitoryInformation personnelDormitoryInformation
     */
    void updatePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation);

    /**
     * 删除
     *
     * @param personnelDormitoryInformation personnelDormitoryInformation
     */
    void deletePersonnelDormitoryInformation(PersonnelDormitoryInformation personnelDormitoryInformation);
}

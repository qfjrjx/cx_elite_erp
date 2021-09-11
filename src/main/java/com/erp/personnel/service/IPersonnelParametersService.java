package com.erp.personnel.service;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelParameters;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.system.entity.User;

import java.util.List;

/**
 * 人事参数表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-03 15:19:47
 */
public interface IPersonnelParametersService extends IService<PersonnelParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelParameters personnelParameters
     * @return IPage<PersonnelParameters>
     */
    IPage<PersonnelParameters> findPersonnelParameterss(QueryRequest request, PersonnelParameters personnelParameters);

    /**
     * 查询（所有）
     *
     *
     * @return List<PersonnelParameters>
     */
    List<PersonnelParameters> queryDuties();

    /**
     * 新增
     *
     * @param personnelParameters personnelParameters
     */
    void createPersonnelParameters(PersonnelParameters personnelParameters);

    /**
     * 修改
     *
     * @param personnelParameters personnelParameters
     */
    void updatePersonnelParameters(PersonnelParameters personnelParameters);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deletePersonnel(String[] ids);

    PersonnelParameters findById(Long id);

    List<PersonnelParameters> queryPosition();

    List<PersonnelParameters> queryTechnical();

    List<PersonnelParameters> queryEducation();

    List<PersonnelParameters> querySocialSecurity();
}

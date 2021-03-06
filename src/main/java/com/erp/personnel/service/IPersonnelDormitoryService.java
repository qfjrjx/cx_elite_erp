package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelDormitoryInformation;

import java.text.ParseException;
import java.util.List;

/**
 * 宿舍管理表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-19 09:35:28
 */
public interface IPersonnelDormitoryService extends IService<PersonnelDormitory> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelDormitory personnelDormitory
     * @return IPage<PersonnelDormitory>
     */
    IPage<PersonnelDormitory> findPersonnelDormitorys(QueryRequest request, PersonnelDormitory personnelDormitory);

    /**
     * 查询（所有）
     *
     * @param personnelDormitory personnelDormitory
     * @return List<PersonnelDormitory>
     */
    List<PersonnelDormitory> findPersonnelDormitorys(PersonnelDormitory personnelDormitory);

    /**
     * 新增
     *
     * @param personnelDormitory personnelDormitory
     */
    void createPersonnelDormitory(PersonnelDormitory personnelDormitory) throws ParseException;

    /**
     * 修改
     *
     * @param personnelDormitory personnelDormitory
     */
    void updatePersonnelDormitory(PersonnelDormitory personnelDormitory) throws ParseException;
    /**
     * 删除
     *
     * @param ids ids
     */
    void deletePersonnelDormitory(String[] ids);

    PersonnelDormitory findDormitoryById(Long id);

}

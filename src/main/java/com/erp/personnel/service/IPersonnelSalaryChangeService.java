package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelSalaryChange;

import java.text.ParseException;
import java.util.List;

/**
 * 调薪记录 Service接口
 *
 * @author qiufeng
 * @date 2021-09-25 10:52:51
 */
public interface IPersonnelSalaryChangeService extends IService<PersonnelSalaryChange> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelSalaryChange personnelSalaryChange
     * @return IPage<PersonnelSalaryChange>
     */
    IPage<PersonnelSalaryChange> findPersonnelSalaryChanges(QueryRequest request, PersonnelSalaryChange personnelSalaryChange);

    /**
     * 查询（所有）
     *
     * @param personnelSalaryChange personnelSalaryChange
     * @return List<PersonnelSalaryChange>
     */
    List<PersonnelSalaryChange> findPersonnelSalaryChanges(PersonnelSalaryChange personnelSalaryChange);

    /**
     * 新增
     *
     * @param personnelSalaryChange personnelSalaryChange
     */
    void createPersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange) throws ParseException;

    /**
     * 修改
     *
     * @param personnelSalaryChange personnelSalaryChange
     */
    void updatePersonnelSalaryChange(PersonnelSalaryChange personnelSalaryChange);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deletePersonnelSalaryChange(String[] ids);
}

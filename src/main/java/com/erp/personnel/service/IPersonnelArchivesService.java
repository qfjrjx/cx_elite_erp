package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;

import java.util.List;

/**
 * 用户表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
public interface IPersonnelArchivesService extends IService<PersonnelArchives> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelArchives personnelArchives
     * @return IPage<PersonnelArchives>
     */
    IPage<PersonnelArchives> findPersonnelArchivess(QueryRequest request, PersonnelArchives personnelArchives);

    /**
     * 查询（所有）
     *
     * @param personnelArchives personnelArchives
     * @return IPage<PersonnelArchives>
     */
    IPage<PersonnelArchives> findPersonnelArchivesList(PersonnelArchives personnelArchives, QueryRequest request);
    /**
     * 新增
     *
     * @param personnelArchives personnelArchives
     */
    void createPersonnelArchives(PersonnelArchives personnelArchives);

    /**
     * 修改
     *
     * @param personnelArchives personnelArchives
     */
    void updatePersonnelArchives(PersonnelArchives personnelArchives);

    /**
     * 删除
     *
     * @param personnelArchives personnelArchives
     */
    void deletePersonnelArchives(PersonnelArchives personnelArchives);



}

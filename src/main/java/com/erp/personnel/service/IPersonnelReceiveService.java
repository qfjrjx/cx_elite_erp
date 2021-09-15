package com.erp.personnel.service;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelReceive;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 领取记录表 Service接口
 *
 * @author qiufeng
 * @date 2021-09-14 15:45:00
 */
public interface IPersonnelReceiveService extends IService<PersonnelReceive> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelReceive personnelReceive
     * @return IPage<PersonnelReceive>
     */
    IPage<PersonnelReceive> findPersonnelReceives(QueryRequest request, PersonnelReceive personnelReceive);

    /**
     * 查询（所有）
     *
     * @param personnelReceive personnelReceive
     * @return List<PersonnelReceive>
     */
    List<PersonnelReceive> findPersonnelReceives(PersonnelReceive personnelReceive);

    /**
     * 新增
     *
     * @param personnelReceive personnelReceive
     */
    void createPersonnelReceive(PersonnelReceive personnelReceive);

    /**
     * 修改
     *
     * @param personnelReceive personnelReceive
     */
    void updatePersonnelReceive(PersonnelReceive personnelReceive);

    /**
     * 删除
     *
     * @param personnelReceive personnelReceive
     */
    void deletePersonnelReceive(PersonnelReceive personnelReceive);

    IPage<PersonnelArchives> findReceiveArchivesList(PersonnelArchives personnelArchives, QueryRequest request);
}

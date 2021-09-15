package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelReceive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 领取记录表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-14 15:45:00
 */
@Mapper
public interface PersonnelReceiveMapper extends BaseMapper<PersonnelReceive> {

    long countPersonnelReceive(@Param("personnelReceive") PersonnelReceive personnelReceive);

    IPage<PersonnelReceive> findPersonnelReceivePage(Page<PersonnelReceive> page,@Param("personnelReceive") PersonnelReceive personnelReceive);

    long countReceiveArchives(@Param("personnelArchives") PersonnelArchives personnelArchives);

    IPage<PersonnelArchives> findReceiveArchivesPage(Page<PersonnelArchives> page,@Param("personnelArchives") PersonnelArchives personnelArchives);
}

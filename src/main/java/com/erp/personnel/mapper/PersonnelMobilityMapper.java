package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelMobility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 调岗记录 Mapper
 *
 * @author qiufeng
 * @date 2021-09-25 08:56:16
 */
@Mapper
public interface PersonnelMobilityMapper extends BaseMapper<PersonnelMobility> {

    long countPersonnelMobility(@Param("personnelMobility") PersonnelMobility personnelMobility);

    IPage<PersonnelMobility> findPersonnelMobilityPage(@Param("personnelMobility")Page<PersonnelMobility> page, PersonnelMobility personnelMobility);

    long countReceiveArchivesMobility(@Param("personnelArchives")PersonnelArchives personnelArchives);

    IPage<PersonnelArchives> findReceiveArchivesMobilityPage(Page<PersonnelArchives> page,@Param("personnelArchives") PersonnelArchives personnelArchives);
}

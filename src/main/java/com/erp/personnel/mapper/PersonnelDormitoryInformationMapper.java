package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelDormitoryInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 宿舍人员入住信息表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-19 11:43:19
 */
@Mapper
public interface PersonnelDormitoryInformationMapper extends BaseMapper<PersonnelDormitoryInformation> {

    long countPersonnelDormitoryInformations(@Param("personnelDormitoryInformation") PersonnelDormitoryInformation personnelDormitoryInformation);

    IPage<PersonnelDormitoryInformation> findPersonnelDormitoryInformationsPage(Page<PersonnelDormitoryInformation> page, @Param("personnelDormitoryInformation") PersonnelDormitoryInformation personnelDormitoryInformation);
}
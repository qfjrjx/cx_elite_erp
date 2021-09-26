package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelSalaryChange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 调薪记录 Mapper
 *
 * @author qiufeng
 * @date 2021-09-25 10:52:51
 */
@Mapper
public interface PersonnelSalaryChangeMapper extends BaseMapper<PersonnelSalaryChange> {


    long countPersonnelSalaryChange(@Param("personnelSalaryChange") PersonnelSalaryChange personnelSalaryChange);

    IPage<PersonnelSalaryChange> findPersonnelSalaryChangePage(Page<PersonnelSalaryChange> page,@Param("personnelSalaryChange") PersonnelSalaryChange personnelSalaryChange);
}

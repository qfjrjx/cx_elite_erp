package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.enterprise.entity.EmployeeAddressBook;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Mapper
public interface PersonnelArchivesMapper extends BaseMapper<PersonnelArchives> {

    IPage<PersonnelArchives> findPersonnelArchivesPage(Page<PersonnelArchives> page, @Param("personnelArchives")PersonnelArchives personnelArchives);

    long countPersonnelArchives(@Param("personnelArchives") PersonnelArchives personnelArchives);

    PersonnelArchives findArchivesById(@Param("userId")Long userId);

    void saveOrUpdate(PersonnelArchives personnelArchives);

    IPage<PersonnelArchives> findPersonnelArchives(Page<PersonnelArchives> page,@Param("personnelArchives") PersonnelArchives personnelArchives);

    void savePersonnelArchives(@Param("personnelArchives") PersonnelArchives personnelArchives);
    //查询出最后一个员工信息
    PersonnelArchives queryPersonnelArchives();

    long countEmployeeAddressBook(@Param("employeeAddressBook") EmployeeAddressBook employeeAddressBook);

    IPage<EmployeeAddressBook> findEmployeeAddressBookPage(Page<EmployeeAddressBook> page,@Param("employeeAddressBook") EmployeeAddressBook employeeAddressBook);
}

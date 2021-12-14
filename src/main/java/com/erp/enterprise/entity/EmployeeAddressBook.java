package com.erp.enterprise.entity;

import lombok.Data;
/**
 * 通讯录 Entity
 *
 * @author qiufeng
 * @date 2021-12-14 10:23:08
 */
@Data
public class EmployeeAddressBook {
    /**
     * 员工工号
     */
    private String jobNumber;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工部门
     */
    private Long deptId;
    /**
     * 员工部门名称
     */
    private String deptName;

    /**
     * 手机号码
     */
    private String phone;


}

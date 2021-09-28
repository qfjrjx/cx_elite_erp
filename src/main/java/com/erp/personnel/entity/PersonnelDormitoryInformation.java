package com.erp.personnel.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 宿舍人员入住信息表 Entity
 *
 * @author qiufeng
 * @date 2021-09-19 14:35:21
 */
@Data
@TableName("jr_personnel_dormitory_information")
public class PersonnelDormitoryInformation {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍编号
     */
    @TableField("dormitory_no")
    private String dormitoryNo;

    /**
     * 宿舍位置
     */
    @TableField("dormitory_location")
    private String dormitoryLocation;

    /**
     * 已用床位
     */
    @TableField("used_beds")
    private Integer usedBeds;

    /**
     * 员工姓名
     */
    @TableField("employee_name")
    private String employeeName;

    /**
     * 员工电话
     */
    @TableField("employee_telephone")
    private String employeeTelephone;

    /**
     * 员工部门
     */
    @TableField("employee_dept")
    private String employeeDept;

    /**
     * 入住时间
     */
    @TableField("check_in_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkInTime;

    /**
     * 宿舍管理列表id
     */
    private Long dormitoryId;

}

package com.erp.personnel.entity;

import java.util.Date;

import com.erp.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
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
@Excel("员工宿舍入住人员表")
public class PersonnelDormitoryInformation {


    /**
     * 宿舍位置 东宿舍
     */
    public static final String DORMITORYLOCATION_MALE = "1";
    /**
     * 宿舍位置 西宿舍
     */
    public static final String DORMITORYLOCATION_FEMALE = "2";
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍编号
     */
    @TableField("dormitory_no")
    @ExcelField(value = "宿舍编号")
    private String dormitoryNo;

    /**
     * 宿舍位置
     */
    @TableField("dormitory_location")
    @ExcelField(value = "宿舍位置", writeConverterExp = "1=东宿舍,2=西宿舍")
    private String dormitoryLocation;

    /**
     * 已用床位
     */
    @TableField("used_beds")
    @ExcelField(value = "已用床位")
    private Integer usedBeds;

    /**
     * 员工姓名
     */
    @TableField("employee_name")
    @ExcelField(value = "员工姓名")
    private String employeeName;

    /**
     * 员工电话
     */
    @TableField("employee_telephone")
    @ExcelField(value = "员工电话")
    private String employeeTelephone;

    /**
     * 员工部门
     */
    @TableField("employee_dept")
    @ExcelField(value = "员工部门")
    private String employeeDept;

    /**
     * 入住时间
     */
    @TableField("check_in_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelField(value = "入住时间", writeConverter = TimeConverter.class)
    private Date checkInTime;

    /**
     * 宿舍管理列表id
     */
    private Long dormitoryId;

}

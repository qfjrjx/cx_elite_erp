package com.erp.personnel.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 调薪记录 Entity
 *
 * @author qiufeng
 * @date 2021-09-25 10:52:51
 */
@Data
@TableName("jr_personnel_salary_change")
public class PersonnelSalaryChange {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 部门
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 岗位
     */
    @TableField("position_name")
    private String positionName;
    /**
     * 职务
     */
    @TableField("grouping_name")
    private String groupingName;

    /**
     * 入职日期
     */
    @TableField("entry_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 调整日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("adjustment_data")
    private Date adjustmentData;

    /**
     * 创建日期
     */
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 1-上调，2-下调
     */
    @TableField("salary_state")
    private Integer salaryState;

    /**
     * 调整基数
     */
    @TableField("adjustment_base")
    private String adjustmentBase;

}

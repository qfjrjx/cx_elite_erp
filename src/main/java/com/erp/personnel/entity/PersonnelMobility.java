package com.erp.personnel.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 调岗记录 Entity
 *
 * @author qiufeng
 * @date 2021-09-25 08:56:16
 */
@Data
@TableName("jr_personnel_mobility")
public class PersonnelMobility {

    /**
     * 编号
     */
    @TableId(value = "Id", type = IdType.AUTO)
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
     * 出生日期
     */
    @TableField("birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 身份证号码
     */
    @TableField("identity_ard")
    private String identityArd;

    /**
     * 原部门
     */
    @TableField("department_id")
    private String departmentId;

    /**
     * 原部门名称
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 原岗位
     */
    @TableField("position_id")
    private String positionId;

    /**
     * 原岗位名称
     */
    @TableField("position_name")
    private String positionName;

    /**
     * 原职务ID
     */
    @TableField("grouping_id")
    private String groupingId;

    /**
     * 原职务名
     */
    @TableField("grouping_name")
    private String groupingName;

    /**
     * 调至部门
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 调至部门名称
     */
    @TableField("to_department_name")
    private String toDepartmentName;

    /**
     * 调至岗位
     */
    @TableField("to_position_id")
    private Long toPositionId;

    /**
     * 调至岗位名称
     */
    @TableField("to_position_name")
    private String toPositionName;

    /**
     * 调至职务ID
     */
    @TableField("to_grouping_id")
    private Long toGroupingId;

    /**
     * 调至职务名
     */
    @TableField("to_grouping_name")
    private String toGroupingName;

    /**
     * 调动生效日期
     */
    @TableField("transfer_data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transferData;

    /**
     * 状态 1.晋升,2.降级,3.岗位调动
     */
    @TableField("transfer_status")
    private Integer transferStatus;

    /**
     * 创建时间
     */
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}

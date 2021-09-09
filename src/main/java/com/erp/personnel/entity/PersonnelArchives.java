package com.erp.personnel.entity;

import java.util.Date;

import com.erp.common.converter.TimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表 Entity
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Data
@TableName("jr_personnel_archives")
public class PersonnelArchives {

    /**
     * 编号
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 员工工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
     * 员工姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 员工性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 员工民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 员工部门
     */
    /**
     * 部门 ID
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 技术级别
     */
    @TableField("technical_id")
    private Long technicalId;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 固定电话
     */
    @TableField("fixed_telephone")
    private String fixedTelephone;

    /**
     * 社保缴纳
     */
    @TableField("social_security")
    private String socialSecurity;

    /**
     * 户口类型
     */
    @TableField("hukou_type")
    private String hukouType;

    /**
     * 家庭地址
     */
    @TableField("address")
    private String address;

    /**
     * 是否考勤      0-否，1-是
     */
    @TableField("attendance")
    private String attendance;

    /**
     * 出生日期
     */
    @TableField("birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    /**
     * 员工籍贯
     */
    @TableField("staff_native")
    private String staffNative;

    /**
     * 身份证号
     */
    @TableField("identity_ard")
    private String identityArd;

    /**
     * 所属岗位
     */
    @TableField("position_id")
    private Long positionId;

    /**
     * 紧急联系人电话
     */
    @TableField("emergency_phone")
    private String emergencyPhone;

    /**
     * 紧急联系人
     */
    @TableField("emergency_user")
    private String emergencyUser;

    /**
     * 现住地址
     */
    @TableField("present_address")
    private String presentAddress;

    /**
     * 所属职务
     */
    @TableField("duties_id")
    private Long dutiesId;

    /**
     * 员工学历
     */
    @TableField("education_id")
    private Long educationId;

    /**
     * 毕业院校
     */
    @TableField("school_by")
    private String schoolBy;

    /**
     * 婚姻情况
     */
    @TableField("marriage")
    private String marriage;

    /**
     * 入职日期
     */
    @TableField("entry_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    /**
     * 离职日期
     */
    @TableField("quit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date quitDate;

    /**
     * 在职状态 1表示在职，2表示离职,3休假
     */
    @TableField("user_state")
    private String userState;

    /**
     * 员工照片
     */
    @TableField("user_img")
    private String userImg;

    /**
     * 教育经历
     */
    @TableField("education_undergo")
    private String educationUndergo;

    /**
     * 工作经历
     */
    @TableField("occupational")
    private String occupational;

    /**
     * 员工年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 转正日期
     */
    @TableField("positive_dates")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date positiveDates;

    /**
     * 员工专业
     */
    @TableField("specialty")
    private String specialty;

    /**
     * 工资开户行
     */
    @TableField("payroll_bank")
    private String payrollBank;

    /**
     * 工资账号
     */
    @TableField("bank_card")
    private String bankCard;

    /**
     * 孝善奖账号
     */
    @TableField("filiality_fine_card")
    private String filialityFineCard;

    /**
     * 孝善奖开户行
     */
    @TableField("filiality_fine_bank")
    private String filialityFineBank;

    /**
     * 部门名称
     */
    @ExcelField(value = "部门")
    @TableField(exist = false)
    private String deptName;

    /**
     * 所属岗位
     */
    @ExcelField(value = "岗位")
    @TableField(exist = false)
    private String positionName;

    /**
     * 所属职务
     */
    @ExcelField(value = "职务")
    @TableField(exist = false)
    private String dutiesName;
    /**
     * 员工学历
     */
    @ExcelField(value = "学历")
    @TableField(exist = false)
    private String educationName;

}

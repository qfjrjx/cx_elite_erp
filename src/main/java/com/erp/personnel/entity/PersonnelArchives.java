package com.erp.personnel.entity;

import java.io.Serializable;
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

import javax.validation.constraints.NotBlank;

/**
 * 用户表 Entity
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Data
@TableName("jr_personnel_archives")
@Excel("员工档案信息表")
public class PersonnelArchives implements Serializable, Cloneable{

    /**
     * 编号
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 员工工号
     */
    @TableField("job_number")
    @ExcelField(value = "员工工号")
    private String jobNumber;

    /**
     * 员工姓名
     */
    @TableField("user_name")
    @ExcelField(value = "员工姓名")
    private String userName;

    /**
     * 员工性别
     */
    @TableField("gender")
    @ExcelField(value = "员工性别", writeConverterExp = "1=男,2=女")
    private String gender;

    /**
     * 员工民族
     */
    @TableField("nation")
    @ExcelField(value = "员工民族")
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
    @ExcelField(value = "手机号码")
    private String phone;

    /**
     * 固定电话
     */
    @TableField("fixed_telephone")
    @ExcelField(value = "固定电话")
    private String fixedTelephone;

    @TableField(exist = false)
    private String id;
    /**
     * 社保缴纳
     */
    @ExcelField(value = "社保缴纳")
    private String parameterName;

    /**
     * 户口类型
     */
    @TableField("hukou_type")
    @ExcelField(value = "户口类型", writeConverterExp = "1=城镇,2=农村")
    private String hukouType;

    /**
     * 家庭地址
     */
    @TableField("address")
    @ExcelField(value = "家庭地址")
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
    @ExcelField(value = "出生日期", writeConverter = TimeConverter.class)
    private Date birthdate;

    /**
     * 员工籍贯
     */
    @TableField("staff_native")
    @ExcelField(value = "员工籍贯")
    private String staffNative;

    /**
     * 身份证号
     */
    @TableField("identity_ard")
    @ExcelField(value = "身份证号")
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
    @ExcelField(value = "紧急联系人电话")
    private String emergencyPhone;

    /**
     * 紧急联系人
     */
    @TableField("emergency_user")
    @ExcelField(value = "紧急联系人")
    private String emergencyUser;

    /**
     * 现住地址
     */
    @TableField("present_address")
    @ExcelField(value = "现住地址")
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
    @ExcelField(value = "毕业院校")
    private String schoolBy;

    /**
     * 婚姻情况
     */
    @TableField("marriage")
    @ExcelField(value = "婚姻情况", writeConverterExp = "1=未婚,2=已婚,3=离异")
    private String marriage;

    /**
     * 入职日期
     */
    @TableField("entry_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "入职日期", writeConverter = TimeConverter.class)
    private Date entryDate;

    /**
     * 离职日期
     */
    @TableField("quit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "离职日期", writeConverter = TimeConverter.class)
    private Date quitDate;

    /**
     * 在职状态 1表示在职，2表示离职,3休假
     */
    @TableField("user_state")
    @ExcelField(value = "在职状态", writeConverterExp = "1=在职,2=离职,3=休假")
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
    @ExcelField(value = "教育经历")
    private String educationUndergo;

    /**
     * 工作经历
     */
    @TableField("occupational")
    @ExcelField(value = "工作经历")
    private String occupational;

    /**
     * 员工年龄
     */
    @TableField("age")
    @ExcelField(value = "员工年龄")
    private Integer age;

    /**
     * 转正日期
     */
    @TableField("positive_dates")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "转正日期", writeConverter = TimeConverter.class)
    private Date positiveDates;

    /**
     * 员工专业
     */
    @TableField("specialty")
    @ExcelField(value = "员工专业")
    private String specialty;

    /**
     * 工资开户行
     */
    @TableField("payroll_bank")
    @ExcelField(value = "工资开户行")
    private String payrollBank;

    /**
     * 工资账号
     */
    @TableField("bank_card")
    @ExcelField(value = "工资账号")
    private String bankCard;

    /**
     * 孝善奖账号
     */
    @TableField("filiality_fine_card")
    @ExcelField(value = "孝善奖账号")
    private String filialityFineCard;

    /**
     * 孝善奖开户行
     */
    @TableField("filiality_fine_bank")
    @ExcelField(value = "孝善奖开户行")
    private String filialityFineBank;

    /**
     * 部门名称
     */
    @ExcelField(value = "员工部门")
    @TableField(exist = false)
    private String deptName;

    /**
     * 所属岗位
     */
    @ExcelField(value = "员工岗位")
    @TableField(exist = false)
    private String positionName;

    /**
     * 所属职务
     */
    @ExcelField(value = "员工职务")
    @TableField(exist = false)
    private String dutiesName;
    /**
     * 员工学历
     */
    @ExcelField(value = "员工学历")
    @TableField(exist = false)
    private String educationName;

}

package com.erp.personnel.entity;

import java.io.Serializable;
import java.util.Date;

import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;

/**
 * 人事参数表 Entity
 *
 * @author qiufeng
 * @date 2021-09-03 15:19:47
 */
@Data
@TableName("jr_personnel_parameters")
public class PersonnelParameters implements Serializable {

    /**
     * 人事参数id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别    1-岗位 ，2-技术级别，3-加班类型，4-请假类型，5-员工学历，6-职务，7-人事，8-状态
     */
    @TableField("parameter_category")
    @NotBlank(message = "{required}")
    @ExcelField(value = "性别", writeConverterExp = "1=岗位,2=技术级别,3=加班类型,4=请假类型,5=员工学历,6=职务,7=人事,8=状态")
    private String parameterCategory;

    /**
     * 参数值
     */
    @TableField("parameter_value")
    private String parameterValue;

    /**
     * 参数名称
     */
    @TableField("parameter_name")
    private String parameterName;

    /**
     * 排序
     */
    @TableField("parameter_sort")
    private Long parameterSort;

    /**
     * 备注
     */
    @TableField("parameter_remarks")
    private String parameterRemarks;

    /**
     * 状态   1-启用，2-禁用
     */
    @TableField("parameter_state")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "1=启用,2=禁用")
    private String parameterState;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}

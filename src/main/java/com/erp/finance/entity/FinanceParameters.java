package com.erp.finance.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 财务参数表 Entity
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
@Data
@TableName("jr_finance_parameters")
public class FinanceParameters {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别
     */
    @TableField("finance_parameter_category")
    private String financeParameterCategory;

    /**
     * 参数值
     */
    @TableField("finance_parameter_value")
    private String financeParameterValue;

    /**
     * 参数名称
     */
    @TableField("finance_parameter_name")
    private String financeParameterName;

    /**
     * 排序
     */
    @TableField("finance_sort")
    private Integer financeSort;

    /**
     * 状态
     */
    @TableField("finance_state")
    private String financeState;

    /**
     * 备注
     */
    @TableField("finance_remarks")
    private String financeRemarks;

    /**
     * 创建人
     */
    @TableField("finance_creator")
    private String financeCreator;

    /**
     * 创建时间
     */
    @TableField("finance_creation_time")
    private Date financeCreationTime;

}

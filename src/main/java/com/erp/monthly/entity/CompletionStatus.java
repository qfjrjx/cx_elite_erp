package com.erp.monthly.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 完成情况表 Entity
 *
 * @author qiufeng
 * @date 2021-12-17 14:06:34
 */
@Data
@TableName("jr_completion_status")
public class CompletionStatus {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 月份
     */
    @TableField("completion_monthly")
    private String completionMonthly;

    /**
     * 销售目标金额
     */
    @TableField("sales_target_amount")
    private BigDecimal salesTargetAmount;
    /**
     * 销售完成金额
     */
    @TableField("sales_completion_amount")
    private BigDecimal salesCompletionAmount;
    /**
     * 销售完成率
     */
    @TableField("sales_completion_rate")
    private String salesCompletionRate;

    /**
     * 技术目标金额
     */
    @TableField("technical_target_amount")
    private BigDecimal technicalTargetAmount;
    /**
     * 技术完成金额
     */
    @TableField("technical_completion_amount")
    private BigDecimal technicalCompletionAmount;
    /**
     * 技术完成率
     */
    @TableField("technical_completion_rate")
    private String technicalCompletionRate;

    /**
     * 生产目标金额
     */
    @TableField("production_target_amount")
    private BigDecimal productionTargetAmount;
    /**
     * 生产完成金额
     */
    @TableField("production_completion_amount")
    private BigDecimal productionCompletionAmount;
    /**
     * 生产完成率
     */
    @TableField("production_completion_rate")
    private String productionCompletionRate;

    /**
     * 发货目标金额
     */
    @TableField("delivery_target_amount")
    private BigDecimal deliveryTargetAmount;
    /**
     * 发货完成金额
     */
    @TableField("delivery_completion_amount")
    private BigDecimal deliveryCompletionAmount;
    /**
     * 发货完成率
     */
    @TableField("delivery_completion_rate")
    private String deliveryCompletionRate;

}

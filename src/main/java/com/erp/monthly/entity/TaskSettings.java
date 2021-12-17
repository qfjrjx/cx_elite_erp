package com.erp.monthly.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 任务设置表  Entity
 *
 * @author qiufeng
 * @date 2021-12-16 17:12:06
 */
@Data
@TableName("jr_task_settings")
public class TaskSettings {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 月份
     */
    @TableField("task_settings_monthly")
    private String taskSettingsMonthly;

    /**
     * 销售目标
     */
    @TableField("sales_target")
    private String salesTarget;

    /**
     * 设计目标
     */
    @TableField("design_goal")
    private String designGoal;

    /**
     * 生产目标
     */
    @TableField("production_target")
    private String productionTarget;

    /**
     * 发货目标
     */
    @TableField("delivery_target")
    private String deliveryTarget;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

}

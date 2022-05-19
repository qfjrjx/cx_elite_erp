package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 生产计划附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-02 17:57:59
 */
@Data
@TableName("jr_production_plan_schedule")
public class ProductionPlanSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 销售订单
     */
    @TableField("plan_order")
    private String planOrder;

    /**
     * 数量
     */
    @TableField("plan_amount")
    private String planAmount;

    /**
     * 机器要求
     */
    @TableField("plan_machine")
    private String planMachine;

    /**
     * 配置
     */
    @TableField("plan_configuration")
    private String planConfiguration;

    private String configureName;

    /**
     * 刀具大小
     */
    @TableField("plan_cutting")
    private String planCutting;

    /**
     * 加工工序
     */
    @TableField("plan_process")
    private String planProcess;

    /**
     * 其他要求
     */
    @TableField("plan_other")
    private String planOther;

    /**
     * 夹具要求
     */
    @TableField("plan_jig")
    private String planJig;

    /**
     * 产品形状
     */
    @TableField("plan_shape")
    private String planShape;

    /**
     * 产品尺寸
     */
    @TableField("plan_size")
    private String planSize;

    /**
     * 备注
     */
    @TableField("plan_note")
    private String planNote;

    /**
     * 附件
     */
    @TableField("plan_attachment")
    private String planAttachment;

    /**
     * 订单号
     */
    @TableField("plan_number")
    private String planNumber;

    /**
     * 交货日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("plan_date")
    private Date planDate;

    //private Date orderDate;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 规格型号
     */
    private String specificationModel;

    /**
     * 制单人
     */
    private String userName;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 产品名称
     */
    private String planProductName;

    /**
     * 规格型号
     */
    private String planSpecifications;

    /**
     * 机器码
     */
    private String planCode;

}

package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 生产计划 Entity
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
@Data
@TableName("jr_production_plan")
public class ProductionPlan {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态：1 登记 2 完成
     */
    @TableField("plan_state")
    private String planState;

    /**
     * 交货日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("plan_date")
    private Date planDate;

    /**
     * 订单号
     */
    @TableField("plan_number")
    private String planNumber;

    /**
     * 客户名称
     */
    @TableField("plan_customer_name")
    private String planCustomerName;

    /**
     * 产品名称
     */
    @TableField("plan_product_name")
    private String planProductName;

    /**
     * 规格型号
     */
    @TableField("plan_specifications")
    private String planSpecifications;

    /**
     * 机器码
     */
    @TableField("plan_code")
    private String planCode;

    /**
     * 配置
     */
    @TableField("plan_configuration")
    private String planConfiguration;

    private String configureName;

    /**
     * 机器BOM
     */
    @TableField("plan_machine_bom")
    private String planMachineBom;

    /**
     * 发货单号
     */
    @TableField("plan_invoice")
    private String planInvoice;

    /**
     * 出厂编码
     */
    @TableField("plan_factory")
    private String planFactory;

    /**
     * 机加附件
     */
    @TableField("plan_machining")
    private String planMachining;

    /**
     * 装配附件
     */
    @TableField("plan_assembly")
    private String planAssembly;

    /**
     * 说明1
     */
    @TableField("plan_instructions_one")
    private String planInstructionsOne;

    /**
     * 说明2
     */
    @TableField("plan_instructions_two")
    private String planInstructionsTwo;

    /**
     * 说明3
     */
    @TableField("plan_instructions_three")
    private String planInstructionsThree;

    /**
     * 制单人
     */
    @TableField("plan_voucher")
    private String planVoucher;

    /**
     * 制单时间
     */
    @TableField("plan_voucher_date")
    private Date planVoucherDate;

    /**
     * 备注
     */
    @TableField("plan_note")
    private String planNote;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

package com.erp.sale.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售订单附表 Entity
 *
 * @author qiufeng
 * @date 2022-04-18 17:08:44
 */
@Data
@TableName("jr_sale_order_schedule")
public class SaleOrderSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1-登记，2-确认，3-审核，4-核准,5-生产回复
     */
    @TableField("order_state")
    private String orderState;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModel;

    /**
     * 配置
     */
    @TableField("configure_name")
    private String configureName;

    /**
     * 单位
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 数量
     */
    @TableField("quantity_name")
    private String quantityName;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    /**
     * 金额
     */
    @TableField("amount_money")
    private BigDecimal amountMoney;
    /**
     * 交货日期
     */
    @TableField("delivery_date")
    private Date deliveryDate;

    /**
     * 备注
     */
    @TableField("order_remarks")
    private String orderRemarks;

    /**
     * 机器要求
     */
    @TableField("machine_requirements")
    private String machineRequirements;

    /**
     * 电脑配置
     */
    @TableField("computer_configuration")
    private String computerConfiguration;

    /**
     * 刀具大小
     */
    @TableField("tool_size")
    private String toolSize;

    /**
     * 每小时产量
     */
    @TableField("hourly_production")
    private String hourlyProduction;

    /**
     * 加工工序
     */
    @TableField("processing_procedure")
    private String processingProcedure;

    /**
     * 夹具要求
     */
    @TableField("fixture_requirements")
    private String fixtureRequirements;

    /**
     * 产品形状
     */
    @TableField("product_shape")
    private String productShape;

    /**
     * 产品尺寸
     */
    @TableField("product_size")
    private String productSize;

    /**
     * 其他要求
     */
    @TableField("other_requirements")
    private String otherRequirements;

}

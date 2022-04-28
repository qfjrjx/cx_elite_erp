package com.erp.sale.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售订单表 Entity
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Data
public class SaleOrderAll {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态  1-登记，2-确认，3-审核，4-核准,5-生产回复
     */
    @TableField("order_state")
    private String orderState;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 日期
     */
    @TableField("order_date")
    private Date orderDate;

    private transient String startTimeFrom;
    private transient String endTimeTo;


    /**
     * 关联客户档案id
     */
    @TableField("customer_id")
    private Long customerId;

    /**
     * 客户
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 业务员
     */
    @TableField("salesman_name")
    private String salesmanName;

    /**
     * 币种
     */
    @TableField("currency_id")
    private Long currencyId;

    private String currencyName;

    /**
     * 税率
     */
    @TableField("tax_rate_id")
    private Long taxRateId;
    private String taxRate;

    /**
     * 付款方式
     */
    @TableField("payment_method_id")
    private Long paymentMethodId;

    private String paymentMethod;

    /**
     * 订金
     */
    @TableField("deposit_money")
    private BigDecimal depositMoney;
    /**
     * 是否开票       1-是，2-否
     */
    @TableField("invoice_not")
    private Integer invoiceNot;

    /**
     * 联系人
     */
    @TableField("contacts_name")
    private String contactsName;

    /**
     * 手机
     */
    @TableField("mobile_phone")
    private String mobilePhone;

    /**
     * 附件
     */
    @TableField("enclosure_name")
    private String enclosureName;

    /**
     * 订单类型          1-配件，2-整机
     */
    @TableField("order_type")
    private Integer orderType;

    /**
     售后员
     */
    @TableField("after_sales_clerk")
    private String afterSalesClerk;

    /**
     附表id
     */
    private Long orderScheduleId;

    /**
      产品名称
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
     * 

金额
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
     电脑配置
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

    /**
     * 日期
     */
    @TableField("create_date")
    private Date createDate;

}

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
 * @date 2022-04-18 17:08:40
 */
@Data
@TableName("jr_sale_order")
public class SaleOrder {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 售后员
     */
    @TableField("after_sales_clerk")
    private String afterSalesClerk;

    /**
     * 订单创建时间
     */
    @TableField("create_date")
    private Date createDate;

}

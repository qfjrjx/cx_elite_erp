package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购付款_附表 Entity
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:47
 */
@Data
@TableName("jr_purchase_payment_schedule")
public class PurchasePaymentSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    @TableField("invoice_date")
    private Date invoiceDate;

    /**
     * 发票号
     */
    @TableField("invoice_numbers")
    private String invoiceNumbers;

    /**
     * 金额
     */
    @TableField("invoice_money")
    private BigDecimal invoiceMoney;
    /**
     * 已付金额
     */
    @TableField("prepaid_money")
    private BigDecimal prepaidMoney;
    /**
     * 本次付款
     */
    @TableField("the_payment")
    private BigDecimal thePayment;
    /**
     * 采购单号
     */
    @TableField("payment_code")
    private String paymentCode;

    /**
     * 付款单号
     */
    @TableField("payment_number")
    private String paymentNumber;

    /**
     * 物料名称
     */
    @TableField("invoice_name")
    private String invoiceName;

    /**
     * 物料编码
     */
    @TableField("invoice_coding")
    private String invoiceCoding;

    /**
     * 品牌
     */
    @TableField("invoice_brand")
    private String invoiceBrand;

    /**
     * 规格
     */
    @TableField("invoice_specifications")
    private String invoiceSpecifications;

    /**
     * 单位
     */
    @TableField("invoice_company")
    private String invoiceCompany;

    /**
     * 数量
     */
    @TableField("invoice_quantity")
    private String invoiceQuantity;

    /**
     * 单价
     */
    @TableField("tax_price")
    private BigDecimal taxPrice;
    /**
     * 金额
     */
    @TableField("tax_money")
    private BigDecimal taxMoney;

    /**
     * 上次采购单价
     */
    @TableField("procurement_price")
    private BigDecimal procurementPrice;

    /**
     * 前次采购单价
     */
    @TableField("previous_price")
    private BigDecimal previousPrice;

    /**
     * 备注
     */
    @TableField("invoice_remarks")
    private String invoiceRemarks;

    /**
     * 备注
     */
    @TableField("inspection_subclass")
    private String inspectionSubclass;

}


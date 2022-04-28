package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购发票_附表 Entity
 *
 * @author qiufeng
 * @date 2022-04-06 09:35:05
 */
@Data
@TableName("jr_purchase_invoice_schedule")
public class PurchaseInvoiceSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 开票日期
     */
    @TableField("invoice_date")
    private Date invoiceDate;

    /**
     * 单号
     */
    @TableField("invoice_code")
    private String invoiceCode;

    /**
     * 口径
     */
    @TableField("invoice_use")
    private String invoiceUse;

    /**
     * 物料名称
     */
    @TableField("invoice_name")
    private String invoiceName;

    /**
     * 规格型号
     */
    @TableField("invoice_specifications")
    private String invoiceSpecifications;

    /**
     * 数量
     */
    @TableField("invoice_quantity")
    private String invoiceQuantity;

    /**
     * 含税单价
     */
    @TableField("tax_price")
    private BigDecimal taxPrice;
    /**
     * 含税金额
     */
    @TableField("tax_money")
    private BigDecimal taxMoney;
    /**
     * 不含税单价
     */
    @TableField("tax_no_price")
    private BigDecimal taxNoPrice;
    /**
     * 不含税金额
     */
    @TableField("tax_no_money")
    private BigDecimal taxNoMoney;
    /**
     * 开票数量
     */
    @TableField("invoice_amount")
    private String invoiceAmount;

    /**
     * 金额
     */
    @TableField("invoice_money")
    private BigDecimal invoiceMoney;

    /**
     * 发票号
     */
    @TableField("invoice_numbers")
    private String invoiceNumbers;

    /**
     * 已付定金
     */
    @TableField("invoice_deposit")
    private BigDecimal invoiceDeposit;

    /**
     * 采购订单
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 品牌
     */
    @TableField("invoice_brand")
    private String invoiceBrand;

    /**
     * 单位
     */
    @TableField("invoice_company")
    private String invoiceCompany;

    /**
     * 物料编码
     */
    @TableField("invoice_coding")
    private String invoiceCoding;

    /**
     * 小类
     */
    @TableField("invoice_subclass")
    private String invoiceSubclass;

    private String invoiceSupplier;

    private String invoiceRemarks;
}

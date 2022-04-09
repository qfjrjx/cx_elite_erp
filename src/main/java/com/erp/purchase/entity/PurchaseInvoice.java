package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购发票 Entity
 *
 * @author qiufeng
 * @date 2022-04-06 09:34:31
 */
@Data
@TableName("jr_purchase_invoice")
public class PurchaseInvoice {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    @TableField("invoice_state")
    private String invoiceState;

    /**
     * 开票日期
     */
    @TableField("invoice_date")
    private Date invoiceDate;

    /**
     * 发票号
     */
    @TableField("invoice_numbers")
    private String invoiceNumbers;

    /**
     * 单位
     */
    @TableField("invoice_supplier")
    private String invoiceSupplier;

    /**
     * 金额
     */
    private BigDecimal invoiceMoney;
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

    private String taxRateName;

    /**
     * 税额
     */
    @TableField("tax_rate_money")
    private BigDecimal taxRateMoney;
    /**
     * 登记人
     */
    @TableField("registration")
    private String registration;

    /**
     * 登记日期
     */
    @TableField("registration_date")
    private Date registrationDate;

    /**
     * 备注
     */
    @TableField("invoice_remarks")
    private String invoiceRemarks;

    /**
     *日期分类
     */
    @TableField("invoice_date_state")
    private String invoiceDateState;

    /**
     * 入库数量总数
     */
    private String invoiceQuantitySum;

    /**
     * 税前金额总数
     */
    private String taxMoneySum;

    /**
     *开票数量
     */
    private String invoiceAmount;
}

package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Entity
 *
 * @author qiufeng
 * @date 2022-04-02 15:02:26
 */
@Data
@TableName("jr_purchase_settlement")
public class PurchaseSettlement {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    @TableField("settlement_state")
    private String settlementState;

    /**
     * 日期
     */
    @TableField("settlement_date")
    private Date settlementDate;

    /**
     * 单号
     */
    @TableField("settlement_numbers")
    private String settlementNumbers;

    /**
     * 口径
     */
    @TableField("settlement_use")
    private String settlementUse;

    /**
     * 供应商
     */
    @TableField("settlement_supplier")
    private String settlementSupplier;

    /**
     * 库房
     */
    @TableField("settlement_library")
    private String settlementLibrary;

    /**
     * 数量
     */
    @TableField("settlement_quantity")
    private String settlementQuantity;

    /**
     * 金额
     */
    @TableField("settlement_money")
    private BigDecimal settlementMoney;
    /**
     * 不含税金额
     */
    @TableField("settlement_no_money")
    private BigDecimal settlementNoMoney;
    /**
     * 币种
     */
    @TableField("currency_id")
    private Long currencyId;

    private String currencyName;
    /**
     * 是否开票
     */
    @TableField("settlement_invoice")
    private Long settlementInvoice;

    /**
     * 税率
     */
    @TableField("tax_rate_id")
    private Long taxRateId;

    private String taxRateName;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

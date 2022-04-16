package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 采购结算_附表 Entity
 *
 * @author qiufeng
 * @date 2022-04-02 15:03:00
 */
@Data
@TableName("jr_purchase_settlement_schedule")
public class PurchaseSettlementSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("settlement_code")
    private String settlementCode;

    /**
     * 名称
     */
    @TableField("settlement_name")
    private String settlementName;

    /**
     * 规格
     */
    @TableField("settlement_specifications")
    private String settlementSpecifications;

    /**
     * 材质
     */
    @TableField("settlement_quality")
    private String settlementQuality;

    /**
     * 品牌
     */
    @TableField("settlement_brand")
    private String settlementBrand;

    /**
     * 单位
     */
    @TableField("settlement_company")
    private String settlementCompany;

    /**
     * 数量
     */
    @TableField("settlement_quantity")
    private Long settlementQuantity;

    /**
     * 单价
     */
    @TableField("settlement_price")
    private BigDecimal settlementPrice;
    /**
     * 金额
     */
    @TableField("settlement_money")
    private BigDecimal settlementMoney;
    /**
     * 大类
     */
    @TableField("settlement_category")
    private String settlementCategory;

    /**
     * 小类
     */
    @TableField("settlement_subclass")
    private String settlementSubclass;

    /**
     * 备注
     */
    @TableField("settlement_remarks")
    private String settlementRemarks;

    /**
     * 采购结算单号
     */
    @TableField("settlement_number")
    private String settlementNumber;

    /**
     * 已付定金
     */
    @TableField("settlement_deposit")
    private BigDecimal settlementDeposit;

    /**
     * 采购结算单号
     */
    @TableField("order_number")
    private String orderNumber;


    /**
     *日期
     */
    private String settlementDate;

    /**
     *口径
     */
    private String settlementUse;

    /**
     *供应商
     */
    private String settlementSupplier;
}

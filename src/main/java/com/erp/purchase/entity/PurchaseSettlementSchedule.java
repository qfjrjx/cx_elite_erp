package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购结算_附表 Entity
 *
 * @author qiufeng
 * @date 2022-04-02 15:03:00
 */
@Data
@Excel("收货统计")
@TableName("jr_purchase_settlement_schedule")
public class PurchaseSettlementSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *日期
     */
    @ExcelField(value = "日期", dateFormat = "yyyy-MM-dd")
    private Date settlementDate;

    /**
     * 采购结算单号
     */
    @ExcelField(value = "收货单号")
    @TableField("settlement_number")
    private String settlementNumber;

    /**
     *口径
     */
    @ExcelField(value = "口径", writeConverterExp = "1=采购退回,2=采购入库")
    private String settlementUse;

    /**
     *供应商
     */
    @ExcelField(value = "供应商")
    private String settlementSupplier;

    /**
     * 库房
     */
    @ExcelField(value = "库房")
    private String settlementLibrary;

    /**
     * 编码
     */
    @ExcelField(value = "编码")
    @TableField("settlement_code")
    private String settlementCode;

    /**
     * 名称
     */
    @ExcelField(value = "名称")
    @TableField("settlement_name")
    private String settlementName;

    /**
     * 规格
     */
    @ExcelField(value = "规格")
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
    @ExcelField(value = "品牌")
    @TableField("settlement_brand")
    private String settlementBrand;

    /**
     * 单位
     */
    @ExcelField(value = "单位")
    @TableField("settlement_company")
    private String settlementCompany;

    /**
     * 币种
     */
    @ExcelField(value = "币种")
    private String currencyName;

    private String currencyId;

    /**
     * 数量
     */
    @ExcelField(value = "数量")
    @TableField("settlement_quantity")
    private Long settlementQuantity;

    /**
     * 单价
     */
    @ExcelField(value = "单价")
    @TableField("settlement_price")
    private BigDecimal settlementPrice;
    /**
     * 金额
     */
    @ExcelField(value = "金额")
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
    @ExcelField(value = "备注")
    @TableField("settlement_remarks")
    private String settlementRemarks;

    /**
     * 已付定金
     */
    @TableField("settlement_deposit")
    private BigDecimal settlementDeposit;

    /**
     * 采购订单
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 是否开票
     */
    @TableField("whether_invoice")
    private String whetherInvoice;

    /**
     * 不含税金额
     */
    private String settlementNoMoney;

    /**
     * 不含税单价
     */
    private String settlementNoPrice;

    private transient String signedDateFrom;
    private transient String signedDateTo;
}

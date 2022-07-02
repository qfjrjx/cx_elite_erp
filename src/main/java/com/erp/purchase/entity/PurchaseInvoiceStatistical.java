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
@Excel("发票统计")
@TableName("jr_purchase_settlement_schedule")
public class PurchaseInvoiceStatistical {

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
    @TableField("settlement_number")
    private String settlementNumber;

    /**
     * 发票号
     */
    @ExcelField(value = "发票号")
    private String invoiceNumbers;

    /**
     *供应商
     */
    @ExcelField(value = "供应商")
    private String settlementSupplier;


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
    @TableField("settlement_brand")
    private String settlementBrand;

    /**
     * 币种
     */
    private String currencyName;

    private String currencyId;

    /**
     * 数量
     */
    @ExcelField(value = "入库数量")
    @TableField("settlement_quantity")
    private Long settlementQuantity;

    /**
     * 开票数量
     */
    @ExcelField(value = "开票数量")
    private String invoiceAmount;

    /**
     * 未票数量
     */
    @ExcelField(value = "未票数量")
    private String notInvoice;

    /**
     * 单位
     */
    @ExcelField(value = "计量单位")
    @TableField("settlement_company")
    private String settlementCompany;

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

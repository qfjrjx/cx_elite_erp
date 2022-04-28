package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购订单表 Entity
 *
 * @author qiufeng
 * @date 2022-02-20 14:25:03
 */
@Data
@TableName("jr_purchase_order")
public class PurchaseOrder {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 1-登记，2-确认，3-核准
     */
    @TableField("order_state")
    private String orderState;

    /**
     * 日期
     */
    @TableField("purchase_requisition_date")
    private Date purchaseRequisitionDate;

    /**
     * 订单号
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;

    private String supplierNameList;

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
     * 付款方式  1-预付，2-到付
     */
    @TableField("payment_method")
    private String paymentMethod;

    /**
     * 定金
     */
    @TableField("order_deposit")
    private BigDecimal orderDeposit;

    /**
     * 是否开票  1-是，2-否
     */
    @TableField("invoice_billing_situation")
    private String invoiceBillingSituation;

    /**
     * 制单人
     */
    @TableField("order_preparer")
    private String orderPreparer;

    /**
     * 制单日期
     */
    @TableField("order_preparation_date")
    private Date orderPreparationDate;

    /**
     * 订购数量
     */
    private Integer orderQuantity;

    /**
     * 已付金额
     */
    private BigDecimal amountPaid;

    /**
     * 未付金额
     */
    private BigDecimal unpaidAmount;
    /**
     * 金额
     */
    private BigDecimal orderMoney;
    /**
     * 已付定金
     */
    private BigDecimal depositPaid;


    /**
     * 编码
     */
    private String orderCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 规格
     */
    private String orderSpecifications;

    /**
     * 材质
     */
    private String orderQuality;

    /**
     * 品牌
     */
    private String orderBrand;

    /**
     * 单位
     */
    private String orderCompany;


    /**
     * 单价
     */
    private BigDecimal unitPrice;


    /**
     * 备注
     */
    private String orderRemarks;

    /**
     * 小类
     */
    private String orderSubclass;

    /**
     * 大类
     */
    private String orderCategory;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

package com.erp.purchase.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 采购退货_附表 Entity
 *
 * @author qiufeng
 * @date 2022-03-29 08:45:02
 */
@Data
@TableName("jr_purchase_refund_schedule")
public class PurchaseRefundSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("refund_code")
    private String refundCode;

    /**
     * 名称
     */
    @TableField("refund_name")
    private String refundName;

    /**
     * 规格
     */
    @TableField("refund_specifications")
    private String refundSpecifications;

    /**
     * 材质
     */
    @TableField("refund_quality")
    private String refundQuality;

    /**
     * 品牌
     */
    @TableField("refund_brand")
    private String refundBrand;

    /**
     * 单位
     */
    @TableField("refund_company")
    private String refundCompany;

    /**
     * 数量
     */
    @TableField("refund_quantity")
    private Long refundQuantity;

    /**
     * 单价
     */
    @TableField("refund_price")
    private BigDecimal refundPrice;

    /**
     * 金额
     */
    @TableField("refund_money")
    private BigDecimal refundMoney;

    /**
     * 采购订单
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 备注
     */
    @TableField("refund_remarks")
    private String refundRemarks;

    /**
     * 备注
     */
    @TableField("refund_number")
    private String refundNumber;

    /**
     * 库房
     */
    @TableField("refund_library")
    private String refundLibrary;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;

    /**
     * 小类
     */
    @TableField("refund_subclass")
    private String refundSubclass;

    /**
     * 大类
     */
    @TableField("refund_category")
    private String refundCategory;
}

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
 * @date 2022-02-20 14:25:09
 */
@Data
@TableName("jr_purchase_order_schedule")
public class PurchaseOrderSchedule {

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
     * 申请单号
     */
    @TableField("application_no")
    private String applicationNo;

    /**
     * 编码
     */
    @TableField("order_code")
    private String orderCode;

    /**
     * 物料名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 规格
     */
    @TableField("order_specifications")
    private String orderSpecifications;

    /**
     * 材质
     */
    @TableField("order_quality")
    private String orderQuality;

    /**
     * 品牌
     */
    @TableField("order_brand")
    private String orderBrand;

    /**
     * 单位
     */
    @TableField("order_company")
    private String orderCompany;

    /**
     * 数量
     */
    @TableField("order_quantity")
    private Integer orderQuantity;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    /**
     * 金额
     */
    @TableField("order_money")
    private BigDecimal orderMoney;
    /**
     * 交货日期
     */
    @TableField("delivery_date")
    private Date deliveryDate;

    /**
     * 备注
     */
    @TableField("order_remarks")
    private String orderRemarks;

    /**
     * 小类
     */
    @TableField("order_subclass")
    private String orderSubclass;

    /**
     * 大类
     */
    @TableField("order_category")
    private String orderCategory;

    /**
     * 库位
     */
    @TableField("order_location")
    private String orderLocation;

}

package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 采购申请表附表 Entity
 *
 * @author qiufeng
 * @date 2022-02-17 15:25:34
 */
@Data
@TableName("jr_purchase_requisition_schedule")
public class PurchaseRequisitionSchedule {

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 编码
     */
    @TableField("apply_code")
    private String applyCode;

    /**
     * 名称
     */
    @TableField("apply_name")
    private String applyName;

    /**
     * 规格
     */
    @TableField("apply_specifications")
    private String applySpecifications;

    /**
     * 材质
     */
    @TableField("apply_quality")
    private String applyQuality;

    /**
     * 品牌
     */
    @TableField("apply_brand")
    private String applyBrand;

    /**
     * 单位
     */
    @TableField("apply_company")
    private String applyCompany;

    /**
     * 申请数量
     */
    @TableField("apply_quantity")
    private Integer applyQuantity;

    /**
     * 交货日期
     */
    @TableField("delivery_date")
    private Date deliveryDate;

    /**
     * 备注
     */
    @TableField("apply_remarks")
    private String applyRemarks;

    /**
     * 小类
     */
    @TableField("apply_subclass")
    private String applySubclass;

    /**
     * 大类
     */
    @TableField("apply_category")
    private String applyCategory;

    /**
     * 是否核销
     */
    @TableField("apply_cancel")
    private String applyCancel;

}

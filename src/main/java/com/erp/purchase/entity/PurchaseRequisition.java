package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 采购申请表 Entity
 *
 * @author qiufeng
 * @date 2022-01-19 16:39:10
 */
@Data
public class PurchaseRequisition {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态   1-登记，2-确认，3-核准
     */
    @TableField("apply_state")
    private String applyState;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 日期
     */
    @TableField("purchase_requisition_date")
    private Date purchaseRequisitionDate;

    /**
     * 部门
     */
    @TableField("department_id")
    private Long departmentId;

    private String departmentName;



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
     * 制单人
     */
    @TableField("apply_preparer")
    private String applyPreparer;

    /**
     * 制单日期
     */
    @TableField("apply_preparation_date")
    private Date applyPreparationDate;

    /**
     * 小类
     */
    private String applySubclass;

    /**
     * 大类
     */
    private String applyCategory;

    /**
     * 是否核销
     */
    private String applyCancel;

    /**
     * 库位
     */
    private String applyLocation;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

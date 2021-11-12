package com.erp.purchase.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 采购参数表 Entity
 *
 * @author qiufeng
 * @date 2021-11-09 16:20:36
 */
@Data
@TableName("jr_purchase_parameters")
public class PurchaseParameters {

    /**
     * 参数类别：1-材质
     */
    public static final String product_material = "1";
    /**
     * 参数类别：2-订购类型
     */
    public static final String order_type = "2";
    /**
     * 参数类别：3-付款方式
     */
    public static final String payment_method = "3";
    /**
     * 参数类别：4-计量单位
     */
    public static final String unit_of_measure = "4";
    /**
     * 参数类别：5-物料类别
     */
    public static final String material_category = "5";

    /**
     * 状态: 1-启用
     */
    public static final String parameters_state = "1";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别  1-材质，2-订购类型，3-付款方式，4-计量单位，5-物料类别
     */
    @TableField("purchase_parameters_category")
    private String purchaseParametersCategory;

    /**
     * 参数值
     */
    @TableField("purchase_parameters_value")
    private String purchaseParametersValue;

    /**
     * 参数名称
     */
    @TableField("purchase_parameters_name")
    private String purchaseParametersName;

    /**
     * 排序
     */
    @TableField("purchase_parameters_sort")
    private Long purchaseParametersSort;

    /**
     * 状态   1-启用，2-禁用
     */
    @TableField("purchase_parameters_state")
    private String purchaseParametersState;

    /**
     * 备注
     */
    @TableField("purchase_parameters_remarks")
    private String purchaseParametersRemarks;

    /**
     * 创建人
     */
    @TableField("purchase_parameters_creator")
    private String purchaseParametersCreator;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

}

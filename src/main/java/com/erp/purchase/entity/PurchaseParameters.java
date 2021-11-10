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
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别
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
     * 状态
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

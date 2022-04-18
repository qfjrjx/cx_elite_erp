package com.erp.sale.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 产品表
             Entity
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
@Data
@TableName("jr_sale_product")
public class SaleProduct {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModel;

    /**
     * 备注
     */
    @TableField("product_remarks")
    private String productRemarks;
    /**
     * 产品状态
     */
    @TableField("product_state")
    private String productState;

    /**
     *计量单位
    */
    private String purchaseParametersName;

}

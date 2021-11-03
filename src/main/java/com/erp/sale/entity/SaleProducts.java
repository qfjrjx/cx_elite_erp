package com.erp.sale.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 产品表
             Entity
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
@Data
@TableName("jr_sale_product")
public class SaleProducts {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productNames;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModels;

    /**
     * 备注
     */
    @TableField("product_remarks")
    private String productRemark;

    /**
     * 产品状态
     */
    @TableField("product_state")
    private String productStates;

}

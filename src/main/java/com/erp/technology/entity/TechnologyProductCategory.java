package com.erp.technology.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 产品类别表 Entity
 *
 * @author qiufeng
 * @date 2021-11-05 17:22:38
 */
@Data
@TableName("jr_technology_product_category")
public class TechnologyProductCategory {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品类别   1-大类，2-小类
     */
    @TableField("product_category")
    private String productCategory;

    /**
     * 所属大类
     */
    @TableField("product_belonging_category")
    private String productBelongingCategory;

    /**
     * 类别名称
     */
    @TableField("product_category_name")
    private String productCategoryName;

    /**
     * 排序
     */
    @TableField("product_category_sort")
    private String productCategorySort;

    /**
     * 状态  1-启用，2-禁用
     */
    @TableField("product_category_state")
    private String productCategoryState;

    /**
     * 备注
     */
    @TableField("product_category_remarks")
    private String productCategoryRemarks;

}

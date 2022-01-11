package com.erp.purchase.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 物料类别表 Entity
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:14
 */
@Data
@TableName("jr_purchase_material_category")
public class PurchaseMaterialCategory {


    /**
     * 物料类别：1-大类，
     */
    public static final String GENERAL_CATEGORY = "1";
    /**
     * 物料类别：2-小类
     */
    public static final String SUBCLASS = "2";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态 1-启用，2-禁用
     */
    @TableField("material_category_state")
    private String materialCategoryState;

    /**
     * 物料类别  1-大类，2-小类
     */
    @TableField("material_category")
    private String materialCategory;

    /**
     * 所属大类
     */
    @TableField("belonging_general_category")
    private String belongingGeneralCategory;

    /**
     * 类别名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 排序
     */
    @TableField("material_category_sort")
    private String materialCategorySort;

    /**
     * 备注
     */
    @TableField("material_category_remarks")
    private String materialCategoryRemarks;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

}

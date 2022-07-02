package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表 Entity
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:19
 */
@Data
@TableName("jr_purchase_material_file")
public class PurchaseMaterialFile {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("material_code")
    private String materialCode;

    /**
     * 物料名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 规格型号
     */
    @TableField("specifications_model")
    private String specificationsModel;

    /**
     * 库位
     */
    @TableField("material_location")
    private String materialLocation;

    /**
     * 材质
     */
    @TableField("material_quality_id")
    private Long materialQualityId;


    private String materialQuality;

    /**
     * 品牌
     */
    @TableField("material_brand")
    private String materialBrand;

    /**
     * 大类
     */
    @TableField("general_category_id")
    private Long generalCategoryId;

    private String generalCategory;

    /**
     * 小类
     */
    @TableField("material_subclass_id")
    private Long materialSubclassId;

    private String materialSubclass;

    /**
     * 计量单位
     */
    @TableField("metering_company_id")
    private Long meteringCompanyId;

    private String meteringCompany;

    /**
     * 理论重量
     */
    @TableField("theoretical_weight")
    private BigDecimal theoreticalWeight;
    /**
     * 库存上限
     */
    @TableField("inventory_ceiling")
    private String inventoryCeiling;

    /**
     * 库存下限
     */
    @TableField("inventory_lower")
    private String inventoryLower;

    /**
     * 最小单位
     */
    @TableField("minimum_unit")
    private String minimumUnit;

    /**
     * 状态  1-启用，2-禁用
     */
    @TableField("material_state")
    private String materialState;

    /**
     * 备注
     */
    @TableField("material_remarks")
    private String materialRemarks;

    /**
     * 图片
     */
    @TableField("material_picture")
    private String materialPicture;


    private String materialFilePicture;

    /**
     * 建档人
     */
    @TableField("material_filing_person")
    private String materialFilingPerson;

    /**
     * 建档日期
     */
    @TableField("material_created_date")
    private Date materialCreatedDate;

}

package com.erp.production.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 设置BOM附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-17 15:19:14
 */
@Data
@TableName("jr_setup_bom_schedule")
public class SetupBomSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * BOM编码
     */
    @TableField("bom_code")
    private String bomCode;

    /**
     * 物料编码
     */
    @TableField("bom_material_code")
    private String bomMaterialCode;

    /**
     * 物料名称
     */
    @TableField("bom_material_name")
    private String bomMaterialName;

    /**
     * 规格
     */
    @TableField("bom_specifications")
    private String bomSpecifications;

    /**
     * 品牌
     */
    @TableField("bom_brand")
    private String bomBrand;

    /**
     * 计划数量
     */
    @TableField("bom_amount")
    private String bomAmount;

    /**
     * 替代料
     */
    @TableField("bom_material")
    private String bomMaterial;

    /**
     * 替代数量
     */
    @TableField("bom_material_amount")
    private String bomMaterialAmount;

    /**
     * 备注
     */
    @TableField("bom_note")
    private String bomNote;

    /**
     * 机器码
     */
    @TableField("plan_number")
    private String planNumber;

    /**
     * 单位
     */
    @TableField("bom_unit")
    private String bomUnit;

    /**
     * 二级id
     */
    @TableField("tree_secondary_id")
    private Integer treeSecondaryId;

    /**
     * 库位
     */
    @TableField("bom_location")
    private String bomLocation;

    /**
     * 机器BOM
     */
    @TableField("plan_machine_bom")
    private String planMachineBom;

    /**
     * 材质
     */
    @TableField("plan_quality")
    private String planQuality;

}

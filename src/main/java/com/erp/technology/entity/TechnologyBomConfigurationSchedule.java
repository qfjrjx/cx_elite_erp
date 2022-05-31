package com.erp.technology.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * BOM配置附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-09 09:44:00
 */
@Data
@TableName("jr_technology_bom_configuration_schedule")
public class TechnologyBomConfigurationSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * BOM编码
     */
    @TableField("parameter_code")
    private String parameterCode;

    /**
     * 图纸号
     */
    @TableField("parameter_drawings")
    private String parameterDrawings;

    /**
     * 物料名称
     */
    @TableField("parameter_material")
    private String parameterMaterial;

    /**
     * 规格型号
     */
    @TableField("parameter_specifications")
    private String parameterSpecifications;

    /**
     * 单位
     */
    @TableField("parameter_unit")
    private String parameterUnit;

    /**
     * 品牌
     */
    @TableField("parameter_brand")
    private String parameterBrand;

    /**
     * 数量
     */
    @TableField("parameter_number")
    private String parameterNumber;

    /**
     * 替代物料
     */
    @TableField("parameter_replace")
    private String parameterReplace;

    /**
     * 备注
     */
    @TableField("parameter_note")
    private String parameterNote;

    /**
     * 创建时间
     */
    @TableField("parameter_date")
    private Date parameterDate;

    /**
     * 物料编码
     */
    @TableField("parameter_material_code")
    private String parameterMaterialCode;

    /**
     * 库位
     */
    @TableField("parameter_location")
    private String parameterLocation;

    /**
     * 材质
     */
    @TableField("parameter_quality")
    private String parameterQuality;

}

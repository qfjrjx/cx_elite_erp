package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 缺件查阅表 Entity
 *
 * @author qiufeng
 * @date 2022-05-23 09:27:28
 */
@Data
@TableName("jr_production_lack")
public class ProductionLack {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单号
     */
    @TableField("recipients_code")
    private String recipientsCode;

    /**
     * 日期
     */
    @TableField("lack_date")
    private Date lackDate;

    /**
     * 缺件单号
     */
    @TableField("lack_code")
    private String lackCode;

    /**
     * 物料名称
     */
    @TableField("lack_material")
    private String lackMaterial;

    /**
     * 规格型号
     */
    @TableField("lack_specifications")
    private String lackSpecifications;

    /**
     * 机床编号
     */
    @TableField("lack_machine")
    private String lackMachine;

    /**
     * 仓库名称
     */
    @TableField("lack_warehouse")
    private String lackWarehouse;

    /**
     * 缺件数量
     */
    @TableField("lack_number")
    private String lackNumber;

    /**
     * 单位
     */
    @TableField("lack_unit")
    private String lackUnit;

    /**
     * 是否领用
     */
    @TableField("lack_recipients")
    private String lackRecipients;

    /**
     * 物料编码
     */
    @TableField("lack_material_code")
    private String lackMaterialCode;

    /**
     * 库房
     */
    private String recipientsWarehouse;

    /**
     * 材质
     */
    private String recipientsMass;

    /**
     * 品牌
     */
    private String recipientsBrand;

    /**
     * 已领数量
     */
    private String recipientsNumber;

    /**
     * 备注
     */
    private String recipientsNote;

    /**
     *库位
     */
    private String recipientsLocation;
}

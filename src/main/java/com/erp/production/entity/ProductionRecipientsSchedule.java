package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 生产领用附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-19 16:45:08
 */
@Data
@TableName("jr_production_recipients_schedule")
public class ProductionRecipientsSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物料名称
     */
    @TableField("recipients_material")
    private String recipientsMaterial;

    /**
     * 规格型号
     */
    @TableField("recipients_specifications")
    private String recipientsSpecifications;

    /**
     * 材质
     */
    @TableField("recipients_mass")
    private String recipientsMass;

    /**
     * 品牌
     */
    @TableField("recipients_brand")
    private String recipientsBrand;

    /**
     * 单位
     */
    @TableField("recipients_unit")
    private String recipientsUnit;

    /**
     * 数量
     */
    @TableField("recipients_number")
    private String recipientsNumber;

    /**
     * 机加工单价
     */
    @TableField("recipients_price")
    private BigDecimal recipientsPrice;
    /**
     * 库位
     */
    @TableField("recipients_location")
    private String recipientsLocation;

    /**
     * 备注
     */
    @TableField("recipients_note")
    private String recipientsNote;

}

package com.erp.production.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 缺件领用附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:10
 */
@Data
@TableName("jr_lack_recipients_schedule")
public class LackRecipientsSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单号
     */
    @TableField("lack_code")
    private String lackCode;

    /**
     * 编码
     */
    @TableField("lack_coding")
    private String lackCoding;

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
     * 单位
     */
    @TableField("lack_unit")
    private String lackUnit;

    /**
     * 缺件数量
     */
    @TableField("lack_number")
    private String lackNumber;

    /**
     * 库位
     */
    @TableField("lack_location")
    private String lackLocation;

    /**
     * 备注
     */
    @TableField("lack_note")
    private String lackNote;

    /**
     * 机器码
     */
    @TableField("lack_machine")
    private String lackMachine;

    /**
     * 库存数量
     */
    @TableField("inventory_number")
    private String inventoryNumber;

    /**
     * 已领数量
     */
    @TableField("recipients_number")
    private String recipientsNumber;

    /**
     * 本次领用数量
     */
    @TableField("the_number")
    private String theNumber;

    /**
     * 缺件单号
     */
    @TableField("recipients_lack_code")
    private String recipientsLackCode;

}

package com.erp.production.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 设置BOM Entity
 *
 * @author qiufeng
 * @date 2022-05-17 15:19:03
 */
@Data
@TableName("jr_setup_bom")
public class SetupBom {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @TableField("bom_number")
    private String bomNumber;

    /**
     * 名称
     */
    @TableField("bom_name")
    private String bomName;

    /**
     * 一级id
     */
    @TableField("tree_id")
    private Integer treeId;

    /**
     * 机器码
     */
    @TableField("plan_number")
    private String planNumber;

    /**
     * 部分名称
     */
    @TableField("bom_part")
    private String bomPart;

    /**
     * 配置名称
     */
    @TableField("bom_configuration")
    private String bomConfiguration;

    /**
     * BOM编码
     */
    @TableField("bom_code")
    private String bomCode;

}

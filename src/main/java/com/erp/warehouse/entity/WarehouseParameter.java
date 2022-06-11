package com.erp.warehouse.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 仓库参数 Entity
 *
 * @author qiufeng
 * @date 2022-06-01 15:53:35
 */
@Data
@TableName("jr_warehouse_parameter")
public class WarehouseParameter {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 参数值
     */
    @TableField("parameter_value")
    private String parameterValue;

    /**
     * 参数名称
     */
    @TableField("parameter_name")
    private String parameterName;

    /**
     * 参数类别
     */
    @TableField("parameter_type")
    private String parameterType;

    /**
     * 名排序
     */
    @TableField("parameter_sorting")
    private String parameterSorting;

    /**
     * 状态
     */
    @TableField("parameter_state")
    private String parameterState;

    /**
     * 备注
     */
    @TableField("parameter_note")
    private String parameterNote;

}

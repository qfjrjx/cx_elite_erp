package com.erp.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 库房区位 Entity
 *
 * @author qiufeng
 * @date 2022-06-01 10:40:04
 */
@Data
@TableName("jr_warehouse_location")
public class WarehouseLocation {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态1：启用2：禁用
     */
    @TableField("location_state")
    private String locationState;

    /**
     * 库房类型
     */
    @TableField("location_type")
    private String locationType;

    /**
     * 库房名称
     */
    @TableField("location_name")
    private String locationName;

    /**
     * 排序
     */
    @TableField("location_sorting")
    private String locationSorting;

    /**
     * 备注
     */
    @TableField("location_note")
    private String locationNote;

    /**
     * 创建人
     */
    @TableField("location_create")
    private String locationCreate;

    /**
     * 创建时间
     */
    @TableField("location_create_date")
    private Date locationCreateDate;

    /**
     * 库房编码
     */
    @TableField("location_code")
    private String locationCode;

}

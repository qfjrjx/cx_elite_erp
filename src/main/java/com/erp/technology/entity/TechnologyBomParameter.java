package com.erp.technology.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * BOM参数 Entity
 *
 * @author qiufeng
 * @date 2022-05-05 11:30:22
 */
@Data
@TableName("jr_technology_bom_parameter")
public class TechnologyBomParameter {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 参数类别
     */
    @TableField("parameter_category")
    private String parameterCategory;

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
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("parameter_date")
    private Date parameterDate;

    /**
     * 备注
     */
    @TableField("parameter_note")
    private String parameterNote;

}

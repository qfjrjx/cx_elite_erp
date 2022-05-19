package com.erp.technology.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * BOM配置 Entity
 *
 * @author qiufeng
 * @date 2022-05-09 09:43:35
 */
@Data
@TableName("jr_technology_bom_configuration")
public class TechnologyBomConfiguration {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态1：登记，2：确认，3：受控
     */
    @TableField("parameter_state")
    private String parameterState;

    /**
     * BOM编码
     */
    @TableField("parameter_code")
    private String parameterCode;

    /**
     * 建档人
     */
    @TableField("parameter_inputting")
    private String parameterInputting;

    /**
     * 建档日期
     */
    @TableField("parameter_inputting_date")
    private Date parameterInputtingDate;

    /**
     * 部分名称
     */
    @TableField("parameter_part")
    private String parameterPart;

    private String parameterName;

    /**
     * 配置
     */
    @TableField("parameter_with")
    private String parameterWith;

    /**
     * 附件
     */
    @TableField("parameter_attachment")
    private String parameterAttachment;

    /**
     * 说明
     */
    @TableField("parameter_instructions")
    private String parameterInstructions;

}

package com.erp.technology.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 设置工序表 Entity
 *
 * @author qiufeng
 * @date 2021-11-13 11:44:55
 */
@Data
@TableName("jr_technology_set_operation")
public class TechnologySetOperation {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工件图纸号
     */
    @TableField("workpiece_drawing_no")
    private String workpieceDrawingNo;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModel;

    /**
     * 顺序
     */
    @TableField("product_order")
    private Integer productOrder;

    /**
     * 工序
     */
    @TableField("product_working_procedure")
    private String productWorkingProcedure;

    /**
     * 单价
     */
    @TableField("product_unit_price")
    private BigDecimal productUnitPrice;
    /**
     * 备注说明
     */
    @TableField("product_remarks")
    private String productRemarks;

}

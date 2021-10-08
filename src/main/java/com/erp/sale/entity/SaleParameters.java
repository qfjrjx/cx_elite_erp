package com.erp.sale.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 销售参数表

          数据库表名：                              对应java表名：

          jr_sale_parameters                     SaleParameters Entity
 *
 * @author qiufeng
 * @date 2021-10-07 10:18:55
 */
@Data
@TableName("jr_sale_parameters")
public class SaleParameters {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别     1-付款方式，2-结算方式，3-业务区域，4-运输费用
     */
    @TableField("parameter_category")
    private Integer parameterCategory;

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
     * 排序
     */
    @TableField("parameter_sort")
    private Integer parameterSort;

    /**
     * 1-启用，2禁用
     */
    @TableField("parameter_state")
    private Integer parameterState;

    /**
     * 备注
     */
    @TableField("parameter_remarks")
    private String parameterRemarks;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

}

package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 生产领用 Entity
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
@Data
@TableName("jr_production_recipients")
public class ProductionRecipients {

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
     * 制单时间
     */
    @TableField("recipients_date")
    private Date recipientsDate;

    /**
     * 单号
     */
    @TableField("recipients_code")
    private String recipientsCode;

    /**
     * 缺件单号
     */
    @TableField("recipients_lack_code")
    private String recipientsLackCode;

    /**
     * 领用部门
     */
    @TableField("recipients_department")
    private String recipientsDepartment;

    /**
     * 领用人
     */
    @TableField("recipients_people")
    private String recipientsPeople;

    /**
     * 机器码
     */
    @TableField("recipients_machine")
    private String recipientsMachine;

    /**
     * 客户
     */
    @TableField("recipients_customer")
    private String recipientsCustomer;

    /**
     * 机器型号
     */
    @TableField("recipients_model")
    private String recipientsModel;

    /**
     * 库房
     */
    @TableField("recipients_warehouse")
    private String recipientsWarehouse;

    /**
     * 出库人
     */
    @TableField("recipients_outbound")
    private String recipientsOutbound;

    /**
     * 出库时间
     */
    @TableField("recipients_outbound_date")
    private Date recipientsOutboundDate;

    /**
     * 缺件领用
     */
    @TableField("recipients_lack_recipients")
    private String recipientsLackRecipients;

}

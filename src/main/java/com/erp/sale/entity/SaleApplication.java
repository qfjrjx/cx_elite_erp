package com.erp.sale.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售申请表 Entity
 *
 * @author qiufeng
 * @date 2022-03-23 17:37:36
 */
@Data
@TableName("jr_sale_application")
public class SaleApplication {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请交货日期
     */
    @TableField("requested_delivery_date")
    private Date requestedDeliveryDate;

    /**
     * 申请单号
     */
    @TableField("application_no")
    private String applicationNo;

    /**
     * 关联客户档案id
     */
    @TableField("customer_id")
    private Long customerId;

    /**
     * 客户
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 业务员
     */
    @TableField("salesman_name")
    private String salesmanName;

    /**
     * 附件
     */
    @TableField("enclosure_name")
    private String enclosureName;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 设计交期
     */
    @TableField("design_date")
    private Date designDate;

    /**
     * 设计回复
     */
    @TableField("design_reply")
    private String designReply;

    /**
     * 采购交期
     */
    @TableField("purchase_date")
    private Date purchaseDate;

    /**
     * 采购回复
     */
    @TableField("purchase_reply")
    private String purchaseReply;

    /**
     * 生产交期
     */
    @TableField("production_date")
    private Date productionDate;

    /**
     * 生产回复
     */
    @TableField("production_reply")
    private String productionReply;

    /**
     * 装配交期
     */
    @TableField("assembling_date")
    private Date assemblingDate;

    /**
     * 装配回复
     */
    @TableField("assembling_reply")
    private String assemblingReply;

}

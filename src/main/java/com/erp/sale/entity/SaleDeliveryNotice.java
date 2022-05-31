package com.erp.sale.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 发货通知 Entity
 *
 * @author qiufeng
 * @date 2022-05-14 09:19:42
 */
@Data
@TableName("jr_sale_delivery_notice")
public class SaleDeliveryNotice {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发货单号
     */
    @TableField("shipment_no")
    private String shipmentNo;

    /**
     * 发货日期
     */
    @TableField("delivery_date")
    private Date deliveryDate;

    /**
     * 关联客户档案id
     */
    @TableField("customer_id")
    private Long customerId;

    /**
     * 销售客户
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 业务员 
     */
    @TableField("salesman_name")
    private String salesmanName;

    /**
     * 订单类型          1-配件，2-整机 
     */
    @TableField("order_type")
    private String orderType;

    /**
     * 库房
     */
    @TableField("storage_room")
    private String storageRoom;

    /**
     * 收货人 
     */
    @TableField("consignee_name")
    private String consigneeName;

    /**
     * 收货手机
     */
    @TableField("receiving_phone")
    private String receivingPhone;

    /**
     * 收货地址
     */
    @TableField("receiving_address")
    private String receivingAddress;

    /**
     * 售后员
     */
    @TableField("after_sales_clerk")
    private String afterSalesClerk;

    /**
     * 是否记账           1-是，2-否
     */
    @TableField("order_bookkeeping")
    private String orderBookkeeping;

    /**
     * 制单人
     */
    @TableField("preparer_name")
    private String preparerName;

    /**
     * 订单创建时间
     */
    @TableField("create_date")
    private Date createDate;


}

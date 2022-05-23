package com.erp.sale.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 发货通知附表 Entity
 *
 * @author qiufeng
 * @date 2022-05-14 09:19:45
 */
@Data
@TableName("jr_sale_delivery_notice_schedule")
public class SaleDeliveryNoticeSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    @TableField("delivery_notice_state")
    private String deliveryNoticeState;

    /**
     * 发货单号
     */
    @TableField("shipment_no")
    private String shipmentNo;

    /**
     * 订单附表id
     */
    @TableField("order_schedule_id")
    private Long orderScheduleId;

    /**
     * 库房
     */
    @TableField("schedule_storage_room")
    private String scheduleStorageRoom;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModel;

    /**
     * 机器配置
     */
    @TableField("machine_configuration")
    private String machineConfiguration;

    /**
     * 是否开票       1-是，2-否
     */
    @TableField("invoice_not")
    private String invoiceNot;

    /**
     * 订单数
     */
    @TableField("order_count")
    private Integer orderCount;

    /**
     * 已发数
     */
    @TableField("number_sent")
    private Integer numberSent;

    /**
     *本次发货（发货数量）
     */
    @TableField("this_shipment")
    private Integer thisShipment;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    /**
     * 币种           1-人民币，2-美元，3-欧元
     */
    @TableField("currency_name")
    private String currencyName;

    /**
     * 订单号
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 类别
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 优惠金额
     */
    @TableField("preferential_amount")
    private BigDecimal preferentialAmount;
    /**
     * 邮费
     */
    @TableField("order_postage")
    private BigDecimal orderPostage;
    /**
     * 备注
     */
    @TableField("shipping_notice_remarks")
    private String shippingNoticeRemarks;

    /**
     * 联系人
     */
    @TableField("contacts_name")
    private String contactsName;

    /**
     * 联系电话
     */
    @TableField("contact_number")
    private String contactNumber;

    /**
     * 出库人
     */
    @TableField("warehouse_name")
    private String warehouseName;

    /**
     * 物流公司
     */
    @TableField("logistics_company")
    private String logisticsCompany;

    /**
     * 司机姓名
     */
    @TableField("driver_name")
    private String driverName;

    /**
     * 司机电话
     */
    @TableField("driver_telephone")
    private String driverTelephone;

    /**
     * 车牌号
     */
    @TableField("plate_number")
    private String plateNumber;

    /**
     * 回单号
     */
    @TableField("receipt_no")
    private String receiptNo;

    /**
     * 回单
     */
    @TableField("order_receipt")
    private String orderReceipt;

    /**
     * 机器码
     */
    @TableField("machine_code")
    private String machineCode;

}

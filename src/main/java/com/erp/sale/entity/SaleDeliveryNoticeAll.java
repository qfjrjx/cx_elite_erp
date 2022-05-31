package com.erp.sale.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 发货通知 Entity
 *
 * @author qiufeng
 * @date 2022-05-13 10:13:33
 */
@Data
public class SaleDeliveryNoticeAll {

    /**
     * 主键
     */
    private Long id;

    /**
     * 状态
     */
    private String deliveryNoticeState;

    /**
     * 发货单号
     */
    private String shipmentNo;

    /**
     * 发货日期
     */
    private Date deliveryDate;

    /**
     * 关联客户档案id
     */
    private Long customerId;

    /**
     * 销售客户
     */
    private String customerName;

    /**
     * 业务员 
     */
    private String salesmanName;

    /**
     * 订单类型          1-配件，2-整机 
     */
    private String orderType;

    /**
     * 库房
     */
    private String storageRoom;

    /**
     * 收货人 
     */
    private String consigneeName;

    /**
     * 收货手机
     */
    private String receivingPhone;

    /**
     * 收货地址
     */
    private String receivingAddress;

    /**
     * 售后员
     */
    private String afterSalesClerk;

    /**
     * 是否记账           1-是，2-否
     */
    private String orderBookkeeping;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 规格型号
     */
    private String specificationModel;

    /**
     * 是否开票       1-是，2-否
     */
    private String invoiceNot;

    /**
     * 订单数
     */
    private Integer orderCount;

    /**
     * 已发数
     */
    private Integer numberSent;

    /**
     * 本次发货（发货数量）
     */
    private Integer thisShipment;

    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 币种           1-人民币，2-美元，3-欧元
     */
    private String currencyName;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 类别
     */
    private String categoryName;

    /**
     * 优惠金额
     */
    private BigDecimal preferentialAmount;
    /**
     * 邮费
     */
    private BigDecimal orderPostage;
    /**
     * 备注
     */
    private String shippingNoticeRemarks;

    /**
     * 配置
     */
    private String machineConfiguration;

    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 制单人
     */
    private String preparerName;

    /**
     * 出库人
     */
    private String warehouseName;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverTelephone;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 回单号
     */
    private String receiptNo;

    /**
     * 回单
     */
    private String orderReceipt;

    /**
     * 机器码
     */
    private String machineCode;

}

package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购付款 Entity
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:21
 */
@Data
@TableName("jr_purchase_payment")
public class PurchasePayment {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审核状态
     */
    @TableField("payment_state")
    private String paymentState;

    /**
     * 日期
     */
    @TableField("payment_date")
    private Date paymentDate;

    /**
     * 单位
     */
    @TableField("payment_supplier")
    private String paymentSupplier;

    /**
     * 币种
     */
    @TableField("currency_id")
    private Long currencyId;

    private String currencyName;

    /**
     * 付款类型
     */
    @TableField("payment_type")
    private String paymentType;

    /**
     * 付款方式
     */
    @TableField("payment_way")
    private String paymentWay;

    /**
     * 是否开票
     */
    @TableField("payment_invoice")
    private String paymentInvoice;

    /**
     * 操作人
     */
    @TableField("payment_operation")
    private String paymentOperation;

    /**
     * 操作日期
     */
    @TableField("payment_operation_date")
    private Date paymentOperationDate;

    /**
     * 备注
     */
    @TableField("payment_remarks")
    private String paymentRemarks;

    /**
     * 付款状态
     */
    @TableField("payment_shape")
    private String paymentShape;

    /**
     * 确认付款时间
     */
    @TableField("payment_shape_date")
    private Date paymentShapeDate;

    /**
     * 采购单号
     */
    @TableField("payment_code")
    private String paymentCode;

    /**
     * 付款单号
     */
    @TableField("payment_number")
    private String paymentNumber;

    private BigDecimal paymentMoney;

    /**
     * 发票号
     */
    private String invoiceNumbers;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

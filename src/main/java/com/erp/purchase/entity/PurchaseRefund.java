package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购退货 Entity
 *
 * @author qiufeng
 * @date 2022-03-28 15:35:47
 */
@Data
@TableName("jr_purchase_refund")
public class PurchaseRefund {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    @TableField("refund_state")
    private String refundState;

    /**
     * 日期
     */
    @TableField("refund_date")
    private Date refundDate;

    /**
     * 退货单号
     */
    @TableField("refund_number")
    private String refundNumber;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;

    /**
     * 库房
     */
    @TableField("refund_library")
    private String refundLibrary;

    /**
     * 数量
     */
    @TableField("refund_quantity")
    private String refundQuantity;

    /**
     * 金额
     */
    @TableField("refund_money")
    private BigDecimal refundMoney;
    /**
     * 币种
     */
    @TableField("currency_id")
    private Long currencyId;

    private String currencyName;

    /**
     * 税率
     */
    @TableField("tax_rate_id")
    private Long taxRateId;

    private String taxRateName;

    /**
     * 制单人
     */
    @TableField("refund_preparer")
    private String refundPreparer;

    /**
     * 制单日期
     */
    @TableField("refund_preparation_date")
    private Date refundPreparationDate;

    /**
     * 出库人
     */
    @TableField("refund_outbound")
    private String refundOutbound;

    /**
     * 出库日期
     */
    @TableField("refund_outbound_date")
    private Date refundOutboundDate;

    /**
     * 采购订单
     */
    @TableField("order_number")
    private String orderNumber;

}

package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购收货表 Entity
 *
 * @author qiufeng
 * @date 2022-03-19 09:44:35
 */
@Data
@TableName("jr_purchase_closed")
public class PurchaseClosed {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态:1-登记，2-入库，3-结算
     */
    @TableField("closed_state")
    private String closedState;

    /**
     * 日期
     */
    @TableField("purchase_requisition_date")
    private Date purchaseRequisitionDate;

    /**
     * 收货单号
     */
    @TableField("closed_number")
    private String closedNumber;

    /**
     * 库房:1-外购，2-机加，3-废品，4-资产，5-成品
     */
    @TableField("warehouse_state")
    private String warehouseState;


    /**
     * 入库人
     */
    @TableField("closed_storage")
    private String closedStorage;

    /**
     * 入库日期
     */
    @TableField("closed_storage_date")
    private Date closedStorageDate;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;



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
     * 金额
     */
    private BigDecimal orderMoney;

    /**
     * 订购数量
     */
    private Integer orderQuantity;

}

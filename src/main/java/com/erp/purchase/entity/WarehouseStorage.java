package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Entity
 *采购入库表
 * @author qiufeng
 * @date 2022-03-29 13:53:06
 */
@Data
@TableName("jr_warehouse_storage")
public class WarehouseStorage {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    @TableField("storage_state")
    private String storageState;

    /**
     * 日期
     */
    @TableField("storage_date")
    private Date storageDate;

    /**
     * 收货单号
     */
    @TableField("storage_numbers")
    private String storageNumbers;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;

    /**
     * 库房
     */
    @TableField("storage_library")
    private String storageLibrary;

    /**
     * 数量
     */
    @TableField("storage_quantity")
    private String storageQuantity;

    /**
     * 金额
     */
    @TableField("storage_money")
    private BigDecimal storageMoney;
    /**
     * 币种
     */
    @TableField("currency_id")
    private Long currencyId;

    /**
     * 税率
     */
    @TableField("tax_rate_id")
    private Long taxRateId;

    /**
     * 制单人
     */
    @TableField("storage_preparer")
    private String storagePreparer;

    /**
     * 制单日期
     */
    @TableField("storage_preparer_date")
    private Date storagePreparerDate;

    /**
     * 采购订单
     */
    @TableField("order_number")
    private String orderNumber;

}

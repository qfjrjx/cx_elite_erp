package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购入库_附表 Entity
 *
 * @author qiufeng
 * @date 2022-03-30 09:55:45
 */
@Data
@TableName("jr_warehouse_storage_schedule")
public class WarehouseStorageSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("storage_code")
    private String storageCode;

    /**
     * 名称
     */
    @TableField("storage_name")
    private String storageName;

    /**
     * 规格
     */
    @TableField("storage_specifications")
    private String storageSpecifications;

    /**
     * 材质
     */
    @TableField("storage_quality")
    private String storageQuality;

    /**
     * 品牌
     */
    @TableField("storage_brand")
    private String storageBrand;

    /**
     * 单位
     */
    @TableField("storage_company")
    private String storageCompany;

    /**
     * 数量
     */
    @TableField("storage_quantity")
    private String storageQuantity;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;
    /**
     * 金额
     */
    @TableField("storage_money")
    private BigDecimal storageMoney;
    /**
     * 采购订单
     */
    @TableField("order_numbers")
    private String orderNumbers;

    /**
     * 库位
     */
    @TableField("storage_location")
    private String storageLocation;

    /**
     * 备注
     */
    @TableField("storage_remarks")
    private String storageRemarks;

    /**
     * 小类
     */
    @TableField("storage_subclass")
    private String storageSubclass;

    /**
     * 日期
     */
    @TableField("storage_date")
    private Date storageDate;

    /**
     * 大类
     */
    @TableField("storage_category")
    private String storageCategory;

    /**
     * 已付定金
     */
    @TableField("storage_deposit")
    private BigDecimal storageDeposit;

    /**
     * 收货单号
     */
    private String storageNumbers;

    /**
     * 供应商
     */
    private String supplierName;

    /**
     * 库房
     */
    private String storageLibrary;

    /**
     * 订单号
     */
    private String orderNumber;

}

package com.erp.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购入库 Entity
 *
 * @author qiufeng
 * @date 2022-06-03 15:17:46
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
     * 单号
     */
    @TableField("storage_coding")
    private String storageCoding;


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

    private String locationName;

    /**
     * 数量
     */
    @TableField("storage_quantity")
    private String storageQuantity;

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
     * UUID
     */
    @TableField("uuid")
    private String uuid;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

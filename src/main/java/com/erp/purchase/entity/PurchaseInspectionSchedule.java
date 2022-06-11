package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 来货检验_附表 Entity
 *
 * @author qiufeng
 * @date 2022-03-22 09:38:22
 */
@Data
@TableName("jr_purchase_inspection_schedule")
public class PurchaseInspectionSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 采购订单
     */
    @TableField("order_numbers")
    private String orderNumbers;

    /**
     * 物料名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 规格型号
     */
    @TableField("inspectionr_specifications")
    private String inspectionrSpecifications;

    /**
     * 单位
     */
    @TableField("inspection_company")
    private String inspectionCompany;

    /**
     * 订购数
     */
    @TableField("order_quantity")
    private String orderQuantity;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    @TableField("inspection_money")
    private BigDecimal inspectionMoney;

    /**
     * 来货检验单号
     */
    @TableField("inspection_number")
    private String inspectionNumber;

    /**
     * 备注
     */
    @TableField("inspection_remarks")
    private String inspectionRemarks;

    /**
     * 小类
     */
    @TableField("inspection_subclass")
    private String inspectionSubclass;

    /**
     * 大类
     */
    @TableField("inspection_category")
    private String inspectionCategory;

    /**
     * 合格数
     */
    @TableField("inspection_qualified")
    private String inspectionQualified;

    /**
     * 质检员
     */
    @TableField("inspection_quality")
    private String inspectionQuality;

    /**
     * 质检日期
     */
    @TableField("inspection_quality_date")
    private Date inspectionQualityDate;

    /**
     * 送库人
     */
    @TableField("inspection_library")
    private String inspectionLibrary;

    /**
     * 送库日期
     */
    @TableField("inspection_library_date")
    private Date inspectionLibraryDate;

    /**
     * 入库单号
     */
    @TableField("inspection_library_code")
    private String inspectionLibraryCode;

    /**
     * 状态1：登记 2：确认 3：检验 4：送库
     */
    @TableField("inspection_state")
    private String inspectionState;

    /**
     * 检验结果描述
     */
    @TableField("inspection_describe")
    private String inspectionDescribe;

    /**
     * 库位
     */
    @TableField("inspection_location")
    private String inspectionLocation;

    /**
     * 材质
     */
    @TableField("inspection_material")
    private String inspectionMaterial;

    /**
     * 品牌
     */
    @TableField("inspection_brand")
    private String inspectionBrand;

    /**
     * 物料编码
     */
    @TableField("inspection_code")
    private String inspectionCode;

    /**
     * 物料编码
     */
    @TableField("uuid")
    private String uuid;

    /**
     * 定金
     */
    @TableField("inspection_deposit")
    private BigDecimal inspectionDeposit;


    /**
     * 制单人
     */
    private String userName;

    /**
     * 供应商
     */
    private String supplierName;

    /**
     * 币种
     */
    private Long currencyId;

    /**
     * 税率
     */
    private Long taxRateId;

    private String taxRateName;
    private String currencyName;


}

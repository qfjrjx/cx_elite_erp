package com.erp.technology.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 产品表
             Entity
 *
 * @author qiufeng
 * @date 2021-11-09 10:30:07
 */
@Data
@TableName("jr_technology_product")
public class TechnologyProduct {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工序状态  1-未设置，2-已设置
     */
    @TableField("product_process_status")
    private String productProcessStatus;

    /**
     * 产品名称/图纸+工件名称
     */
    @TableField("product_name")
    private String productName;
    //全称简拼
    private transient String productNameAbbreviation;

    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 规格型号
     */
    @TableField("product_specification_model")
    private String productSpecificationModel;

    /**
     * 产品状态 1-启用，2-禁用
     */
    @TableField("product_state")
    private String productState;

    /**
     * 备注
     */
    @TableField("product_remarks")
    private String productRemarks;

    /**
     * 建档人
     */
    @TableField("product_filing_person")
    private String productFilingPerson;

    /**
     * 建档日期
     */
    @TableField("product_created_date")
    private Date productCreatedDate;

    /**
     * 查看图片
     */
    @TableField("product_view_picture")
    private String productViewPicture;

    /**
     * 材质
     */
    @TableField("product_material_id")
    private Long productMaterialId;

    private String productMaterial;

    /**
     * 品牌
     */
    @TableField("product_brand")
    private String productBrand;

    /**
     * 计量单位
     */
    @TableField("product_metering_company_id")
    private Long productMeteringCompanyId;

    private String productMeteringCompany;

    /**
     * 理论重量
     */
    @TableField("product_theoretical_weight")
    private BigDecimal productTheoreticalWeight;
    /**
     * 最小单位
     */
    @TableField("product_minimum_unit")
    private String productMinimumUnit;

    /**
     * 生产单价
     */
    @TableField("product_production_monovalent")
    private BigDecimal productProductionMonovalent;
    /**
     * 大类
     */
    @TableField("product_general_category_id")
    private Long productGeneralCategoryId;

    private String productGeneralCategory;

    /**
     * 小类
     */
    @TableField("product_subclass_id")
    private Long productSubclassId;

    private String productSubclass;

    /**
     * 库存数量
     */
    @TableField("product_inventory_quantity")
    private Integer productInventoryQuantity;

    /**
     * 最近入库
     */
    @TableField("product_recent_warehousing")
    private Integer productRecentWarehousing;
    /**
     * 库存上限
     */
    @TableField("inventory_ceiling")
    private String inventoryCeiling;

    /**
     * 库存下限
     */
    @TableField("inventory_lower_limit")
    private String inventoryLowerLimit;

}

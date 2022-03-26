package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

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
     * 状态
     */
    private String inspectionState;


}

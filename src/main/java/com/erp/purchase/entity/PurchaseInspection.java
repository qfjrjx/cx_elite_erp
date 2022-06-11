package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 来货检验 Entity
 *
 * @author qiufeng
 * @date 2022-03-22 09:34:27
 */
@Data
@TableName("jr_purchase_inspection")
public class PurchaseInspection {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态
     */
    private String inspectionState;

    /**
     * 日期
     */
    @TableField("purchase_requisition_date")
    private Date purchaseRequisitionDate;

    /**
     * 单号
     */
    @TableField("inspection_number")
    private String inspectionNumber;

    /**
     * 供应商
     */
    @TableField("supplier_name")
    private String supplierName;

    /**
     * 类型
     */
    @TableField("inspection_type")
    private String inspectionType;

    /**
     * 订购数
     */
    @TableField("order_quantity")
    private String orderQuantity;

    /**
     * 制单人
     */
    @TableField("inspection_preparer")
    private String inspectionPreparer;

    /**
     * 制单日期
     */
    @TableField("inspection_preparation_date")
    private Date inspectionPreparationDate;

    /**
     * 备注
     */
    @TableField("inspection_remarks")
    private String inspectionRemarks;

    /**
     * 税率
     */
    @TableField("tax_rate_id")
    private String taxRateId;

    private String taxRateName;

    /**
     * 币种
     */
    @TableField("currency_id")
    private String currencyId;

    private String currencyName;

    private transient String signedDateFrom;
    private transient String signedDateTo;

    //物料名称
    private String materialName;

    //规格型号
    private String inspectionrSpecifications;

    /**
     * 合格数
     */
    private String inspectionQualified;

    /**
     * 质检员
     */
    private String inspectionQuality;

    /**
     * 质检日期
     */
    private Date inspectionQualityDate;

    /**
     * 送库人
     */
    private String inspectionLibrary;

    /**
     * 送库日期
     */
    private Date inspectionLibraryDate;

    /**
     * 入库单号
     */
    private String inspectionLibraryCode;

    /**
     * 检验结果描述
     */
    private String inspectionDescribe;

    /**
     * 附表主键
     */
    private Long ids;

}

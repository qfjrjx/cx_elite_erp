package com.erp.purchase.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 采购申请表 Entity
 * @author qiufeng
 * @date 2022-02-17 15:25:31
 */
@Data
@TableName("jr_purchase_requisition")
public class PurchaseRequisitionPositive {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态   1-登记，2-确认，3-核准
     */
    @TableField("apply_state")
    private String applyState;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 日期
     */
    @TableField("purchase_requisition_date")
    private Date purchaseRequisitionDate;

    /**
     * 部门
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 制单人
     */
    @TableField("apply_preparer")
    private String applyPreparer;

    /**
     * 制单日期
     */
    @TableField("apply_preparation_date")
    private Date applyPreparationDate;

}

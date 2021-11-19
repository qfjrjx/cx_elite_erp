package com.erp.finance.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 财务参数表 Entity
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
@Data
@TableName("jr_finance_parameters")
public class FinanceParameters {

    /**
     * 参数类别：1-报支类别
     */
    public static final String EXPENSE_REPORTING_CATEGORY = "1";
    /**
     * 参数类别：2-币种
     */
    public static final String CURRENCY = "2";
    /**
     * 参数类别：3-调整方式
     */
    public static final String ARRANGE_MODE = "3";
    /**
     * 参数类别：4-发票类型
     */
    public static final String INVOICE_TYPE = "4";
    /**
     * 参数类别：5-付款方式
     */
    public static final String PAYMENT_METHOD = "5";
    /**
     * 参数类别：6-付款类型
     */
    public static final String PAYMENT_TYPE = "6";
    /**
     * 参数类别：7-管理费用
     */
    public static final String ADMINISTRATIVE_EXPENSES = "7";
    /**
     * 参数类别：8-收款方式
     */
    public static final String COLLECTION_METHOD = "8";
    /**
     * 参数类别：9-收款类型
     */
    public static final String COLLECTION_TYPE = "9";
    /**
     * 参数类别：10-收入类别
     */
    public static final String INCOME_CATEGORY = "10";
    /**
     * 参数类别：11-收支类别
     */
    public static final String REVENUE_EXPENDITURE_CATEGORY = "11";
    /**
     * 参数类别：12-税率
     */
    public static final String TAX_RATE = "12";
    /**
     * 参数类别：13-销售费用
     */
    public static final String SELLING_EXPENSES = "13";
    /**
     * 参数类别：14-制造费用
     */
    public static final String MANUFACTURING_EXPENSES = "14";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数类别  1-报支类别,2-币种,3-调整方式,4-发票类型,5-付款方式,6-付款类型,7-管理费用,8-收款方式,9-收款类型,10-收入类别,11-收支类别,12-税率,13-销售费用,14-制造费用
     */
    @TableField("finance_parameter_category")
    private String financeParameterCategory;

    /**
     * 参数值
     */
    @TableField("finance_parameter_value")
    private String financeParameterValue;

    /**
     * 参数名称
     */
    @TableField("finance_parameter_name")
    private String financeParameterName;

    /**
     * 排序
     */
    @TableField("finance_sort")
    private Integer financeSort;

    /**
     * 状态
     */
    @TableField("finance_state")
    private String financeState;

    /**
     * 备注
     */
    @TableField("finance_remarks")
    private String financeRemarks;

    /**
     * 创建人
     */
    @TableField("finance_creator")
    private String financeCreator;

    /**
     * 创建时间
     */
    @TableField("finance_creation_time")
    private Date financeCreationTime;

}

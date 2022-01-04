package com.erp.expense.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 费用报支表 Entity
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
@Data
@TableName("jr_expense_reporting")
public class ExpenseReporting {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("expense_reporting_date")
    private Date expenseReportingDate;

    /**
     * 用款部门
     */
    @TableField("user_department")
    private Long userDepartment;
    /*部门名称*/
    private String deptName;



    /**
     * 经办人
     */
    @TableField("expense_reporting_handler")
    private String expenseReportingHandler;

    /**
     * 支付方式  1-先付，2-后付，3-分批付
     */
    @TableField("payment_method")
    private String paymentMethod;

    /**
     * 费用类型
     */
    @TableField("expense_type")
    private Long expenseType;
    /*费用类别名称*/
    private String expenseTypeName;




    /**
     * 单位名称
     */
    @TableField("unit_name")
    private String unitName;

    /**
     * 开户银行
     */
    @TableField("bank_of_deposit")
    private String bankOfDeposit;

    /**
     * 账号
     */
    @TableField("account_number")
    private BigDecimal accountNumber;
    /**
     * 用款金额
     */
    @TableField("amount_used")
    private String amountUsed;

    /**
     * 是否开票  1-未开，2-已开
     */
    @TableField("invoice_or_not")
    private String invoiceOrNot;

    /**
     * 用途
     */
    @TableField("expense_reporting_purpose")
    private String expenseReportingPurpose;

    /**
     * 项目名称
     */
    @TableField("entry_name")
    private String entryName;

    /**
     * 项目总价
     */
    @TableField("total_project_price")
    private BigDecimal totalProjectPrice;
    /**
     * 付款明细
     */
    @TableField("payment_details")
    private String paymentDetails;

    /**
     * 未付款多少
     */
    @TableField("unpaid_number")
    private Integer unpaidNumber;

    /**
     * 汇款备注
     */
    @TableField("remittance_remarks")
    private String remittanceRemarks;

    /**
     * 制单人
     */
    @TableField("expense_reporting_preparer")
    private String expenseReportingPreparer;

    /**
     * 状态   1-登记，2-确认
     */
    @TableField("expense_reporting_state")
    private String expenseReportingState;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

}

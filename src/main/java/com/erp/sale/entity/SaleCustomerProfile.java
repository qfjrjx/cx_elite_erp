package com.erp.sale.entity;


import com.erp.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 客户档案表

             数据库表名：                                             对应java表名：

             jr_sale_customer_profile                            SaleCustomerProfile Entity
 *
 * @author qiufeng
 * @date 2021-10-08 16:51:04
 */
@Data
@TableName("jr_sale_customer_profile")
@Excel("客户档案表")
public class SaleCustomerProfile {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联业务人员表
     */
    @TableField("personnel_id")
    private Long personnelId;

    /**
     * 业务人员
     */
    @ExcelField(value = "业务人员")
    @TableField("personnel_name")
    private String personnelName;

    /**
     * 客户全称
     */
    @TableField("customer_full_name")
    @ExcelField(value = "客户全称")
    private String customerFullName;
    //全称简拼
    private transient String abbreviationOfFullName;

    /**
     * 客户简称
     */
    @TableField("customer_abbreviation")
    @ExcelField(value = "客户简称")
    private String customerAbbreviation;

    /**
     * 单位电话
     */
    @TableField("work_telephone")
    @ExcelField(value = "单位电话")
    private String workTelephone;

    /**
     * 单位传真
     */
    @TableField("unit_fax")
    @ExcelField(value = "单位传真")
    private String unitFax;

    /**
     * 联系人
     */
    @TableField("customer_contacts")
    @ExcelField(value = "联系人")
    private String customerContacts;

    /**
     * 联系电话
     */
    @TableField("contact_number")
    @ExcelField(value = "联系电话")
    private String contactNumber;

    /**
     * 电子邮箱
     */
    @TableField("customer_mail")
    @ExcelField(value = "电子邮箱")
    private String customerMail;

    /**
     * 单位地址
     */
    @TableField("unit_address")
    @ExcelField(value = "单位地址")
    private String unitAddress;

    /**
     * 状态                1-启用，2-禁用
     */
    @TableField("customer_state")
    @ExcelField(value = "状态", writeConverterExp = "1=启用,2=禁用")
    private Integer customerState;

    /**
     * 备注
     */
    @TableField("customer_remarks")
    @ExcelField(value = "备注")
    private String customerRemarks;

    /**
     * 开户银行
     */
    @TableField("bank_of_deposit")
    @ExcelField(value = "开户银行")
    private String bankOfDeposit;

    /**
     * 银行账号
     */
    @TableField("bank_account")
    @ExcelField(value = "银行账号")
    private String bankAccount;

    /**
     * 单位税号
     */
    @TableField("unit_tax_no")
    @ExcelField(value = "单位税号")
    private Integer unitTaxNo;

    /**
     * 开票电话
     */
    @TableField("billing_telephone")
    @ExcelField(value = "开票电话")
    private String billingTelephone;

    /**
     * 开票地址
     */
    @TableField("billing_address")
    @ExcelField(value = "开票地址")
    private String billingAddress;

    /**
     * 收货人
     */
    @TableField("customer_consignee")
    @ExcelField(value = "收货人")
    private String customerConsignee;

    /**
     * 收货电话
     */
    @TableField("receiving_telephone")
    @ExcelField(value = "收货电话")
    private String receivingTelephone;

    /**
     * 邮政编码
     */
    @TableField("postal_code")
    @ExcelField(value = "邮政编码")
    private String postalCode;

    /**
     * 收货地址
     */
    @TableField("receiving_address")
    @ExcelField(value = "收货地址")
    private String receivingAddress;

    /**
     * 建档日期
     */
    @TableField("created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "建档日期", writeConverter = TimeConverter.class)
    private Date createdDate;
    /*开始时间*/
    private transient String createdDateFrom;
   /* 结束时间*/
    private transient String createdDateTo;
    /**
     * 修改建档日期
     */
    @TableField("update_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateCreatedDate;

    /**
     * 客户编码
     */
    @TableField("customer_code")
    @ExcelField(value = "客户编码")
    private String customerCode;

}

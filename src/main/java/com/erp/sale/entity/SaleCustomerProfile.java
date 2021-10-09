package com.erp.sale.entity;


import com.erp.common.converter.TimeConverter;
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
    @TableField("personnel_name")
    private String personnelName;

    /**
     * 客户全称
     */
    @TableField("customer_full_name")
    private String customerFullName;
    //全称简拼
    private transient String abbreviationOfFullName;

    /**
     * 客户简称
     */
    @TableField("customer_abbreviation")
    private String customerAbbreviation;

    /**
     * 单位电话
     */
    @TableField("work_telephone")
    private String workTelephone;

    /**
     * 单位传真
     */
    @TableField("unit_fax")
    private String unitFax;

    /**
     * 联系人
     */
    @TableField("customer_contacts")
    private String customerContacts;

    /**
     * 联系电话
     */
    @TableField("contact_number")
    private String contactNumber;

    /**
     * 电子邮箱
     */
    @TableField("customer_mail")
    private String customerMail;

    /**
     * 单位地址
     */
    @TableField("unit_address")
    private String unitAddress;

    /**
     * 状态                1-启用，2-禁用
     */
    @TableField("customer_state")
    private Integer customerState;

    /**
     * 备注
     */
    @TableField("customer_remarks")
    private String customerRemarks;

    /**
     * 开户银行
     */
    @TableField("bank_of_deposit")
    private String bankOfDeposit;

    /**
     * 银行账号
     */
    @TableField("bank_account")
    private String bankAccount;

    /**
     * 单位税号
     */
    @TableField("unit_tax_no")
    private Integer unitTaxNo;

    /**
     * 开票电话
     */
    @TableField("billing_telephone")
    private String billingTelephone;

    /**
     * 开票地址
     */
    @TableField("billing_address")
    private String billingAddress;

    /**
     * 收货人
     */
    @TableField("customer_consignee")
    private String customerConsignee;

    /**
     * 收货电话
     */
    @TableField("receiving_telephone")
    private String receivingTelephone;

    /**
     * 邮政编码
     */
    @TableField("postal_code")
    private String postalCode;

    /**
     * 收货地址
     */
    @TableField("receiving_address")
    private String receivingAddress;

    /**
     * 建档日期
     */
    @TableField("created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    private transient String createdDateFrom;
    private transient String createdDateTo;

    /**
     * 客户编码
     */
    @TableField("customer_code")
    private String customerCode;

}

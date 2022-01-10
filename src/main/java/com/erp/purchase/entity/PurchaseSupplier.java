package com.erp.purchase.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 供货单位表 Entity
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:05
 */
@Data
@TableName("jr_purchase_supplier")
public class PurchaseSupplier {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 状态  1-启用，2-禁用
     */
    @TableField("supplier_state")
    private String supplierState;

    /**
     * 供应商编码
     */
    @TableField("supplier_code")
    private String supplierCode;

    /**
     * 供应商全称
     */
    @TableField("supplier_full_name")
    private String supplierFullName;

    /**
     * 供应商全称简拼
     */
    private String abbreviationOfFullName;

    /**
     * 供应商简称
     */
    @TableField("supplier_abbreviation")
    private String supplierAbbreviation;

    /**
     * 联系人
     */
    @TableField("supplier_contacts")
    private String supplierContacts;

    /**
     * 联系电话（基本信息）
     */
    @TableField("supplier_contact_number")
    private String supplierContactNumber;

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
     * 电子邮箱
     */
    @TableField("electronic_mailbox")
    private String electronicMailbox;

    /**
     * 备注
     */
    @TableField("supplier_remarks")
    private String supplierRemarks;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

    /**
     * 单位地址
     */
    @TableField("unit_address")
    private String unitAddress;

    /**
     * 开户银行
     */
    @TableField("bank_of_deposit")
    private String bankOfDeposit;

    /**
     * 联系电话（财税信息）
     */
    @TableField("contact_number")
    private String contactNumber;

    /**
     * 税号
     */
    @TableField("duty_paragraph")
    private String dutyParagraph;

    /**
     * 银行帐号
     */
    @TableField("bank_account_number")
    private String bankAccountNumber;

    /**
     * 发票地址
     */
    @TableField("invoice_address")
    private String invoiceAddress;

    /**
     * 收货人
     */
    @TableField("logistics_consignee")
    private String logisticsConsignee;

    /**
     * 联系电话（物流信息）
     */
    @TableField("logistics_contact_number")
    private String logisticsContactNumber;

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

}

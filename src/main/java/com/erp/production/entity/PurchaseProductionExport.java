package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.converter.TimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产计划 Entity
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
@Data
@TableName("jr_production_plan")
@Excel("生产采购统计")
public class PurchaseProductionExport {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态：1 登记 2 完成
     */
    @TableField("plan_state")
    @ExcelField(value = "状态", writeConverterExp = "1=未完,2=完成")
    private String planState;

    /**
     * 交货日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("plan_date")
    @ExcelField(value = "交货日期", writeConverter = TimeConverter.class)
    private Date planDate;

    /**
     * 订单号
     */
    @TableField("plan_number")
    @ExcelField(value = "订单号")
    private String planNumber;

    /**
     * 客户名称
     */
    @TableField("plan_customer_name")
    @ExcelField(value = "客户名称")
    private String planCustomerName;

    /**
     * 产品名称
     */
    @TableField("plan_product_name")
    @ExcelField(value = "产品名称")
    private String planProductName;

    /**
     * 规格型号
     */
    @TableField("plan_specifications")
    @ExcelField(value = "规格型号")
    private String planSpecifications;

    /**
     * 机器码
     */
    @TableField("plan_code")
    @ExcelField(value = "机器码")
    private String planCode;

    /**
     * 配置
     */
    @TableField("plan_configuration")
    @ExcelField(value = "配置")
    private String planConfiguration;

    private String configureName;

    /**
     * 机器BOM
     */
    @TableField("plan_machine_bom")
    private String planMachineBom;

    /**
     * 发货单号
     */
    @TableField("plan_invoice")
    private String planInvoice;

    /**
     * 出厂编码
     */
    @TableField("plan_factory")
    private String planFactory;

    /**
     * 机加附件
     */
    @TableField("plan_machining")
    private String planMachining;

    /**
     * 装配附件
     */
    @TableField("plan_assembly")
    private String planAssembly;

    /**
     * 说明1
     */
    @TableField("plan_instructions_one")
    private String planInstructionsOne;

    /**
     * 说明2
     */
    @TableField("plan_instructions_two")
    private String planInstructionsTwo;

    /**
     * 说明3
     */
    @TableField("plan_instructions_three")
    private String planInstructionsThree;

    /**
     * 制单人
     */
    @TableField("plan_voucher")
    private String planVoucher;

    /**
     * 制单时间
     */
    @TableField("plan_voucher_date")
    private Date planVoucherDate;

    /**
     * 备注
     */
    @TableField("plan_note")
    private String planNote;

    private transient String signedDateFrom;
    private transient String signedDateTo;

    private transient String signedDateFromOne;
    private transient String signedDateToOne;

    /**
     * 设置BOM日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("plan_machine_bom_date")
    private Date planMachineBomDate;

    /**
     * 金额
     */
    @TableField("plan_money")
    private BigDecimal planMoney;

    /**
     * 生产负责人
     */
    @TableField("plan_head")
    private String planHead;

    /**
     * 生产预计完成
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("plan_expect_date")
    private Date planExpectDate;

    /**
     * 生产实际完成
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("plan_actual_date")
    private Date planActualDate;

    /**
     * 生产工资
     */
    @TableField("plan_wage")
    private BigDecimal planWage;

    /**
     * 生产负责人
     */
    @TableField("plan_head_one")
    private String planHeadOne;

    /**
     * 生产预计完成
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("plan_expect_date_one")
    private Date planExpectDateOne;

    /**
     * 生产实际完成
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("plan_actual_date_one")
    private Date planActualDateOne;

    /**
     * 生产工资
     */
    @TableField("plan_wage_one")
    private BigDecimal planWageOne;

    /**
     * 生产内容
     */
    @TableField("plan_content")
    private String planContent;

    /**
     * 单位
     */
    @TableField("plan_unit")
    @ExcelField(value = "单位")
    private String planUnit;

    @ExcelField(value = "数量")
    private String planAmount;

    /**
     * 采购负责人
     */
    @TableField("plan_negative")
    @ExcelField(value = "采购负责人")
    private String planNegative;

    /**
     * 采购预计完成时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ExcelField(value = "采购预计完成时间", writeConverter = TimeConverter.class)
    @TableField("plan_expect_date_two")
    private Date planExpectDateTwo;

    /**
     * 采购实际完成时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ExcelField(value = "采购实际完成时间", writeConverter = TimeConverter.class)
    @TableField("plan_actual_date_two")
    private Date planActualDateTwo;

    /**
     * 采购内容
     */
    @TableField("plan_content_one")
    @ExcelField(value = "采购内容")
    private String planContentOne;

}

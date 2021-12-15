package com.erp.enterprise.entity;

import java.util.Date;

import com.erp.sale.entity.SaleOrder;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 业绩日报表  Entity
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
@Data
@TableName("jr_enterprise_performance_daily")
public class EnterprisePerformanceDaily {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("performance_daily_date")
    private Date performanceDailyDate;

    private transient String signedDateFrom;
    private transient String signedDateTo;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 姓名
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 类型
     */
    @TableField("performance_daily_type")
    private String performanceDailyType;

    /**
     * 金额
     */
    @TableField("amount_money")
    private String amountMoney;

    /**
     * 备注
     */
    @TableField("performance_daily_remarks")
    private String performanceDailyRemarks;

    /**
     * 状态   1-登记，2-确认
     */
    @TableField("performance_daily_state")
    private String performanceDailyState;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;



}

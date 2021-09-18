package com.erp.personnel.entity;

import java.io.Serializable;
import java.util.Date;

import com.erp.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工合同表 Entity
 *
 * @author qiufeng
 * @date 2021-09-16 10:46:36
 */
@Data
@TableName("jr_personnel_contract")
@Excel("员工合同信息表")
public class PersonnelContract implements Serializable {

    private static final long serialVersionUID = -7912592673056859104L;
    /**
     * 主键
     */
    @TableId(value = "contract_id", type = IdType.AUTO)
    private Long contractId;

    /**
     * 关联员工档案表
     */
    @TableField("user_id")
    private String userId;

    /**
     * 员工编号
     */
    @TableField("staff_wno")
    @ExcelField(value = "员工工号")
    private String staffWno;

    /**
     * 员工姓名
     */
    @TableField("staff_name")
    @ExcelField(value = "员工姓名")
    private String staffName;

    /**
     * 签订日期
     */
    @TableField("signed_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "签订日期", writeConverter = TimeConverter.class)
    private Date signedDate;
    private transient String signedDateFrom;
    private transient String signedDateTo;

    /**
     * 到期时间
     */
    @TableField("expire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelField(value = "到期时间", writeConverter = TimeConverter.class)
    private Date expireDate;
    private transient String expireDateFrom;
    private transient String expireDateTo;

    /**
     * 合同状态 1-未到期 ， 2-已到期
     */
    @TableField("contract_state")
    @ExcelField(value = "合同状态", writeConverterExp = "1=未到期,2=已到期")
    private Integer contractState;

    /**
     * 合同还有30天到期状态  默认1-未确认，2-已确认
     */
    @TableField("contract_tips_state")
    private Integer contractTipsState;

}

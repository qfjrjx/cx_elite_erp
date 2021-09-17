package com.erp.personnel.entity;

import java.io.Serializable;
import java.util.Date;

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
    private String staffWno;

    /**
     * 员工姓名
     */
    @TableField("staff_name")
    private String staffName;

    /**
     * 签订日期
     */
    @TableField("signed_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signedDate;
    private transient String signedDateFrom;
    private transient String signedDateTo;

    /**
     * 到期时间
     */
    @TableField("expire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    private transient String expireDateFrom;
    private transient String expireDateTo;

    /**
     * 状态
     */
    @TableField("contract_state")
    private Integer contractState;

}

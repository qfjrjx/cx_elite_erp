package com.erp.production.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 缺件领用 Entity
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:17
 */
@Data
@TableName("jr_lack_recipients")
public class LackRecipients {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态
     */
    @TableField("lack_state")
    private String lackState;

    /**
     * 日期
     */
    @TableField("lack_date")
    private Date lackDate;

    /**
     * 单号
     */
    @TableField("lack_code")
    private String lackCode;

    /**
     * 机器码
     */
    @TableField("lack_machine")
    private String lackMachine;

    /**
     * 库房
     */
    @TableField("lack_warehouse")
    private String lackWarehouse;

    /**
     * 领用部门
     */
    @TableField("lack_department")
    private String lackDepartment;

    /**
     * 领用人
     */
    @TableField("lack_people")
    private String lackPeople;

    /**
     * 出库人
     */
    @TableField("lack_outbound")
    private String lackOutbound;

    /**
     * 出库时间
     */
    @TableField("lack_outbound_date")
    private Date lackOutboundDate;

    /**
     * 创建日期
     */
    @TableField("lack_create_date")
    private Date lackCreateDate;

    /**
     * 创建人
     */
    @TableField("lack_create_name")
    private String lackCreateName;

    private transient String signedDateFrom;
    private transient String signedDateTo;

}

package com.erp.personnel.entity;

import java.util.Date;

import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 领取记录表 Entity
 *
 * @author qiufeng
 * @date 2021-09-14 15:45:00
 */
@Data
@TableName("jr_personnel_receive")
public class PersonnelReceive {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 工号
     */
    @TableField("receive_wno")
    private String receiveWno;

    /**
     * 领取物品
     */
    @TableField("receive_goods")
    private String receiveGoods;

    /**
     * 领取时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 1.已领取2.已归还
     */
    @TableField("receive_state")
    private Integer receiveState;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
    /**
     * 部门id
     */
    @TableField(exist = false)
    private Long deptId;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 岗位
     */
    private String positionName;




}

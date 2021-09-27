package com.erp.personnel.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 宿舍管理表 Entity
 *
 * @author qiufeng
 * @date 2021-09-19 09:35:28
 */
@Data
@TableName("jr_personnel_dormitory")
public class PersonnelDormitory {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍编号
     */
    @TableField("dormitory_no")
    private String dormitoryNo;

    /**
     * 宿舍地点
     */
    @TableField("dormitory_place")
    private String dormitoryPlace;

    /**
     * 床位总数
     */
    @TableField("reside_nnt")
    private Integer resideNnt;

    /**
     * 已用床位
     */
    @TableField("present_nnt")
    private Integer presentNnt;

    /**
     * 状态   1-启用，2-禁用
     */
    @TableField("dormitory_state")
    private String dormitoryState;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 管理员
     */
    @TableField("dormitory_administrators")
    private String dormitoryAdministrators;

}

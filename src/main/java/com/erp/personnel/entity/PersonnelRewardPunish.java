package com.erp.personnel.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 奖罚记录 Entity
 *
 * @author qiufeng
 * @date 2021-09-25 14:06:53
 */
@Data
@TableName("jr_personnel_reward_punish")
public class PersonnelRewardPunish {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 部门
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 岗位
     */
    @TableField("position_name")
    private String positionName;

    /**
     * 职位
     */
    @TableField("grouping_name")
    private String groupingName;

    /**
     * 奖罚数
     */
    @TableField("magnitude")
    private Integer magnitude;

    /**
     * 原因
     */
    @TableField("reward_punish_explain")
    private String rewardPunishExplain;

    /**
     * 类型 1奖励2.惩罚
     */
    @TableField("reward_punish_type")
    private Integer rewardPunishType;

    /**
     * 入职日期
     */
    @TableField("entry_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 创建日期
     */
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 奖罚日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("reward_punish_date")
    private Date rewardPunishDate;

}

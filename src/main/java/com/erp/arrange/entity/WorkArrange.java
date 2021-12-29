package com.erp.arrange.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 工作安排表 Entity
 *
 * @author qiufeng
 * @date 2021-12-20 15:35:45
 */
@Data
@TableName("jr_work_arrange")
public class WorkArrange {

    /**
     * 1-登记，
     */
    public static final String STATE_REGISTER = "1";
    /**
     * 2-确认，
     */
    public static final String STATE_CONFIRM = "2";
    /**
     * 3-开始，
     */
    public static final String STATE_START = "3";
    /**
     * 4-完成，
     */
    public static final String STATE_COMPLETE = "4";
    /**
     * 5-考核，
     */
    public static final String STATE_ASSESSMENT = "5";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单号
     */
    @TableField("odd_numbers")
    private String oddNumbers;

    /**
     * 起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("start_date")
    private Date startDate;

    private transient String signedDateFrom;
    private transient String signedDateTo;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("end_date")
    private Date endDate;

    /**
     * 工作类型  1-会议，2-日常，3-外出，4-接待
     */
    @TableField("work_type")
    private String workType;

    /**
     * 提醒日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("reminder_date")
    private Date reminderDate;

    /**
     * 工作地点
     */
    @TableField("duty_station")
    private String dutyStation;

    /**
     * 工作内容
     */
    @TableField("job_content")
    private String jobContent;

    /**
     * 发起人
     */
    @TableField("arrange_initiator")
    private String arrangeInitiator;

    /**
     * 责任人
     */
    @TableField("person_liable")
    private String personLiable;

    /**
     * 监督人
     */
    @TableField("arrange_supervisor")
    private String arrangeSupervisor;

    /**
     * 状态    1-登记，2-确认，3-开始，4-完成，5-考核
     */
    @TableField("arrange_state")
    private String arrangeState;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 完成时间
     */
    @TableField("completion_time")
    private Date completionTime;

    /**
     * 考核状态   1-合格 ， 2-不合格
     */
    @TableField("appraisal_status")
    private String appraisalStatus;

    /**
     * 考核评语
     */
    @TableField("assessment_comments")
    private String assessmentComments;

    /**
     * 考核时间
     */
    @TableField("assessment_time")
    private Date assessmentTime;

    /**
     * 考核人
     */
    @TableField("assessment_people")
    private String assessmentPeople;

    /**
     * 处罚金额
     */
    @TableField("penalty_amount")
    private BigDecimal penaltyAmount;

}

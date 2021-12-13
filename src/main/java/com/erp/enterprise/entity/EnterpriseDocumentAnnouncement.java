package com.erp.enterprise.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公文公告表 Entity
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:08
 */
@Data
@TableName("jr_enterprise_document_announcement")
public class EnterpriseDocumentAnnouncement {

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
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("creation_time")
    private Date creationTime;

    private transient String signedDateFrom;
    private transient String signedDateTo;

    /**
     * 部门
     */
    @TableField("department_id")
    private Long departmentId;

    private String departmentName;

    /**
     * 类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 主题
     */
    @TableField("announcement_subject")
    private String announcementSubject;

    /**
     * 状态  1-登记，2-确认，3-核准
     */
    @TableField("announcement_state")
    private String announcementState;

    /**
     * 附件
     */
    @TableField("announcement_enclosure")
    private String announcementEnclosure;

    /**
     * 备注
     */
    @TableField("announcement_remarks")
    private String announcementRemarks;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

}

package com.erp.enterprise.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 参数设置表 Entity
 *
 * @author qiufeng
 * @date 2021-12-09 10:22:55
 */
@Data
@TableName("jr_enterprise_resources_parameters")
public class EnterpriseResourcesParameters {

    /**
     * 1-工作类型，
     */
    public static final String WORK_TYPE = "1";
    /**
     * 2-公文类型，
     */
    public static final String DOCUMENT_TYPE = "2";
    /**
     * 3-文件档案类型，
     */
    public static final String FILE_TYPE = "3";
    /**
     * 4-责任单位
     */
    public static final String RESPONSIBLE_UNIT = "4";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数值
     */
    @TableField("parameter_value")
    private String parameterValue;

    /**
     * 参数名称
     */
    @TableField("parameter_name")
    private String parameterName;

    /**
     * 参数类别  1-工作类型，2-公文类型，3-文件档案类型，4-责任单位
     */
    @TableField("parameter_category")
    private String parameterCategory;

    /**
     * 排序
     */
    @TableField("common_resource_sort")
    private String commonResourceSort;

    /**
     * 状态
     */
    @TableField("common_resource_state")
    private String commonResourceState;

    /**
     * 备注
     */
    @TableField("common_resource_remarks")
    private String commonResourceRemarks;

    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;

}

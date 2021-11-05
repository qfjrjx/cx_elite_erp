package com.erp.sale.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售申请：【设计回复】【采购回复】【生产回复】【装配回复】 Entity
 *
 * @author qiufeng
 * @date 2021-11-04 14:09:01
 */
@Data
@TableName("jr_sale_application_reply")
public class SaleApplicationReply {


    /**
     * 回复部门：设计回复
     */
    public static final String design_reply = "设计回复";
    /**
     * 回复部门：采购回复
     */
    public static final String purchase_reply = "采购回复";
    /**
     * 回复部门：生产回复
     */
    public static final String production_reply = "生产回复";
    /**
     * 回复部门：装配回复
     */
    public static final String assembling_reply = "装配回复";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 销售申请表id
     */
    @TableField("sale_application_id")
    private Long saleApplicationId;

    /**
     * 回复人
     */
    @TableField("respondent")
    private String respondent;

    /**
     * 回复日期
     */
    @TableField("reply_date")
    private Date replyDate;

    /**
     * 回复交期
     */
    @TableField("reply_delivery_date")
    private Date replyDeliveryDate;

    /**
     * 回复内容
     */
    @TableField("reply_content")
    private String replyContent;

    /**
     * 回复部门
     */
    @TableField("reply_department")
    private String replyDepartment;

}

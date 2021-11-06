package com.erp.sale.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售申请表 Entity
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
@Data
@TableName("jr_sale_application")
public class SaleApplication {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请交货日期
     */
    @TableField("requested_delivery_date")
    private Date requestedDeliveryDate;

    private transient String signedDateFrom;
    private transient String signedDateTo;
    /**
     * 申请单号
     */
    @TableField("application_no")
    private String applicationNo;

    /**
     * 关联客户档案id
     */
    @TableField("customer_id")
    private Long customerId;

    /**
     * 客户
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 业务员
     */
    @TableField("salesman_name")
    private String salesmanName;

    /**
     * 

产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 规格型号
     */
    @TableField("specification_model")
    private String specificationModel;

    /**
     * 配置
     */
    @TableField("configure_name")
    private String configureName;

    /**
     * 单位
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 数量
     */
    @TableField("quantity_name")
    private Integer quantityName;

    /**
     * 附件
     */
    @TableField("enclosure_name")
    private String enclosureName;

    /**
     * 设计交期
     */
    @TableField("design_date")
    private Date designDate;

    /**
     * 设计回复
     */
    @TableField("design_reply")
    private String designReply;

    /**
     * 采购交期
     */
    @TableField("purchase_date")
    private Date purchaseDate;

    /**
     * 采购回复
     */
    @TableField("purchase_reply")
    private String purchaseReply;

    /**
     * 生产交期
     */
    @TableField("production_date")
    private Date productionDate;

    /**
     * 生产回复
     */
    @TableField("production_reply")
    private String productionReply;

    /**
     * 装配交期
     */
    @TableField("assembling_date")
    private Date assemblingDate;

    /**
     * 装配回复
     */
    @TableField("assembling_reply")
    private String assemblingReply;

    /**
     * 说明
     */
    @TableField("explain_name")
    private String explainName;

    /**
     * 机器要求
     */
    @TableField("machine_requirements")
    private String machineRequirements;

    /**
     * 电脑配置
     */
    @TableField("computer_configuration")
    private String computerConfiguration;

    /**
     * 刀具大小
     */
    @TableField("tool_size")
    private String toolSize;
    /**
     * 每小时产量
     */
    @TableField("hourly_production")
    private String hourlyProduction;
    /**
     * 加工工序
     */
    @TableField("processing_procedure")
    private String processingProcedure;

    /**
     * 夹具要求
     */
    @TableField("fixture_requirements")
    private String fixtureRequirements;

    /**
     * 产品形状
     */
    @TableField("product_shape")
    private String productShape;

    /**
     * 产品尺寸
     */
    @TableField("product_size")
    private String productSize;

    /**
     * 其他要求
     */
    @TableField("other_requirements")
    private String otherRequirements;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

}

package com.erp.sale.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售申请表-附表 Entity
 *
 * @author qiufeng
 * @date 2022-03-23 16:53:10
 */
@Data
@TableName("jr_sale_application_schedule")
public class SaleApplicationSchedule {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请单号
     */
    @TableField("application_no")
    private String applicationNo;

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

}

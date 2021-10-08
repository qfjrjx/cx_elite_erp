package com.erp.sale.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 业务人员表

               数据库表名：                                         对应java表名：
    
               jr_sale_business_personnel                    SaleBusinessPersonnel Entity
 *
 * @author qiufeng
 * @date 2021-10-08 13:34:13
 */
@Data
@TableName("jr_sale_business_personnel")
public class SaleBusinessPersonnel {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联员工档案id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 工号
     */
    @TableField("personnel_number")
    private String personnelNumber;

    /**
     * 姓名
     */
    @TableField("personnel_name")
    private String personnelName;

    /**
     * 区域   1-江苏，2-温州，3-中山，4-永康
     */
    @TableField("personnel_region")
    private Integer personnelRegion;

    /**
     * 1-启用，2-禁用
     */
    @TableField("personnel_state")
    private Integer personnelState;

    /**
     * 备注
     */
    @TableField("personnel_remarks")
    private String personnelRemarks;
    /**
     * 参数名称
     */
    private String parameterName;


}

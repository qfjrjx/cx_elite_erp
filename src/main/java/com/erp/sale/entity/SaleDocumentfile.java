package com.erp.sale.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 上传文件表 Entity
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
@Data
@TableName("jr_sale_documentfile")
public class SaleDocumentfile {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 加密文件名
     */
    @TableField("name")
    private String name;

    /**
     * 真实文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件地址
     */
    @TableField("path")
    private String path;

    /**
     * 文件后缀名
     */
    @TableField("suffix")
    private String suffix;

    /**
     * 创建时间
     */
    @TableField("time")
    private Date time;

}

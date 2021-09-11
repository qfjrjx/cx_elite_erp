package com.erp.personnel.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author qiufeng
 * @date 2021-09-11 09:27:44
 */
@Data
@TableName("jr_archives_parameters")
public class ArchivesParameters {

    /**
     * 
     */
    @TableField("archives_id")
    private Long archivesId;

    /**
     * 
     */
    @TableField("parameters_id")
    private Long parametersId;

}

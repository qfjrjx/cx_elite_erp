package com.erp.arrange.entity;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 工作安排统计 Entity
 *
 * @author qiufeng
 * @date 2021-12-24 15:23:08
 */
@Data
@Excel("工作安排统计")
public class WorkArrangementStatistics {
    /**
     * 月度
     */
    @ExcelField(value = "月度")
    private String monthly;

    /**
     * 员工姓名
     */
    @ExcelField(value = "员工姓名")
    private String personLiable;

    /**
     * 合格（个数）
     */
    @ExcelField(value = "合格（个数）")
    private Integer qualified;

    /**
     * 不合格（个数）
     */
    @ExcelField(value = "不合格（个数）")
    private Integer unqualified;

    /**
     * 处罚金额
     */
    @ExcelField(value = "处罚金额")
    private BigDecimal penaltyAmount;

    /**
     * 本月工作任务（总数量）
     */
    @ExcelField(value = "本月工作任务（总数量）")
    private Integer totalQuantity;


    private transient String signedDateFrom;
    private transient String signedDateTo;


}

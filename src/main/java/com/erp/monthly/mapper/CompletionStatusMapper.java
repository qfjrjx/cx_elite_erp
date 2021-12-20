package com.erp.monthly.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.monthly.entity.CompletionStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 完成情况表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-17 14:06:34
 */
@Mapper
public interface CompletionStatusMapper extends BaseMapper<CompletionStatus> {

    long countCompletionStatus(@Param("completionStatus") CompletionStatus completionStatus);

    IPage<CompletionStatus> findCompletionStatusPage(Page<CompletionStatus> page,@Param("completionStatus") CompletionStatus completionStatus);
}

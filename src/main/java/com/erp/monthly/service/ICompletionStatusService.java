package com.erp.monthly.service;

import com.erp.common.entity.QueryRequest;
import com.erp.monthly.entity.CompletionStatus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 完成情况表 Service接口
 *
 * @author qiufeng
 * @date 2021-12-17 14:06:34
 */
public interface ICompletionStatusService extends IService<CompletionStatus> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param completionStatus completionStatus
     * @return IPage<CompletionStatus>
     */
    IPage<CompletionStatus> findCompletionStatuss(QueryRequest request, CompletionStatus completionStatus);

    /**
     * 查询（所有）
     *
     * @param completionStatus completionStatus
     * @return List<CompletionStatus>
     */
    List<CompletionStatus> findCompletionStatuss(CompletionStatus completionStatus);

    /**
     * 新增
     *
     * @param completionStatus completionStatus
     */
    void createCompletionStatus(CompletionStatus completionStatus);

    /**
     * 修改
     *
     * @param completionStatus completionStatus
     */
    void updateCompletionStatus(CompletionStatus completionStatus);

    /**
     * 删除
     *
     * @param ids completionStatus
     */
    void deleteCompletionStatus(String[] ids);
}

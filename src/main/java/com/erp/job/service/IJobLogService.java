package com.erp.job.service;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.job.entity.JobLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

/**
 * @author qiufeng
 */
public interface IJobLogService extends IService<JobLog> {

    /**
     * 获取定时任务日志分页数据
     *
     * @param request request
     * @param jobLog  jobLog
     * @return 定时任务日志分页数据
     */
    IPage<JobLog> findJobLogs(QueryRequest request, JobLog jobLog);

    /**
     * 保存定时任务日志
     *
     * @param log 定时任务日志
     */
    @Async(FebsConstant.FEBS_SHIRO_THREAD_POOL)
    void saveJobLog(JobLog log);

    /**
     * 删除定时任务日志
     *
     * @param jobLogIds 定时任务日志id数组
     */
    void deleteJobLogs(String[] jobLogIds);
}

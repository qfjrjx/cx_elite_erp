package com.erp.monthly.service;

import com.erp.common.entity.QueryRequest;
import com.erp.monthly.entity.TaskSettings;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 任务设置表  Service接口
 *
 * @author qiufeng
 * @date 2021-12-16 17:12:06
 */
public interface ITaskSettingsService extends IService<TaskSettings> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param taskSettings taskSettings
     * @return IPage<TaskSettings>
     */
    IPage<TaskSettings> findTaskSettingss(QueryRequest request, TaskSettings taskSettings);

    /**
     * 查询（所有）
     *
     * @param taskSettings taskSettings
     * @return List<TaskSettings>
     */
    List<TaskSettings> findTaskSettingss(TaskSettings taskSettings);

    /**
     * 新增
     *
     * @param taskSettings taskSettings
     */
    void createTaskSettings(TaskSettings taskSettings);

    /**
     * 修改
     *
     * @param taskSettings taskSettings
     */
    void updateTaskSettings(TaskSettings taskSettings);

    /**
     * 删除
     *
     * @param ids taskSettings
     */
    void deleteTaskSettings(String[] ids);

    TaskSettings taskSettingsById(Long id);
}

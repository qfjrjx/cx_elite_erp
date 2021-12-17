package com.erp.monthly.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.monthly.entity.TaskSettings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 任务设置表  Mapper
 *
 * @author qiufeng
 * @date 2021-12-16 17:12:06
 */
@Mapper
public interface TaskSettingsMapper extends BaseMapper<TaskSettings> {

    long countTaskSettings(@Param("taskSettings") TaskSettings taskSettings);

    IPage<TaskSettings> findTaskSettingsPage(Page<TaskSettings> page,@Param("taskSettings") TaskSettings taskSettings);

    TaskSettings taskSettingsById(@Param("id") Long id);
}

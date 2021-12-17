package com.erp.monthly.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.monthly.entity.TaskSettings;
import com.erp.monthly.mapper.TaskSettingsMapper;
import com.erp.monthly.service.ITaskSettingsService;
import com.erp.technology.entity.TechnologyProductCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 任务设置表  Service实现
 *
 * @author qiufeng
 * @date 2021-12-16 17:12:06
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskSettingsServiceImpl extends ServiceImpl<TaskSettingsMapper, TaskSettings> implements ITaskSettingsService {

    private final TaskSettingsMapper taskSettingsMapper;

    @Override
    public IPage<TaskSettings> findTaskSettingss(QueryRequest request, TaskSettings taskSettings) {
        Page<TaskSettings> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countTaskSettings(taskSettings));
        return baseMapper.findTaskSettingsPage(page,taskSettings);
    }

    @Override
    public List<TaskSettings> findTaskSettingss(TaskSettings taskSettings) {
	    LambdaQueryWrapper<TaskSettings> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTaskSettings(TaskSettings taskSettings) {
        this.save(taskSettings);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTaskSettings(TaskSettings taskSettings) {
        this.saveOrUpdate(taskSettings);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTaskSettings(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public TaskSettings taskSettingsById(Long id) {

        return baseMapper.taskSettingsById(id);
    }
}

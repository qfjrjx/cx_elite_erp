package com.erp.monthly.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.monthly.entity.TaskSettings;
import com.erp.monthly.service.ITaskSettingsService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiufeng
 */
@Controller("monthlyView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "monthly")
@RequiredArgsConstructor
public class ViewController {

    private final ITaskSettingsService taskSettingsService;

    /*月度任务管理模块开始*/
    /* */
    @GetMapping("taskSettings/list")
    @RequiresPermissions("taskSettings:view")
    public String taskSettingsIndex(){
        return FebsUtil.view("monthlyTask/monthlyTaskList");
    }

    //任务设置添加
    @GetMapping("taskSettings/add")
    @RequiresPermissions("taskSettings:add")
    public String taskSettingsAdd() {
        return FebsUtil.view("monthlyTask/monthlyTaskAdd");
    }
    //任务设置修改
    @GetMapping("taskSettings/update/{id}")
    @RequiresPermissions("taskSettings:update")
    public String taskSettingsUpdate(@PathVariable Long id, Model model) {
        taskSettingsModel(id, model, false);
        return FebsUtil.view("monthlyTask/monthlyTaskUpdate");
    }
    //任务设置修改回填
    private void taskSettingsModel(Long id, Model model, Boolean transform) {
        TaskSettings taskSettings = taskSettingsService.taskSettingsById(id);
        model.addAttribute("taskSettings", taskSettings);
    }
    //月度任务模块结束
}

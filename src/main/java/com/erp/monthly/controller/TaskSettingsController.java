package com.erp.monthly.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.monthly.entity.TaskSettings;
import com.erp.monthly.service.ITaskSettingsService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 任务设置表  Controller
 *
 * @author qiufeng
 * @date 2021-12-16 17:12:06
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class TaskSettingsController extends BaseController {

    private final ITaskSettingsService taskSettingsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "taskSettings")
    public String taskSettingsIndex(){
        return FebsUtil.view("taskSettings/taskSettings");
    }

    @GetMapping("taskSettings")
    @ResponseBody
    @RequiresPermissions("taskSettings:list")
    public FebsResponse getAllTaskSettingss(TaskSettings taskSettings) {
        return new FebsResponse().success().data(taskSettingsService.findTaskSettingss(taskSettings));
    }

    @GetMapping("taskSettings/list")
    @ResponseBody
    @RequiresPermissions("taskSettings:view")
    public FebsResponse taskSettingsList(QueryRequest request, TaskSettings taskSettings) {
        Map<String, Object> dataTable = getDataTable(this.taskSettingsService.findTaskSettingss(request, taskSettings));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增TaskSettings", exceptionMessage = "新增TaskSettings失败")
    @PostMapping("taskSettings/add")
    @ResponseBody
    @RequiresPermissions("taskSettings:add")
    public FebsResponse addTaskSettings(@Valid TaskSettings taskSettings) {
        this.taskSettingsService.createTaskSettings(taskSettings);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TaskSettings", exceptionMessage = "删除TaskSettings失败")
    @GetMapping("taskSettings/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("taskSettings:delete")
    public FebsResponse deleteTaskSettings(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.taskSettingsService.deleteTaskSettings(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TaskSettings", exceptionMessage = "修改TaskSettings失败")
    @PostMapping("taskSettings/update")
    @ResponseBody
    @RequiresPermissions("taskSettings:update")
    public FebsResponse updateTaskSettings(TaskSettings taskSettings) {
        this.taskSettingsService.updateTaskSettings(taskSettings);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TaskSettings", exceptionMessage = "导出Excel失败")
    @PostMapping("taskSettings/excel")
    @ResponseBody
    @RequiresPermissions("taskSettings:export")
    public void export(QueryRequest queryRequest, TaskSettings taskSettings, HttpServletResponse response) {
        List<TaskSettings> taskSettingss = this.taskSettingsService.findTaskSettingss(queryRequest, taskSettings).getRecords();
        ExcelKit.$Export(TaskSettings.class, response).downXlsx(taskSettingss, false);
    }
}

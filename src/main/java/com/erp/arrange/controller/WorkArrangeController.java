package com.erp.arrange.controller;

import com.erp.arrange.entity.WorkArrange;
import com.erp.arrange.service.IWorkArrangeService;
import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 工作安排表 Controller
 *
 * @author qiufeng
 * @date 2021-12-20 15:35:45
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class WorkArrangeController extends BaseController {

    private final IWorkArrangeService workArrangeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "workArrange")
    public String workArrangeIndex(){
        return FebsUtil.view("workArrange/workArrange");
    }

    @GetMapping("workArrange")
    @ResponseBody
    @RequiresPermissions("workArrange:list")
    public FebsResponse getAllWorkArranges(WorkArrange workArrange) {
        return new FebsResponse().success().data(workArrangeService.findWorkArranges(workArrange));
    }

    @GetMapping("workArrange/list")
    @ResponseBody
    @RequiresPermissions("workArrange:view")
    public FebsResponse workArrangeList(QueryRequest request, WorkArrange workArrange) {
        Map<String, Object> dataTable = getDataTable(this.workArrangeService.findWorkArranges(request, workArrange));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增WorkArrange", exceptionMessage = "新增WorkArrange失败")
    @PostMapping("workArrange/add")
    @ResponseBody
    @RequiresPermissions("workArrange:add")
    public FebsResponse addWorkArrange(@Valid WorkArrange workArrange) throws ParseException {
        this.workArrangeService.createWorkArrange(workArrange);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除WorkArrange", exceptionMessage = "删除WorkArrange失败")
    @GetMapping("workArrange/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("workArrange:delete")
    public FebsResponse deleteWorkArrange(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.workArrangeService.deleteWorkArrange(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WorkArrange", exceptionMessage = "修改WorkArrange失败")
    @PostMapping("workArrange/update")
    @ResponseBody
    @RequiresPermissions("workArrange:update")
    public FebsResponse updateWorkArrange(WorkArrange workArrange) {
        this.workArrangeService.updateWorkArrange(workArrange);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WorkArrange", exceptionMessage = "导出Excel失败")
    @PostMapping("workArrange/excel")
    @ResponseBody
    @RequiresPermissions("workArrange:export")
    public void export(QueryRequest queryRequest, WorkArrange workArrange, HttpServletResponse response) {
        List<WorkArrange> workArranges = this.workArrangeService.findWorkArranges(queryRequest, workArrange).getRecords();
        ExcelKit.$Export(WorkArrange.class, response).downXlsx(workArranges, false);
    }

    @ControllerEndpoint(operation = "确认WorkArrange", exceptionMessage = "确认WorkArrange失败")
    @PostMapping("workArrange/confirm/{id}")
    @ResponseBody
    @RequiresPermissions("workArrange:confirm")
    public FebsResponse confirmWorkArrange(@PathVariable Long id) {
        this.workArrangeService.updateWorkArrangeState(id,WorkArrange.STATE_CONFIRM);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "取消WorkArrange", exceptionMessage = "取消WorkArrange失败")
    @PostMapping("workArrange/cancel/{id}")
    @ResponseBody
    @RequiresPermissions("workArrange:cancel")
    public FebsResponse cancelWorkArrange(@PathVariable Long id) {
        this.workArrangeService.updateWorkArrangeState(id,WorkArrange.STATE_REGISTER);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "开始WorkArrange", exceptionMessage = "开始WorkArrange失败")
    @PostMapping("workArrange/start/{id}")
    @ResponseBody
    @RequiresPermissions("workArrange:start")
    public FebsResponse startWorkArrange(@PathVariable Long id) throws ParseException {
        this.workArrangeService.updateWorkArrangeStateDate(id,WorkArrange.STATE_START);
        return new FebsResponse().success();

    }
    @ControllerEndpoint(operation = "完成WorkArrange", exceptionMessage = "完成WorkArrange失败")
    @PostMapping("workArrange/complete/{id}")
    @ResponseBody
    @RequiresPermissions("workArrange:complete")
    public FebsResponse completeWorkArrange(@PathVariable Long id) throws ParseException {
        this.workArrangeService.updateWorkArrangeStateDate(id,WorkArrange.STATE_COMPLETE);
        return new FebsResponse().success();

    }

    @ControllerEndpoint(operation = "考核WorkArrange", exceptionMessage = "考核WorkArrange失败")
    @PostMapping("workArrange/assessment")
    @ResponseBody
    @RequiresPermissions("workArrange:assessment")
    public FebsResponse workArrangeAssessment(WorkArrange workArrange) throws ParseException {
        this.workArrangeService.workArrangeAssessment(workArrange);
        return new FebsResponse().success();
    }
}

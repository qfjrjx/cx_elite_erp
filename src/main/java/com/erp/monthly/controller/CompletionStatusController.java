package com.erp.monthly.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.monthly.entity.CompletionStatus;
import com.erp.monthly.service.ICompletionStatusService;
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
 * 完成情况表 Controller
 *
 * @author qiufeng
 * @date 2021-12-17 14:06:34
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class CompletionStatusController extends BaseController {

    private final ICompletionStatusService completionStatusService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "completionStatus")
    public String completionStatusIndex(){
        return FebsUtil.view("completionStatus/completionStatus");
    }

    @GetMapping("completionStatus")
    @ResponseBody
    @RequiresPermissions("completionStatus:list")
    public FebsResponse getAllCompletionStatuss(CompletionStatus completionStatus) {
        return new FebsResponse().success().data(completionStatusService.findCompletionStatuss(completionStatus));
    }

    @GetMapping("completionStatus/list")
    @ResponseBody
    @RequiresPermissions("completionStatus:view")
    public FebsResponse completionStatusList(QueryRequest request, CompletionStatus completionStatus) {
        Map<String, Object> dataTable = getDataTable(this.completionStatusService.findCompletionStatuss(request, completionStatus));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增CompletionStatus", exceptionMessage = "新增CompletionStatus失败")
    @PostMapping("completionStatus")
    @ResponseBody
    @RequiresPermissions("completionStatus:add")
    public FebsResponse addCompletionStatus(@Valid CompletionStatus completionStatus) {
        this.completionStatusService.createCompletionStatus(completionStatus);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除CompletionStatus", exceptionMessage = "删除CompletionStatus失败")
    @GetMapping("completionStatus/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("completionStatus:delete")
    public FebsResponse deleteCompletionStatus(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.completionStatusService.deleteCompletionStatus(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改CompletionStatus", exceptionMessage = "修改CompletionStatus失败")
    @PostMapping("completionStatus/update")
    @ResponseBody
    @RequiresPermissions("completionStatus:update")
    public FebsResponse updateCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatusService.updateCompletionStatus(completionStatus);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改CompletionStatus", exceptionMessage = "导出Excel失败")
    @PostMapping("completionStatus/excel")
    @ResponseBody
    @RequiresPermissions("completionStatus:export")
    public void export(QueryRequest queryRequest, CompletionStatus completionStatus, HttpServletResponse response) {
        List<CompletionStatus> completionStatuss = this.completionStatusService.findCompletionStatuss(queryRequest, completionStatus).getRecords();
        ExcelKit.$Export(CompletionStatus.class, response).downXlsx(completionStatuss, false);
    }
}

package com.erp.monitor.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.monitor.entity.LoginLog;
import com.erp.monitor.service.ILoginLogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author qiufeng
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
@RequiredArgsConstructor
public class LoginLogController extends BaseController {

    private final ILoginLogService loginLogService;

    @GetMapping("list")
    @RequiresPermissions("loginlog:view")
    public FebsResponse loginLogList(LoginLog loginLog, QueryRequest request) {
        return new FebsResponse().success()
                .data(getDataTable(loginLogService.findLoginLogs(loginLog, request)));
    }

    @GetMapping("delete/{ids}")
    @RequiresPermissions("loginlog:delete")
    @ControllerEndpoint(exceptionMessage = "删除日志失败")
    public FebsResponse deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        loginLogService.deleteLoginLogs(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("loginlog:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, LoginLog loginLog, HttpServletResponse response) {
        ExcelKit.$Export(LoginLog.class, response)
                .downXlsx(loginLogService.findLoginLogs(loginLog, request).getRecords(), false);
    }
}

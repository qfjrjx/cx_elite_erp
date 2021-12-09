package com.erp.enterprise.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;
import com.erp.enterprise.service.IEnterprisePerformanceDailyService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 业绩日报表  Controller
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class EnterprisePerformanceDailyController extends BaseController {

    private final IEnterprisePerformanceDailyService enterprisePerformanceDailyService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "enterprisePerformanceDaily")
    public String enterprisePerformanceDailyIndex(){
        return FebsUtil.view("enterprisePerformanceDaily/enterprisePerformanceDaily");
    }

    @GetMapping("enterprisePerformanceDaily")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:list")
    public FebsResponse getAllEnterprisePerformanceDailys(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        return new FebsResponse().success().data(enterprisePerformanceDailyService.findEnterprisePerformanceDailys(enterprisePerformanceDaily));
    }

    @GetMapping("enterprisePerformanceDaily/list")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:list")
    public FebsResponse enterprisePerformanceDailyList(QueryRequest request, EnterprisePerformanceDaily enterprisePerformanceDaily) {
        Map<String, Object> dataTable = getDataTable(this.enterprisePerformanceDailyService.findEnterprisePerformanceDailys(request, enterprisePerformanceDaily));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增EnterprisePerformanceDaily", exceptionMessage = "新增EnterprisePerformanceDaily失败")
    @PostMapping("enterprisePerformanceDaily")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:add")
    public FebsResponse addEnterprisePerformanceDaily(@Valid EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.enterprisePerformanceDailyService.createEnterprisePerformanceDaily(enterprisePerformanceDaily);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除EnterprisePerformanceDaily", exceptionMessage = "删除EnterprisePerformanceDaily失败")
    @GetMapping("enterprisePerformanceDaily/delete")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:delete")
    public FebsResponse deleteEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.enterprisePerformanceDailyService.deleteEnterprisePerformanceDaily(enterprisePerformanceDaily);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterprisePerformanceDaily", exceptionMessage = "修改EnterprisePerformanceDaily失败")
    @PostMapping("enterprisePerformanceDaily/update")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:update")
    public FebsResponse updateEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.enterprisePerformanceDailyService.updateEnterprisePerformanceDaily(enterprisePerformanceDaily);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterprisePerformanceDaily", exceptionMessage = "导出Excel失败")
    @PostMapping("enterprisePerformanceDaily/excel")
    @ResponseBody
    @RequiresPermissions("enterprisePerformanceDaily:export")
    public void export(QueryRequest queryRequest, EnterprisePerformanceDaily enterprisePerformanceDaily, HttpServletResponse response) {
        List<EnterprisePerformanceDaily> enterprisePerformanceDailys = this.enterprisePerformanceDailyService.findEnterprisePerformanceDailys(queryRequest, enterprisePerformanceDaily).getRecords();
        ExcelKit.$Export(EnterprisePerformanceDaily.class, response).downXlsx(enterprisePerformanceDailys, false);
    }
}

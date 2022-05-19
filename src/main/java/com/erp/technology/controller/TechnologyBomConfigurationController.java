package com.erp.technology.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.technology.entity.TechnologyBomConfiguration;
import com.erp.technology.entity.TechnologyBomConfigurationSchedule;
import com.erp.technology.service.ITechnologyBomConfigurationService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BOM配置 Controller
 *
 * @author qiufeng
 * @date 2022-05-09 09:43:35
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class TechnologyBomConfigurationController extends BaseController {

    private final ITechnologyBomConfigurationService technologyBomConfigurationService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "technologyBomConfiguration")
    public String technologyBomConfigurationIndex(){
        return FebsUtil.view("technologyBomConfiguration/technologyBomConfiguration");
    }

    @GetMapping("technologyBomConfiguration")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:list")
    public FebsResponse getAllTechnologyBomConfigurations(TechnologyBomConfiguration technologyBomConfiguration) {
        return new FebsResponse().success().data(technologyBomConfigurationService.findTechnologyBomConfigurations(technologyBomConfiguration));
    }

    @GetMapping("technologyBomConfiguration/list")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:view")
    public FebsResponse technologyBomConfigurationList(QueryRequest request, TechnologyBomConfiguration technologyBomConfiguration) {
        Map<String, Object> dataTable = getDataTable(this.technologyBomConfigurationService.findTechnologyBomConfigurations(request, technologyBomConfiguration));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增TechnologyBomConfiguration", exceptionMessage = "新增TechnologyBomConfiguration失败")
    @PostMapping("technologyBomConfiguration/add")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:add")
    public FebsResponse addTechnologyBomConfiguration(@Valid String technologyBomConfiguration , @RequestBody String dataTable) throws ParseException {
        this.technologyBomConfigurationService.createTechnologyBomConfiguration(technologyBomConfiguration,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TechnologyBomConfiguration", exceptionMessage = "删除TechnologyBomConfiguration失败")
    @GetMapping("technologyBomConfiguration/delete/{parameterCode}")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:delete")
    public FebsResponse deleteTechnologyBomConfiguration(@PathVariable String parameterCode) {
        this.technologyBomConfigurationService.deleteTechnologyBomConfiguration(parameterCode);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyBomConfiguration", exceptionMessage = "修改TechnologyBomConfiguration失败")
    @PostMapping("technologyBomConfiguration/update")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:update")
    public FebsResponse updateTechnologyBomConfiguration(@Valid String technologyBomConfiguration , @RequestBody String dataTable) throws ParseException {
        this.technologyBomConfigurationService.updateTechnologyBomConfiguration(technologyBomConfiguration,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyBomConfiguration", exceptionMessage = "导出Excel失败")
    @PostMapping("technologyBomConfiguration/excel")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:export")
    public void export(QueryRequest queryRequest, TechnologyBomConfiguration technologyBomConfiguration, HttpServletResponse response) {
        List<TechnologyBomConfiguration> technologyBomConfigurations = this.technologyBomConfigurationService.findTechnologyBomConfigurations(queryRequest, technologyBomConfiguration).getRecords();
        ExcelKit.$Export(TechnologyBomConfiguration.class, response).downXlsx(technologyBomConfigurations, false);
    }

    @GetMapping("configurationRefer/query")
    @ResponseBody
    public Map queryConfigurationRefer(@RequestParam String parameterCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<TechnologyBomConfigurationSchedule> technologyBomConfigurationSchedules = this.technologyBomConfigurationService.queryConfigurationRefer(parameterCode);
            map.put("replies",technologyBomConfigurationSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ControllerEndpoint(operation = "确认", exceptionMessage = "确认失败")
    @GetMapping("technologyBomConfiguration/confirm/{ids}")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:confirm")
    public FebsResponse confirmTechnologyBomConfiguration(@PathVariable String ids) {
        this.technologyBomConfigurationService.confirmTechnologyBomConfiguration(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "取消", exceptionMessage = "取消失败")
    @GetMapping("technologyBomConfiguration/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("technologyBomConfiguration:cancel")
    public FebsResponse cancelTechnologyBomConfiguration(@PathVariable String ids) {
        this.technologyBomConfigurationService.cancelTechnologyBomConfiguration(ids);
        return new FebsResponse().success();
    }
}

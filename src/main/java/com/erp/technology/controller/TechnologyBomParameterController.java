package com.erp.technology.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.technology.entity.TechnologyBomParameter;
import com.erp.technology.service.ITechnologyBomParameterService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * BOM参数 Controller
 *
 * @author qiufeng
 * @date 2022-05-05 11:30:22
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class TechnologyBomParameterController extends BaseController {

    private final ITechnologyBomParameterService technologyBomParameterService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "technologyBomParameter")
    public String technologyBomParameterIndex(){
        return FebsUtil.view("technologyBomParameter/technologyBomParameter");
    }

    @GetMapping("technologyBomParameter")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:list")
    public FebsResponse getAllTechnologyBomParameters(TechnologyBomParameter technologyBomParameter) {
        return new FebsResponse().success().data(technologyBomParameterService.findTechnologyBomParameters(technologyBomParameter));
    }

    @GetMapping("technologyBomParameter/list")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:view")
    public FebsResponse technologyBomParameterList(QueryRequest request, TechnologyBomParameter technologyBomParameter) {
        Map<String, Object> dataTable = getDataTable(this.technologyBomParameterService.findTechnologyBomParameters(request, technologyBomParameter));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增TechnologyBomParameter", exceptionMessage = "新增TechnologyBomParameter失败")
    @PostMapping("technologyBomParameter/add")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:add")
    public FebsResponse addTechnologyBomParameter(@Valid TechnologyBomParameter technologyBomParameter) {
        this.technologyBomParameterService.createTechnologyBomParameter(technologyBomParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TechnologyBomParameter", exceptionMessage = "删除TechnologyBomParameter失败")
    @GetMapping("technologyBomParameter/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:delete")
    public FebsResponse deleteTechnologyBomParameter(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.technologyBomParameterService.deleteTechnologyBomParameter(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyBomParameter", exceptionMessage = "修改TechnologyBomParameter失败")
    @PostMapping("technologyBomParameter/update")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:update")
    public FebsResponse updateTechnologyBomParameter(TechnologyBomParameter technologyBomParameter) {
        this.technologyBomParameterService.updateTechnologyBomParameter(technologyBomParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyBomParameter", exceptionMessage = "导出Excel失败")
    @PostMapping("technologyBomParameter/excel")
    @ResponseBody
    @RequiresPermissions("technologyBomParameter:export")
    public void export(QueryRequest queryRequest, TechnologyBomParameter technologyBomParameter, HttpServletResponse response) {
        List<TechnologyBomParameter> technologyBomParameters = this.technologyBomParameterService.findTechnologyBomParameters(queryRequest, technologyBomParameter).getRecords();
        ExcelKit.$Export(TechnologyBomParameter.class, response).downXlsx(technologyBomParameters, false);
    }
}

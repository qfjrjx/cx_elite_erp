package com.erp.quality.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.quality.entity.QualityParameter;
import com.erp.quality.service.IQualityParameterService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 品质参数 Controller
 *
 * @author qiufeng
 * @date 2022-06-02 10:08:22
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class QualityParameterController extends BaseController {

    private final IQualityParameterService qualityParameterService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "qualityParameter")
    public String qualityParameterIndex(){
        return FebsUtil.view("qualityParameter/qualityParameter");
    }

    @GetMapping("qualityParameter")
    @ResponseBody
    @RequiresPermissions("qualityParameter:list")
    public FebsResponse getAllQualityParameters(QualityParameter qualityParameter) {
        return new FebsResponse().success().data(qualityParameterService.findQualityParameters(qualityParameter));
    }

    @GetMapping("qualityParameter/list")
    @ResponseBody
    @RequiresPermissions("qualityParameter:view")
    public FebsResponse qualityParameterList(QueryRequest request, QualityParameter qualityParameter) {
        Map<String, Object> dataTable = getDataTable(this.qualityParameterService.findQualityParameters(request, qualityParameter));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增QualityParameter", exceptionMessage = "新增QualityParameter失败")
    @PostMapping("qualityParameter/add")
    @ResponseBody
    @RequiresPermissions("qualityParameter:add")
    public FebsResponse addQualityParameter(@Valid QualityParameter qualityParameter) {
        this.qualityParameterService.createQualityParameter(qualityParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除QualityParameter", exceptionMessage = "删除QualityParameter失败")
    @GetMapping("qualityParameter/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("qualityParameter:delete")
    public FebsResponse deleteQualityParameter(@PathVariable String ids) {
        this.qualityParameterService.deleteQualityParameter(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改QualityParameter", exceptionMessage = "修改QualityParameter失败")
    @PostMapping("qualityParameter/update")
    @ResponseBody
    @RequiresPermissions("qualityParameter:update")
    public FebsResponse updateQualityParameter(QualityParameter qualityParameter) {
        this.qualityParameterService.updateQualityParameter(qualityParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改QualityParameter", exceptionMessage = "导出Excel失败")
    @PostMapping("qualityParameter/excel")
    @ResponseBody
    @RequiresPermissions("qualityParameter:export")
    public void export(QueryRequest queryRequest, QualityParameter qualityParameter, HttpServletResponse response) {
        List<QualityParameter> qualityParameters = this.qualityParameterService.findQualityParameters(queryRequest, qualityParameter).getRecords();
        ExcelKit.$Export(QualityParameter.class, response).downXlsx(qualityParameters, false);
    }
}

package com.erp.warehouse.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.warehouse.entity.WarehouseParameter;
import com.erp.warehouse.service.IWarehouseParameterService;
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
 * 仓库参数 Controller
 *
 * @author qiufeng
 * @date 2022-06-01 15:53:35
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class WarehouseParameterController extends BaseController {

    private final IWarehouseParameterService warehouseParameterService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "warehouseParameter")
    public String warehouseParameterIndex(){
        return FebsUtil.view("warehouseParameter/warehouseParameter");
    }

    @GetMapping("warehouseParameter")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:list")
    public FebsResponse getAllWarehouseParameters(WarehouseParameter warehouseParameter) {
        return new FebsResponse().success().data(warehouseParameterService.findWarehouseParameters(warehouseParameter));
    }

    @GetMapping("warehouseParameter/list")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:view")
    public FebsResponse warehouseParameterList(QueryRequest request, WarehouseParameter warehouseParameter) {
        Map<String, Object> dataTable = getDataTable(this.warehouseParameterService.findWarehouseParameters(request, warehouseParameter));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增WarehouseParameter", exceptionMessage = "新增WarehouseParameter失败")
    @PostMapping("warehouseParameter/add")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:add")
    public FebsResponse addWarehouseParameter(@Valid WarehouseParameter warehouseParameter) {
        this.warehouseParameterService.createWarehouseParameter(warehouseParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除WarehouseParameter", exceptionMessage = "删除WarehouseParameter失败")
    @GetMapping("warehouseParameter/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:delete")
    public FebsResponse deleteWarehouseParameter(@PathVariable String ids) {
        this.warehouseParameterService.deleteWarehouseParameter(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseParameter", exceptionMessage = "修改WarehouseParameter失败")
    @PostMapping("warehouseParameter/update")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:update")
    public FebsResponse updateWarehouseParameter(WarehouseParameter warehouseParameter) {
        this.warehouseParameterService.updateWarehouseParameter(warehouseParameter);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseParameter", exceptionMessage = "导出Excel失败")
    @PostMapping("warehouseParameter/excel")
    @ResponseBody
    @RequiresPermissions("warehouseParameter:export")
    public void export(QueryRequest queryRequest, WarehouseParameter warehouseParameter, HttpServletResponse response) {
        List<WarehouseParameter> warehouseParameters = this.warehouseParameterService.findWarehouseParameters(queryRequest, warehouseParameter).getRecords();
        ExcelKit.$Export(WarehouseParameter.class, response).downXlsx(warehouseParameters, false);
    }
}

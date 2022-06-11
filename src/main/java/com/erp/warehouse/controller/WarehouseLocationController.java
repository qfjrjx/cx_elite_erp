package com.erp.warehouse.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.warehouse.entity.WarehouseLocation;
import com.erp.warehouse.service.IWarehouseLocationService;
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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 库房区位 Controller
 *
 * @author qiufeng
 * @date 2022-06-01 10:40:04
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class WarehouseLocationController extends BaseController {

    private final IWarehouseLocationService warehouseLocationService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "warehouseLocation")
    public String warehouseLocationIndex(){
        return FebsUtil.view("warehouseLocation/warehouseLocation");
    }

    @GetMapping("warehouseLocation")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:list")
    public FebsResponse getAllWarehouseLocations(WarehouseLocation warehouseLocation) {
        return new FebsResponse().success().data(warehouseLocationService.findWarehouseLocations(warehouseLocation));
    }

    @GetMapping("warehouseLocation/list")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:view")
    public FebsResponse warehouseLocationList(QueryRequest request, WarehouseLocation warehouseLocation) {
        Map<String, Object> dataTable = getDataTable(this.warehouseLocationService.findWarehouseLocations(request, warehouseLocation));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增WarehouseLocation", exceptionMessage = "新增WarehouseLocation失败")
    @PostMapping("warehouseLocation/add")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:add")
    public FebsResponse addWarehouseLocation(@Valid WarehouseLocation warehouseLocation) throws ParseException {
        this.warehouseLocationService.createWarehouseLocation(warehouseLocation);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除WarehouseLocation", exceptionMessage = "删除WarehouseLocation失败")
    @GetMapping("warehouseLocation/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:delete")
    public FebsResponse deleteWarehouseLocation(@PathVariable String ids) {
        this.warehouseLocationService.deleteWarehouseLocation(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseLocation", exceptionMessage = "修改WarehouseLocation失败")
    @PostMapping("warehouseLocation/update")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:update")
    public FebsResponse updateWarehouseLocation(WarehouseLocation warehouseLocation) throws ParseException {
        this.warehouseLocationService.updateWarehouseLocation(warehouseLocation);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseLocation", exceptionMessage = "导出Excel失败")
    @PostMapping("warehouseLocation/excel")
    @ResponseBody
    @RequiresPermissions("warehouseLocation:export")
    public void export(QueryRequest queryRequest, WarehouseLocation warehouseLocation, HttpServletResponse response) {
        List<WarehouseLocation> warehouseLocations = this.warehouseLocationService.findWarehouseLocations(queryRequest, warehouseLocation).getRecords();
        ExcelKit.$Export(WarehouseLocation.class, response).downXlsx(warehouseLocations, false);
    }
}

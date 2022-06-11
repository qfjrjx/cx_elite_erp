package com.erp.warehouse.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.warehouse.entity.WarehouseStorage;
import com.erp.warehouse.service.IWarehouseStorageService;
import com.wuwenze.poi.ExcelKit;
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
 * 采购入库 Controller
 *
 * @author qiufeng
 * @date 2022-06-03 11:33:18
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class WarehouseStorageController extends BaseController {

    private final IWarehouseStorageService warehouseStorageService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "warehouseStorage")
    public String warehouseStorageIndex(){
        return FebsUtil.view("warehouseStorage/warehouseStorage");
    }

    @GetMapping("warehouseStorage")
    @ResponseBody
    @RequiresPermissions("warehouseStorage:list")
    public FebsResponse getAllWarehouseStorages(WarehouseStorage warehouseStorage) {
        return new FebsResponse().success().data(warehouseStorageService.findWarehouseStorages(warehouseStorage));
    }

    @GetMapping("warehouseStorage/list")
    @ResponseBody
    @RequiresPermissions("procurementStorage:view")
    public FebsResponse warehouseStorageList(QueryRequest request, WarehouseStorage warehouseStorage) {
        Map<String, Object> dataTable = getDataTable(this.warehouseStorageService.findWarehouseStorages(request, warehouseStorage));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增WarehouseStorage", exceptionMessage = "新增WarehouseStorage失败")
    @PostMapping("warehouseStorage")
    @ResponseBody
    @RequiresPermissions("procurementStorage:add")
    public FebsResponse addWarehouseStorage(@Valid WarehouseStorage warehouseStorage) {
        this.warehouseStorageService.createWarehouseStorage(warehouseStorage);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除WarehouseStorage", exceptionMessage = "删除WarehouseStorage失败")
    @GetMapping("warehouseStorage/delete")
    @ResponseBody
    @RequiresPermissions("procurementStorage:delete")
    public FebsResponse deleteWarehouseStorage(WarehouseStorage warehouseStorage) {
        this.warehouseStorageService.deleteWarehouseStorage(warehouseStorage);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseStorage", exceptionMessage = "修改WarehouseStorage失败")
    @PostMapping("warehouseStorage/update")
    @ResponseBody
    @RequiresPermissions("procurementStorage:update")
    public FebsResponse updateWarehouseStorage(WarehouseStorage warehouseStorage) {
        this.warehouseStorageService.updateWarehouseStorage(warehouseStorage);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改WarehouseStorage", exceptionMessage = "导出Excel失败")
    @PostMapping("warehouseStorage/excel")
    @ResponseBody
    @RequiresPermissions("procurementStorage:export")
    public void export(QueryRequest queryRequest, WarehouseStorage warehouseStorage, HttpServletResponse response) {
        List<WarehouseStorage> warehouseStorages = this.warehouseStorageService.findWarehouseStorages(queryRequest, warehouseStorage).getRecords();
        ExcelKit.$Export(WarehouseStorage.class, response).downXlsx(warehouseStorages, false);
    }

    @GetMapping("warehouseStorage/query")
    @ResponseBody
    public Map queryWarehouseStorage(@RequestParam String storageCoding) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<WarehouseStorage> warehouseStorages = this.warehouseStorageService.queryWarehouseStorage(storageCoding);
            map.put("replies",warehouseStorages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /*采购入库*/
    @ControllerEndpoint(operation = "采购入库", exceptionMessage = "采购入库失败")
    @GetMapping("warehouseStorage/storage/{ids}")
    @ResponseBody
    @RequiresPermissions("procurementStorage:storage")
    public FebsResponse warehouseStorageStorage(@PathVariable String ids) throws ParseException {
        this.warehouseStorageService.warehouseStorageStorage(ids);
        return new FebsResponse().success();
    }

    /*取消*/
    @ControllerEndpoint(operation = "取消入库", exceptionMessage = "取消入库失败")
    @GetMapping("warehouseStorage/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("procurementStorage:cancel")
    public FebsResponse cancelWarehouseStorageStorage(@PathVariable String ids) throws ParseException {
        this.warehouseStorageService.cancelWarehouseStorageStorage(ids);
        return new FebsResponse().success();
    }
}

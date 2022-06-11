package com.erp.warehouse.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.warehouse.entity.WarehouseLocation;
import com.erp.warehouse.entity.WarehouseParameter;
import com.erp.warehouse.service.IWarehouseLocationService;
import com.erp.warehouse.service.IWarehouseParameterService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

/**
 * @author wangchuang
 */
@Controller("warehouseView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "warehouse")
@RequiredArgsConstructor
public class ViewController {

    //库房区位service接口
    private final IWarehouseLocationService warehouseLocationService;
    //仓库参数service接口
    private final IWarehouseParameterService warehouseParameterService;

    /* 库房区位模块开始 */

    //库房区位查询
    @GetMapping("warehouseLocation/list")
    @RequiresPermissions("warehouseLocation:view")
    public String warehouseLocationIndex(){
        return FebsUtil.view("warehouseLocation/warehouseLocationList");
    }

    //库房区位新增
    @GetMapping("warehouseLocation/add")
    @RequiresPermissions("warehouseLocation:add")
    public String warehouseLocationAdd(Model model){
        //查询库房类型
        List<WarehouseParameter> parameterValue  = warehouseParameterService.queryParameterValue();
        model.addAttribute("parameterValue",parameterValue);
        return FebsUtil.view("warehouseLocation/warehouseLocationAdd");
    }

    //库房区位修改
    @GetMapping("warehouseLocation/update/{id}")
    @RequiresPermissions("warehouseLocation:update")
    public String warehouseLocationUpdate(@PathVariable Long id, Model model) throws ParseException {
        warehouseLocationModel(id, model, false);
        return FebsUtil.view("warehouseLocation/warehouseLocationUpdate");
    }

    private void warehouseLocationModel(Long id, Model model, Boolean transform) throws ParseException {
        WarehouseLocation warehouseLocation = warehouseLocationService.warehouseLocationId(id);
        model.addAttribute("warehouseLocation", warehouseLocation);
    }

    /* 库房区位模块结束 */

    /* 仓库参数模块开始 */

    //仓库参数查询
    @GetMapping("warehouseParameter/list")
    @RequiresPermissions("warehouseParameter:view")
    public String warehouseParameterIndex(){
        return FebsUtil.view("warehouseParameter/warehouseParameterList");
    }

    //仓库参数新增
    @GetMapping("warehouseParameter/add")
    @RequiresPermissions("warehouseParameter:add")
    public String warehouseParameterAdd(){
        return FebsUtil.view("warehouseParameter/warehouseParameterAdd");
    }

    //仓库参数修改
    @GetMapping("warehouseParameter/update/{id}")
    @RequiresPermissions("warehouseParameter:update")
    public String warehouseParameterUpdate(@PathVariable Long id, Model model) throws ParseException {
        warehouseParameterModel(id, model, false);
        return FebsUtil.view("warehouseParameter/warehouseParameterUpdate");
    }

    private void warehouseParameterModel(Long id, Model model, Boolean transform) throws ParseException {
        WarehouseParameter warehouseParameter = warehouseParameterService.warehouseParameterId(id);
        model.addAttribute("warehouseParameter", warehouseParameter);
    }

    /* 仓库参数模块结束 */

    /* 采购入库模块开始 */

    //采购入库查询
    @GetMapping("procurementStorage/list")
    @RequiresPermissions("procurementStorage:view")
    public String procurementStorageIndex(Model model){
        //查询库房信息
        List<WarehouseLocation> location  = warehouseLocationService.queryLocationName();
        model.addAttribute("location",location);
        return FebsUtil.view("procurementStorage/procurementStorageList");
    }

    /* 采购入库模块结束 */
}

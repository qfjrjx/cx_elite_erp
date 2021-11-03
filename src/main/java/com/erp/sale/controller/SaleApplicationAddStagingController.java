package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleApplicationAddStaging;
import com.erp.sale.service.ISaleApplicationAddStagingService;
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
 * 销售申请暂存表 Controller
 *
 * @author qiufeng
 * @date 2021-10-20 17:12:25
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleApplicationAddStagingController extends BaseController {

    private final ISaleApplicationAddStagingService saleApplicationAddStagingService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleApplicationAddStaging")
    public String saleApplicationAddStagingIndex(){
        return FebsUtil.view("saleApplicationAddStaging/saleApplicationAddStaging");
    }

    @GetMapping("saleApplicationAddStaging")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:list")
    public FebsResponse getAllSaleApplicationAddStagings(SaleApplicationAddStaging saleApplicationAddStaging) {
        return new FebsResponse().success().data(saleApplicationAddStagingService.findSaleApplicationAddStagings(saleApplicationAddStaging));
    }

    @GetMapping("saleApplicationAddStaging/list")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:list")
    public FebsResponse saleApplicationAddStagingList(QueryRequest request, SaleApplicationAddStaging saleApplicationAddStaging) {
        Map<String, Object> dataTable = getDataTable(this.saleApplicationAddStagingService.findSaleApplicationAddStagings(request, saleApplicationAddStaging));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleApplicationAddStaging", exceptionMessage = "新增SaleApplicationAddStaging失败")
    @PostMapping("saleApplicationAddStaging")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:add")
    public FebsResponse addSaleApplicationAddStaging(@Valid SaleApplicationAddStaging saleApplicationAddStaging) {
        this.saleApplicationAddStagingService.createSaleApplicationAddStaging(saleApplicationAddStaging);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleApplicationAddStaging", exceptionMessage = "删除SaleApplicationAddStaging失败")
    @GetMapping("saleApplicationAddStaging/delete")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:delete")
    public FebsResponse deleteSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging) {
        this.saleApplicationAddStagingService.deleteSaleApplicationAddStaging(saleApplicationAddStaging);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleApplicationAddStaging", exceptionMessage = "修改SaleApplicationAddStaging失败")
    @PostMapping("saleApplicationAddStaging/update")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:update")
    public FebsResponse updateSaleApplicationAddStaging(SaleApplicationAddStaging saleApplicationAddStaging) {
        this.saleApplicationAddStagingService.updateSaleApplicationAddStaging(saleApplicationAddStaging);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleApplicationAddStaging", exceptionMessage = "导出Excel失败")
    @PostMapping("saleApplicationAddStaging/excel")
    @ResponseBody
    @RequiresPermissions("saleApplicationAddStaging:export")
    public void export(QueryRequest queryRequest, SaleApplicationAddStaging saleApplicationAddStaging, HttpServletResponse response) {
        List<SaleApplicationAddStaging> saleApplicationAddStagings = this.saleApplicationAddStagingService.findSaleApplicationAddStagings(queryRequest, saleApplicationAddStaging).getRecords();
        ExcelKit.$Export(SaleApplicationAddStaging.class, response).downXlsx(saleApplicationAddStagings, false);
    }
}

package com.erp.sale.controller;
import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleApplicationAll;
import com.erp.sale.service.ISaleApplicationService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售申请表 Controller
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleApplicationController extends BaseController {

    private final ISaleApplicationService saleApplicationService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleApplication")
    public String saleApplicationIndex(){
        return FebsUtil.view("saleApplication/saleApplication");
    }

    @GetMapping("saleApplication")
    @ResponseBody
    @RequiresPermissions("saleApplication:list")
    public FebsResponse getAllSaleApplications(SaleApplicationAll saleApplicationAll) {
        return new FebsResponse().success().data(saleApplicationService.findSaleApplications(saleApplicationAll));
    }
    //列表查询
    @GetMapping("saleApplication/list")
    @ResponseBody
    @RequiresPermissions("saleApplication:view")
    public FebsResponse saleApplicationList(QueryRequest request, SaleApplicationAll saleApplicationAll) {
        Map<String, Object> dataTable = getDataTable(this.saleApplicationService.findSaleApplications(request, saleApplicationAll));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleApplication", exceptionMessage = "新增SaleApplication失败")
    @PostMapping("saleApplication/add")
    @ResponseBody
    public FebsResponse addSaleApplication (@RequestParam String saleApplication,@RequestParam String dataTable,@RequestParam String contImg) throws Exception {
        this.saleApplicationService.addSaleApplication(saleApplication,dataTable,contImg);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleApplication", exceptionMessage = "删除SaleApplication失败")
    @GetMapping("saleApplication/delete")
    @ResponseBody
    @RequiresPermissions("saleApplication:delete")
    public FebsResponse deleteSaleApplication(SaleApplicationAll saleApplicationAll) {
        this.saleApplicationService.deleteSaleApplication(saleApplicationAll);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleApplication", exceptionMessage = "修改SaleApplication失败")
    @PostMapping("saleApplication/update")
    @ResponseBody
    @RequiresPermissions("saleApplication:update")
    public FebsResponse updateSaleApplication(@RequestParam String saleApplicationData,@RequestParam String dataTable,@RequestParam String contImg) throws ParseException {
        this.saleApplicationService.updateSaleApplication(saleApplicationData,dataTable,contImg);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleApplication", exceptionMessage = "导出Excel失败")
    @PostMapping("saleApplication/excel")
    @ResponseBody
    @RequiresPermissions("saleApplication:export")
    public void export(QueryRequest queryRequest, SaleApplicationAll saleApplicationAll, HttpServletResponse response) {
        List<SaleApplicationAll> saleApplicationAlls = this.saleApplicationService.findSaleApplications(queryRequest, saleApplicationAll).getRecords();
        ExcelKit.$Export(SaleApplicationAll.class, response).downXlsx(saleApplicationAlls, false);
    }

    @GetMapping("saleApplications/list")
    @ResponseBody
    @RequiresPermissions("saleApplication:view")
    public Map saleApplicationsList(@RequestParam String applicationNoTwo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SaleApplicationAll> saleApplicationAlls = this.saleApplicationService.saleApplicationsList(applicationNoTwo);
            map.put("replies", saleApplicationAlls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ControllerEndpoint(operation = "设计回复SaleApplication", exceptionMessage = "设计回复SaleApplication失败")
    @PostMapping("saleApplication/designReply")
    @ResponseBody
    public FebsResponse designReplySaleApplication (@RequestParam String designReplyParam,@RequestParam String userName) throws ParseException {
        this.saleApplicationService.designReplySaleApplication(designReplyParam,userName);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "采购回复SaleApplication", exceptionMessage = "采购回复SaleApplication失败")
    @PostMapping("saleApplication/purchaseReply")
    @ResponseBody
    public FebsResponse saleApplicationPurchaseReply (@RequestParam String purchaseReplyParam,@RequestParam String userName) throws ParseException {
        this.saleApplicationService.saleApplicationPurchaseReply(purchaseReplyParam,userName);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "生产回复SaleApplication", exceptionMessage = "生产回复SaleApplication失败")
    @PostMapping("saleApplication/productionReply")
    @ResponseBody
    public FebsResponse saleApplicationProductionReply (@RequestParam String productionReplyParam,@RequestParam String userName) throws ParseException {
        this.saleApplicationService.saleApplicationProductionReply(productionReplyParam,userName);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "装配回复SaleApplication", exceptionMessage = "装配回复SaleApplication失败")
    @PostMapping("saleApplication/assemblingReply")
    @ResponseBody
    public FebsResponse saleApplicationAssemblingReply (@RequestParam String assemblingReplyParam,@RequestParam String userName) throws ParseException {
        this.saleApplicationService.saleApplicationAssemblingReply(assemblingReplyParam,userName);
        return new FebsResponse().success();
    }

}

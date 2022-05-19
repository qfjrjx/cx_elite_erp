package com.erp.production.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.service.IProductionRecipientsService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 生产领用 Controller
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class ProductionRecipientsController extends BaseController {

    private final IProductionRecipientsService productionRecipientsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "productionRecipients")
    public String productionRecipientsIndex(){
        return FebsUtil.view("productionRecipients/productionRecipients");
    }

    @GetMapping("productionRecipients")
    @ResponseBody
    @RequiresPermissions("productionRecipients:list")
    public FebsResponse getAllProductionRecipientss(ProductionRecipients productionRecipients) {
        return new FebsResponse().success().data(productionRecipientsService.findProductionRecipientss(productionRecipients));
    }

    @GetMapping("productionRecipients/list")
    @ResponseBody
    @RequiresPermissions("productionRecipients:list")
    public FebsResponse productionRecipientsList(QueryRequest request, ProductionRecipients productionRecipients) {
        Map<String, Object> dataTable = getDataTable(this.productionRecipientsService.findProductionRecipientss(request, productionRecipients));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ProductionRecipients", exceptionMessage = "新增ProductionRecipients失败")
    @PostMapping("productionRecipients")
    @ResponseBody
    @RequiresPermissions("productionRecipients:add")
    public FebsResponse addProductionRecipients(@Valid ProductionRecipients productionRecipients) {
        this.productionRecipientsService.createProductionRecipients(productionRecipients);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ProductionRecipients", exceptionMessage = "删除ProductionRecipients失败")
    @GetMapping("productionRecipients/delete")
    @ResponseBody
    @RequiresPermissions("productionRecipients:delete")
    public FebsResponse deleteProductionRecipients(ProductionRecipients productionRecipients) {
        this.productionRecipientsService.deleteProductionRecipients(productionRecipients);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ProductionRecipients", exceptionMessage = "修改ProductionRecipients失败")
    @PostMapping("productionRecipients/update")
    @ResponseBody
    @RequiresPermissions("productionRecipients:update")
    public FebsResponse updateProductionRecipients(ProductionRecipients productionRecipients) {
        this.productionRecipientsService.updateProductionRecipients(productionRecipients);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ProductionRecipients", exceptionMessage = "导出Excel失败")
    @PostMapping("productionRecipients/excel")
    @ResponseBody
    @RequiresPermissions("productionRecipients:export")
    public void export(QueryRequest queryRequest, ProductionRecipients productionRecipients, HttpServletResponse response) {
        List<ProductionRecipients> productionRecipientss = this.productionRecipientsService.findProductionRecipientss(queryRequest, productionRecipients).getRecords();
        ExcelKit.$Export(ProductionRecipients.class, response).downXlsx(productionRecipientss, false);
    }
}

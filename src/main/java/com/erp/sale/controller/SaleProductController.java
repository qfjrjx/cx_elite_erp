package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleProduct;
import com.erp.sale.entity.SaleProducts;
import com.erp.sale.service.ISaleProductService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 产品表
             Controller
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleProductController extends BaseController {

    private final ISaleProductService saleProductService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleProduct")
    public String saleProductIndex(){
        return FebsUtil.view("saleProduct/saleProduct");
    }

    @GetMapping("saleProduct")
    @ResponseBody
    @RequiresPermissions("saleProduct:list")
    public FebsResponse getAllSaleProducts(SaleProduct saleProduct) {
        return new FebsResponse().success().data(saleProductService.findSaleProducts(saleProduct));
    }

    @GetMapping("saleProduct/list")
    @ResponseBody
    public FebsResponse saleProductList(QueryRequest request, SaleProduct saleProduct) {
        Map<String, Object> dataTable = getDataTable(this.saleProductService.findSaleProducts(request, saleProduct));
        return new FebsResponse().success().data(dataTable);
    }
    @GetMapping("saleProducts/list")
    @ResponseBody
    public FebsResponse saleProductsList(QueryRequest request, SaleProducts saleProducts) {
        Map<String, Object> dataTable = getDataTable(this.saleProductService.findSaleProduct(request, saleProducts));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleProduct", exceptionMessage = "新增SaleProduct失败")
    @PostMapping("saleProduct")
    @ResponseBody
    @RequiresPermissions("saleProduct:add")
    public FebsResponse addSaleProduct(@Valid SaleProduct saleProduct) {
        this.saleProductService.createSaleProduct(saleProduct);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleProduct", exceptionMessage = "删除SaleProduct失败")
    @GetMapping("saleProduct/delete")
    @ResponseBody
    @RequiresPermissions("saleProduct:delete")
    public FebsResponse deleteSaleProduct(SaleProduct saleProduct) {
        this.saleProductService.deleteSaleProduct(saleProduct);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleProduct", exceptionMessage = "修改SaleProduct失败")
    @PostMapping("saleProduct/update")
    @ResponseBody
    @RequiresPermissions("saleProduct:update")
    public FebsResponse updateSaleProduct(SaleProduct saleProduct) {
        this.saleProductService.updateSaleProduct(saleProduct);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleProduct", exceptionMessage = "修改SaleProduct失败")
    @PostMapping("saleProduct/updateState/{id}")
    @ResponseBody
    public FebsResponse updateSaleProductState(@PathVariable Long id) {
        this.saleProductService.updateSaleProductState(id);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "修改SaleProduct", exceptionMessage = "修改SaleProduct失败")
    @PostMapping("saleProduct/updateStates/{id}")
    @ResponseBody
    public FebsResponse updateSaleProductStates(@PathVariable Long id) {
        this.saleProductService.updateSaleProductStates(id);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleProduct", exceptionMessage = "导出Excel失败")
    @PostMapping("saleProduct/excel")
    @ResponseBody
    @RequiresPermissions("saleProduct:export")
    public void export(QueryRequest queryRequest, SaleProduct saleProduct, HttpServletResponse response) {
        List<SaleProduct> saleProducts = this.saleProductService.findSaleProducts(queryRequest, saleProduct).getRecords();
        ExcelKit.$Export(SaleProduct.class, response).downXlsx(saleProducts, false);
    }
}

package com.erp.technology.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.technology.entity.TechnologyProduct;
import com.erp.technology.service.ITechnologyProductService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 产品表
             Controller
 *
 * @author qiufeng
 * @date 2021-11-09 10:30:07
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class TechnologyProductController extends BaseController {

    private final ITechnologyProductService technologyProductService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "technologyProduct")
    public String technologyProductIndex(){
        return FebsUtil.view("technologyProduct/technologyProduct");
    }

    @GetMapping("technologyProduct")
    @ResponseBody
    @RequiresPermissions("technologyProduct:view")
    public FebsResponse getAllTechnologyProducts(TechnologyProduct technologyProduct) {
        return new FebsResponse().success().data(technologyProductService.findTechnologyProducts(technologyProduct));
    }

    @GetMapping("technologyProduct/list")
    @ResponseBody
    @RequiresPermissions("technologyProduct:view")
    public FebsResponse technologyProductList(QueryRequest request, TechnologyProduct technologyProduct) {
        Map<String, Object> dataTable = getDataTable(this.technologyProductService.findTechnologyProducts(request, technologyProduct));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增TechnologyProduct", exceptionMessage = "新增TechnologyProduct失败")
    @PostMapping("technologyProduct/add")
    @ResponseBody
    @RequiresPermissions("technologyProduct:add")
    public FebsResponse addTechnologyProduct(@Valid TechnologyProduct technologyProduct) throws ParseException {
        this.technologyProductService.createTechnologyProduct(technologyProduct);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TechnologyProduct", exceptionMessage = "删除TechnologyProduct失败")
    @GetMapping("technologyProduct/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("technologyProduct:delete")
    public FebsResponse deleteTechnologyProduct(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.technologyProductService.deleteTechnologyProduct(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyProduct", exceptionMessage = "修改TechnologyProduct失败")
    @PostMapping("technologyProduct/update")
    @ResponseBody
    @RequiresPermissions("technologyProduct:update")
    public FebsResponse updateTechnologyProduct(TechnologyProduct technologyProduct) {
        this.technologyProductService.updateTechnologyProduct(technologyProduct);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyProduct", exceptionMessage = "导出Excel失败")
    @PostMapping("technologyProduct/excel")
    @ResponseBody
    @RequiresPermissions("technologyProduct:export")
    public void export(QueryRequest queryRequest, TechnologyProduct technologyProduct, HttpServletResponse response) {
        List<TechnologyProduct> technologyProducts = this.technologyProductService.findTechnologyProducts(queryRequest, technologyProduct).getRecords();
        ExcelKit.$Export(TechnologyProduct.class, response).downXlsx(technologyProducts, false);
    }
}

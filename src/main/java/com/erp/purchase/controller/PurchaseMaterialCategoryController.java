package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseMaterialCategory;
import com.erp.purchase.service.IPurchaseMaterialCategoryService;
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
import java.util.List;
import java.util.Map;

/**
 * 物料类别表 Controller
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:14
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseMaterialCategoryController extends BaseController {

    private final IPurchaseMaterialCategoryService purchaseMaterialCategoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseMaterialCategory")
    public String purchaseMaterialCategoryIndex(){
        return FebsUtil.view("purchaseMaterialCategory/purchaseMaterialCategory");
    }

    @GetMapping("purchaseMaterialCategory")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:list")
    public FebsResponse getAllPurchaseMaterialCategorys(PurchaseMaterialCategory purchaseMaterialCategory) {
        return new FebsResponse().success().data(purchaseMaterialCategoryService.findPurchaseMaterialCategorys(purchaseMaterialCategory));
    }

    @GetMapping("purchaseMaterialCategory/list")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:view")
    public FebsResponse purchaseMaterialCategoryList(QueryRequest request, PurchaseMaterialCategory purchaseMaterialCategory) {
        Map<String, Object> dataTable = getDataTable(this.purchaseMaterialCategoryService.findPurchaseMaterialCategorys(request, purchaseMaterialCategory));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseMaterialCategory", exceptionMessage = "新增PurchaseMaterialCategory失败")
    @PostMapping("purchaseMaterialCategory/add")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:add")
    public FebsResponse addPurchaseMaterialCategory(@Valid PurchaseMaterialCategory purchaseMaterialCategory) {
        this.purchaseMaterialCategoryService.createPurchaseMaterialCategory(purchaseMaterialCategory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PurchaseMaterialCategory", exceptionMessage = "删除PurchaseMaterialCategory失败")
    @GetMapping("purchaseMaterialCategory/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:delete")
    public FebsResponse deletePurchaseMaterialCategory(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchaseMaterialCategoryService.deletePurchaseMaterialCategory(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseMaterialCategory", exceptionMessage = "修改PurchaseMaterialCategory失败")
    @PostMapping("purchaseMaterialCategory/update")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:update")
    public FebsResponse updatePurchaseMaterialCategory(PurchaseMaterialCategory purchaseMaterialCategory) {
        this.purchaseMaterialCategoryService.updatePurchaseMaterialCategory(purchaseMaterialCategory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseMaterialCategory", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseMaterialCategory/excel")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialCategory:export")
    public void export(QueryRequest queryRequest, PurchaseMaterialCategory purchaseMaterialCategory, HttpServletResponse response) {
        List<PurchaseMaterialCategory> purchaseMaterialCategorys = this.purchaseMaterialCategoryService.findPurchaseMaterialCategorys(queryRequest, purchaseMaterialCategory).getRecords();
        ExcelKit.$Export(PurchaseMaterialCategory.class, response).downXlsx(purchaseMaterialCategorys, false);
    }
}

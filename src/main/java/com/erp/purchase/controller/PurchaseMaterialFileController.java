package com.erp.purchase.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.exception.FebsException;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseMaterialCategory;
import com.erp.purchase.entity.PurchaseMaterialFile;
import com.erp.purchase.service.IPurchaseMaterialCategoryService;
import com.erp.purchase.service.IPurchaseMaterialFileService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物料档案表 Controller
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:19
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PurchaseMaterialFileController extends BaseController {

    private final IPurchaseMaterialFileService purchaseMaterialFileService;

    //物料类别表 Service接口
    private final IPurchaseMaterialCategoryService purchaseMaterialCategoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "purchaseMaterialFile")
    public String purchaseMaterialFileIndex(){
        return FebsUtil.view("purchaseMaterialFile/purchaseMaterialFile");
    }

    @GetMapping("purchaseMaterialFile")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:list")
    public FebsResponse getAllPurchaseMaterialFiles(PurchaseMaterialFile purchaseMaterialFile) {
        return new FebsResponse().success().data(purchaseMaterialFileService.findPurchaseMaterialFiles(purchaseMaterialFile));
    }

    @GetMapping("purchaseMaterialFile/list")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:view")
    public FebsResponse purchaseMaterialFileList(QueryRequest request, PurchaseMaterialFile purchaseMaterialFile) {
        Map<String, Object> dataTable = getDataTable(this.purchaseMaterialFileService.findPurchaseMaterialFiles(request, purchaseMaterialFile));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PurchaseMaterialFile", exceptionMessage = "新增PurchaseMaterialFile失败")
    @PostMapping("purchaseMaterialFile/add")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:add")
    public FebsResponse addPurchaseMaterialFile(@Valid PurchaseMaterialFile purchaseMaterialFile) throws ParseException {
        this.purchaseMaterialFileService.createPurchaseMaterialFile(purchaseMaterialFile);
        return new FebsResponse().success();
    }

   @PostMapping("purchaseMaterialFile/query")
   @ControllerEndpoint(exceptionMessage = "查询小类失败")
   @ResponseBody
   public List<PurchaseMaterialCategory> queryMaterialSubclass(Long id) throws FebsException {
       List<PurchaseMaterialCategory> purchaseMaterialCategories = purchaseMaterialCategoryService.queryMaterialSubclass(id);
       return purchaseMaterialCategories;
   }

    @ControllerEndpoint(operation = "删除PurchaseMaterialFile", exceptionMessage = "删除PurchaseMaterialFile失败")
    @GetMapping("purchaseMaterialFile/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:delete")
    public FebsResponse deletePurchaseMaterialFile(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.purchaseMaterialFileService.deletePurchaseMaterialFile(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PurchaseMaterialFile", exceptionMessage = "修改PurchaseMaterialFile失败")
    @PostMapping("purchaseMaterialFile/update")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:update")
    public FebsResponse updatePurchaseMaterialFile(PurchaseMaterialFile purchaseMaterialFile) {
        this.purchaseMaterialFileService.updatePurchaseMaterialFile(purchaseMaterialFile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "导出PurchaseMaterialFile", exceptionMessage = "导出Excel失败")
    @PostMapping("purchaseMaterialFile/excel")
    @ResponseBody
    @RequiresPermissions("purchaseMaterialFile:export")
    public void export(QueryRequest queryRequest, PurchaseMaterialFile purchaseMaterialFile, HttpServletResponse response) {
        List<PurchaseMaterialFile> purchaseMaterialFiles = this.purchaseMaterialFileService.findPurchaseMaterialFiles(queryRequest, purchaseMaterialFile).getRecords();
        ExcelKit.$Export(PurchaseMaterialFile.class, response).downXlsx(purchaseMaterialFiles, false);
    }
}

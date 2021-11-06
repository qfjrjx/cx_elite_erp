package com.erp.technology.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyProductCategoryService;
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
 * 产品类别表 Controller
 *
 * @author qiufeng
 * @date 2021-11-05 16:27:29
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class TechnologyProductCategoryController extends BaseController {

    private final ITechnologyProductCategoryService technologyProductCategoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "technologyProductCategory")
    public String technologyProductCategoryIndex(){
        return FebsUtil.view("technologyProductCategory/technologyProductCategory");
    }

    @GetMapping("technologyProductCategory")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:list")
    public FebsResponse getAllTechnologyProductCategorys(TechnologyProductCategory technologyProductCategory) {
        return new FebsResponse().success().data(technologyProductCategoryService.findTechnologyProductCategorys(technologyProductCategory));
    }

    @GetMapping("technologyProductCategory/list")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:view")
    public FebsResponse technologyProductCategoryList(QueryRequest request, TechnologyProductCategory technologyProductCategory) {
        Map<String, Object> dataTable = getDataTable(this.technologyProductCategoryService.findTechnologyProductCategorys(request, technologyProductCategory));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增TechnologyProductCategory", exceptionMessage = "新增TechnologyProductCategory失败")
    @PostMapping("technologyProductCategory/add")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:add")
    public FebsResponse addTechnologyProductCategory(@Valid TechnologyProductCategory technologyProductCategory) {
        this.technologyProductCategoryService.createTechnologyProductCategory(technologyProductCategory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除TechnologyProductCategory", exceptionMessage = "删除TechnologyProductCategory失败")
    @GetMapping("technologyProductCategory/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:delete")
    public FebsResponse deleteTechnologyProductCategory(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.technologyProductCategoryService.deleteTechnologyProductCategory(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改TechnologyProductCategory", exceptionMessage = "修改TechnologyProductCategory失败")
    @PostMapping("technologyProductCategory/update")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:update")
    public FebsResponse updateTechnologyProductCategory(TechnologyProductCategory technologyProductCategory) {
        this.technologyProductCategoryService.updateTechnologyProductCategory(technologyProductCategory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "导出TechnologyProductCategory", exceptionMessage = "导出Excel失败")
    @PostMapping("technologyProductCategory/excel")
    @ResponseBody
    @RequiresPermissions("technologyProductCategory:export")
    public void export(QueryRequest queryRequest, TechnologyProductCategory technologyProductCategory, HttpServletResponse response) {
        List<TechnologyProductCategory> technologyProductCategorys = this.technologyProductCategoryService.findTechnologyProductCategorys(queryRequest, technologyProductCategory).getRecords();
        ExcelKit.$Export(TechnologyProductCategory.class, response).downXlsx(technologyProductCategorys, false);
    }
}

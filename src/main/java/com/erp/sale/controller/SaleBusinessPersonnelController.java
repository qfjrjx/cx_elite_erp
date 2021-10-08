package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.service.ISaleBusinessPersonnelService;
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
 * 业务人员表

               数据库表名：                                         对应java表名：
    
               jr_sale_business_personnel                    SaleBusinessPersonnel Controller
 *
 * @author qiufeng
 * @date 2021-10-08 13:34:13
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleBusinessPersonnelController extends BaseController {

    private final ISaleBusinessPersonnelService saleBusinessPersonnelService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleBusinessPersonnel")
    public String saleBusinessPersonnelIndex(){
        return FebsUtil.view("saleBusinessPersonnel/saleBusinessPersonnel");
    }

    @GetMapping("saleBusinessPersonnel")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:list")
    public FebsResponse getAllSaleBusinessPersonnels(SaleBusinessPersonnel saleBusinessPersonnel) {
        return new FebsResponse().success().data(saleBusinessPersonnelService.findSaleBusinessPersonnels(saleBusinessPersonnel));
    }

    @GetMapping("saleBusinessPersonnel/list")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:view")
    public FebsResponse saleBusinessPersonnelList(QueryRequest request, SaleBusinessPersonnel saleBusinessPersonnel) {
        Map<String, Object> dataTable = getDataTable(this.saleBusinessPersonnelService.findSaleBusinessPersonnels(request, saleBusinessPersonnel));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleBusinessPersonnel", exceptionMessage = "新增SaleBusinessPersonnel失败")
    @PostMapping("saleBusinessPersonnel/add")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:add")
    public FebsResponse addSaleBusinessPersonnel(@Valid SaleBusinessPersonnel saleBusinessPersonnel) {
        this.saleBusinessPersonnelService.createSaleBusinessPersonnel(saleBusinessPersonnel);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleBusinessPersonnel", exceptionMessage = "删除SaleBusinessPersonnel失败")
    @GetMapping("saleBusinessPersonnel/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:delete")
    public FebsResponse deleteSaleBusinessPersonnel(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.saleBusinessPersonnelService.deleteSaleBusinessPersonnel(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleBusinessPersonnel", exceptionMessage = "修改SaleBusinessPersonnel失败")
    @PostMapping("saleBusinessPersonnel/update")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:update")
    public FebsResponse updateSaleBusinessPersonnel(SaleBusinessPersonnel saleBusinessPersonnel) {
        this.saleBusinessPersonnelService.updateSaleBusinessPersonnel(saleBusinessPersonnel);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleBusinessPersonnel", exceptionMessage = "导出Excel失败")
    @PostMapping("saleBusinessPersonnel/excel")
    @ResponseBody
    @RequiresPermissions("saleBusinessPersonnel:export")
    public void export(QueryRequest queryRequest, SaleBusinessPersonnel saleBusinessPersonnel, HttpServletResponse response) {
        List<SaleBusinessPersonnel> saleBusinessPersonnels = this.saleBusinessPersonnelService.findSaleBusinessPersonnels(queryRequest, saleBusinessPersonnel).getRecords();
        ExcelKit.$Export(SaleBusinessPersonnel.class, response).downXlsx(saleBusinessPersonnels, false);
    }
}

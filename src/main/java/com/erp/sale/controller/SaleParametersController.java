package com.erp.sale.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.service.ISaleParametersService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售参数表

          数据库表名：                              对应java表名：

          jr_sale_parameters                     SaleParameters Controller
 *
 * @author qiufeng
 * @date 2021-10-07 10:18:55
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleParametersController extends BaseController {

    private final ISaleParametersService saleParametersService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleParameters")
    public String saleParametersIndex(){
        return FebsUtil.view("saleParameters/saleParameters");
    }

    @GetMapping("saleParameters")
    @ResponseBody
    @RequiresPermissions("saleParameters:view")
    public FebsResponse getAllSaleParameterss(SaleParameters saleParameters) {
        return new FebsResponse().success().data(saleParametersService.findSaleParameterss(saleParameters));
    }

    @GetMapping("saleParameters/list")
    @ResponseBody
    @RequiresPermissions("saleParameters:view")
    public FebsResponse saleParametersList(QueryRequest request, SaleParameters saleParameters) {
        Map<String, Object> dataTable = getDataTable(this.saleParametersService.findSaleParameterss(request, saleParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleParameters", exceptionMessage = "新增SaleParameters失败")
    @PostMapping("saleParameters/add")
    @ResponseBody
    @RequiresPermissions("saleParameters:add")
    public FebsResponse addSaleParameters(@Valid SaleParameters saleParameters) {
        Date date = null;
        Date createTime = new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(createTime);
        try {
            date = format.parse(str);
            saleParameters.setCreateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.saleParametersService.createSaleParameters(saleParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleParameters", exceptionMessage = "删除SaleParameters失败")
    @GetMapping("saleParameters/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("saleParameters:delete")
    public FebsResponse deleteSaleParameters(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.saleParametersService.deleteSaleParameters(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleParameters", exceptionMessage = "修改SaleParameters失败")
    @PostMapping("saleParameters/update")
    @ResponseBody
    @RequiresPermissions("saleParameters:update")
    public FebsResponse updateSaleParameters(SaleParameters saleParameters) {
        this.saleParametersService.updateSaleParameters(saleParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("saleParameters/excel")
    @ResponseBody
    @RequiresPermissions("saleParameters:export")
    public void export(QueryRequest queryRequest, SaleParameters saleParameters, HttpServletResponse response) {
        List<SaleParameters> saleParameterss = this.saleParametersService.findSaleParameterss(queryRequest, saleParameters).getRecords();
        ExcelKit.$Export(SaleParameters.class, response).downXlsx(saleParameterss, false);
    }
}

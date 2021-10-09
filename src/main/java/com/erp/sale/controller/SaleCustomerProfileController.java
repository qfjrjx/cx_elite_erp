package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleCustomerProfile;
import com.erp.sale.service.ISaleCustomerProfileService;
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
 * 客户档案表

             数据库表名：                                             对应java表名：

             jr_sale_customer_profile                            SaleCustomerProfile Controller
 *
 * @author qiufeng
 * @date 2021-10-08 16:51:04
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleCustomerProfileController extends BaseController {

    private final ISaleCustomerProfileService saleCustomerProfileService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleCustomerProfile")
    public String saleCustomerProfileIndex(){
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfile");
    }

    @GetMapping("saleCustomerProfile")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:list")
    public FebsResponse getAllSaleCustomerProfiles(SaleCustomerProfile saleCustomerProfile) {
        return new FebsResponse().success().data(saleCustomerProfileService.findSaleCustomerProfiles(saleCustomerProfile));
    }

    @GetMapping("saleCustomerProfile/list")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:view")
    public FebsResponse saleCustomerProfileList(QueryRequest request, SaleCustomerProfile saleCustomerProfile) {
        Map<String, Object> dataTable = getDataTable(this.saleCustomerProfileService.findSaleCustomerProfiles(request, saleCustomerProfile));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleCustomerProfile", exceptionMessage = "新增SaleCustomerProfile失败")
    @PostMapping("saleCustomerProfile/add")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:add")
    public FebsResponse addSaleCustomerProfile(@Valid SaleCustomerProfile saleCustomerProfile) {
        Date date = null;
        Date createTime = new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(createTime);
        try {
            date = format.parse(str);
            saleCustomerProfile.setCreatedDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.saleCustomerProfileService.createSaleCustomerProfile(saleCustomerProfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleCustomerProfile", exceptionMessage = "删除SaleCustomerProfile失败")
    @GetMapping("saleCustomerProfile/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:delete")
    public FebsResponse deleteSaleCustomerProfile(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.saleCustomerProfileService.deleteSaleCustomerProfile(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleCustomerProfile", exceptionMessage = "修改SaleCustomerProfile失败")
    @PostMapping("saleCustomerProfile/update")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:update")
    public FebsResponse updateSaleCustomerProfile(SaleCustomerProfile saleCustomerProfile) {
        this.saleCustomerProfileService.updateSaleCustomerProfile(saleCustomerProfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleCustomerProfile", exceptionMessage = "导出Excel失败")
    @PostMapping("saleCustomerProfile/excel")
    @ResponseBody
    @RequiresPermissions("saleCustomerProfile:export")
    public void export(QueryRequest queryRequest, SaleCustomerProfile saleCustomerProfile, HttpServletResponse response) {
        List<SaleCustomerProfile> saleCustomerProfiles = this.saleCustomerProfileService.findSaleCustomerProfiles(queryRequest, saleCustomerProfile).getRecords();
        ExcelKit.$Export(SaleCustomerProfile.class, response).downXlsx(saleCustomerProfiles, false);
    }
}

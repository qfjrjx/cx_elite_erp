package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleDeliveryNoticeAll;
import com.erp.sale.entity.SaleOrderSchedule;
import com.erp.sale.service.ISaleDeliveryNoticeService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ๅ่ดง้็ฅ Controller
 *
 * @author qiufeng
 * @date 2022-05-13 10:13:33
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleDeliveryNoticeController extends BaseController {

    private final ISaleDeliveryNoticeService saleDeliveryNoticeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleDeliveryNotice")
    public String saleDeliveryNoticeIndex(){
        return FebsUtil.view("saleDeliveryNotice/saleDeliveryNotice");
    }

    @GetMapping("saleDeliveryNotice")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:list")
    public FebsResponse getAllSaleDeliveryNotices(SaleDeliveryNoticeAll saleDeliveryNotice) {
        return new FebsResponse().success().data(saleDeliveryNoticeService.findSaleDeliveryNotices(saleDeliveryNotice));
    }

    @GetMapping("saleDeliveryNotice/list")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:view")
    public FebsResponse saleDeliveryNoticeList(QueryRequest request, SaleDeliveryNoticeAll saleDeliveryNotice) {
        Map<String, Object> dataTable = getDataTable(this.saleDeliveryNoticeService.findSaleDeliveryNotices(request, saleDeliveryNotice));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "ๆฐๅขSaleDeliveryNotice", exceptionMessage = "ๆฐๅขSaleDeliveryNoticeๅคฑ่ดฅ")
    @PostMapping("saleDeliveryNotice/add")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:add")
    public FebsResponse addSaleDeliveryNotice(@RequestParam String saleDeliveryNoticeData,@RequestParam String dataTable) throws ParseException {
        this.saleDeliveryNoticeService.createSaleDeliveryNotice(saleDeliveryNoticeData,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "ๅ?้คSaleDeliveryNotice", exceptionMessage = "ๅ?้คSaleDeliveryNoticeๅคฑ่ดฅ")
    @GetMapping("saleDeliveryNotice/delete")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:delete")
    public FebsResponse deleteSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice) {
        this.saleDeliveryNoticeService.deleteSaleDeliveryNotice(saleDeliveryNotice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "ไฟฎๆนSaleDeliveryNotice", exceptionMessage = "ไฟฎๆนSaleDeliveryNoticeๅคฑ่ดฅ")
    @PostMapping("saleDeliveryNotice/update")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:update")
    public FebsResponse updateSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice) {
        this.saleDeliveryNoticeService.updateSaleDeliveryNotice(saleDeliveryNotice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "ไฟฎๆนSaleDeliveryNotice", exceptionMessage = "ๅฏผๅบExcelๅคฑ่ดฅ")
    @PostMapping("saleDeliveryNotice/excel")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:export")
    public void export(QueryRequest queryRequest, SaleDeliveryNoticeAll saleDeliveryNotice, HttpServletResponse response) {
        List<SaleDeliveryNoticeAll> saleDeliveryNotices = this.saleDeliveryNoticeService.findSaleDeliveryNotices(queryRequest, saleDeliveryNotice).getRecords();
        ExcelKit.$Export(SaleDeliveryNoticeAll.class, response).downXlsx(saleDeliveryNotices, false);
    }

    @ControllerEndpoint(operation = "ๆ?นๆฎ้ๆฉๅฎขๆทๆฅ่ฏขๅบๅฏไปฅๅ่ดง็ๆฐๆฎsaleDeliveryNotice", exceptionMessage = "ๆฅ่ฏขๅฎขๆทๆฐๆฎsaleDeliveryNoticeๅคฑ่ดฅ")
    @GetMapping("saleDeliveryNoticeData/list")
    @ResponseBody
    @RequiresPermissions("saleDeliveryNotice:view")
    public Map saleOrderDateList(@RequestParam Long customerData) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SaleDeliveryNoticeAll> saleDeliveryNoticeAll = this.saleDeliveryNoticeService.saleDeliveryNoticeList(customerData);
            map.put("replies", saleDeliveryNoticeAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}

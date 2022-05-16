package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.exception.FebsException;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleDocumentfile;
import com.erp.sale.service.ISaleDocumentfileService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jodconverter.DocumentConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * 上传文件表 Controller
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleDocumentfileController extends BaseController {

    private final ISaleDocumentfileService saleDocumentfileService;


    @GetMapping(FebsConstant.VIEW_PREFIX + "saleDocumentfile")
    public String saleDocumentfileIndex(){
        return FebsUtil.view("saleDocumentfile/saleDocumentfile");
    }

    @GetMapping("saleDocumentfile")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:list")
    public FebsResponse getAllSaleDocumentfiles(SaleDocumentfile saleDocumentfile) {
        return new FebsResponse().success().data(saleDocumentfileService.findSaleDocumentfiles(saleDocumentfile));
    }

    @GetMapping("saleDocumentfile/list")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:list")
    public FebsResponse saleDocumentfileList(QueryRequest request, SaleDocumentfile saleDocumentfile) {
        Map<String, Object> dataTable = getDataTable(this.saleDocumentfileService.findSaleDocumentfiles(request, saleDocumentfile));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleDocumentfile", exceptionMessage = "新增SaleDocumentfile失败")
    @PostMapping("saleDocumentfile")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:add")
    public FebsResponse addSaleDocumentfile(@Valid SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.createSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleDocumentfile", exceptionMessage = "删除SaleDocumentfile失败")
    @GetMapping("saleDocumentfile/delete")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:delete")
    public FebsResponse deleteSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.deleteSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleDocumentfile", exceptionMessage = "修改SaleDocumentfile失败")
    @PostMapping("saleDocumentfile/update")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:update")
    public FebsResponse updateSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.updateSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleDocumentfile", exceptionMessage = "导出Excel失败")
    @PostMapping("saleDocumentfile/excel")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:export")
    public void export(QueryRequest queryRequest, SaleDocumentfile saleDocumentfile, HttpServletResponse response) {
        List<SaleDocumentfile> saleDocumentfiles = this.saleDocumentfileService.findSaleDocumentfiles(queryRequest, saleDocumentfile).getRecords();
        ExcelKit.$Export(SaleDocumentfile.class, response).downXlsx(saleDocumentfiles, false);
    }

    //删除
    @GetMapping("saleDocumentFile/deleteFile")
    @ResponseBody
    public Map deleteFile(@RequestParam("contFile") String contFile){
        Map res = new HashMap();
        try {
            //截取：之前的字符串
            int contImgOne = contFile.indexOf(".");
            String contFileOne = contFile.substring(0,contImgOne);
            saleDocumentfileService.deleteSaleDocumentFile(contFileOne);
            res.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}

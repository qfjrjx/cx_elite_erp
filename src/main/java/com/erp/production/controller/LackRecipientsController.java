package com.erp.production.controller;


import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.production.entity.LackRecipients;
import com.erp.production.entity.LackRecipientsSchedule;
import com.erp.production.service.ILackRecipientsService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缺件领用 Controller
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:17
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class LackRecipientsController extends BaseController {

    private final ILackRecipientsService lackRecipientsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "lackRecipients")
    public String lackRecipientsIndex(){
        return FebsUtil.view("lackRecipients/lackRecipients");
    }

    @GetMapping("lackRecipients")
    @ResponseBody
    @RequiresPermissions("lackRecipients:list")
    public FebsResponse getAllLackRecipientss(LackRecipients lackRecipients) {
        return new FebsResponse().success().data(lackRecipientsService.findLackRecipientss(lackRecipients));
    }

    @GetMapping("lackRecipients/list")
    @ResponseBody
    @RequiresPermissions("lackRecipients:view")
    public FebsResponse lackRecipientsList(QueryRequest request, LackRecipients lackRecipients) {
        Map<String, Object> dataTable = getDataTable(this.lackRecipientsService.findLackRecipientss(request, lackRecipients));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增LackRecipients", exceptionMessage = "新增LackRecipients失败")
    @PostMapping("lackRecipients/add")
    @ResponseBody
    @RequiresPermissions("lackRecipients:add")
    public FebsResponse addLackRecipients(@Valid String lackRecipients , @RequestBody String dataTable) throws ParseException {
        this.lackRecipientsService.createLackRecipients(lackRecipients,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除LackRecipients", exceptionMessage = "删除LackRecipients失败")
    @GetMapping("lackRecipients/delete")
    @ResponseBody
    @RequiresPermissions("lackRecipients:delete")
    public FebsResponse deleteLackRecipients(LackRecipients lackRecipients) {
        this.lackRecipientsService.deleteLackRecipients(lackRecipients);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改LackRecipients", exceptionMessage = "修改LackRecipients失败")
    @PostMapping("lackRecipients/update")
    @ResponseBody
    @RequiresPermissions("lackRecipients:update")
    public FebsResponse updateLackRecipients(@Valid String lackCode , @RequestBody String dataTable) throws ParseException {
        this.lackRecipientsService.updateLackRecipients(lackCode,dataTable);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改LackRecipients", exceptionMessage = "导出Excel失败")
    @PostMapping("lackRecipients/excel")
    @ResponseBody
    @RequiresPermissions("lackRecipients:export")
    public void export(QueryRequest queryRequest, LackRecipients lackRecipients, HttpServletResponse response) {
        List<LackRecipients> lackRecipientss = this.lackRecipientsService.findLackRecipientss(queryRequest, lackRecipients).getRecords();
        ExcelKit.$Export(LackRecipients.class, response).downXlsx(lackRecipientss, false);
    }

    @GetMapping("lackRecipients/query")
    @ResponseBody
    public Map queryLackRecipients(@RequestParam String lackCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<LackRecipientsSchedule> lackRecipientsSchedules = this.lackRecipientsService.queryLackRecipients(lackCode);
            map.put("replies",lackRecipientsSchedules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @ControllerEndpoint(operation = "取消", exceptionMessage = "取消失败")
    @GetMapping("lackRecipients/cancel/{ids}")
    @ResponseBody
    @RequiresPermissions("lackRecipients:cancel")
    public FebsResponse cancelLackRecipients(@PathVariable String ids) {
        this.lackRecipientsService.cancelLackRecipients(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "确认", exceptionMessage = "确认失败")
    @GetMapping("lackRecipients/confirm/{ids}")
    @ResponseBody
    @RequiresPermissions("lackRecipients:confirm")
    public FebsResponse confirmLackRecipients(@PathVariable String ids) {
        this.lackRecipientsService.confirmLackRecipients(ids);
        return new FebsResponse().success();
    }
}

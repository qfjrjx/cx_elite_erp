package com.erp.enterprise.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.erp.enterprise.service.IEnterpriseDocumentAnnouncementService;
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
 * 公文公告表 Controller
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:08
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class EnterpriseDocumentAnnouncementController extends BaseController {

    private final IEnterpriseDocumentAnnouncementService enterpriseDocumentAnnouncementService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "enterpriseDocumentAnnouncement")
    public String enterpriseDocumentAnnouncementIndex(){
        return FebsUtil.view("enterpriseDocumentAnnouncement/enterpriseDocumentAnnouncement");
    }

    @GetMapping("enterpriseDocumentAnnouncement")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:list")
    public FebsResponse getAllEnterpriseDocumentAnnouncements(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
        return new FebsResponse().success().data(enterpriseDocumentAnnouncementService.findEnterpriseDocumentAnnouncements(enterpriseDocumentAnnouncement));
    }

    @GetMapping("enterpriseDocumentAnnouncement/list")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:view")
    public FebsResponse enterpriseDocumentAnnouncementList(QueryRequest request, EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
        Map<String, Object> dataTable = getDataTable(this.enterpriseDocumentAnnouncementService.findEnterpriseDocumentAnnouncements(request, enterpriseDocumentAnnouncement));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增EnterpriseDocumentAnnouncement", exceptionMessage = "新增EnterpriseDocumentAnnouncement失败")
    @PostMapping("enterpriseDocumentAnnouncement/add")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:add")
    public FebsResponse addEnterpriseDocumentAnnouncement(@Valid EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) throws ParseException {
        this.enterpriseDocumentAnnouncementService.createEnterpriseDocumentAnnouncement(enterpriseDocumentAnnouncement);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除EnterpriseDocumentAnnouncement", exceptionMessage = "删除EnterpriseDocumentAnnouncement失败")
    @GetMapping("enterpriseDocumentAnnouncement/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:delete")
    public FebsResponse deleteEnterpriseDocumentAnnouncement(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.enterpriseDocumentAnnouncementService.deleteEnterpriseDocumentAnnouncement(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterpriseDocumentAnnouncement", exceptionMessage = "修改EnterpriseDocumentAnnouncement失败")
    @PostMapping("enterpriseDocumentAnnouncement/update")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:update")
    public FebsResponse updateEnterpriseDocumentAnnouncement(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
        this.enterpriseDocumentAnnouncementService.updateEnterpriseDocumentAnnouncement(enterpriseDocumentAnnouncement);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改EnterpriseDocumentAnnouncement", exceptionMessage = "导出Excel失败")
    @PostMapping("enterpriseDocumentAnnouncement/excel")
    @ResponseBody
    @RequiresPermissions("enterpriseDocumentAnnouncement:export")
    public void export(QueryRequest queryRequest, EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement, HttpServletResponse response) {
        List<EnterpriseDocumentAnnouncement> enterpriseDocumentAnnouncements = this.enterpriseDocumentAnnouncementService.findEnterpriseDocumentAnnouncements(queryRequest, enterpriseDocumentAnnouncement).getRecords();
        ExcelKit.$Export(EnterpriseDocumentAnnouncement.class, response).downXlsx(enterpriseDocumentAnnouncements, false);
    }
}

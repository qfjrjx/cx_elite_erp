package com.erp.enterprise.controller;

import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EmployeeAddressBook;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.erp.personnel.service.IPersonnelArchivesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 通讯录 Controller
 *
 * @author qiufeng
 * @date 2021-12-14 10:23:08
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class EmployeeAddressBookController extends BaseController {
   /* 用户表 Service接口*/
    private final IPersonnelArchivesService personnelArchivesService;

    @GetMapping("employeeAddressBook/list")
    @ResponseBody
    @RequiresPermissions("employeeAddressBook:view")
    public FebsResponse getAllEmployeeAddressBook(QueryRequest request,EmployeeAddressBook employeeAddressBook) {
        Map<String, Object> dataTable = getDataTable(this.personnelArchivesService.findEmployeeAddressBook(employeeAddressBook,request));
        return new FebsResponse().success().data(dataTable);
    }


}

package com.erp.others.controller;

import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.others.entity.DataPermissionTest;
import com.erp.others.service.IDataPermissionTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 *
 * @author qiufeng
 * @date 2020-04-14 15:25:33
 */
@Slf4j
@RestController
@RequestMapping("datapermission/test")
@RequiredArgsConstructor
public class DataPermissionTestController extends BaseController {

    private final IDataPermissionTestService dataPermissionTestService;

    @GetMapping("list")
    @RequiresPermissions("others:datapermission")
    public FebsResponse dataPermissionTestList(QueryRequest request, DataPermissionTest dataPermissionTest) {
        return new FebsResponse().success()
                .data(getDataTable(dataPermissionTestService.findDataPermissionTests(request, dataPermissionTest)));
    }
}

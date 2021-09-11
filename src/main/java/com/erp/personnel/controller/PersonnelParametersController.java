package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.exception.FebsException;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.service.IPersonnelParametersService;
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
 * 人事参数表 Controller
 *
 * @author qiufeng
 * @date 2021-09-03 15:19:47
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class PersonnelParametersController extends BaseController {

    private final IPersonnelParametersService personnelParametersService;

    @GetMapping("personnelParameters/queryDuties")
    @ControllerEndpoint(exceptionMessage = "获取职务失败")
    @ResponseBody
    public FebsResponse queryDuties() throws FebsException {
        return new FebsResponse().success().data(personnelParametersService.queryDuties());
    }

    @GetMapping("personnelParameters/queryPosition")
    @ControllerEndpoint(exceptionMessage = "获取岗位失败")
    @ResponseBody
    public FebsResponse queryPosition() throws FebsException {
        return new FebsResponse().success().data(personnelParametersService.queryPosition());
    }

    @GetMapping("personnelParameters/querySocialSecurity")
    @ControllerEndpoint(exceptionMessage = "获取社保失败")
    @ResponseBody
    public FebsResponse querySocialSecurity() throws FebsException {
        return new FebsResponse().success().data(personnelParametersService.querySocialSecurity());
    }



    @GetMapping("personnelParameters/list")
    @ResponseBody
    @RequiresPermissions("personnelParameters:view")
    public FebsResponse personnelParametersList(QueryRequest request, PersonnelParameters personnelParameters) {
        Map<String, Object> dataTable = getDataTable(this.personnelParametersService.findPersonnelParameterss(request, personnelParameters));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelParameters", exceptionMessage = "新增PersonnelParameters失败")
    @PostMapping("personnelParameters")
    @ResponseBody
    @RequiresPermissions("personnelParameters:add")
    public FebsResponse addPersonnelParameters(@Valid PersonnelParameters personnelParameters) {
        Date date = null;
        Date createTime = new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String str = format.format(createTime);
        try {
            date = format.parse(str);
            personnelParameters.setCreateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.personnelParametersService.createPersonnelParameters(personnelParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelParameters", exceptionMessage = "删除PersonnelParameters失败")
    @GetMapping("personnelParameters/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelParameters:delete")
    public FebsResponse deletePersonnelParameters(@NotBlank(message = "{required}") @PathVariable String ids) {
        personnelParametersService.deletePersonnel(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelParameters", exceptionMessage = "修改PersonnelParameters失败")
    @PostMapping("personnelParameters/update")
    @ResponseBody
    @RequiresPermissions("personnelParameters:update")
    public FebsResponse updatePersonnelParameters(PersonnelParameters personnelParameters) {
        Date date = null;
        Date createTime = new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String str = format.format(createTime);
        try {
            date = format.parse(str);
            personnelParameters.setCreateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.personnelParametersService.updatePersonnelParameters(personnelParameters);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelParameters", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelParameters/excel")
    @ResponseBody
    @RequiresPermissions("personnelParameters:export")
    public void export(QueryRequest queryRequest, PersonnelParameters personnelParameters, HttpServletResponse response) {
        List<PersonnelParameters> personnelParameterss = this.personnelParametersService.findPersonnelParameterss(queryRequest, personnelParameters).getRecords();
        ExcelKit.$Export(PersonnelParameters.class, response).downXlsx(personnelParameterss, false);
    }

}

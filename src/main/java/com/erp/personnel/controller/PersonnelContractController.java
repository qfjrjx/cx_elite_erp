package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.personnel.entity.PersonnelContract;
import com.erp.personnel.service.IPersonnelContractService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 员工合同表 Controller
 *
 * @author qiufeng
 * @date 2021-09-16 10:46:36
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelContractController extends BaseController {

    private final IPersonnelContractService personnelContractService;

    @GetMapping("personnelContract")
    @ResponseBody
    @RequiresPermissions("personnelContract:list")
    public FebsResponse getAllPersonnelContracts(PersonnelContract personnelContract) throws ParseException{
        List<String> arrayList = new ArrayList<>();
        List<PersonnelContract> contractsList = personnelContractService.queryContractTipsList();
        for (PersonnelContract contract:contractsList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            //System.out.println(simpleDateFormat.format(new Date()));// new Date()为获取当前系统时间
            String date = simpleDateFormat.format(new Date());//系统当前时间
            //System.out.println("系统当前时间："+date);
            long timeStart = simpleDateFormat.parse(date).getTime();//转化系统当前时间为毫秒值
            //System.out.println("转化系统当前时间为毫秒值："+timeStart);
            String expireDate = simpleDateFormat.format(contract.getExpireDate());//获取到期时间
            //System.out.println("获取到期时间："+expireDate);
            long timeEnd = simpleDateFormat.parse(expireDate).getTime();//转化到期时间为毫秒值
            //System.out.println("转化到期时间为毫秒值："+timeEnd);
            long dayCount= (timeEnd-timeStart)/(24*3600*1000);////两个日期想减得到天数
            //System.out.println("两个日期想减得到天数："+dayCount);
            if(dayCount<=30){
                String staffName = contract.getStaffName();
                arrayList.add(staffName);
            }
        }
        return new FebsResponse().success().data(arrayList);
    }

    @GetMapping("personnelContract/list")
    @ResponseBody
    @RequiresPermissions("personnelContract:list")
    public FebsResponse personnelContractList(QueryRequest request, PersonnelContract personnelContract) throws ParseException{

        if (personnelContract.getStaffName() == null && personnelContract.getStaffName() == "" | personnelContract.getContractState() == null | personnelContract.getSignedDateFrom()== null | personnelContract.getExpireDateFrom() == null){
          List<PersonnelContract> contractsList = personnelContractService.queryContractList();
          for (PersonnelContract contract:contractsList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            //System.out.println(simpleDateFormat.format(new Date()));// new Date()为获取当前系统时间
            String date = simpleDateFormat.format(new Date());//系统当前时间
            //System.out.println("系统当前时间："+date);
            long timeStart = simpleDateFormat.parse(date).getTime();//转化系统当前时间为毫秒值
            //System.out.println("转化系统当前时间为毫秒值："+timeStart);
            String expireDate = simpleDateFormat.format(contract.getExpireDate());//获取到期时间
            //System.out.println("获取到期时间："+expireDate);
            long timeEnd = simpleDateFormat.parse(expireDate).getTime();//转化到期时间为毫秒值
            //System.out.println("转化到期时间为毫秒值："+timeEnd);
            long dayCount= (timeEnd-timeStart)/(24*3600*1000);////两个日期想减得到天数
            //System.out.println("两个日期想减得到天数："+dayCount);
            if(dayCount<0){
              Long contractId = contract.getContractId();
              int contractState = 2;
              personnelContractService.contractStateUpdate(contractId,contractState);
            }
          }
        }
        Map<String, Object> dataTable = getDataTable(this.personnelContractService.findPersonnelContracts(request, personnelContract));


        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelContract", exceptionMessage = "新增PersonnelContract失败")
    @PostMapping("personnelContract/add")
    @ResponseBody
    @RequiresPermissions("personnelContract:add")
    public FebsResponse addPersonnelContract(@Valid PersonnelContract personnelContract){
        this.personnelContractService.createPersonnelContract(personnelContract);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelContract", exceptionMessage = "删除PersonnelContract失败")
    @GetMapping("personnelContract/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelContract:delete")
    public FebsResponse deletePersonnelContract(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelContractService.deletePersonnelContract(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelContract", exceptionMessage = "修改PersonnelContract失败")
    @PostMapping("personnelContract/update")
    @ResponseBody
    @RequiresPermissions("personnelContract:update")
    public FebsResponse updatePersonnelContract(PersonnelContract personnelContract) throws ParseException {
        this.personnelContractService.updatePersonnelContract(personnelContract);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "导出PersonnelContract", exceptionMessage = "导出Excel失败")
    @GetMapping("personnelContract/excel")
    @RequiresPermissions("personnelContract:export")
    public void export(QueryRequest queryRequest, PersonnelContract personnelContract, HttpServletResponse response) {
        List<PersonnelContract> personnelContracts = this.personnelContractService.findPersonnelContracts(queryRequest, personnelContract).getRecords();
        ExcelKit.$Export(PersonnelContract.class, response)
                .downXlsx(personnelContracts, false);
    }
    @ControllerEndpoint(operation = "修改PersonnelContract", exceptionMessage = "修改PersonnelContract失败")
    @PostMapping("personnelContractTips/update/")
    @ResponseBody
    @RequiresPermissions("personnelContract:update")
    public FebsResponse updateContractTipsState(@RequestParam String name){
        this.personnelContractService.updateContractTipsState(StringUtils.split(name, Strings.COMMA));
        return new FebsResponse().success();
    }


}

package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelRewardPunish;
import com.erp.personnel.service.IPersonnelRewardPunishService;
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
 * 奖罚记录 Controller
 *
 * @author qiufeng
 * @date 2021-09-25 14:06:53
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelRewardPunishController extends BaseController {

    private final IPersonnelRewardPunishService personnelRewardPunishService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "personnelRewardPunish")
    public String personnelRewardPunishIndex(){
        return FebsUtil.view("personnelRewardPunish/personnelRewardPunish");
    }

    @GetMapping("personnelRewardPunish")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:list")
    public FebsResponse getAllPersonnelRewardPunishs(PersonnelRewardPunish personnelRewardPunish) {
        return new FebsResponse().success().data(personnelRewardPunishService.findPersonnelRewardPunishs(personnelRewardPunish));
    }

    @GetMapping("personnelRewardPunish/list")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:list")
    public FebsResponse personnelRewardPunishList(QueryRequest request, PersonnelRewardPunish personnelRewardPunish) {
        Map<String, Object> dataTable = getDataTable(this.personnelRewardPunishService.findPersonnelRewardPunishs(request, personnelRewardPunish));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增PersonnelRewardPunish", exceptionMessage = "新增PersonnelRewardPunish失败")
    @PostMapping("personnelRewardPunish/add")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:add")
    public FebsResponse addPersonnelRewardPunish(@Valid PersonnelRewardPunish personnelRewardPunish) throws ParseException {
        this.personnelRewardPunishService.createPersonnelRewardPunish(personnelRewardPunish);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelRewardPunish", exceptionMessage = "删除PersonnelRewardPunish失败")
    @GetMapping("personnelRewardPunish/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:delete")
    public FebsResponse deletePersonnelRewardPunish(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelRewardPunishService.deletePersonnelRewardPunish(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelRewardPunish", exceptionMessage = "修改PersonnelRewardPunish失败")
    @PostMapping("personnelRewardPunish/update")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:update")
    public FebsResponse updatePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish) {
        this.personnelRewardPunishService.updatePersonnelRewardPunish(personnelRewardPunish);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelRewardPunish", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelRewardPunish/excel")
    @ResponseBody
    @RequiresPermissions("personnelRewardPunish:export")
    public void export(QueryRequest queryRequest, PersonnelRewardPunish personnelRewardPunish, HttpServletResponse response) {
        List<PersonnelRewardPunish> personnelRewardPunishs = this.personnelRewardPunishService.findPersonnelRewardPunishs(queryRequest, personnelRewardPunish).getRecords();
        ExcelKit.$Export(PersonnelRewardPunish.class, response).downXlsx(personnelRewardPunishs, false);
    }
}

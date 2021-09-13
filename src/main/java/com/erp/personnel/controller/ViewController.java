package com.erp.personnel.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.monitor.helper.FebsActuatorHelper;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.service.IPersonnelArchivesService;
import com.erp.personnel.service.IPersonnelParametersService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author qiufeng
 */
@Controller("personnelView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "personnel")
@RequiredArgsConstructor
public class ViewController {

    private final FebsActuatorHelper actuatorHelper;

    private final IPersonnelParametersService personnelParametersService;


    private final IPersonnelArchivesService personnelArchivesService;

    /* 人事参数模块开始 */
    @GetMapping("personnelParameters")
    @RequiresPermissions("personnelParameters:view")
    public String personnelParametersIndex(){
        return FebsUtil.view("personnel/parameters");
    }

    @GetMapping("personnelParameters/add")
    @RequiresPermissions("personnelParameters:add")
    public String systemUserAdd() {
        return FebsUtil.view("personnel/parametersAdd");
    }

    @GetMapping("personnelParameters/update/{id}")
    @RequiresPermissions("personnelParameters:update")
    public String systemUserUpdate(@PathVariable Long id, Model model) {
        resolvePersonnelParametersModel(id, model, false);
        return FebsUtil.view("personnel/parametersUpdate");
    }

    private void resolvePersonnelParametersModel(Long id, Model model, Boolean transform) {
        PersonnelParameters parameters = personnelParametersService.findById(id);
        model.addAttribute("parameters", parameters);
    }
    /* 人事参数模块结束 */

    /* 员工档案模块开始 */
    @GetMapping("personnelArchives")
    @RequiresPermissions("personnelArchives:view")
    public String personnelArchivesIndex(){
        return FebsUtil.view("archives/archivesList");
    }


    @GetMapping("personnelArchives/add")
    @RequiresPermissions("personnelArchives:add")
    public String archivesAdd(Model model) {

        List<PersonnelParameters> technical  = personnelParametersService.queryTechnical();
        model.addAttribute("technical",technical);

        List<PersonnelParameters> education  = personnelParametersService.queryEducation();
        model.addAttribute("education",education);


        return FebsUtil.view("archives/archivesAdd");
    }

    @GetMapping("personnelArchives/update/{userId}")
    @RequiresPermissions("personnelArchives:update")
    public String personnelArchivesUpdate(@PathVariable Long userId, Model model) {
        //查询技术级别
        List<PersonnelParameters> technical  = personnelParametersService.queryTechnical();
        model.addAttribute("technical",technical);

        //查询岗位信息
        List<PersonnelParameters> position  = personnelParametersService.queryPosition();
        model.addAttribute("position",position);
        //查询岗位信息
        List<PersonnelParameters> duties  = personnelParametersService.queryDuties();
        model.addAttribute("duties",duties);
        //查询学历信息
        List<PersonnelParameters> education  = personnelParametersService.queryEducation();
        model.addAttribute("education",education);
        personnelArchivesParametersModel(userId, model, false);
        return FebsUtil.view("archives/archivesUpdate");
    }

    private void personnelArchivesParametersModel(Long userId, Model model, Boolean transform) {
        PersonnelArchives archives = personnelArchivesService.findArchivesById(userId);

        model.addAttribute("archives", archives);

        if (archives.getEntryDate() != null) {
            model.addAttribute("entryDate", DateUtil.getDateFormat(archives.getEntryDate(), DateUtil.FULL_TIME_SPLIT));
        } if (archives.getBirthdate() != null) {
            model.addAttribute("birthDate", DateUtil.getDateFormat(archives.getBirthdate(), DateUtil.FULL_TIME_SPLIT));
        } if (archives.getQuitDate() != null) {
            model.addAttribute("quitDate", DateUtil.getDateFormat(archives.getQuitDate(), DateUtil.FULL_TIME_SPLIT));
        } if (archives.getPositiveDates() != null) {
            model.addAttribute("positiveDates", DateUtil.getDateFormat(archives.getPositiveDates(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    /* 员工档案模块结束 */



}

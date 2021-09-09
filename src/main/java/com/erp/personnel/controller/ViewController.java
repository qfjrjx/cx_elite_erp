package com.erp.personnel.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.JsonResult;
import com.erp.common.utils.FebsUtil;
import com.erp.monitor.helper.FebsActuatorHelper;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.service.IPersonnelParametersService;
import com.erp.personnel.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiufeng
 */
@Controller("personnelView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "personnel")
@RequiredArgsConstructor
public class ViewController {

    private final FebsActuatorHelper actuatorHelper;

    private final IPersonnelParametersService personnelParametersService;

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



    /* 员工档案模块结束 */



}

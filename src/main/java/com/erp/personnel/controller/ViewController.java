package com.erp.personnel.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.monitor.endpoint.FebsMetricsEndpoint;
import com.erp.monitor.entity.JvmInfo;
import com.erp.monitor.entity.ServerInfo;
import com.erp.monitor.entity.TomcatInfo;
import com.erp.monitor.helper.FebsActuatorHelper;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.service.IPersonnelParametersService;
import com.erp.system.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author MrBird
 */
@Controller("personnelView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "personnel")
@RequiredArgsConstructor
public class ViewController {

    private final FebsActuatorHelper actuatorHelper;

    private final IPersonnelParametersService personnelParametersService;

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

}

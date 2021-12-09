package com.erp.enterprise.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.erp.enterprise.service.IEnterpriseResourcesParametersService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiufeng
 */
@Controller("enterpriseView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "enterprise")
@RequiredArgsConstructor
public class ViewController {

    //参数设置表 Service接口
    private final IEnterpriseResourcesParametersService enterpriseResourcesParametersService;

    /*企业管理模块开始*/
    /*公共资源-参数设置列表*/
    @GetMapping("enterpriseResourcesParameters/list")
    @RequiresPermissions("enterpriseResourcesParameters:view")
    public String resourcesParameterIndex(){
        return FebsUtil.view("resourcesParameter/resourcesParameterList");
    }

    /*公共资源-参数设置添加*/
    @GetMapping("enterpriseResourcesParameters/add")
    @RequiresPermissions("enterpriseResourcesParameters:add")
    public String resourcesParameterAdd() {
        return FebsUtil.view("resourcesParameter/resourcesParameterAdd");
    }
    /*公共资源-参数设置修改*/
    @GetMapping("enterpriseResourcesParameters/update/{id}")
    @RequiresPermissions("enterpriseResourcesParameters:update")
    public String  resourcesParameterUpdate(@PathVariable Long id, Model model) {
        resourcesParameterModel(id, model, false);
        return FebsUtil.view("resourcesParameter/resourcesParameterUpdate");
    }
    /*公共资源-参数设置修改回填*/
    private void resourcesParameterModel(Long id, Model model, Boolean transform) {
        EnterpriseResourcesParameters enterpriseResourcesParameters = enterpriseResourcesParametersService.resourcesParameterById(id);
        model.addAttribute("enterpriseResourcesParameters", enterpriseResourcesParameters);
    }
    //企业管理模块结束
}

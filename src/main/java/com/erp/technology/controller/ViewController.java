package com.erp.technology.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiufeng
 */
@Controller("technologyView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "technology")
@RequiredArgsConstructor
public class ViewController {

    /* 人事参数模块开始 */
    @GetMapping("technologyProductCategory/list")
    @RequiresPermissions("technologyProductCategory:view")
    public String technologyProductCategoryIndex(){
        return FebsUtil.view("technology/technologyList");
    }
    //人事参数添加
   /* @GetMapping("personnelParameters/add")
    @RequiresPermissions("personnelParameters:add")
    public String systemUserAdd() {
        return FebsUtil.view("personnel/parametersAdd");
    }*/
    //人事参数修改
   /* @GetMapping("personnelParameters/update/{id}")
    @RequiresPermissions("personnelParameters:update")
    public String systemUserUpdate(@PathVariable Long id, Model model) {
        resolvePersonnelParametersModel(id, model, false);
        return FebsUtil.view("personnel/parametersUpdate");
    }*/
    //人事参数修改回填
    /*private void resolvePersonnelParametersModel(Long id, Model model, Boolean transform) {
        PersonnelParameters parameters = personnelParametersService.findById(id);
        model.addAttribute("parameters", parameters);
    }*/
    /* 人事参数模块结束 */
}

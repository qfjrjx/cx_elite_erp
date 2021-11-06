package com.erp.technology.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.service.ITechnologyProductCategoryService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiufeng
 */
@Controller("technologyView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "technology")
@RequiredArgsConstructor
public class ViewController {

    //产品类别表 Service接口
    private final ITechnologyProductCategoryService technologyProductCategoryService;

    /* 产品类别模块开始 */
    @GetMapping("technologyProductCategory/list")
    @RequiresPermissions("technologyProductCategory:view")
    public String technologyProductCategoryIndex(){
        return FebsUtil.view("technology/technologyList");
    }
    //产品类别添加
    @GetMapping("technologyProductCategory/add")
    @RequiresPermissions("technologyProductCategory:add")
    public String systemUserAdd() {
        return FebsUtil.view("technology/technologyAdd");
    }
    //产品类别修改
    @GetMapping("technologyProductCategory/update/{id}")
    @RequiresPermissions("technologyProductCategory:update")
    public String technologyProductCategoryUpdate(@PathVariable Long id, Model model) {
        technologyProductCategoryModel(id, model, false);
        return FebsUtil.view("technology/technologyUpdate");
    }
    //产品类别修改回填
    private void technologyProductCategoryModel(Long id, Model model, Boolean transform) {
        TechnologyProductCategory technologyProductCategory = technologyProductCategoryService.findTechnologyById(id);
        model.addAttribute("technologyProductCategory", technologyProductCategory);
    }
    /* 产品类别模块结束 */
}

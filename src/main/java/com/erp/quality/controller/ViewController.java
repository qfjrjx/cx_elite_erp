package com.erp.quality.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.purchase.entity.PurchaseInspectionSchedule;
import com.erp.purchase.service.IPurchaseInspectionService;
import com.erp.quality.entity.QualityParameter;
import com.erp.quality.service.IQualityParameterService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

/**
 * @author wangchuang
 */
@Controller("qualityView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "quality")
@RequiredArgsConstructor
public class ViewController {

    //品质参数service接口
    private final IQualityParameterService qualityParameterService;
    //品质外检service接口
    private final IPurchaseInspectionService purchaseInspectionService;

    /*品质参数开始*/

    //品质参数查询
    @GetMapping("qualityParameter/list")
    @RequiresPermissions("qualityParameter:view")
    public String qualityParameterIndex(){
        return FebsUtil.view("qualityParameter/qualityParameterList");
    }

    //品质参数新增
    @GetMapping("qualityParameter/add")
    @RequiresPermissions("qualityParameter:add")
    public String qualityParameterAdd(){
        return FebsUtil.view("qualityParameter/qualityParameterAdd");
    }

    //品质参数修改
    @GetMapping("qualityParameter/update/{id}")
    @RequiresPermissions("qualityParameter:update")
    public String qualityParameterUpdate(@PathVariable Long id, Model model) throws ParseException {
        qualityParameterModel(id, model, false);
        return FebsUtil.view("qualityParameter/qualityParameterUpdate");
    }

    private void qualityParameterModel(Long id, Model model, Boolean transform) throws ParseException {
        QualityParameter qualityParameter = qualityParameterService.qualityParameterId(id);
        model.addAttribute("qualityParameter", qualityParameter);
    }

    /*品质参数结束*/

    /*品质外检开始*/

    //品质外检查询
    @GetMapping("qualityInspection/list")
    @RequiresPermissions("qualityInspection:view")
    public String qualityInspectionIndex(){
        return FebsUtil.view("qualityInspection/qualityInspectionList");
    }

    //品质外检修改
    @GetMapping("qualityInspection/inspection/{ids}")
    @RequiresPermissions("qualityParameter:update")
    public String qualityInspectionInspection(@PathVariable Long ids, Model model) throws ParseException {
        qualityInspectionModel(ids, model, false);
        return FebsUtil.view("qualityInspection/qualityInspectionInspection");
    }

    private void qualityInspectionModel(Long ids, Model model, Boolean transform) throws ParseException {
        PurchaseInspectionSchedule purchaseInspectionSchedule = purchaseInspectionService.qualityInspectionId(ids);
        model.addAttribute("purchaseInspectionSchedule", purchaseInspectionSchedule);
    }

    /*品质外检结束*/
}

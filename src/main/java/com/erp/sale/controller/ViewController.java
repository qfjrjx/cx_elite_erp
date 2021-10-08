package com.erp.sale.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.service.ISaleBusinessPersonnelService;
import com.erp.sale.service.ISaleParametersService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qiufeng
 */
@Controller("saleParametersView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "sale")
@RequiredArgsConstructor
public class ViewController {
    //销售参数Service接口
    private final ISaleParametersService saleParametersService;
    //业务人员Service接口
    private final ISaleBusinessPersonnelService saleBusinessPersonnelService;

    /* 销售参数模块开始 */
    @GetMapping("saleParameters")
    @RequiresPermissions("saleParameters:view")
    public String saleParametersIndex(){
        return FebsUtil.view("salesParameters/salesParametersList");
    }
    //销售参数添加
    @GetMapping("saleParameters/add")
    @RequiresPermissions("saleParameters:add")
    public String systemUserAdd() {
        return FebsUtil.view("salesParameters/salesParametersAdd");
    }
    //销售参数修改
    @GetMapping("saleParameters/update/{id}")
    @RequiresPermissions("saleParameters:update")
    public String systemUserUpdate(@PathVariable Long id, Model model) {
        resolveSalesParametersModel(id, model, false);
        return FebsUtil.view("salesParameters/salesParametersUpdate");
    }
    //销售参数修改回填
    private void resolveSalesParametersModel(Long id, Model model, Boolean transform) {
        SaleParameters saleParameters = saleParametersService.findSaleParametersById(id);
        model.addAttribute("saleParameters", saleParameters);
    }
    /* 销售参数模块结束 */

    @GetMapping("saleBusinessPersonnel")
    @RequiresPermissions("saleBusinessPersonnel:view")
    public String saleBusinessPersonnelIndex(){
        return FebsUtil.view("businessPersonnel/businessPersonnelList");
    }

    //业务人员新增
    @GetMapping("saleBusinessPersonnel/add")
    @RequiresPermissions("saleBusinessPersonnel:add")
    public String saleBusinessPersonnelAdd(Model model) {
        //查询区域信息
        List<SaleBusinessPersonnel> saleBusinessPersonnel  = saleParametersService.querySaleBusinessPersonnel();
        model.addAttribute("saleBusinessPersonnel",saleBusinessPersonnel);

        return FebsUtil.view("businessPersonnel/businessPersonnelAdd");
    }


    //业务人员修改
    @GetMapping("saleBusinessPersonnel/update/{id}")
    @RequiresPermissions("saleBusinessPersonnel:update")
    public String saleBusinessPersonnelUpdate(@PathVariable Long id, Model model) {
        saleBusinessPersonnelModel(id, model, false);
        return FebsUtil.view("businessPersonnel/businessPersonnelUpdate");
    }
    //业务人员修改回填
    private void saleBusinessPersonnelModel(Long id, Model model, Boolean transform) {
        //查询区域信息
        List<SaleBusinessPersonnel> saleBusiness  = saleParametersService.querySaleBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);

        SaleBusinessPersonnel saleBusinessPersonnel = saleBusinessPersonnelService.findSaleBusinessPersonnelById(id);
        model.addAttribute("saleBusinessPersonnel", saleBusinessPersonnel);
    }

    /* 员工档案查询开始 */
    @GetMapping("employeeFileUpdate")
    public String employeeFileIndex(){
        return FebsUtil.view("businessPersonnel/employeeFileUpdate");
    }
    /* 员工档案结束 */
}

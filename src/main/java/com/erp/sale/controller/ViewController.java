package com.erp.sale.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.entity.SaleCustomerProfile;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.service.ISaleBusinessPersonnelService;
import com.erp.sale.service.ISaleCustomerProfileService;
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
    //客户档案Service接口
    private final ISaleCustomerProfileService saleCustomerProfileService;

    /* 销售参数模块开始 */
    //跳转到销售参数页面
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


    /* 业务人员模块开始 */
    //跳转到业务人员页面
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
    /* 业务人员模块结束 */

    /* 员工档案查询开始 */
    @GetMapping("employeeFileUpdate")
    public String employeeFileIndex(){
        return FebsUtil.view("businessPersonnel/employeeFileUpdate");
    }
    /* 员工档案结束 */

    /* 客户档案模块结束 */
    //跳转到客户档案页面
    @GetMapping("saleCustomerProfile")
    @RequiresPermissions("saleCustomerProfile:view")
    public String saleCustomerProfileIndex(Model model){
        //查询业务员信息
        List<SaleBusinessPersonnel> saleBusiness  = saleBusinessPersonnelService.queryBusinessPersonnel();
        model.addAttribute("saleBusiness",saleBusiness);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileList");
    }
    //客户档案添加
    @GetMapping("saleCustomerProfile/add")
    @RequiresPermissions("saleCustomerProfile:add")
    public String saleCustomerProfileAdd() {
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileAdd");
    }
    //业务人员修改
    @GetMapping("saleCustomerProfile/update/{id}")
    @RequiresPermissions("saleCustomerProfile:update")
    public String saleCustomerProfileUpdate(@PathVariable Long id, Model model) {
        saleCustomerProfileModel(id, model, false);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileUpdate");
    }
    //业务人员修改回填
    private void saleCustomerProfileModel(Long id, Model model, Boolean transform) {

        SaleCustomerProfile saleCustomerProfile = saleCustomerProfileService.findSaleCustomerProfileById(id);
        model.addAttribute("saleCustomerProfile", saleCustomerProfile);
    }
    //业务人员查阅
    @GetMapping("saleCustomerProfileConsult/detail/{id}")
    @RequiresPermissions("saleCustomerProfile:view")
    public String saleCustomerProfileConsult(@PathVariable Long id, Model model) {
        saleCustomerProfileConsultModel(id, model, false);
        return FebsUtil.view("saleCustomerProfile/saleCustomerProfileConsult");
    }
    //业务人员查阅回填
    private void saleCustomerProfileConsultModel(Long id, Model model, Boolean transform) {

        SaleCustomerProfile saleCustomerProfile = saleCustomerProfileService.findSaleCustomerProfileById(id);
        model.addAttribute("saleCustomerProfile", saleCustomerProfile);
    }
    /* 客户档案模块结束 */
    //双击跳到客户档案页面
    @GetMapping("saleCustomerPersonnelList")
    @RequiresPermissions("saleBusinessPersonnel:view")
    public String saleCustomerPersonnelList() {
        return FebsUtil.view("saleCustomerProfile/saleCustomerPersonnelList");
    }


}

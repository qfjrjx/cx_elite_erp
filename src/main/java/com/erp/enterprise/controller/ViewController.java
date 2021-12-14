package com.erp.enterprise.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.erp.enterprise.service.IEnterpriseDocumentAnnouncementService;
import com.erp.enterprise.service.IEnterpriseResourcesParametersService;
import com.erp.sale.entity.SaleBusinessPersonnel;
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
@Controller("enterpriseView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "enterprise")
@RequiredArgsConstructor
public class ViewController {

    //参数设置表 Service接口
    private final IEnterpriseResourcesParametersService enterpriseResourcesParametersService;
    //公文公告表 Service接口
    private final IEnterpriseDocumentAnnouncementService enterpriseDocumentAnnouncementService;

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

    /*公共资源-公文公告列表*/
    @GetMapping("enterpriseDocumentAnnouncement/list")
    @RequiresPermissions("enterpriseDocumentAnnouncement:view")
    public String documentAnnouncementIndex(Model model){
        //查询参数设置里的公文类型信息
        List<EnterpriseResourcesParameters> resourcesParameters  = enterpriseResourcesParametersService.queryEnterpriseResourcesParameters(EnterpriseResourcesParameters.DOCUMENT_TYPE);
        model.addAttribute("resourcesParameters",resourcesParameters);
        return FebsUtil.view("documentAnnouncement/documentAnnouncementList");
    }
    /*公共资源-公文公告添加*/
    @GetMapping("enterpriseDocumentAnnouncement/add")
    @RequiresPermissions("enterpriseDocumentAnnouncement:add")
    public String documentAnnouncementAdd(Model model) {
        //查询参数设置里的公文类型信息
        List<EnterpriseResourcesParameters> enterpriseResourcesParameters  = enterpriseResourcesParametersService.queryEnterpriseResourcesParameters(EnterpriseResourcesParameters.DOCUMENT_TYPE);
        model.addAttribute("enterpriseResourcesParameters",enterpriseResourcesParameters);
        return FebsUtil.view("documentAnnouncement/documentAnnouncementAdd");
    }

    /*公共资源-公文公告修改*/
    @GetMapping("enterpriseDocumentAnnouncement/update/{id}")
    @RequiresPermissions("enterpriseDocumentAnnouncement:update")
    public String documentAnnouncementUpdate(@PathVariable Long id, Model model) {
        documentAnnouncementModel(id, model, false);
        //查询参数设置里的公文类型信息
        List<EnterpriseResourcesParameters> resourcesParameters  = enterpriseResourcesParametersService.queryEnterpriseResourcesParameters(EnterpriseResourcesParameters.DOCUMENT_TYPE);
        model.addAttribute("resourcesParameters",resourcesParameters);
        return FebsUtil.view("documentAnnouncement/documentAnnouncementUpdate");
    }
    /*公共资源-公文公告修改回填*/
    private void documentAnnouncementModel(Long id, Model model, Boolean transform) {
        EnterpriseDocumentAnnouncement documentAnnouncement = enterpriseDocumentAnnouncementService.documentAnnouncementById(id);
        model.addAttribute("documentAnnouncement", documentAnnouncement);
        if (documentAnnouncement.getCreationTime() != null) {
            model.addAttribute("creationTime", DateUtil.getDateFormat(documentAnnouncement.getCreationTime(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    /*公共资源-通讯录*/
    @GetMapping("employeeAddressBook/list")
    @RequiresPermissions("employeeAddressBook:view")
    public String employeeAddressBookIndex(){
        return FebsUtil.view("employeeAddressBook/employeeAddressList");
    }

    //企业管理模块结束
}

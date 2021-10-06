package com.erp.personnel.controller;

import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.monitor.helper.FebsActuatorHelper;
import com.erp.personnel.entity.*;
import com.erp.personnel.service.*;
import com.erp.system.entity.User;
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

    //人事参数表 Service接口
    private final IPersonnelParametersService personnelParametersService;
    //用户表 Service接口
    private final IPersonnelArchivesService personnelArchivesService;
    //领取记录表 Service接口
    private final IPersonnelReceiveService personnelReceiveService;
    //员工合同表 Service接口
    private final IPersonnelContractService personnelContractService;
    //宿舍管理表 Service接口
    private final IPersonnelDormitoryService personnelDormitoryService;
    //宿舍人员入住信息表 Service接口
    private final IPersonnelDormitoryInformationService personnelDormitoryInformationService;

    /* 人事参数模块开始 */
    @GetMapping("personnelParameters")
    @RequiresPermissions("personnelParameters:view")
    public String personnelParametersIndex(){
        return FebsUtil.view("personnel/parameters");
    }
    //人事参数添加
    @GetMapping("personnelParameters/add")
    @RequiresPermissions("personnelParameters:add")
    public String systemUserAdd() {
        return FebsUtil.view("personnel/parametersAdd");
    }
    //人事参数修改
    @GetMapping("personnelParameters/update/{id}")
    @RequiresPermissions("personnelParameters:update")
    public String systemUserUpdate(@PathVariable Long id, Model model) {
        resolvePersonnelParametersModel(id, model, false);
        return FebsUtil.view("personnel/parametersUpdate");
    }
    //人事参数修改回填
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
    @GetMapping("personnelArchives/detail/{userId}")
    @RequiresPermissions("personnelArchives:view")
    public String personnelArchivesDetail(@PathVariable Long userId, Model model) {
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
        return FebsUtil.view("archives/archivesDetail");
    }

    private void personnelArchivesParametersModel(Long userId, Model model, Boolean transform) {
        PersonnelArchives archives = personnelArchivesService.findArchivesById(userId);
        String sex = archives.getGender();

        if (sex.equals("1")) {
            archives.setGender("男");
        } if (sex.equals("2")) {
            archives.setGender("女");
        }
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

    /* 员工领取记录开始*/
    @GetMapping("personnelReceive")
    @RequiresPermissions("personnelReceive:view")
    public String personnelReceiveIndex(){
        return FebsUtil.view("receive/receiveList");
    }


    @GetMapping("personnelReceive/add")
    @RequiresPermissions("personnelReceive:add")
    public String personnelReceiveAdd(){
        return FebsUtil.view("receive/receiveAdd");
    }
    @GetMapping("receiveArchives")
    @RequiresPermissions("personnelArchives:view")
    public String receiveArchivesIndex(){
        return FebsUtil.view("receive/receiveArchivesList");
    }

    @GetMapping("personnelReceive/update/{id}")
    @RequiresPermissions("personnelReceive:update")
    public String personnelReceiveUpdate(@PathVariable Long id, Model model) {
        receiveModel(id, model, false);
        return FebsUtil.view("receive/receiveUpdate");
    }

    private void receiveModel(Long id, Model model, Boolean transform) {
        PersonnelReceive receive = personnelReceiveService.findById(id);
        model.addAttribute("receives", receive);
    }
    /* 员工领取记录模块结束 */

    /* 员工合同模块开始 */

    @GetMapping("personnelContract")
    @RequiresPermissions("personnelContract:list")
    public String personnelContractIndex(){
        return FebsUtil.view("contract/contractList");
    }


    @GetMapping("personnelContract/add")
    @RequiresPermissions("personnelContract:add")
    public String personnelContractAdd(){
        return FebsUtil.view("contract/contractAdd");
    }


    @GetMapping("personnelContract/update/{id}")
    @RequiresPermissions("personnelContract:update")
    public String personnelContractUpdate(@PathVariable Long id, Model model) {
        personnelContractModel(id, model, false);
        return FebsUtil.view("contract/contractUpdate");
    }

    private void personnelContractModel(Long id, Model model, Boolean transform) {
        PersonnelContract contract = personnelContractService.findContractById(id);
        model.addAttribute("contracts", contract);

        if (contract.getSignedDate()!= null) {
            model.addAttribute("signedDate", DateUtil.getDateFormat(contract.getSignedDate(), DateUtil.FULL_TIME_SPLIT));
        } if (contract.getExpireDate() != null) {
            model.addAttribute("expireDate", DateUtil.getDateFormat(contract.getExpireDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    /* 员工合同模块结束 */


    /* 员工宿舍模块开始 */
    //宿舍管理列表
    @GetMapping("personnelDormitory")
    @RequiresPermissions("personnelDormitory:list")
    public String personnelDormitoryIndex(){
        return FebsUtil.view("dormitory/dormitoryList");
    }
    //添加宿舍管理列表
    @GetMapping("personnelDormitory/add")
    @RequiresPermissions("personnelDormitory:add")
    public String personnelDormitoryAdd(){
        return FebsUtil.view("dormitory/dormitoryAdd");
    }
    //宿舍管理列表修改
    @GetMapping("personnelDormitory/update/{id}")
    @RequiresPermissions("personnelDormitory:update")
    public String personnelDormitoryUpdate(@PathVariable Long id, Model model) {
        personnelDormitoryModel(id, model, false);
        return FebsUtil.view("dormitory/dormitoryUpdate");
    }
    //宿舍管理列表修改回填
    private void personnelDormitoryModel(Long id, Model model, Boolean transform) {
        PersonnelDormitory dormitory = personnelDormitoryService.findDormitoryById(id);
        model.addAttribute("dormitory", dormitory);
    }

    //宿舍入住人员列表
    @GetMapping("personnelDormitoryInformation")
    @RequiresPermissions("personnelDormitoryInformation:list")
    public String personnelDormitoryInformationIndex(){
        return FebsUtil.view("dormitoryInformation/dormitoryInformationList");
    }
    //添加入住宿舍人员列表
    @GetMapping("personnelDormitoryInformation/add")
    @RequiresPermissions("personnelDormitoryInformation:add")
    public String personnelDormitoryInformationAdd(){
        return FebsUtil.view("dormitoryInformation/dormitoryInformationAdd");
    }
    //宿舍人员管理列表修改
    @GetMapping("personnelDormitoryInformation/update/{id}")
    @RequiresPermissions("personnelDormitoryInformation:update")
    public String personnelDormitoryInformationUpdate(@PathVariable Long id, Model model) {
        personnelDormitoryInformationModel(id, model, false);
        return FebsUtil.view("dormitoryInformation/dormitoryInformationUpdate");
    }
    //宿舍人员管理列表修改回填
    private void personnelDormitoryInformationModel(Long id, Model model, Boolean transform) {
        PersonnelDormitoryInformation dormitoryInformation = personnelDormitoryInformationService.findDormitoryInformationById(id);
        String dormitoryLocation = dormitoryInformation.getDormitoryLocation();
        switch (dormitoryLocation) {
            case PersonnelDormitoryInformation.DORMITORYLOCATION_MALE:
                dormitoryInformation.setDormitoryLocation("东宿舍");
                break;
            case PersonnelDormitoryInformation.DORMITORYLOCATION_FEMALE:
                dormitoryInformation.setDormitoryLocation("西宿舍");
                break;
            default:
                break;
        }
        model.addAttribute("dormitoryInformation", dormitoryInformation);
        if (dormitoryInformation.getCheckInTime() != null) {
            model.addAttribute("checkInTime", DateUtil.getDateFormat(dormitoryInformation.getCheckInTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }
    /* 员工宿舍模块结束 */

    /* 调岗记录模块开始 */
    @GetMapping("personnelMobility")
    @RequiresPermissions("personnelMobility:list")
    public String personnelMobilityIndex(){
        return FebsUtil.view("mobility/mobilityList");
    }

    /* 添加调岗记录 */
    @GetMapping("personnelMobility/add")
    @RequiresPermissions("personnelMobility:add")
    public String personnelMobilityAdd(){
        return FebsUtil.view("mobility/mobilityAdd");
    }

    /* 调岗记录模块结束 */



    /* 调薪记录模块开始 */
    @GetMapping("personnelSalaryChange")
    @RequiresPermissions("personnelSalaryChange:list")
    public String personnelSalaryIndex(){
        return FebsUtil.view("salary/salaryList");
    }


    /* 添加调薪记录 */
    @GetMapping("personnelSalaryChange/add")
    @RequiresPermissions("personnelSalaryChange:add")
    public String personnelSalaryChangeAdd(){
        return FebsUtil.view("salary/salaryAdd");
    }

    /* 调薪记录模块结束 */

    /* 奖罚记录模块开始 */
    @GetMapping("personnelRewardPunish")
    @RequiresPermissions("personnelRewardPunish:list")
    public String personnelRewardPunishIndex(){
        return FebsUtil.view("rewardPunish/rewardPunishList");
    }


    /* 添加奖罚记录 */
    @GetMapping("personnelRewardPunish/add")
    @RequiresPermissions("personnelRewardPunish:add")
    public String personnelRewardPunishAdd(){
        return FebsUtil.view("rewardPunish/rewardPunishAdd");
    }

    /* 奖罚记录模块结束 */

    /* 员工档案查询开始 */
    @GetMapping("employeeFile")
    public String employeeFileIndex(){
        return FebsUtil.view("mobility/employeeFile");
    }
    /* 员工档案结束 */

    /* 员工宿舍列表开始 */
    @GetMapping("dormitoryList")
    public String dormitoryIndex(){
        return FebsUtil.view("dormitoryInformation/dormitoryBackfillList");
    }
    /* 员工宿舍列表结束 */






}

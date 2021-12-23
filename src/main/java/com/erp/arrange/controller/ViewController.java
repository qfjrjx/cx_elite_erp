package com.erp.arrange.controller;

import com.erp.arrange.entity.WorkArrange;
import com.erp.arrange.service.IWorkArrangeService;
import com.erp.common.entity.FebsConstant;
import com.erp.common.utils.DateUtil;
import com.erp.common.utils.FebsUtil;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.service.IFinanceParametersService;
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
@Controller("arrangeView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "arrange")
@RequiredArgsConstructor
public class ViewController {

    private final IWorkArrangeService workArrangeService;

    /*工作安排模块开始*/
    /*工作安排列表查询*/
    @GetMapping("workArrange/list")
    @RequiresPermissions("workArrange:view")
    public String workArrangeIndex(){
        return FebsUtil.view("arrange/workArrangeList");
    }

    //工作安排添加
    @GetMapping("workArrange/add")
    @RequiresPermissions("workArrange:add")
    public String workArrangeAdd() {
        return FebsUtil.view("arrange/workArrangeAdd");
    }
    //发起人  人员选择列表
    @GetMapping("arrangeInitiator/list")
    @RequiresPermissions("personnelArchives:view")
    public String arrangeInitiatorIndex(){
        return FebsUtil.view("arrange/arrangeInitiatorList");
    }
    //责任人  人员选择列表
    @GetMapping("arrangeSupervisor/list")
    @RequiresPermissions("personnelArchives:view")
    public String arrangeSupervisorIndex(){
        return FebsUtil.view("arrange/arrangeSupervisorList");
    }
    //监督人  人员选择列表
    @GetMapping("personLiable/list")
    @RequiresPermissions("personnelArchives:view")
    public String personLiableIndex(){
        return FebsUtil.view("arrange/personLiableList");
    }
    //工作安排修改
    @GetMapping("workArrange/update/{id}")
    @RequiresPermissions("workArrange:update")
    public String workArrangeUpdate(@PathVariable Long id, Model model) {
         workArrangeModel(id, model, false);
        return FebsUtil.view("arrange/workArrangeUpdate");
    }
    //工作安排修改回填
    private void workArrangeModel(Long id, Model model, Boolean transform) {
        WorkArrange workArrange = workArrangeService.findWorkArrangeById(id);
        model.addAttribute("workArrange", workArrange);
        if (workArrange.getStartDate() != null) {
            model.addAttribute("startTime", DateUtil.getDateFormat(workArrange.getStartDate(), DateUtil.FULL_TIME_SPLIT));
        }if (workArrange.getEndDate() != null) {
            model.addAttribute("endTime", DateUtil.getDateFormat(workArrange.getEndDate(), DateUtil.FULL_TIME_SPLIT));
        }if (workArrange.getReminderDate() != null) {
            model.addAttribute("reminderTime", DateUtil.getDateFormat(workArrange.getReminderDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }

    //工作安排考核
    @GetMapping("workArrange/assessment/{id}")
    @RequiresPermissions("workArrange:assessment")
    public String workArrangeAssessment(@PathVariable Long id, Model model) {
        workArrangeAssessmentModel(id, model, false);
        return FebsUtil.view("arrange/workArrangeAssessment");
    }
    //工作安排考核回填
    private void workArrangeAssessmentModel(Long id, Model model, Boolean transform) {
        WorkArrange workArrange = workArrangeService.findWorkArrangeById(id);
        model.addAttribute("workArrange", workArrange);
        if (workArrange.getStartDate() != null) {
            model.addAttribute("startTime", DateUtil.getDateFormat(workArrange.getStartDate(), DateUtil.FULL_TIME_SPLIT));
        }if (workArrange.getEndDate() != null) {
            model.addAttribute("endTime", DateUtil.getDateFormat(workArrange.getEndDate(), DateUtil.FULL_TIME_SPLIT));
        }if (workArrange.getReminderDate() != null) {
            model.addAttribute("reminderTime", DateUtil.getDateFormat(workArrange.getReminderDate(), DateUtil.FULL_TIME_SPLIT));
        }
    }
    //工作安排模块结束
}

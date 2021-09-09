package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.JsonResult;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.service.IPersonnelArchivesService;
import com.erp.personnel.util.FileUtil;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表 Controller
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class PersonnelArchivesController extends BaseController {

    private final IPersonnelArchivesService personnelArchivesService;

    @GetMapping("personnelArchives/list")
    @ResponseBody
    @RequiresPermissions("personnelArchives:view")
    public FebsResponse personnelArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        return new FebsResponse().success().data(getDataTable(personnelArchivesService.findPersonnelArchivesList(personnelArchives,request)));
    }

    @ControllerEndpoint(operation = "新增PersonnelArchives", exceptionMessage = "新增PersonnelArchives失败")
    @PostMapping("personnelArchives")
    @ResponseBody
    @RequiresPermissions("personnelArchives:add")
    public FebsResponse addPersonnelArchives(@Valid PersonnelArchives personnelArchives) {
        this.personnelArchivesService.createPersonnelArchives(personnelArchives);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelArchives", exceptionMessage = "删除PersonnelArchives失败")
    @GetMapping("personnelArchives/delete")
    @ResponseBody
    @RequiresPermissions("personnelArchives:delete")
    public FebsResponse deletePersonnelArchives(PersonnelArchives personnelArchives) {
        this.personnelArchivesService.deletePersonnelArchives(personnelArchives);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelArchives", exceptionMessage = "修改PersonnelArchives失败")
    @PostMapping("personnelArchives/update")
    @ResponseBody
    @RequiresPermissions("personnelArchives:update")
    public FebsResponse updatePersonnelArchives(PersonnelArchives personnelArchives) {
        this.personnelArchivesService.updatePersonnelArchives(personnelArchives);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改PersonnelArchives", exceptionMessage = "导出Excel失败")
    @PostMapping("personnelArchives/excel")
    @ResponseBody
    @RequiresPermissions("personnelArchives:export")
    public void export(QueryRequest queryRequest, PersonnelArchives personnelArchives, HttpServletResponse response) {
        List<PersonnelArchives> personnelArchivess = this.personnelArchivesService.findPersonnelArchivess(queryRequest, personnelArchives).getRecords();
        ExcelKit.$Export(PersonnelArchives.class, response).downXlsx(personnelArchivess, false);
    }
    //图片上传功能
    @PostMapping("personnelArchives/uploadFiles")
    @ResponseBody
    public JsonResult uploadFiles(@Valid MultipartFile imgs, HttpServletRequest request) throws Exception{
        String fileName = imgs.getOriginalFilename();
        //设置文件上传路径
        try {
            //获取图片在服务器地址下
            String fangdi= request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort()+"/imgupload/";
            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            FileUtil.uploadFile(imgs.getBytes(), filePath, fileName);

            //获取图片在项目路径下的地址
            String basePath=request.getSession().getServletContext().getRealPath("/");

            FileUtil.uploadFile(imgs.getBytes(), basePath, fileName);

            Map<String,String> map=new HashMap<>();
            map.put("file",fangdi+fileName);
            map.put("name","imgupload/"+fileName);

            return new JsonResult( map, ExecuteResultState.SUCCEED,"成功");
        } catch (Exception e) {
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

    }
}

package com.erp.personnel.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.*;
import com.erp.common.exception.FebsException;
import com.erp.common.utils.FebsUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.service.IPersonnelArchivesService;
import com.erp.personnel.util.FileUtil;
import com.erp.system.entity.User;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public FebsResponse addPersonnelArchives(@Valid PersonnelArchives personnelArchives) throws ParseException {
        this.personnelArchivesService.createPersonnelArchives(personnelArchives);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除PersonnelArchives", exceptionMessage = "删除PersonnelArchives失败")
    @GetMapping("personnelArchives/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("personnelArchives:delete")
    public FebsResponse deletePersonnelArchives(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.personnelArchivesService.deletePersonnelArchives(StringUtils.split(ids, Strings.COMMA));
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

    @GetMapping("personnelArchives/excel")
    @RequiresPermissions("personnelArchives:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, PersonnelArchives personnelArchives, HttpServletResponse response){
        ExcelKit.$Export(PersonnelArchives.class, response)
                .downXlsx(personnelArchivesService.findPersonnelArchivesList(personnelArchives,queryRequest).getRecords(),false);
    }
    //图片上传控制器
    @PostMapping("personnelArchives/uploadFiles")
    @ResponseBody
    public Map uploadPicture(@RequestParam("file")MultipartFile file, HttpServletRequest servletRequest)
            throws IOException {

        Map res = new HashMap();

        //上传文件名
        String name = file.getOriginalFilename();//上传文件的真实名称

        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        // String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母+数字）作为文件名
        String hash = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = hash + suffixName;
        String os = System.getProperty("os.name");
        ////上传文件保存路径
        String filePath="";
        if(os.toLowerCase().startsWith("win")){
            //windows下的路径
            filePath ="d:/pictureUpload/upload";
        }else {
            //linux下的路径
            filePath="/root/pictureUpload/upload";
        }
        File filepath = new File(filePath, fileName);
        System.out.println("随机数文件名称"+filepath.getName());
        System.out.println("文件地址"+filepath);
        //判断路径是否存在，没有就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文档中
        File tempFile = new File(filePath + File.separator + fileName);
        System.out.println("将上传文件保存到一个目标文档中"+tempFile);
        file.transferTo(tempFile);

        // resUrl.put("src", tempFile.getPath());
        res.put("code", "0");
        res.put("msg", "");
        res.put("data", tempFile.getName());
        return res;
    }
}

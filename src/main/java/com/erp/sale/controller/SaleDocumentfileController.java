package com.erp.sale.controller;

import com.erp.common.annotation.ControllerEndpoint;
import com.erp.common.controller.BaseController;
import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.FebsResponse;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.FebsUtil;
import com.erp.sale.entity.SaleDocumentfile;
import com.erp.sale.service.ISaleDocumentfileService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * 上传文件表 Controller
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class SaleDocumentfileController extends BaseController {

    private final ISaleDocumentfileService saleDocumentfileService;

/*    @Autowired
    private DocumentConverter converter;  //用于转换*/

    @GetMapping(FebsConstant.VIEW_PREFIX + "saleDocumentfile")
    public String saleDocumentfileIndex(){
        return FebsUtil.view("saleDocumentfile/saleDocumentfile");
    }

    @GetMapping("saleDocumentfile")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:list")
    public FebsResponse getAllSaleDocumentfiles(SaleDocumentfile saleDocumentfile) {
        return new FebsResponse().success().data(saleDocumentfileService.findSaleDocumentfiles(saleDocumentfile));
    }

    @GetMapping("saleDocumentfile/list")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:list")
    public FebsResponse saleDocumentfileList(QueryRequest request, SaleDocumentfile saleDocumentfile) {
        Map<String, Object> dataTable = getDataTable(this.saleDocumentfileService.findSaleDocumentfiles(request, saleDocumentfile));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增SaleDocumentfile", exceptionMessage = "新增SaleDocumentfile失败")
    @PostMapping("saleDocumentfile")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:add")
    public FebsResponse addSaleDocumentfile(@Valid SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.createSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除SaleDocumentfile", exceptionMessage = "删除SaleDocumentfile失败")
    @GetMapping("saleDocumentfile/delete")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:delete")
    public FebsResponse deleteSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.deleteSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleDocumentfile", exceptionMessage = "修改SaleDocumentfile失败")
    @PostMapping("saleDocumentfile/update")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:update")
    public FebsResponse updateSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.saleDocumentfileService.updateSaleDocumentfile(saleDocumentfile);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改SaleDocumentfile", exceptionMessage = "导出Excel失败")
    @PostMapping("saleDocumentfile/excel")
    @ResponseBody
    @RequiresPermissions("saleDocumentfile:export")
    public void export(QueryRequest queryRequest, SaleDocumentfile saleDocumentfile, HttpServletResponse response) {
        List<SaleDocumentfile> saleDocumentfiles = this.saleDocumentfileService.findSaleDocumentfiles(queryRequest, saleDocumentfile).getRecords();
        ExcelKit.$Export(SaleDocumentfile.class, response).downXlsx(saleDocumentfiles, false);
    }
    //上传控制器
    @PostMapping("document/uploadFile")
    @ResponseBody
    public Map uploadPicture(@RequestParam("file")MultipartFile file, HttpServletRequest servletRequest)
            throws IOException {

        Map res = new HashMap();

        //上传文件名
        String name = file.getOriginalFilename();//上传文件的真实名称
        //以“.”截取文件名称
        int nameOne = name.indexOf(".");
        //从下标0开始截取到“.”
        String contName = name.substring(0,nameOne);
        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        // String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母+数字）作为文件名
        String hash = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = hash + suffixName;
        String os = System.getProperty("os.name");
        ////上传文件保存路径
        String path="";
        if(os.toLowerCase().startsWith("win")){
            //windows下的路径
            path ="d:/pictureUpload/uploadFile";
        }else {
            //linux下的路径
            path="/root/pictureUpload/uploadFile";
        }
        File filePath = new File(path, fileName);
        //System.out.println("随机数文件名称"+filePath.getName());
        //System.out.println("文件地址"+filePath);
        //判断路径是否存在，没有就创建一个
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文档中
        File tempFile = new File(path + File.separator + fileName);
        System.out.println("将上传文件保存到一个目标文档中"+tempFile);
        file.transferTo(tempFile);
        if(file!=null){
            //将上传的文件信息写入数据库
            SaleDocumentfile documentFile=new SaleDocumentfile();
            documentFile.setName(hash);
            documentFile.setFileName(contName);
            documentFile.setSuffix(suffixName);
            documentFile.setPath(path);
            documentFile.setTime(new Timestamp(new Date().getTime()));
            saleDocumentfileService.addDocumentFile(documentFile);

        }
        // resUrl.put("src", tempFile.getPath());
        res.put("code", "0");
        res.put("msg", "");
        //文件名+后缀名
        res.put("data", tempFile.getName());
        return res;
    }
    //上传控制器
    @PostMapping("document/uploadPictureImg")
    @ResponseBody
    public Map uploadPictureImg(@RequestParam("file")MultipartFile file, HttpServletRequest servletRequest)
            throws IOException {

        Map res = new HashMap();

        //上传文件名
        String name = file.getOriginalFilename();//上传文件的真实名称
        //以“.”截取文件名称
        int nameOne = name.indexOf(".");
        //从下标0开始截取到“.”
        String contName = name.substring(0,nameOne);
        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        // String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母+数字）作为文件名
        String hash = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = hash + suffixName;
        String fileNameOne = hash;
        String os = System.getProperty("os.name");
        ////上传文件保存路径
        String path="";
        if(os.toLowerCase().startsWith("win")){
            //windows下的路径
            path ="d:/pictureUpload/uploadFile";
        }else {
            //linux下的路径
            path="/root/pictureUpload/uploadFile";
        }
        File filePath = new File(path, fileName);
        //System.out.println("随机数文件名称"+filePath.getName());
        //System.out.println("文件地址"+filePath);
        //判断路径是否存在，没有就创建一个
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文档中
        File tempFile = new File(path + File.separator + fileName);
        File tempFileOne = new File(path + File.separator + fileNameOne);
        //System.out.println("将上传文件保存到一个目标文档中"+tempFile);
        file.transferTo(tempFile);
        if(file!=null){
            //将上传的文件信息写入数据库
            SaleDocumentfile documentFile=new SaleDocumentfile();
            documentFile.setName(hash);
            documentFile.setFileName(contName);
            documentFile.setSuffix(suffixName);
            documentFile.setPath(path);
            documentFile.setTime(new Timestamp(new Date().getTime()));
            saleDocumentfileService.addDocumentFile(documentFile);

        }
        // resUrl.put("src", tempFile.getPath());
        res.put("code", "0");
        res.put("msg", "");
        //文件名+后缀名
         //System.out.println("文件名称"+tempFile.getName());
        res.put("data", tempFile.getName());
        res.put("dataName", tempFileOne.getName());
        return res;
    }
    @GetMapping("saleDocumentFile/down")
    public void download(HttpServletResponse response,@RequestParam("fileName") String fileName) {
        try {
            SaleDocumentfile documentFile = saleDocumentfileService.findSaleDocumentFileByName(fileName);
            String path = documentFile.getPath();
            String name = documentFile.getName();
            String nameOne = documentFile.getFileName();
            String suffix = documentFile.getSuffix();
            String filePath = path+"/"+name+suffix;
            String filePathOne = path+"/"+nameOne+suffix;
           // String filenames = name+suffix;
           // System.out.println("文件地址"+filePath);
            // filePath是指欲下载的文件的路径。
            File file = new File(filePath);
            File fileOne = new File(filePathOne);
            // 取得文件名。
            String filename = file.getName();
            String filenameOne = fileOne.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filenameOne.getBytes("gb2312"),"ISO8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @GetMapping("saleDocumentFile/browse")
    @ResponseBody
    public Map browse(HttpServletResponse response,@RequestParam("fileName") String fileName) {
        Map res = new HashMap();
        try {
            SaleDocumentfile documentFileBrowse = saleDocumentfileService.findSaleDocumentFileByName(fileName);
            String path = documentFileBrowse.getPath();
            String name = documentFileBrowse.getName();
            String nameOne = documentFileBrowse.getFileName();
            String suffix = documentFileBrowse.getSuffix();
            //String filePath = path+"/"+name+suffix;
            String filePath = name+suffix;
            String filePathOne = path+"/"+nameOne+suffix;
             String filenames = name+suffix;
             System.out.println("文件地址"+filePath);
             res.put("data",filePath);
            // filePath是指欲下载的文件的路径。
           /* File file = new File(filePath);
            File fileOne = new File(filePathOne);
            // 取得文件名。
            String filename = file.getName();
            String filenameOne = fileOne.getName();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    //删除
    @GetMapping("saleDocumentFile/deleteFile")
    @ResponseBody
    public Map deleteFile(@RequestParam("contFile") String contFile){
        Map res = new HashMap();
        try {
            //截取：之前的字符串
            int contImgOne = contFile.indexOf(".");
            String contFileOne = contFile.substring(0,contImgOne);
            saleDocumentfileService.deleteSaleDocumentFile(contFileOne);
            res.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /*@ResponseBody
    @RequestMapping("saleDocumentFile/Preview")
    public void toPdfFile(HttpServletResponse response,@RequestParam("filePath") String filePath) {
        SaleDocumentfile documentFileBrowse = saleDocumentfileService.findSaleDocumentFileByName(filePath);
        String path = documentFileBrowse.getPath();
        String name = documentFileBrowse.getName();
        String suffix = documentFileBrowse.getSuffix();
        String filePathOne = path+"/"+name+suffix;
        System.out.println("文件地址"+filePathOne);
        //File file = new File("D:\\testMyDoc\\doc\\testJc.docx");//需要转换的文件
        File file = new File(filePathOne);//需要转换的文件
        try {
            File newFile = new File("D:/testMyDoc");//转换之后文件生成的地址
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            String savePath="D:/testMyDoc/"; //pdf文件生成保存的路径
            String fileName="JCccc"+UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
            String fileType=".pdf"; //pdf文件后缀
            String newFileMix=savePath+fileName+fileType;  //将这三个拼接起来,就是我们最后生成文件保存的完整访问路径了

            //文件转化
            converter.convert(file).to(new File(newFileMix)).execute();
            //使用response,将pdf文件以流的方式发送的前端浏览器上
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(newFileMix));// 读取文件
            int i = IOUtils.copy(in, outputStream);   // copy流数据,i为字节数
            in.close();
            outputStream.close();
            System.out.println("流已关闭,可预览,该文件字节大小："+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

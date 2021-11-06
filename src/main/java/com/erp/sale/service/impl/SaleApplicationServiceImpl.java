package com.erp.sale.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.sale.entity.SaleApplication;
import com.erp.sale.entity.SaleApplicationReply;
import com.erp.sale.mapper.SaleApplicationMapper;
import com.erp.sale.service.ISaleApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售申请表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-14 09:51:32
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleApplicationServiceImpl extends ServiceImpl<SaleApplicationMapper, SaleApplication> implements ISaleApplicationService {

    private final SaleApplicationMapper saleApplicationMapper;

    @Override
    public IPage<SaleApplication> findSaleApplications(QueryRequest request, SaleApplication saleApplication) {
        Page<SaleApplication> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleApplication(saleApplication));
        return baseMapper.findSaleApplicationPage(page,saleApplication);
    }

    @Override
    public List<SaleApplication> findSaleApplications(SaleApplication saleApplication) {
	    LambdaQueryWrapper<SaleApplication> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleApplication(SaleApplication saleApplication) {
        this.save(saleApplication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleApplication(SaleApplication saleApplication) {
        this.saveOrUpdate(saleApplication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleApplication(SaleApplication saleApplication) {
        LambdaQueryWrapper<SaleApplication> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
    @Override
    public void addSaleApplication(String requestedDeliveryDate, String customerName, String salesmanName, String dataTable,String contImg) throws ParseException {
        SaleApplication saleApplication = new SaleApplication();
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询出最后一个销售申请单号
        SaleApplication saleApplicationOne = baseMapper.querySaleApplication();
        String createTimeMonth = simpleDateFormat.format(saleApplicationOne.getCreateDate());
         if (month.equals(createTimeMonth)){
             String applicationNo = saleApplicationOne.getApplicationNo();
             String applicationNoOne = applicationNo.substring(7,9);
             int oddNumbers = Integer.parseInt(applicationNoOne);
             if (oddNumbers<9){
                 for (int i = oddNumbers; i < 100; i++) {
                     String n = "";
                     // 满足条件1
                     if (i < 10) {
                         n = "00" + (i+1);
                     }if ((i + 1) % 10 != 0){
                         saleApplication.setApplicationNo("SQ"+yearLast+month+n);
                         break;
                     }
                 }
             }else{
                 int ff = oddNumbers+1;
                 if(ff < 100){
                     saleApplication.setApplicationNo("SQ"+yearLast+month+"0"+ff);
                 }
             }
         }else if(!month.equals(createTimeMonth)){
             String n = "";
             int i = 0;
             // 满足条件1
             if (i < 10){
                 n = "00" + i;
             }
             if ((i + 1) % 10 != 0){
                 saleApplication.setApplicationNo("SQ"+yearLast+month+n);
             }
         }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date requestedDeliveryDates = sdf.parse(requestedDeliveryDate);//格式化数据，取当前时间结果
        saleApplication.setRequestedDeliveryDate(requestedDeliveryDates);
        //System.out.println("客 户:"+customerName);
        saleApplication.setCustomerName(customerName);
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        saleApplication.setCreateDate(today);
        if (!contImg.equals("")){
            //截取：之前的字符串
            int contImgOne = contImg.indexOf(".");
            String contImgTwo = contImg.substring(0,contImgOne);
            saleApplication.setEnclosureName(contImgTwo);
        }
        //System.out.println("列表数据:"+dataTable);
            saleApplication.setSalesmanName(salesmanName);
           // System.out.println("业  务 员:"+jsonArray.get(i).toString());
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String explainName = jsonArrayOne.getJSONObject(i).getString("explainName");
            String specificationModel = jsonArrayOne.getJSONObject(i).getString("specificationModel");
            String quantityName = jsonArrayOne.getJSONObject(i).getString("quantityName");
            String parameterOption = jsonArrayOne.getJSONObject(i).getString("parameterOption");
            saleApplication.setExplainName(explainName);
            saleApplication.setSpecificationModel(specificationModel);
            saleApplication.setQuantityName(Integer.parseInt(quantityName));
            //将List集合转成json字符串
            JSONArray jsonArrayTwo = JSONArray.parseArray(parameterOption);
            //StringBuilder拼接字符串方式
            StringBuilder strOne = new StringBuilder();
            for(int j = 0; j < jsonArrayTwo.size(); j++){
                String parameterOne = jsonArrayTwo.get(j).toString();
                //System.out.println("------------->>"+parameterOne);
                //截取：之前的字符串
                int start = parameterOne.indexOf(":");
                String allocationName = parameterOne.substring(0,start);
                //System.out.println("------"+allocationName);
                //截取：之后的字符串
                String str1 =parameterOne.substring(0, parameterOne.indexOf(":"));
                String configurationContent =parameterOne.substring(str1.length()+1, parameterOne.length());
                if(allocationName.equals("机器要求")){
                    saleApplication.setMachineRequirements(configurationContent);
                    strOne.append("机器要求:"+configurationContent+",");
                }if (allocationName.equals("电脑配置")){
                    saleApplication.setComputerConfiguration(configurationContent);
                    strOne.append("电脑配置:"+configurationContent+",");
                }if (allocationName.equals("刀具大小")){
                    saleApplication.setToolSize(configurationContent);
                    strOne.append("刀具大小:"+configurationContent+",");
                }if (allocationName.equals("每小时产量")){
                    saleApplication.setHourlyProduction(configurationContent);
                    strOne.append("每小时产量:"+configurationContent+",");
                }if (allocationName.equals("加工工序")){
                    saleApplication.setProcessingProcedure(configurationContent);
                    strOne.append("加工工序:"+configurationContent+",");
                }if (allocationName.equals("夹具要求")){
                    saleApplication.setFixtureRequirements(configurationContent);
                    strOne.append("夹具要求:"+configurationContent+",");
                }if (allocationName.equals("产品形状")){
                    saleApplication.setProductShape(configurationContent);
                    strOne.append("产品形状:"+configurationContent+",");
                }if (allocationName.equals("产品尺寸")){
                    saleApplication.setProductSize(configurationContent);
                    strOne.append("产品尺寸:"+configurationContent+",");
                }if (allocationName.equals("其他要求")){
                    saleApplication.setOtherRequirements(configurationContent);
                    strOne.append("其他要求:"+configurationContent+",");
                }
                //System.out.println("parameterOptions----:"+jsonArrayTwo.get(j));
            }
            saleApplication.setConfigureName(strOne.toString());
            String companyName = jsonArrayOne.getJSONObject(i).getString("companyName");
            saleApplication.setCompanyName(companyName);
            String productName = jsonArrayOne.getJSONObject(i).getString("productName");
            saleApplication.setProductName(productName);
            //System.out.println("列表----:"+jsonArrayOne.get(i));
            //添加到数据库
            baseMapper.addSaleApplication(saleApplication);
        }
    }

    @Override
    public int quantityNameStatistics() {

        return baseMapper.quantityNameStatistics();
    }

    @Override
    public SaleApplication findSaleApplicationConfigureViewById(Long id) {

        return baseMapper.findSaleApplicationConfigureViewById(id);
    }

    @Override
    public SaleApplication findSaleApplicationById(Long id) {

        return baseMapper.findSaleApplicationById(id);
    }

    @Override
    public IPage<SaleApplication> saleApplicationsList(QueryRequest request, String applicationNoTwo) {

        Page<SaleApplication> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleApplicationsList(applicationNoTwo));
        return baseMapper.findSaleApplicationsPage(page,applicationNoTwo);
    }
    @Override
    public SaleApplicationReply findSaleApplicationDesignViewById(Long id,String replyDepartment) {
        return baseMapper.findSaleApplicationDesignViewById(id,replyDepartment);
    }

    //设计回复
    @Override
    public void designReplySaleApplication(String designReplyParam,String userName) throws ParseException {
        //获取前台传过来的json
        JSONArray jsonArrayThree = JSONArray.parseArray(designReplyParam);
        //循环json得到所需要的参数
        for(int i = 0; i < jsonArrayThree.size(); i++) {
            String id = jsonArrayThree.getJSONObject(i).getString("id"); //获取参数id
            String designDate = jsonArrayThree.getJSONObject(i).getString("designDate"); //获取参数交期
            String designReply = jsonArrayThree.getJSONObject(i).getString("designReply"); //获取参数回复内容
            baseMapper.designReplySaleApplication(id,designDate,designReply);//根据得到的参数修改数据库表
            SaleApplicationReply saleApplicationReply = new SaleApplicationReply(); //定义销售申请：【设计回复】 Entity
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date = simpleDateFormat.format(new Date());//系统当前时间
            Date today = simpleDateFormat.parse(date); //string类型的日期转换为date
            saleApplicationReply.setSaleApplicationId(Long.parseLong(id));//给销售申请回复表，赋值销售申请表里的id，并将String类型的id转化为long类型
            saleApplicationReply.setRespondent(userName);//给销售申请回复表赋值回复人
            saleApplicationReply.setReplyDate(today);//给销售申请回复表，赋值系统当前时间
            Date todayOne = simpleDateFormat.parse(designDate);//将String类型的设计交期转化为date类型
            saleApplicationReply.setReplyDeliveryDate(todayOne);//给销售申请回复表，赋值销售申请表里的设计交期
            saleApplicationReply.setReplyContent(designReply);//给销售申请回复表，赋值销售申请表里的回复内容
            saleApplicationReply.setReplyDepartment(SaleApplicationReply.design_reply);//给销售申请回复表，赋值销售申请表里的回复部门
            try {
                //根据id查询消息回复情况，查看是否回复过。
                int saleApplicationReplyOne = baseMapper.findSaleApplicationDesignById(Long.parseLong(id),SaleApplicationReply.design_reply);
                //判断查出来的数据是否为空，不为空则修改，为空则添加。
                if (saleApplicationReplyOne>0){
                    baseMapper.updateSaleApplicationReply(saleApplicationReply);//进行修改新回复的数据
                }else{
                    baseMapper.addSaleApplicationReply(saleApplicationReply);//往数据库表添加销售申请回复数据
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
   //采购回复
    @Override
    public void saleApplicationPurchaseReply(String purchaseReplyParam, String userName) throws ParseException {
        //获取前台传过来的json
        JSONArray jsonArrayFour = JSONArray.parseArray(purchaseReplyParam);
        //循环json得到所需要的参数
        for(int i = 0; i < jsonArrayFour.size(); i++) {
            String id = jsonArrayFour.getJSONObject(i).getString("id"); //获取参数id
            String purchaseDate = jsonArrayFour.getJSONObject(i).getString("purchaseDate"); //获取参数交期
            String purchaseReply = jsonArrayFour.getJSONObject(i).getString("purchaseReply"); //获取参数回复内容
            baseMapper.saleApplicationPurchaseReply(id,purchaseDate,purchaseReply);//根据得到的参数修改数据库表
            SaleApplicationReply salePurchaseReply = new SaleApplicationReply(); //定义销售申请：【采购回复】 Entity
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date = simpleDateFormat.format(new Date());//系统当前时间
            Date today = simpleDateFormat.parse(date); //string类型的日期转换为date
            salePurchaseReply.setSaleApplicationId(Long.parseLong(id));//给销售申请回复表，赋值销售申请表里的id，并将String类型的id转化为long类型
            salePurchaseReply.setRespondent(userName);//给销售申请回复表赋值回复人
            salePurchaseReply.setReplyDate(today);//给销售申请回复表，赋值系统当前时间
            Date todayOne = simpleDateFormat.parse(purchaseDate);//将String类型的设计交期转化为date类型
            salePurchaseReply.setReplyDeliveryDate(todayOne);//给销售申请回复表，赋值销售申请表里的设计交期
            salePurchaseReply.setReplyContent(purchaseReply);//给销售申请回复表，赋值销售申请表里的回复内容
            salePurchaseReply.setReplyDepartment(SaleApplicationReply.purchase_reply);//给销售申请回复表，赋值销售申请表里的回复部门
            try {
                //根据id查询消息回复情况，查看是否回复过。
                int saleApplicationReplyTwos = baseMapper.findSaleApplicationDesignById(Long.parseLong(id),SaleApplicationReply.purchase_reply);
                //判断查出来的数据是否为空，不为空则修改，为空则添加。
                if (saleApplicationReplyTwos>0){
                    baseMapper.updateSaleApplicationReply(salePurchaseReply);//进行修改新采购回复的数据
                }else{
                    baseMapper.addSaleApplicationReply(salePurchaseReply);//往数据库表添加销售申请采购回复数据
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //生产回复
    @Override
    public void saleApplicationProductionReply(String productionReplyParam, String userName) throws ParseException {
        //获取前台传过来的json
        JSONArray jsonArraySix = JSONArray.parseArray(productionReplyParam);
        //循环json得到所需要的参数
        for(int i = 0; i < jsonArraySix.size(); i++) {
            String id = jsonArraySix.getJSONObject(i).getString("id"); //获取参数id
            String productionDate = jsonArraySix.getJSONObject(i).getString("productionDate"); //获取参数交期
            String productionReply = jsonArraySix.getJSONObject(i).getString("productionReply"); //获取参数回复内容
            baseMapper.saleApplicationProductionReply(id,productionDate,productionReply);//根据得到的参数修改数据库表
            SaleApplicationReply saleProductionReply = new SaleApplicationReply(); //定义销售申请：【生产回复】 Entity
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date = simpleDateFormat.format(new Date());//系统当前时间
            Date today = simpleDateFormat.parse(date); //string类型的日期转换为date
            saleProductionReply.setSaleApplicationId(Long.parseLong(id));//给销售申请回复表，赋值销售申请表里的id，并将String类型的id转化为long类型
            saleProductionReply.setRespondent(userName);//给销售申请回复表赋值回复人
            saleProductionReply.setReplyDate(today);//给销售申请回复表，赋值系统当前时间
            Date todayOne = simpleDateFormat.parse(productionDate);//将String类型的设计交期转化为date类型
            saleProductionReply.setReplyDeliveryDate(todayOne);//给销售申请回复表，赋值销售申请表里的设计交期
            saleProductionReply.setReplyContent(productionReply);//给销售申请回复表，赋值销售申请表里的回复内容
            saleProductionReply.setReplyDepartment(SaleApplicationReply.production_reply);//给销售申请回复表，赋值销售申请表里的回复部门
            try {
                //根据id查询消息回复情况，查看是否回复过。
                int saleProductionReplyOne = baseMapper.findSaleApplicationDesignById(Long.parseLong(id),SaleApplicationReply.production_reply);
                //判断查出来的数据是否为空，不为空则修改，为空则添加。
                if (saleProductionReplyOne>0){
                    baseMapper.updateSaleApplicationReply(saleProductionReply);//进行修改新采购回复的数据
                }else{
                    baseMapper.addSaleApplicationReply(saleProductionReply);//往数据库表添加销售申请采购回复数据
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
   //装配回复
    @Override
    public void saleApplicationAssemblingReply(String assemblingReplyParam, String userName) throws ParseException {
        //获取前台传过来的json
        JSONArray jsonArraySeven = JSONArray.parseArray(assemblingReplyParam);
        //循环json得到所需要的参数
        for(int i = 0; i < jsonArraySeven.size(); i++) {
            String id = jsonArraySeven.getJSONObject(i).getString("id"); //获取参数id
            String assemblingDate = jsonArraySeven.getJSONObject(i).getString("assemblingDate"); //获取参数交期
            String assemblingReply = jsonArraySeven.getJSONObject(i).getString("assemblingReply"); //获取参数回复内容
            baseMapper.saleApplicationAssemblingReply(id,assemblingDate,assemblingReply);//根据得到的参数修改数据库表
            SaleApplicationReply saleAssemblingReply = new SaleApplicationReply(); //定义销售申请：【装配回复】 Entity
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date = simpleDateFormat.format(new Date());//系统当前时间
            Date today = simpleDateFormat.parse(date); //string类型的日期转换为date
            saleAssemblingReply.setSaleApplicationId(Long.parseLong(id));//给销售申请回复表，赋值销售申请表里的id，并将String类型的id转化为long类型
            saleAssemblingReply.setRespondent(userName);//给销售申请回复表赋值回复人
            saleAssemblingReply.setReplyDate(today);//给销售申请回复表，赋值系统当前时间
            Date todayOne = simpleDateFormat.parse(assemblingDate);//将String类型的设计交期转化为date类型
            saleAssemblingReply.setReplyDeliveryDate(todayOne);//给销售申请回复表，赋值销售申请表里的设计交期
            saleAssemblingReply.setReplyContent(assemblingReply);//给销售申请回复表，赋值销售申请表里的回复内容
            saleAssemblingReply.setReplyDepartment(SaleApplicationReply.assembling_reply);//给销售申请回复表，赋值销售申请表里的回复部门
            try {
                //根据id查询消息回复情况，查看是否回复过。
                int saleAssemblingReplyOne = baseMapper.findSaleApplicationDesignById(Long.parseLong(id),SaleApplicationReply.assembling_reply);
                //判断查出来的数据是否为空，不为空则修改，为空则添加。
                if (saleAssemblingReplyOne>0){
                    baseMapper.updateSaleApplicationReply(saleAssemblingReply);//进行修改新采购回复的数据
                }else{
                    baseMapper.addSaleApplicationReply(saleAssemblingReply);//往数据库表添加销售申请采购回复数据
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

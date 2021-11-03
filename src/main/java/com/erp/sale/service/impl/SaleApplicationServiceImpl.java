package com.erp.sale.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.sale.entity.SaleApplication;
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
        //打印当前月
        //System.out.println(month);
        String applicationNo = "SQ"+yearLast+month;
        saleApplication.setApplicationNo(applicationNo);
        //System.out.println("申请交货日期:"+requestedDeliveryDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date requestedDeliveryDates = sdf.parse(requestedDeliveryDate);//格式化数据，取当前时间结果为 2014-10-30
        saleApplication.setRequestedDeliveryDate(requestedDeliveryDates);
        //System.out.println("客 户:"+customerName);
        saleApplication.setCustomerName(customerName);
        if (!contImg.equals("")){
            //截取：之前的字符串
            int contImgOne = contImg.indexOf(".");
            String contImgTwo = contImg.substring(0,contImgOne);
            saleApplication.setEnclosureName(contImgTwo);
        }
        //System.out.println("列表数据:"+dataTable);
        //将List集合转成json字符串
        JSONArray jsonArray = JSONArray.parseArray(salesmanName);
        for(int i = 0; i < jsonArray.size(); i++){
            saleApplication.setSalesmanName(jsonArray.get(i).toString());
           // System.out.println("业  务 员:"+jsonArray.get(i).toString());
        }
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
            StringBuilder strOne = new StringBuilder();
            for(int j = 0; j < jsonArrayTwo.size(); j++){
                //StringBuilder拼接字符串方式
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
    public SaleApplication findSaleApplicationById(String applicationNo) {

        return baseMapper.findSaleApplicationById(applicationNo);
    }

    @Override
    public IPage<SaleApplication> saleApplicationsList(QueryRequest request, String applicationNoTwo) {

        Page<SaleApplication> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleApplicationsList(applicationNoTwo));
        return baseMapper.findSaleApplicationsPage(page,applicationNoTwo);
    }
}

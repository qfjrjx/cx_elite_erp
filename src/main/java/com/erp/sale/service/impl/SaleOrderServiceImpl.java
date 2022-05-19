package com.erp.sale.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.entity.SaleOrderAll;
import com.erp.sale.entity.SaleOrderSchedule;
import com.erp.sale.mapper.SaleOrderMapper;
import com.erp.sale.service.ISaleOrderService;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * saleApplicationAll-table表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrderAll> implements ISaleOrderService {

    private final SaleOrderMapper saleOrderMapper;

    @Override
    public IPage<SaleOrderAll> findSaleOrders(QueryRequest request, SaleOrderAll saleOrder) {
        Page<SaleOrderAll> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleOrder(saleOrder));
        return baseMapper.findSaleOrderPage(page,saleOrder);
    }

    @Override
    public List<SaleOrderAll> findSaleOrders(SaleOrderAll saleOrder) {
	    LambdaQueryWrapper<SaleOrderAll> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createSaleOrder(String saleOrderData,String dataTable, String contImg) throws ParseException {
            SaleOrder saleOrder = new SaleOrder();
            SaleOrderSchedule saleOrderSchedule = new SaleOrderSchedule();
            /*单号案例 SQ2102001*/
            String initials = "HT";
            String generateDocNo = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            //获取当前年
            String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
            //打印当前年
            //System.out.println(yearLast);
            //获取当月
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
            //格式化当前月
            String month = simpleDateFormat.format(date);
            //this.save(saleOrder);
            //查询出最后一个销售申请单号
            SaleOrder saleOrderOne = null;
            saleOrderOne = baseMapper.querySaleOrder();
            String createTimeMonth = "";
        if (saleOrderOne != null){
            createTimeMonth = simpleDateFormat.format(saleOrderOne.getCreateDate());
            if (month.equals(createTimeMonth)){
                String applicationNo = saleOrderOne.getOddNumbers();
                String applicationNoOne = applicationNo.substring(7,9);
                int oddNumbers = Integer.parseInt(applicationNoOne);
                if (oddNumbers<9){
                    for (int i = oddNumbers; i < 100; i++) {
                        String n = "";
                        // 满足条件1
                        if (i < 10) {
                            n = "00" + (i+1);
                        }if ((i + 1) % 10 != 0){
                            generateDocNo = initials+yearLast+month+n;
                            saleOrder.setOddNumbers(generateDocNo);
                            break;
                        }
                    }
                }else{
                    int ff = oddNumbers+1;
                    if(ff < 100){
                        generateDocNo = initials+yearLast+month+"0"+ff;
                        saleOrder.setOddNumbers(generateDocNo);
                    }
                }
            }else if(!month.equals(createTimeMonth)){
                String n = "";
                int i = 1;
                // 满足条件1
                if (i < 10){
                    n = "00" + i;
                }
                if ((i + 1) % 10 != 0){
                    generateDocNo = initials+yearLast+month+n;
                    saleOrder.setOddNumbers(generateDocNo);
                }
            }
        }else {
            String v = "";
            int i = 0;
            // 满足条件1
            if (i < 10){
                v = "00" + i;
            }
            if ((i + 1) % 10 != 0){
                generateDocNo = initials+yearLast+month+v;
                saleOrder.setOddNumbers(generateDocNo);
            }
        }
        JSONObject saleOrderDataKey = JSON.parseObject(saleOrderData);
        String  orderDate =  saleOrderDataKey.getString("orderDate");
        String  customerName =  saleOrderDataKey.getString("customerName");
        String  salesmanName =  saleOrderDataKey.getString("salesmanName");
        String  currencyName =  saleOrderDataKey.getString("currencyName");
        String  taxRate =  saleOrderDataKey.getString("taxRate");
        String  paymentMethod =  saleOrderDataKey.getString("paymentMethod");
        String  depositMoney =  saleOrderDataKey.getString("depositMoney");
        String  invoiceNot =  saleOrderDataKey.getString("invoiceNot");
        String  contactsName =  saleOrderDataKey.getString("contactsName");
        String  mobilePhone =  saleOrderDataKey.getString("mobilePhone");
        String  orderType =  saleOrderDataKey.getString("orderType");
        String  afterSalesClerk =  saleOrderDataKey.getString("afterSalesClerk");
        if (!orderDate.equals("")) {
            Date orderDateOne = sdf.parse(orderDate);//格式化数据，取当前时间结果
            saleOrder.setOrderDate(orderDateOne);
        }
        if (!customerName.equals("")) {
            saleOrder.setCustomerName(customerName);
        }
        if (!salesmanName.equals("")) {
        saleOrder.setSalesmanName(salesmanName);
        }
        if (!currencyName.equals("")) {
            long currencyNameOne = Long.parseLong(currencyName);
            saleOrder.setCurrencyId(currencyNameOne);
        }
        if (!taxRate.equals("")) {
            saleOrder.setTaxRateId(Long.parseLong(taxRate));
        }
        if (!paymentMethod.equals("")) {
            saleOrder.setPaymentMethodId(Long.parseLong(paymentMethod));
        }
        if (!depositMoney.equals("")) {
            BigDecimal depositMoneyTo = new BigDecimal(depositMoney);
            saleOrder.setDepositMoney(depositMoneyTo);
        }
        if (!invoiceNot.equals("")) {
            saleOrder.setInvoiceNot(Integer.parseInt(invoiceNot));
        }
        if (!contactsName.equals("")) {
            saleOrder.setContactsName(contactsName);
        }
        if (!mobilePhone.equals("")) {
            saleOrder.setMobilePhone(mobilePhone);
        }
        if (!orderType.equals("")) {
            saleOrder.setOrderType(Integer.parseInt(orderType));
        }
        if (!afterSalesClerk.equals("")) {
            saleOrder.setAfterSalesClerk(afterSalesClerk);
        }
        if (!contImg.equals("")){
            //截取：之前的字符串
            int contImgOne = contImg.indexOf(".");
            String contImgTwo = contImg.substring(0,contImgOne);
            saleOrder.setEnclosureName(contImgTwo);
        }
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String productName = jsonArrayOne.getJSONObject(i).getString("productName");
            String specificationModel = jsonArrayOne.getJSONObject(i).getString("specificationModel");
            String configureName = jsonArrayOne.getJSONObject(i).getString("configureName");
            String companyName = jsonArrayOne.getJSONObject(i).getString("companyName");
            String quantityName = jsonArrayOne.getJSONObject(i).getString("quantityName");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String amountMoney = jsonArrayOne.getJSONObject(i).getString("amountMoney");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryTime");
            String orderRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            saleOrderSchedule.setOddNumbers(generateDocNo);
            saleOrderSchedule.setProductName(productName);
            saleOrderSchedule.setSpecificationModel(specificationModel);
            saleOrderSchedule.setCompanyName(companyName);
            saleOrderSchedule.setQuantityName(quantityName);
            BigDecimal unitPriceTo = new BigDecimal(unitPrice);
            saleOrderSchedule.setUnitPrice(unitPriceTo);
            if (!amountMoney.equals("")) {
                BigDecimal amountMoneyTo = new BigDecimal(amountMoney);
                saleOrderSchedule.setAmountMoney(amountMoneyTo);
            }
            Date requestedDeliveryDates = sdf.parse(deliveryDate);//格式化数据，取当前时间结果
            saleOrderSchedule.setDeliveryDate(requestedDeliveryDates);
            saleOrderSchedule.setOrderRemarks(orderRemarks);
            //saleApplication.setQuantityName(Integer.parseInt(quantityName));
            //将List集合转成json字符串
            JSONArray jsonArrayTwo = JSONArray.parseArray(configureName);
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
                    saleOrderSchedule.setMachineRequirements(configurationContent);
                    strOne.append("机器要求:"+configurationContent+",");
                }if (allocationName.equals("电脑配置")){
                    saleOrderSchedule.setComputerConfiguration(configurationContent);
                    strOne.append("电脑配置:"+configurationContent+",");
                }if (allocationName.equals("刀具大小")){
                    saleOrderSchedule.setToolSize(configurationContent);
                    strOne.append("刀具大小:"+configurationContent+",");
                }if (allocationName.equals("每小时产量")){
                    saleOrderSchedule.setHourlyProduction(configurationContent);
                    strOne.append("每小时产量:"+configurationContent+",");
                }if (allocationName.equals("加工工序")){
                    saleOrderSchedule.setProcessingProcedure(configurationContent);
                    strOne.append("加工工序:"+configurationContent+",");
                }if (allocationName.equals("夹具要求")){
                    saleOrderSchedule.setFixtureRequirements(configurationContent);
                    strOne.append("夹具要求:"+configurationContent+",");
                }if (allocationName.equals("产品形状")){
                    saleOrderSchedule.setProductShape(configurationContent);
                    strOne.append("产品形状:"+configurationContent+",");
                }if (allocationName.equals("产品尺寸")){
                    saleOrderSchedule.setProductSize(configurationContent);
                    strOne.append("产品尺寸:"+configurationContent+",");
                }if (allocationName.equals("其他要求")){
                    saleOrderSchedule.setOtherRequirements(configurationContent);
                    strOne.append("其他要求:"+configurationContent+",");
                }
                //System.out.println("parameterOptions----:"+jsonArrayTwo.get(j));
            }
            //System.out.println("列表----:"+jsonArrayOne.get(i));
            saleOrderSchedule.setConfigureName(strOne.toString());
            SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dates = simpleDateFormatOne.format(new Date());//系统当前时间
            Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
            saleOrder.setCreateDate(today);
            //添加到数据库附表
            baseMapper.addSaleOrderSchedule(saleOrderSchedule);
        }
        //添加到数据库主表
        baseMapper.addSaleOrder(saleOrder);
    }

    @Override
    public void updateSaleOrder(String saleOrderData, String dataTable, String contImg) throws ParseException {
        SaleOrder saleOrder = new SaleOrder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject saleOrderDataKey = JSON.parseObject(saleOrderData);
        String  oddNumbers =  saleOrderDataKey.getString("oddNumbers");
        String  orderDate =  saleOrderDataKey.getString("orderDate");
        String  customerName =  saleOrderDataKey.getString("customerName");
        String  salesmanName =  saleOrderDataKey.getString("salesmanName");
        String  currencyName =  saleOrderDataKey.getString("currencyName");
        String  taxRate =  saleOrderDataKey.getString("taxRate");
        String  paymentMethod =  saleOrderDataKey.getString("paymentMethod");
        String  depositMoney =  saleOrderDataKey.getString("depositMoney");
        String  invoiceNot =  saleOrderDataKey.getString("invoiceNot");
        String  contactsName =  saleOrderDataKey.getString("contactsName");
        String  mobilePhone =  saleOrderDataKey.getString("mobilePhone");
        String  orderType =  saleOrderDataKey.getString("orderType");
        String  afterSalesClerk =  saleOrderDataKey.getString("afterSalesClerk");
        if (!orderDate.equals("")) {
            Date orderDateOne = sdf.parse(orderDate);//格式化数据，取当前时间结果
            saleOrder.setOrderDate(orderDateOne);
        }
        if (!customerName.equals("")) {
            saleOrder.setCustomerName(customerName);
        }
        if (!salesmanName.equals("")) {
            saleOrder.setSalesmanName(salesmanName);
        }
        if (!currencyName.equals("")) {
            long currencyNameOne = Long.parseLong(currencyName);
            saleOrder.setCurrencyId(currencyNameOne);
        }
        if (!taxRate.equals("")) {
            saleOrder.setTaxRateId(Long.parseLong(taxRate));
        }
        if (!paymentMethod.equals("")) {
            saleOrder.setPaymentMethodId(Long.parseLong(paymentMethod));
        }
        if (!depositMoney.equals("")) {
            BigDecimal depositMoneyTo = new BigDecimal(depositMoney);
            saleOrder.setDepositMoney(depositMoneyTo);
        }
        if (!invoiceNot.equals("")) {
            saleOrder.setInvoiceNot(Integer.parseInt(invoiceNot));
        }
        if (!contactsName.equals("")) {
            saleOrder.setContactsName(contactsName);
        }
        if (!mobilePhone.equals("")) {
            saleOrder.setMobilePhone(mobilePhone);
        }
        if (!orderType.equals("")) {
            saleOrder.setOrderType(Integer.parseInt(orderType));
        }
        if (!afterSalesClerk.equals("")) {
            saleOrder.setAfterSalesClerk(afterSalesClerk);
        }
        if (!contImg.equals("")){
            //截取：之前的字符串
           /* int contImgOne = contImg.indexOf(".");
            String contImgTwo = contImg.substring(0,contImgOne);*/
            saleOrder.setEnclosureName(contImg);
        }
        saleOrder.setOddNumbers(oddNumbers);
        //删除附表数据
        baseMapper.deleteSaleOrderSchedule(oddNumbers);
        JSONArray  jsonArrayOne = JSONArray.parseArray(String.valueOf(dataTable));
        for(int i = 0; i < jsonArrayOne.size(); i++){
            SaleOrderSchedule saleOrderSchedule = new SaleOrderSchedule();
            String productName = jsonArrayOne.getJSONObject(i).getString("productName");
            String specificationModel = jsonArrayOne.getJSONObject(i).getString("specificationModel");
            String configureName =  jsonArrayOne.getJSONObject(i).getString("configureName");
            System.out.println("-------------1>>"+configureName);
            String companyName = jsonArrayOne.getJSONObject(i).getString("companyName");
            String quantityName = jsonArrayOne.getJSONObject(i).getString("quantityName");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String amountMoney = jsonArrayOne.getJSONObject(i).getString("amountMoney");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryDate");
            String orderRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            if (!productName.equals("")) {
                saleOrderSchedule.setProductName(productName);
            }if (!specificationModel.equals("")) {
                saleOrderSchedule.setSpecificationModel(specificationModel);
            }if (!companyName.equals("")) {
                saleOrderSchedule.setCompanyName(companyName);
            }if (!quantityName.equals("")) {
                saleOrderSchedule.setQuantityName(quantityName);
            }if (unitPrice != null && unitPrice != "") {
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                saleOrderSchedule.setUnitPrice(unitPriceTo);
            }
            if (!amountMoney.equals("")) {
                BigDecimal amountMoneyTo = new BigDecimal(amountMoney);
                saleOrderSchedule.setAmountMoney(amountMoneyTo);
            }
            saleOrderSchedule.setOddNumbers(oddNumbers);
            if (deliveryDate != null && deliveryDate != "") {
                Date requestedDeliveryDates = sdf.parse(deliveryDate);//格式化数据，取当前时间结果
                saleOrderSchedule.setDeliveryDate(requestedDeliveryDates);
            }

            saleOrderSchedule.setOrderRemarks(orderRemarks);
            //将List集合转成json字符串
            if(!configureName.equals("")) {
                String str = configureName.substring(0,1);
                JSONArray  jsonArrayTwo;
                if (!str.equals("[")){
                    JsonArray arr = new JsonArray();
                    String[] placeName = configureName.split(",");
                     /* System.out.println("-------------1111111>>"+placeName);*/
                    for (int k = 0; k < placeName.length; k++) {
                        arr.add(placeName[k]);
                    }
                    jsonArrayTwo = JSONArray.parseArray(String.valueOf(arr));
                }else {
                    jsonArrayTwo = JSONArray.parseArray(configureName);
                }
                //StringBuilder拼接字符串方式
                StringBuilder strOne = new StringBuilder();
                for (int j = 0; j < jsonArrayTwo.size(); j++) {
                    String parameterOne = jsonArrayTwo.get(j).toString();
                    //System.out.println("------------->>"+parameterOne);
                    //截取：之前的字符串
                    int start = parameterOne.indexOf(":");
                    String allocationName = parameterOne.substring(0, start);
                    //System.out.println("------"+allocationName);
                    //截取：之后的字符串
                    String str1 = parameterOne.substring(0, parameterOne.indexOf(":"));
                    String configurationContent = parameterOne.substring(str1.length() + 1, parameterOne.length());
                    if (allocationName.equals("机器要求")) {
                        saleOrderSchedule.setMachineRequirements(configurationContent);
                        strOne.append("机器要求:" + configurationContent + ",");
                    }
                    if (allocationName.equals("电脑配置")) {
                        saleOrderSchedule.setComputerConfiguration(configurationContent);
                        strOne.append("电脑配置:" + configurationContent + ",");
                    }
                    if (allocationName.equals("刀具大小")) {
                        saleOrderSchedule.setToolSize(configurationContent);
                        strOne.append("刀具大小:" + configurationContent + ",");
                    }
                    if (allocationName.equals("每小时产量")) {
                        saleOrderSchedule.setHourlyProduction(configurationContent);
                        strOne.append("每小时产量:" + configurationContent + ",");
                    }
                    if (allocationName.equals("加工工序")) {
                        saleOrderSchedule.setProcessingProcedure(configurationContent);
                        strOne.append("加工工序:" + configurationContent + ",");
                    }
                    if (allocationName.equals("夹具要求")) {
                        saleOrderSchedule.setFixtureRequirements(configurationContent);
                        strOne.append("夹具要求:" + configurationContent + ",");
                    }
                    if (allocationName.equals("产品形状")) {
                        saleOrderSchedule.setProductShape(configurationContent);
                        strOne.append("产品形状:" + configurationContent + ",");
                    }
                    if (allocationName.equals("产品尺寸")) {
                        saleOrderSchedule.setProductSize(configurationContent);
                        strOne.append("产品尺寸:" + configurationContent + ",");
                    }
                    if (allocationName.equals("其他要求")) {
                        saleOrderSchedule.setOtherRequirements(configurationContent);
                        strOne.append("其他要求:" + configurationContent + ",");
                    }
                }
                saleOrderSchedule.setConfigureName(strOne.toString());
                //添加到数据库附表
                baseMapper.addSaleOrderSchedule(saleOrderSchedule);
            }
        }
        //修改数据库主表
        baseMapper.updateSaleOrder(saleOrder);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleOrder(SaleOrderAll saleOrder) {
        LambdaQueryWrapper<SaleOrderAll> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public SaleOrderSchedule findSaleOrderConfigureViewById(Long id) {

        return baseMapper.findSaleOrderConfigureViewById(id);
    }

    @Override
    public SaleOrder findSaleOrderById(Long id) {
        return baseMapper.findSaleOrderById(id);
    }

    @Override
    public List<SaleOrderSchedule> saleOrderSchedulesList(String oddNumbersTwo) {

        return baseMapper.saleOrderSchedulesList(oddNumbersTwo);
    }
}

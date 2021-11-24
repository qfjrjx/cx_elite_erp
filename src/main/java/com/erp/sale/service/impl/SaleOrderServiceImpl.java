package com.erp.sale.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleApplication;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.mapper.SaleOrderMapper;
import com.erp.sale.service.ISaleOrderService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 销售订单表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements ISaleOrderService {

    private final SaleOrderMapper saleOrderMapper;

    @Override
    public IPage<SaleOrder> findSaleOrders(QueryRequest request, SaleOrder saleOrder) {
        Page<SaleOrder> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleOrder(saleOrder));
        return baseMapper.findSaleOrderPage(page,saleOrder);
    }

    @Override
    public List<SaleOrder> findSaleOrders(SaleOrder saleOrder) {
	    LambdaQueryWrapper<SaleOrder> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createSaleOrder(String orderDate, String customerName, String salesmanName, String currencyName, String taxRate, String paymentMethod, String depositMoney, String invoiceNot, String contactsName, String mobilePhone, String orderType, String afterSalesClerk, String dataTable, String contImg) throws ParseException {
            SaleOrder saleOrder = new SaleOrder();
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
                            saleOrder.setOddNumbers("HT"+yearLast+month+n);
                            break;
                        }
                    }
                }else{
                    int ff = oddNumbers+1;
                    if(ff < 100){
                        saleOrder.setOddNumbers("HT"+yearLast+month+"0"+ff);
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
                    saleOrder.setOddNumbers("HT"+yearLast+month+n);
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
                saleOrder.setOddNumbers("HT"+yearLast+month+v);
            }
        }
        if (!contImg.equals("")){
            //截取：之前的字符串
            int contImgOne = contImg.indexOf(".");
            String contImgTwo = contImg.substring(0,contImgOne);
            saleOrder.setEnclosureName(contImgTwo);
        }
        saleOrder.setSalesmanName(salesmanName);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String productName = jsonArrayOne.getJSONObject(i).getString("productName");
            String specificationModel = jsonArrayOne.getJSONObject(i).getString("specificationModel");
            String configureName = jsonArrayOne.getJSONObject(i).getString("configureName");
            String companyName = jsonArrayOne.getJSONObject(i).getString("companyName");
            String quantityName = jsonArrayOne.getJSONObject(i).getString("quantityName");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String amountMoney = jsonArrayOne.getJSONObject(i).getString("amountMoney");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryDate");
            String orderRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            saleOrder.setProductName(productName);
            saleOrder.setSpecificationModel(specificationModel);
            saleOrder.setCompanyName(companyName);
            saleOrder.setQuantityName(quantityName);
            BigDecimal unitPriceTo = new BigDecimal(unitPrice);
            saleOrder.setUnitPrice(unitPriceTo);
            BigDecimal amountMoneyTo = new BigDecimal(amountMoney);
            saleOrder.setAmountMoney(amountMoneyTo);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date requestedDeliveryDates = sdf.parse(deliveryDate);//格式化数据，取当前时间结果
            saleOrder.setDeliveryDate(requestedDeliveryDates);
            saleOrder.setOrderRemarks(orderRemarks);
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
                    saleOrder.setMachineRequirements(configurationContent);
                    strOne.append("机器要求:"+configurationContent+",");
                }if (allocationName.equals("电脑配置")){
                    saleOrder.setComputerConfiguration(configurationContent);
                    strOne.append("电脑配置:"+configurationContent+",");
                }if (allocationName.equals("刀具大小")){
                    saleOrder.setToolSize(configurationContent);
                    strOne.append("刀具大小:"+configurationContent+",");
                }if (allocationName.equals("每小时产量")){
                    saleOrder.setHourlyProduction(configurationContent);
                    strOne.append("每小时产量:"+configurationContent+",");
                }if (allocationName.equals("加工工序")){
                    saleOrder.setProcessingProcedure(configurationContent);
                    strOne.append("加工工序:"+configurationContent+",");
                }if (allocationName.equals("夹具要求")){
                    saleOrder.setFixtureRequirements(configurationContent);
                    strOne.append("夹具要求:"+configurationContent+",");
                }if (allocationName.equals("产品形状")){
                    saleOrder.setProductShape(configurationContent);
                    strOne.append("产品形状:"+configurationContent+",");
                }if (allocationName.equals("产品尺寸")){
                    saleOrder.setProductSize(configurationContent);
                    strOne.append("产品尺寸:"+configurationContent+",");
                }if (allocationName.equals("其他要求")){
                    saleOrder.setOtherRequirements(configurationContent);
                    strOne.append("其他要求:"+configurationContent+",");
                }
                //System.out.println("parameterOptions----:"+jsonArrayTwo.get(j));
            }


            //System.out.println("列表----:"+jsonArrayOne.get(i));
            //添加到数据库
            baseMapper.addSaleOrder(saleOrder);
        }

    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleOrder(SaleOrder saleOrder) {
        this.saveOrUpdate(saleOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleOrder(SaleOrder saleOrder) {
        LambdaQueryWrapper<SaleOrder> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

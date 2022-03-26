package com.erp.purchase.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseClosed;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.mapper.PurchaseClosedMapper;
import com.erp.purchase.service.IPurchaseClosedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 采购收货表 Service实现
 *
 * @author qiufeng
 * @date 2022-03-19 09:44:35
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseClosedServiceImpl extends ServiceImpl<PurchaseClosedMapper, PurchaseClosed> implements IPurchaseClosedService {

    private final PurchaseClosedMapper purchaseClosedMapper;

    @Override
    public IPage<PurchaseClosed> findPurchaseCloseds(QueryRequest request, PurchaseClosed purchaseClosed) {
        Page<PurchaseClosed> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseClosed(purchaseClosed));
        return baseMapper.findPurchaseClosedPage(page,purchaseClosed);
    }

    @Override
    public List<PurchaseClosed> findPurchaseCloseds(PurchaseClosed purchaseClosed) {
	    LambdaQueryWrapper<PurchaseClosed> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseClosed(String purchaseClosed, String dataTable) throws ParseException {
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        PurchaseClosed purchaseClosedDate = new PurchaseClosed();
        /*单号案例 LLHRK22030187*/
        String initials = "LLHRK";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(purchaseClosed);
        String  userName =  object.getString("userName");
        String  purchaseRequisitionDate =  object.getString("purchaseRequisitionDate");
        String  supplierName =  object.getString("supplierName");
        String  currencyId =  object.getString("currencyId");
        String  taxRateId =  object.getString("taxRateId");
        String  warehouseState =  object.getString("warehouseState");
        //查询出最后一个工作安排单号
        PurchaseClosed purchaseClosedOne = null;
        purchaseClosedOne = baseMapper.queryPurchaseClosed();
        if (purchaseClosedOne != null){
            String oddNumbers = purchaseClosedOne.getClosedNumber();
            /*单号案例 LWLCG22020155*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchaseClosedDate.setClosedNumber(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchaseClosedDate.setClosedNumber(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        //入库人
        purchaseClosedDate.setClosedStorage(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        purchaseClosedDate.setClosedStorageDate(today);
        //入库日期
        Date purchaseRequisitionDateOne = simpleDateFormatOne.parse(purchaseRequisitionDate);//格式化系统当前时间
        purchaseClosedDate.setPurchaseRequisitionDate(purchaseRequisitionDateOne);
        purchaseClosedDate.setSupplierName(supplierName);
        purchaseClosedDate.setCurrencyId(Long.parseLong(currencyId));
        purchaseClosedDate.setTaxRateId(Long.parseLong(taxRateId));
        purchaseClosedDate.setWarehouseState(warehouseState);
        purchaseClosedDate.setClosedState("1");
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseOrderSchedule purchaseOrderSchedule = new PurchaseOrderSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseOrderSchedule.setOddNumbers(generateDocNo);
            purchaseOrderSchedule.setDeliveryDate(purchaseRequisitionDateOne);
            String orderCode = jsonArrayOne.getJSONObject(i).getString("orderCode");
            String materialName = jsonArrayOne.getJSONObject(i).getString("materialName");
            String orderSpecifications = jsonArrayOne.getJSONObject(i).getString("orderSpecifications");
            String orderQuality = jsonArrayOne.getJSONObject(i).getString("orderQuality");
            String orderBrand = jsonArrayOne.getJSONObject(i).getString("orderBrand");
            String orderCompany = jsonArrayOne.getJSONObject(i).getString("orderCompany");
            String orderQuantity = jsonArrayOne.getJSONObject(i).getString("orderQuantity");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String orderMoney = jsonArrayOne.getJSONObject(i).getString("orderMoney");
            String orderRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            String applicationNo = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            if (!applicationNo.equals("")){
                purchaseOrderSchedule.setApplicationNo(applicationNo);
            }
            if (!orderCode.equals("")) {
                purchaseOrderSchedule.setOrderCode(orderCode);
            }
            if (!materialName.equals("")) {
                purchaseOrderSchedule.setMaterialName(materialName);
            }
            if (orderSpecifications != null && orderSpecifications != "") {
                purchaseOrderSchedule.setOrderSpecifications(orderSpecifications);
            }
            if (!orderQuality.equals("")) {
                purchaseOrderSchedule.setOrderQuality(orderQuality);
            }
            if (!orderBrand.equals("")) {
                purchaseOrderSchedule.setOrderBrand(orderBrand);
            }
            if (!orderCompany.equals("") && !orderCompany.equals(null)) {
                purchaseOrderSchedule.setOrderCompany(orderCompany);
            }
            if (orderQuantity != null && orderQuantity != "") {
                purchaseOrderSchedule.setOrderQuantity(Integer.parseInt(orderQuantity));
            }
            if (unitPrice != null && unitPrice != "") {
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                purchaseOrderSchedule.setUnitPrice(unitPriceTo);
            }
            if (orderMoney != null && orderMoney != "") {
                BigDecimal orderMoneyTo = new BigDecimal(orderMoney);
                purchaseOrderSchedule.setOrderMoney(orderMoneyTo);
            }
            if (orderRemarks != null && orderRemarks != "") {
                purchaseOrderSchedule.setOrderRemarks(orderRemarks);
            }
            //添加到数据库
            baseMapper.savePurchaseOrderSchedule(purchaseOrderSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseClosedDate(purchaseClosedDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseClosed(String closedNumber, String dataTable) throws ParseException{
        baseMapper.deletePurchaseOrderSchedule(closedNumber);
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseOrderSchedule purchaseOrderSchedule = new PurchaseOrderSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            purchaseOrderSchedule.setOddNumbers(closedNumber);
            //取当前时间
            SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
            Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
            purchaseOrderSchedule.setDeliveryDate(today);
            String applicationNo = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String orderCode = jsonArrayOne.getJSONObject(i).getString("orderCode");
            String materialName = jsonArrayOne.getJSONObject(i).getString("materialName");
            String orderSpecifications = jsonArrayOne.getJSONObject(i).getString("orderSpecifications");
            String orderQuality = jsonArrayOne.getJSONObject(i).getString("orderQuality");
            String orderBrand = jsonArrayOne.getJSONObject(i).getString("orderBrand");
            String orderCompany = jsonArrayOne.getJSONObject(i).getString("orderCompany");
            String orderQuantity = jsonArrayOne.getJSONObject(i).getString("orderQuantity");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String orderMoney = jsonArrayOne.getJSONObject(i).getString("orderMoney");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryDate");
            String orderRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            if (!applicationNo.equals("")) {
                purchaseOrderSchedule.setApplicationNo(applicationNo);
            }if (!orderCode.equals("")) {
                purchaseOrderSchedule.setOrderCode(orderCode);
            }if (!materialName.equals("")) {
                purchaseOrderSchedule.setMaterialName(materialName);
            }if (orderSpecifications != null && orderSpecifications != "") {
                purchaseOrderSchedule.setOrderSpecifications(orderSpecifications);
            }if (!orderQuality.equals("")) {
                purchaseOrderSchedule.setOrderQuality(orderQuality);
            }if (!orderBrand.equals("")) {
                purchaseOrderSchedule.setOrderBrand(orderBrand);
            }if (!orderCompany.equals("") && !orderCompany.equals(null)) {
                purchaseOrderSchedule.setOrderCompany(orderCompany);
            }if (orderQuantity != null && orderQuantity != "") {
                purchaseOrderSchedule.setOrderQuantity(Integer.parseInt(orderQuantity));
            }if (unitPrice != null && unitPrice  != "") {
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                purchaseOrderSchedule.setUnitPrice(unitPriceTo);
            }if (orderMoney != null && orderMoney != "") {
                BigDecimal orderMoneyTo = new BigDecimal(orderMoney);
                purchaseOrderSchedule.setOrderMoney(orderMoneyTo);
            }if (orderRemarks != null && orderRemarks != "") {
                purchaseOrderSchedule.setOrderRemarks(orderRemarks);
            }
            //添加到数据库
            baseMapper.savePurchaseOrderSchedule(purchaseOrderSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseClosed(PurchaseClosed purchaseClosed) {
        LambdaQueryWrapper<PurchaseClosed> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public PurchaseClosed queryPurchaseClosedList(Long id) {

        return baseMapper.queryPurchaseClosedList(id);
    }
}

package com.erp.purchase.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.*;
import com.erp.purchase.mapper.PurchaseOrderMapper;
import com.erp.purchase.service.IPurchaseOrderService;
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
 * 采购订单表 Service实现
 *
 * @author qiufeng
 * @date 2022-02-20 14:25:03
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements IPurchaseOrderService {

    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public IPage<PurchaseOrder> findPurchaseOrders(QueryRequest request, PurchaseOrder purchaseOrder) {
        Page<PurchaseParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseOrder(purchaseOrder));
        return baseMapper.findPurchaseOrderPage(page,purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> findPurchaseOrders(PurchaseOrder purchaseOrder) {
	    LambdaQueryWrapper<PurchaseOrder> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createPurchaseOrder(String purchaseOrder, String dataTable) throws ParseException {
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        PurchaseOrder purchaseOrderDate = new PurchaseOrder();
        /*单号案例 LWLCG22020155*/
        String initials = "LWLCG";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);

        JSONObject object = JSON.parseObject(purchaseOrder);
        String  userName =  object.getString("userName");
        String  purchaseRequisitionDate =  object.getString("purchaseRequisitionDate");
        String  supplierName =  object.getString("supplierName");
        String  currencyId =  object.getString("currencyId");
        String  taxRateId =  object.getString("taxRateId");
        String  paymentMethod =  object.getString("paymentMethod");
        String  orderDeposit =  object.getString("orderDeposit");
        String  invoiceBillingSituation =  object.getString("invoiceBillingSituation");
       //查询出最后一个工作安排单号
        PurchaseOrder purchaseOrderOne = null;
        purchaseOrderOne = baseMapper.queryPurchaseOrder();
        if (purchaseOrderOne != null){
            String oddNumbers = purchaseOrderOne.getOrderNumber();
            /*单号案例 LWLCG22020155*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchaseOrderDate.setOrderNumber(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchaseOrderDate.setOrderNumber(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        //制单人
        purchaseOrderDate.setOrderPreparer(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        //制单日期
        purchaseOrderDate.setOrderPreparationDate(today);//把获取系统当前时间赋值给实体对象
        Date purchaseRequisitionDateOne = simpleDateFormatOne.parse(purchaseRequisitionDate);//格式化系统当前时间
        purchaseOrderDate.setPurchaseRequisitionDate(purchaseRequisitionDateOne);
        purchaseOrderDate.setSupplierName(supplierName);
        purchaseOrderDate.setCurrencyId(Long.parseLong(currencyId));
        purchaseOrderDate.setTaxRateId(Long.parseLong(taxRateId));
        purchaseOrderDate.setPaymentMethod(paymentMethod);
        if (!orderDeposit.equals("")) {
            BigDecimal orderDepositTo = new BigDecimal(orderDeposit);
            purchaseOrderDate.setOrderDeposit(orderDepositTo);
        }
        purchaseOrderDate.setInvoiceBillingSituation(invoiceBillingSituation);
        //PurchaseRequisitionSchedule采购申请表
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseOrderSchedule purchaseOrderSchedule = new PurchaseOrderSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            purchaseOrderSchedule.setOddNumbers(generateDocNo);
            String applicationNo = jsonArrayOne.getJSONObject(i).getString("applicationNo");
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
            }if (!deliveryDate.equals("")) {
                Date deliveryDateOne = simpleDateFormatOne.parse(deliveryDate);//格式化系统当前时间
                purchaseOrderSchedule.setDeliveryDate(deliveryDateOne);
            }if (orderRemarks != null && orderRemarks != "") {
                purchaseOrderSchedule.setOrderRemarks(orderRemarks);
            }
            //添加到数据库
            baseMapper.savePurchaseOrderSchedule(purchaseOrderSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseOrderDate(purchaseOrderDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        this.saveOrUpdate(purchaseOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        LambdaQueryWrapper<PurchaseOrder> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<PurchaseOrderSchedule> queryPurchaseOrderSchedule(String oddNumbers) {


        return baseMapper.queryPurchaseOrderSchedule(oddNumbers);
    }
}

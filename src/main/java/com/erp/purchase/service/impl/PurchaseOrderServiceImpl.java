package com.erp.purchase.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseOrder;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.mapper.PurchaseOrderMapper;
import com.erp.purchase.service.IPurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            String orderSubclass = jsonArrayOne.getJSONObject(i).getString("orderSubclass");
            String orderCategory = jsonArrayOne.getJSONObject(i).getString("orderCategory");
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
            }if (orderSubclass != null && orderSubclass != "") {
                purchaseOrderSchedule.setOrderSubclass(orderSubclass);
            }if (orderCategory != null && orderSubclass != "") {
                purchaseOrderSchedule.setOrderCategory(orderCategory);
            }
            //添加到数据库
            baseMapper.savePurchaseOrderSchedule(purchaseOrderSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseOrderDate(purchaseOrderDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseOrder(String orderNumber, String dataTable) throws ParseException  {
        baseMapper.deletePurchaseOrderSchedule(orderNumber);
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseOrderSchedule purchaseOrderSchedule = new PurchaseOrderSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            purchaseOrderSchedule.setOddNumbers(orderNumber);
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
            String orderSubclass = jsonArrayOne.getJSONObject(i).getString("orderSubclass");
            String orderCategory = jsonArrayOne.getJSONObject(i).getString("orderCategory");
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
            }if (orderSubclass != null && orderSubclass != "") {
                purchaseOrderSchedule.setOrderSubclass(orderSubclass);
            }if (orderCategory != null && orderSubclass != "") {
                purchaseOrderSchedule.setOrderCategory(orderCategory);
            }
            //添加到数据库
            baseMapper.savePurchaseOrderSchedule(purchaseOrderSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseOrder(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public List<PurchaseOrderSchedule> queryPurchaseOrderSchedule(String oddNumbers) {


        return baseMapper.queryPurchaseOrderSchedule(oddNumbers);
    }

    @Override
    public PurchaseOrder queryPurchaseOrderList(Long id) {

        return baseMapper.queryPurchaseOrderList(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmPurchaseOrder(String ids) {
        baseMapper.confirmPurchaseOrder(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPurchaseOrder(String ids) {
        baseMapper.cancelPurchaseOrder(ids);
    }

    @Override
    public IPage<PurchaseOrder> findPurchaseClosedQueryPage(QueryRequest request, PurchaseOrder purchaseOrder) {
        Page<PurchaseOrder> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseOrder(purchaseOrder));
        return baseMapper.findPurchaseClosedQueryPage(page,purchaseOrder);
    }

    //删除整单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseRequisitionOrderNumber(String orderNumber) {
        baseMapper.deletePurchaseOrderSchedule(orderNumber);
        baseMapper.deletePurchaseRequisitionOrderNumber(orderNumber);
    }

    //根据供应商名称查询订单
    @Override
    public IPage<PurchaseOrder> queryPurchaseInspectionOrder(QueryRequest request, String supplierName) {
        Page<PurchaseParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseInspectionOrder(supplierName));
        return baseMapper.queryPurchaseInspectionOrder(page,supplierName);
    }

    @Override
    public IPage<PurchaseOrder> findPurchasePriceChanges(QueryRequest request, PurchaseOrder purchaseOrder) {
        Page<PurchaseParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchasePriceChanges(purchaseOrder));
        return baseMapper.findPurchasePriceChanges(page,purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> purchasePriceChangesExport(PurchaseOrder purchaseOrder) {
        return baseMapper.purchasePriceChangesExport(purchaseOrder);
    }
}

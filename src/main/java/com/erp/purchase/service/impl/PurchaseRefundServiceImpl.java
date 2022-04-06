package com.erp.purchase.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.*;
import com.erp.purchase.mapper.PurchaseRefundMapper;
import com.erp.purchase.mapper.PurchaseSettlementMapper;
import com.erp.purchase.service.IPurchaseRefundService;
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
 * 采购退货 Service实现
 *
 * @author qiufeng
 * @date 2022-03-28 15:35:47
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseRefundServiceImpl extends ServiceImpl<PurchaseRefundMapper, PurchaseRefund> implements IPurchaseRefundService {

    private final PurchaseRefundMapper purchaseRefundMapper;

    private final PurchaseSettlementMapper purchaseSettlementMapper;

    @Override
    public IPage<PurchaseRefund> findPurchaseRefunds(QueryRequest request, PurchaseRefund purchaseRefund) {
        Page<PurchaseRefund> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseRefund(purchaseRefund));
        return baseMapper.findPurchaseRefundPage(page,purchaseRefund);
    }

    @Override
    public List<PurchaseRefund> findPurchaseRefunds(PurchaseRefund purchaseRefund) {
	    LambdaQueryWrapper<PurchaseRefund> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseRefund(String purchaseRefund, String dataTable) throws ParseException {
        /*采购退货*/
        PurchaseRefund purchaseRefundDate = new PurchaseRefund();
        /*LWLTH22030003*/
        String initials = "LWLTH";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);

        JSONObject object = JSON.parseObject(purchaseRefund);
        String  userName =  object.getString("userName");
        String  supplierName =  object.getString("supplierName");
        String  currencyId =  object.getString("currencyId");
        String  taxRateId =  object.getString("taxRateId");
        String  refundLibrary =  object.getString("refundLibrary");
        //查询最后一个单号
        PurchaseRefund purchaseRefundOne = null;
        purchaseRefundOne = baseMapper.queryPurchaseRefund();
        if (purchaseRefundOne != null){
            String oddNumbers = purchaseRefundOne.getRefundNumber();
            /*单号案例 LWLCG22020155*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchaseRefundDate.setRefundNumber(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchaseRefundDate.setRefundNumber(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        //制单人
        purchaseRefundDate.setRefundPreparer(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        //制单日期
        purchaseRefundDate.setRefundPreparationDate(today);//把获取系统当前时间赋值给实体对象
        purchaseRefundDate.setRefundState("1");
        purchaseRefundDate.setRefundDate(today);
        purchaseRefundDate.setSupplierName(supplierName);
        purchaseRefundDate.setCurrencyId(Long.parseLong(currencyId));
        purchaseRefundDate.setTaxRateId(Long.parseLong(taxRateId));
        purchaseRefundDate.setRefundLibrary(refundLibrary);

        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        /*采购退货附表*/
        PurchaseRefundSchedule purchaseRefundSchedule = new PurchaseRefundSchedule();
        /*采购结果附表*/
        PurchaseSettlementSchedule purchaseSettlementSchedule = new PurchaseSettlementSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseRefundSchedule.setRefundNumber(generateDocNo);
            purchaseSettlementSchedule.setSettlementNumber(generateDocNo);
            String refundLibraryOnt = jsonArrayOne.getJSONObject(i).getString("refundLibrary");
            String supplierNameOnt = jsonArrayOne.getJSONObject(i).getString("supplierName");
            String refundCode = jsonArrayOne.getJSONObject(i).getString("refundCode");
            String refundName = jsonArrayOne.getJSONObject(i).getString("refundName");
            String refundSpecifications = jsonArrayOne.getJSONObject(i).getString("refundSpecifications");
            String refundQuality = jsonArrayOne.getJSONObject(i).getString("refundQuality");
            String refundCompany = jsonArrayOne.getJSONObject(i).getString("refundCompany");
            String refundPrice = jsonArrayOne.getJSONObject(i).getString("refundPrice");
            Long refundAmount = jsonArrayOne.getJSONObject(i).getLong("refundAmount");
            String refundMoney = jsonArrayOne.getJSONObject(i).getString("refundMoney");
            String refundRemarks = jsonArrayOne.getJSONObject(i).getString("refundRemarks");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String refundBrand = jsonArrayOne.getJSONObject(i).getString("refundBrand");
            String refundSubclass = jsonArrayOne.getJSONObject(i).getString("refundSubclass");
            String refundCategory = jsonArrayOne.getJSONObject(i).getString("refundCategory");
            if (!refundLibraryOnt.equals("")) {
                purchaseRefundSchedule.setRefundLibrary(refundLibraryOnt);
            }if (!supplierNameOnt.equals("")) {
                purchaseRefundSchedule.setSupplierName(supplierNameOnt);
            }if (!refundCode.equals("")) {
                purchaseRefundSchedule.setRefundCode(refundCode);
                purchaseSettlementSchedule.setSettlementCode(refundCode);
            }if (!refundName.equals("")) {
                purchaseRefundSchedule.setRefundName(refundName);
                purchaseSettlementSchedule.setSettlementName(refundName);
            }if (!refundSpecifications.equals("")) {
                purchaseRefundSchedule.setRefundSpecifications(refundSpecifications);
                purchaseSettlementSchedule.setSettlementSpecifications(refundSpecifications);
            }if (!refundQuality.equals("")) {
                purchaseRefundSchedule.setRefundQuality(refundQuality);
                purchaseSettlementSchedule.setSettlementQuality(refundQuality);
            }if (!refundCompany.equals("")) {
                purchaseRefundSchedule.setRefundCompany(refundCompany);
                purchaseSettlementSchedule.setSettlementCompany(refundCompany);
            }if (!refundAmount.equals("")) {
                purchaseRefundSchedule.setRefundQuantity(refundAmount);
                purchaseSettlementSchedule.setSettlementQuantity(refundAmount);
            }if (!refundPrice.equals("")) {
                BigDecimal refundPriceOnt = new BigDecimal(refundPrice);
                purchaseRefundSchedule.setRefundPrice(refundPriceOnt);
                purchaseSettlementSchedule.setSettlementPrice(refundPriceOnt);
            }if (!refundMoney.equals("")) {
                BigDecimal refundMoneyOnt = new BigDecimal(refundMoney);
                purchaseRefundSchedule.setRefundMoney(refundMoneyOnt);
                purchaseSettlementSchedule.setSettlementMoney(refundMoneyOnt);
            }if (!refundSpecifications.equals("")) {
                purchaseRefundSchedule.setRefundSpecifications(refundSpecifications);
            }if (!refundRemarks.equals("")) {
                purchaseRefundSchedule.setRefundRemarks(refundRemarks);
                purchaseSettlementSchedule.setSettlementRemarks(refundRemarks);
            }if (!orderNumber.equals("")) {
                purchaseRefundSchedule.setOrderNumber(orderNumber);
            }if (!refundBrand.equals("")) {
                purchaseRefundSchedule.setRefundBrand(refundBrand);
                purchaseSettlementSchedule.setSettlementBrand(refundBrand);
            }if (!refundSubclass.equals("")) {
                purchaseRefundSchedule.setRefundSubclass(refundSubclass);
                purchaseSettlementSchedule.setSettlementSubclass(refundSubclass);
            }if (!refundCategory.equals("")) {
                purchaseRefundSchedule.setRefundCategory(refundCategory);
                purchaseSettlementSchedule.setSettlementCategory(refundCategory);
            }
            //添加到数据库
            baseMapper.savePurchaseRefundSchedule(purchaseRefundSchedule);
            /*添加到采购结果附表数据*/
            purchaseSettlementMapper.savePurchaseSettlementSchedule(purchaseSettlementSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseRefundDate(purchaseRefundDate);

        /*采购结果*/
        PurchaseSettlement purchaseSettlement = new PurchaseSettlement();
        purchaseSettlement.setSettlementState("1");
        purchaseSettlement.setSettlementDate(today);
        purchaseSettlement.setSettlementNumbers(generateDocNo);
        purchaseSettlement.setSettlementUse("1");
        purchaseSettlement.setSettlementSupplier(supplierName);
        purchaseSettlement.setSettlementLibrary(refundLibrary);
        purchaseSettlement.setCurrencyId(Long.parseLong(currencyId));
        purchaseSettlement.setTaxRateId(Long.parseLong(taxRateId));
        purchaseSettlement.setSettlementInvoice(Long.parseLong("1"));

        purchaseSettlementMapper.savePurchaseSettlementDate(purchaseSettlement);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseRefund(String refundNumber, String dataTable) throws ParseException {
        baseMapper.deletePurchaseRefundSchedule(refundNumber);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseRefundSchedule purchaseRefundSchedule = new PurchaseRefundSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseRefundSchedule.setRefundNumber(refundNumber);
            String refundLibraryOnt = jsonArrayOne.getJSONObject(i).getString("refundLibrary");
            String supplierNameOnt = jsonArrayOne.getJSONObject(i).getString("supplierName");
            String refundCode = jsonArrayOne.getJSONObject(i).getString("refundCode");
            String refundName = jsonArrayOne.getJSONObject(i).getString("refundName");
            String refundSpecifications = jsonArrayOne.getJSONObject(i).getString("refundSpecifications");
            String refundQuality = jsonArrayOne.getJSONObject(i).getString("refundQuality");
            String refundCompany = jsonArrayOne.getJSONObject(i).getString("refundCompany");
            String refundPrice = jsonArrayOne.getJSONObject(i).getString("refundPrice");
            String refundMoney = jsonArrayOne.getJSONObject(i).getString("refundMoney");
            String refundRemarks = jsonArrayOne.getJSONObject(i).getString("refundRemarks");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String refundBrand = jsonArrayOne.getJSONObject(i).getString("refundBrand");
            Long refundQuantity = jsonArrayOne.getJSONObject(i).getLong("refundQuantity");
            String refundSubclass = jsonArrayOne.getJSONObject(i).getString("refundSubclass");
            String refundCategory = jsonArrayOne.getJSONObject(i).getString("refundCategory");

            if (!refundLibraryOnt.equals("")) {
                purchaseRefundSchedule.setRefundLibrary(refundLibraryOnt);
            }
            if (!supplierNameOnt.equals("")) {
                purchaseRefundSchedule.setSupplierName(supplierNameOnt);
            }
            if (!refundCode.equals("")) {
                purchaseRefundSchedule.setRefundCode(refundCode);
            }
            if (!refundName.equals("")) {
                purchaseRefundSchedule.setRefundName(refundName);
            }
            if (!refundSpecifications.equals("")) {
                purchaseRefundSchedule.setRefundSpecifications(refundSpecifications);
            }
            if (!refundQuality.equals("")) {
                purchaseRefundSchedule.setRefundQuality(refundQuality);
            }
            if (!refundCompany.equals("")) {
                purchaseRefundSchedule.setRefundCompany(refundCompany);
            }
            if (!refundQuantity.equals("")) {
                purchaseRefundSchedule.setRefundQuantity(refundQuantity);
            }
            if (!refundPrice.equals("")) {
                BigDecimal refundPriceOnt = new BigDecimal(refundPrice);
                purchaseRefundSchedule.setRefundPrice(refundPriceOnt);
            }
            if (!refundMoney.equals("")) {
                BigDecimal refundMoneyOnt = new BigDecimal(refundMoney);
                purchaseRefundSchedule.setRefundMoney(refundMoneyOnt);
            }
            if (!refundSpecifications.equals("")) {
                purchaseRefundSchedule.setRefundSpecifications(refundSpecifications);
            }
            if (!refundRemarks.equals("")) {
                purchaseRefundSchedule.setRefundRemarks(refundRemarks);
            }
            if (!orderNumber.equals("")) {
                purchaseRefundSchedule.setOrderNumber(orderNumber);
            }
            if (!refundBrand.equals("")) {
                purchaseRefundSchedule.setRefundBrand(refundBrand);
            }
            if (!refundSubclass.equals("")) {
                purchaseRefundSchedule.setRefundSubclass(refundSubclass);
            }
            if (!refundCategory.equals("")) {
                purchaseRefundSchedule.setRefundCategory(refundCategory);
            }
            //添加到数据库
            baseMapper.savePurchaseRefundSchedule(purchaseRefundSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseRefund(String refundNumber) {
        baseMapper.deletePurchaseRefund(refundNumber);
        baseMapper.deletePurchaseRefundSchedule(refundNumber);
	}

    @Override
    public List<PurchaseRefundSchedule> queryPurchaseRefundSchedule(String oddNumbers) {
        return baseMapper.queryPurchaseRefundSchedule(oddNumbers);
    }

    @Override
    public IPage<WarehouseStorageSchedule> findPurchaseRefundAddQueryPage(QueryRequest request, WarehouseStorageSchedule warehouseStorage) {
        Page<WarehouseStorageSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseRefundAddQuery(warehouseStorage));
        return baseMapper.findPurchaseRefundAddQueryPage(page,warehouseStorage);
    }

    @Override
    public PurchaseRefund findPurchaseRefundQueryPage(Long id) {
        return baseMapper.findPurchaseRefundQueryPage(id);
    }
}

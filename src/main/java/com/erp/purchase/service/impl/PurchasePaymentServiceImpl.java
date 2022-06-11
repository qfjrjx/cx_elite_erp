package com.erp.purchase.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseInvoice;
import com.erp.purchase.entity.PurchaseOrderSchedule;
import com.erp.purchase.entity.PurchasePayment;
import com.erp.purchase.entity.PurchasePaymentSchedule;
import com.erp.purchase.mapper.PurchaseOrderMapper;
import com.erp.purchase.mapper.PurchasePaymentMapper;
import com.erp.purchase.service.IPurchasePaymentService;
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
 * 采购付款 Service实现
 *
 * @author qiufeng
 * @date 2022-04-12 09:08:21
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchasePaymentServiceImpl extends ServiceImpl<PurchasePaymentMapper, PurchasePayment> implements IPurchasePaymentService {

    private final PurchasePaymentMapper purchasePaymentMapper;

    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public IPage<PurchasePayment> findPurchasePayments(QueryRequest request, PurchasePayment purchasePayment) {
        Page<PurchasePayment> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchasePayment(purchasePayment));
            return baseMapper.findPurchasePaymentPage(page,purchasePayment);
    }

    @Override
    public List<PurchasePayment> findPurchasePayments(PurchasePayment purchasePayment) {
	    LambdaQueryWrapper<PurchasePayment> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchasePayment(String purchasePayment, String dataTable) throws ParseException {
        PurchasePayment purchasePaymentDate = new PurchasePayment();

        /*LCWFK21100163*/
        String initials = "LCWFK";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);

        JSONObject object = JSON.parseObject(purchasePayment);
        String  userName =  object.getString("userName");
        String  paymentSupplier =  object.getString("paymentSupplier");
        String purchaseRequisitionDate =  object.getString("purchaseRequisitionDate");
        Long  currencyName =  object.getLong("currencyName");
        String  paymentCode =  object.getString("paymentCode");
        String  paymentType =  object.getString("paymentType");
        String paymentWay = object.getString("paymentWay");
        String  paymentMoney =  object.getString("paymentMoney");
        String  paymentInvoice =  object.getString("paymentInvoice");
        String paymentRemarks = object.getString("paymentRemarks");

        //查询最后一个单号
        PurchasePayment PurchasePaymentOne = null;
        PurchasePaymentOne = baseMapper.queryPurchasePayment();
        if (PurchasePaymentOne != null){
            String oddNumbers = PurchasePaymentOne.getPaymentNumber();
            /*单号案例 LCWFK21100165*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchasePaymentDate.setPaymentNumber(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchasePaymentDate.setPaymentNumber(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }

        purchasePaymentDate.setPaymentState("1");
        purchasePaymentDate.setPaymentShape("2");
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间

        Date orderDateOne = simpleDateFormatTwo.parse(purchaseRequisitionDate);//格式化数据，取当前时间结果
        purchasePaymentDate.setPaymentDate(orderDateOne);
        purchasePaymentDate.setPaymentSupplier(paymentSupplier);
        purchasePaymentDate.setCurrencyId(currencyName);
        purchasePaymentDate.setPaymentType(paymentType);
        purchasePaymentDate.setPaymentWay(paymentWay);
        purchasePaymentDate.setPaymentInvoice(paymentInvoice);
        purchasePaymentDate.setPaymentOperation(userName);
        purchasePaymentDate.setPaymentOperationDate(today);
        purchasePaymentDate.setPaymentRemarks(paymentRemarks);
        purchasePaymentDate.setPaymentCode(paymentCode);
        BigDecimal paymentMoneyOtn = new BigDecimal(paymentMoney);
        purchasePaymentDate.setPaymentMoney(paymentMoneyOtn);

        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchasePaymentSchedule purchasePaymentSchedule = new PurchasePaymentSchedule();
        for (int i = 0; i < jsonArrayOne.size(); i++){
            purchasePaymentSchedule.setPaymentNumber(generateDocNo);
            Date invoiceDate = jsonArrayOne.getJSONObject(i).getDate("invoiceDate");
            String invoiceNumbers = jsonArrayOne.getJSONObject(i).getString("invoiceNumbers");
            String invoiceMoney = jsonArrayOne.getJSONObject(i).getString("invoiceMoney");
            String prepaidMoney = jsonArrayOne.getJSONObject(i).getString("prepaidMoney");
            String thePayment = jsonArrayOne.getJSONObject(i).getString("thePayment");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String invoiceName = jsonArrayOne.getJSONObject(i).getString("invoiceName");
            String invoiceCoding = jsonArrayOne.getJSONObject(i).getString("invoiceCoding");
            String invoiceBrand = jsonArrayOne.getJSONObject(i).getString("invoiceBrand");
            String invoiceSpecifications = jsonArrayOne.getJSONObject(i).getString("invoiceSpecifications");
            String invoiceCompany = jsonArrayOne.getJSONObject(i).getString("invoiceCompany");
            String invoiceQuantity = jsonArrayOne.getJSONObject(i).getString("invoiceQuantity");
            String taxPrice = jsonArrayOne.getJSONObject(i).getString("taxPrice");
            String taxMoney = jsonArrayOne.getJSONObject(i).getString("taxMoney");
            String invoiceRemarks = jsonArrayOne.getJSONObject(i).getString("invoiceRemarks");
            String inspectionSubclass = jsonArrayOne.getJSONObject(i).getString("inspectionSubclass");

            //取上次采购单价
            PurchaseOrderSchedule purchaseOrderSchedule = purchaseOrderMapper.queryPaymentPurchaseOrderSchedule(invoiceSpecifications);
            String procurementPrice = "0";
            BigDecimal unitPrice = purchaseOrderSchedule.getUnitPrice();
            if (!unitPrice.equals("")){
                purchasePaymentSchedule.setProcurementPrice(unitPrice);
            }else {
                BigDecimal procurementPriceOtn = new BigDecimal(procurementPrice);
                purchasePaymentSchedule.setProcurementPrice(procurementPriceOtn);
            }
            //取前次采购单价
            PurchaseOrderSchedule previous = purchaseOrderMapper.queryPaymentPrevious(invoiceSpecifications);
            String previousPrice = "0";
            BigDecimal previousUnitPrice = previous.getUnitPrice();
            if (!previousUnitPrice.equals("")){
                purchasePaymentSchedule.setPreviousPrice(previousUnitPrice);
            }else {
                BigDecimal procurementPriceOtn = new BigDecimal(previousPrice);
                purchasePaymentSchedule.setPreviousPrice(procurementPriceOtn);
            }
            purchasePaymentSchedule.setInvoiceDate(invoiceDate);
            purchasePaymentDate.setPaymentCode(paymentCode);
            purchasePaymentSchedule.setInvoiceBrand(invoiceBrand);
            if (!invoiceNumbers.equals("")){
                purchasePaymentSchedule.setInvoiceNumbers(invoiceNumbers);
            }if (!invoiceMoney.equals("")){
                BigDecimal invoiceMoneyOtn = new BigDecimal(invoiceMoney);
                purchasePaymentSchedule.setInvoiceMoney(invoiceMoneyOtn);
            }if (!prepaidMoney.equals("")){
                BigDecimal prepaidMoneyOtn = new BigDecimal(prepaidMoney);
                purchasePaymentSchedule.setPrepaidMoney(prepaidMoneyOtn);
            }if (!thePayment.equals("")){
                BigDecimal thePaymentOtn = new BigDecimal(thePayment);
                purchasePaymentSchedule.setThePayment(thePaymentOtn);
            }if (!orderNumber.equals("")){
                purchasePaymentSchedule.setPaymentCode(orderNumber);
            }if (!invoiceName.equals("")){
                purchasePaymentSchedule.setInvoiceName(invoiceName);
            }if (!invoiceCoding.equals("")){
                purchasePaymentSchedule.setInvoiceCoding(invoiceCoding);
            }if (!invoiceSpecifications.equals("")){
                purchasePaymentSchedule.setInvoiceSpecifications(invoiceSpecifications);
            }if (!invoiceCompany.equals("")){
                purchasePaymentSchedule.setInvoiceCompany(invoiceCompany);
            }if (!invoiceQuantity.equals("")){
                purchasePaymentSchedule.setInvoiceQuantity(invoiceQuantity);
            }if (!taxPrice.equals("")){
                BigDecimal taxPriceOtn = new BigDecimal(taxPrice);
                purchasePaymentSchedule.setTaxPrice(taxPriceOtn);
            }if (!taxMoney.equals("")){
                BigDecimal taxMoneyOtn = new BigDecimal(taxMoney);
                purchasePaymentSchedule.setTaxMoney(taxMoneyOtn);
            }
            purchasePaymentSchedule.setInvoiceRemarks(invoiceRemarks);
            purchasePaymentSchedule.setInspectionSubclass(inspectionSubclass);
            purchasePaymentDate.setPaymentCode(orderNumber);
            baseMapper.savePurchasePaymentSchedule(purchasePaymentSchedule);
            PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
            purchaseInvoice.setPaymentState("2");
            purchaseInvoice.setInvoiceNumbers(invoiceNumbers);
            baseMapper.updatePaymentState(purchaseInvoice);
        }
        baseMapper.savePurchasePaymentData(purchasePaymentDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchasePayment(String paymentNumber ,String dataTable) throws ParseException {
        baseMapper.deletePurchasePaymentSchedule(paymentNumber);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchasePaymentSchedule purchasePaymentSchedule = new PurchasePaymentSchedule();
        for (int i = 0; i < jsonArrayOne.size(); i++){

            purchasePaymentSchedule.setPaymentNumber(paymentNumber);
            Date invoiceDate = jsonArrayOne.getJSONObject(i).getDate("invoiceDate");
            String invoiceNumbers = jsonArrayOne.getJSONObject(i).getString("invoiceNumbers");
            String invoiceMoney = jsonArrayOne.getJSONObject(i).getString("invoiceMoney");
            String prepaidMoney = jsonArrayOne.getJSONObject(i).getString("prepaidMoney");
            String thePayment = jsonArrayOne.getJSONObject(i).getString("thePayment");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");

            String invoiceName = jsonArrayOne.getJSONObject(i).getString("invoiceName");
            String invoiceCoding = jsonArrayOne.getJSONObject(i).getString("invoiceCoding");
            String invoiceBrand = jsonArrayOne.getJSONObject(i).getString("invoiceBrand");
            String invoiceSpecifications = jsonArrayOne.getJSONObject(i).getString("invoiceSpecifications");
            String invoiceCompany = jsonArrayOne.getJSONObject(i).getString("invoiceCompany");
            String invoiceQuantity = jsonArrayOne.getJSONObject(i).getString("invoiceQuantity");
            String taxPrice = jsonArrayOne.getJSONObject(i).getString("taxPrice");
            String taxMoney = jsonArrayOne.getJSONObject(i).getString("taxMoney");
            String invoiceRemarks = jsonArrayOne.getJSONObject(i).getString("invoiceRemarks");
            String inspectionSubclass = jsonArrayOne.getJSONObject(i).getString("inspectionSubclass");
            //取上次采购单价
            PurchaseOrderSchedule purchaseOrderSchedule = purchaseOrderMapper.queryPaymentPurchaseOrderSchedule(invoiceName);
            String procurementPrice = "0";
            BigDecimal unitPrice = purchaseOrderSchedule.getUnitPrice();
            if (!unitPrice.equals("")){
                purchasePaymentSchedule.setProcurementPrice(unitPrice);
            }else {
                BigDecimal procurementPriceOtn = new BigDecimal(procurementPrice);
                purchasePaymentSchedule.setProcurementPrice(procurementPriceOtn);
            }
            //取前次采购单价
            PurchaseOrderSchedule previous = purchaseOrderMapper.queryPaymentPrevious(invoiceSpecifications);
            String previousPrice = "0";
            BigDecimal previousUnitPrice = previous.getUnitPrice();
            if (!previousUnitPrice.equals("")){
                purchasePaymentSchedule.setPreviousPrice(previousUnitPrice);
            }else {
                BigDecimal procurementPriceOtn = new BigDecimal(previousPrice);
                purchasePaymentSchedule.setPreviousPrice(procurementPriceOtn);
            }
            purchasePaymentSchedule.setInvoiceDate(invoiceDate);
            purchasePaymentSchedule.setInvoiceBrand(invoiceBrand);
            if (!invoiceNumbers.equals("")){
                purchasePaymentSchedule.setInvoiceNumbers(invoiceNumbers);
            }if (!invoiceMoney.equals("")){
                BigDecimal invoiceMoneyOtn = new BigDecimal(invoiceMoney);
                purchasePaymentSchedule.setInvoiceMoney(invoiceMoneyOtn);
            }if (!prepaidMoney.equals("")){
                BigDecimal prepaidMoneyOtn = new BigDecimal(prepaidMoney);
                purchasePaymentSchedule.setPrepaidMoney(prepaidMoneyOtn);
            }if (!thePayment.equals("")){
                BigDecimal thePaymentOtn = new BigDecimal(thePayment);
                purchasePaymentSchedule.setThePayment(thePaymentOtn);
            }if (!orderNumber.equals("")){
                purchasePaymentSchedule.setPaymentCode(orderNumber);
            }if (!invoiceName.equals("")){
                purchasePaymentSchedule.setInvoiceName(invoiceName);
            }if (!invoiceCoding.equals("")){
                purchasePaymentSchedule.setInvoiceCoding(invoiceCoding);
            }if (!invoiceSpecifications.equals("")){
                purchasePaymentSchedule.setInvoiceSpecifications(invoiceSpecifications);
            }if (!invoiceCompany.equals("")){
                purchasePaymentSchedule.setInvoiceCompany(invoiceCompany);
            }if (!invoiceQuantity.equals("")){
                purchasePaymentSchedule.setInvoiceQuantity(invoiceQuantity);
            }if (!taxPrice.equals("")){
                BigDecimal taxPriceOtn = new BigDecimal(taxPrice);
                purchasePaymentSchedule.setTaxPrice(taxPriceOtn);
            }if (!taxMoney.equals("")){
                BigDecimal taxMoneyOtn = new BigDecimal(taxMoney);
                purchasePaymentSchedule.setTaxMoney(taxMoneyOtn);
            }if (!invoiceRemarks.equals("")){
                purchasePaymentSchedule.setInvoiceRemarks(invoiceRemarks);
            }
            purchasePaymentSchedule.setInspectionSubclass(inspectionSubclass);
            baseMapper.savePurchasePaymentSchedule(purchasePaymentSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchasePayment(String paymentNumber) {
        String[] parts = paymentNumber.split(",");
        String paymentNumberData = parts[0]; // id
        String invoiceNumbers = parts[1]; // 单号
        baseMapper.deletePurchasePaymentSchedule(paymentNumberData);
        baseMapper.deletePurchasePayment(paymentNumberData);
        baseMapper.updateInvoiceNumbers(invoiceNumbers);
	}

    @Override
    public PurchasePayment findPurchasePaymentQueryPage(String id) {
        return baseMapper.findPurchasePaymentQueryPage(id);
    }

    @Override
    public List<PurchasePaymentSchedule> queryPurchasePaymentSchedule(String paymentNumber) {
        return baseMapper.queryPurchasePaymentSchedule(paymentNumber);
    }

    @Override
    public void confirmPurchasePayment(String ids) {
        baseMapper.confirmPurchasePayment(ids);
    }

    @Override
    public void cancelPurchasePayment(String ids) {
        baseMapper.cancelPurchasePayment(ids);
    }

    @Override
    public void paymentPurchasePayment(String ids)  throws ParseException {
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        PurchasePayment purchasePayment = new PurchasePayment();
        purchasePayment.setPaymentShapeDate(today);
        baseMapper.paymentPurchasePayment(ids,purchasePayment);
    }

    @Override
    public IPage<PurchasePaymentSchedule> purchasePriceChangesQuery(QueryRequest request, PurchasePaymentSchedule purchasePaymentSchedule) {
        Page<PurchasePaymentSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchasePaymentSchedule(purchasePaymentSchedule));
        return baseMapper.findPurchasePaymentSchedulePage(page,purchasePaymentSchedule);
    }
}

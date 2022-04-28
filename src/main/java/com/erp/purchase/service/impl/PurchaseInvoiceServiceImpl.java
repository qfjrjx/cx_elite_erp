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
import com.erp.purchase.entity.PurchaseInvoiceSchedule;
import com.erp.purchase.mapper.PurchaseInvoiceMapper;
import com.erp.purchase.service.IPurchaseInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 采购发票 Service实现
 *
 * @author qiufeng
 * @date 2022-04-06 09:34:31
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseInvoiceServiceImpl extends ServiceImpl<PurchaseInvoiceMapper, PurchaseInvoice> implements IPurchaseInvoiceService {

    private final PurchaseInvoiceMapper purchaseInvoiceMapper;

    @Override
    public IPage<PurchaseInvoice> findPurchaseInvoices(QueryRequest request, PurchaseInvoice purchaseInvoice) {
        Page<PurchaseInvoice> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseInvoice(purchaseInvoice));
        return baseMapper.findPurchaseInvoicePage(page,purchaseInvoice);
    }

    @Override
    public List<PurchaseInvoice> findPurchaseInvoices(PurchaseInvoice purchaseInvoice) {
	    LambdaQueryWrapper<PurchaseInvoice> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseInvoice(String purchaseInvoice, String dataTable) throws ParseException {
        PurchaseInvoice purchaseInvoiceData = new PurchaseInvoice();
        JSONObject object = JSON.parseObject(purchaseInvoice);
        String  userName =  object.getString("userName");
        String  invoiceSupplier =  object.getString("invoiceSupplier");
        String  invoiceNumbers =  object.getString("invoiceNumbers");
        Long  currencyName =  object.getLong("currencyName");
        Long  taxRateName =  object.getLong("taxRateName");
        String  invoiceRemarks =  object.getString("invoiceRemarks");
        String purchaseRequisitionDate = object.getString("purchaseRequisitionDate");

        //登记人
        purchaseInvoiceData.setRegistration(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        //制单日期
        purchaseInvoiceData.setRegistrationDate(today);//把获取系统当前时间赋值给实体对象

        //Date invoiceTime = simpleDateFormatTwo.parse(purchaseRequisitionDate);
        purchaseInvoiceData.setInvoiceDate(today);
        purchaseInvoiceData.setInvoiceState("1");
        purchaseInvoiceData.setInvoiceNumbers(invoiceNumbers);
        purchaseInvoiceData.setInvoiceSupplier(invoiceSupplier);
        purchaseInvoiceData.setCurrencyId(currencyName);
        purchaseInvoiceData.setTaxRateId(taxRateName);
        purchaseInvoiceData.setInvoiceRemarks(invoiceRemarks);

        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseInvoiceSchedule purchaseInvoiceSchedule = new PurchaseInvoiceSchedule();
        for (int i = 0; i < jsonArrayOne.size(); i++){
            Date invoiceDate = jsonArrayOne.getJSONObject(i).getDate("invoiceDate");
            String invoiceCode = jsonArrayOne.getJSONObject(i).getString("invoiceCode");
            String invoiceUse = jsonArrayOne.getJSONObject(i).getString("invoiceUse");
            String invoiceName = jsonArrayOne.getJSONObject(i).getString("invoiceName");
            String invoiceSpecifications = jsonArrayOne.getJSONObject(i).getString("invoiceSpecifications");
            String invoiceQuantity = jsonArrayOne.getJSONObject(i).getString("invoiceQuantity");
            String taxPrice = jsonArrayOne.getJSONObject(i).getString("taxPrice");
            String invoiceMoney = jsonArrayOne.getJSONObject(i).getString("invoiceMoney");
            String invoiceDeposit = jsonArrayOne.getJSONObject(i).getString("invoiceDeposit");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String invoiceBrand = jsonArrayOne.getJSONObject(i).getString("invoiceBrand");
            String invoiceCompany = jsonArrayOne.getJSONObject(i).getString("invoiceCompany");
            String invoiceCoding = jsonArrayOne.getJSONObject(i).getString("invoiceCoding");
            String invoiceSubclass = jsonArrayOne.getJSONObject(i).getString("invoiceSubclass");
            BigDecimal invoiceDepositOtn = new BigDecimal(invoiceDeposit);
            purchaseInvoiceSchedule.setInvoiceDeposit(invoiceDepositOtn);
            if (!invoiceDate.equals("")) {
                purchaseInvoiceSchedule.setInvoiceDate(invoiceDate);
            }if (!invoiceCode.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCode(invoiceCode);
            }if (!invoiceUse.equals("")) {
                purchaseInvoiceSchedule.setInvoiceUse(invoiceUse);
            }if (!invoiceName.equals("")) {
                purchaseInvoiceSchedule.setInvoiceName(invoiceName);
            }if (!invoiceSpecifications.equals("")) {
                purchaseInvoiceSchedule.setInvoiceSpecifications(invoiceSpecifications);
            }if (!invoiceQuantity.equals("")) {
                purchaseInvoiceSchedule.setInvoiceQuantity(invoiceQuantity);
                purchaseInvoiceSchedule.setInvoiceAmount(invoiceQuantity);
            }if (!taxPrice.equals("")) {
                BigDecimal taxPriceTo = new BigDecimal(taxPrice);
                purchaseInvoiceSchedule.setTaxPrice(taxPriceTo);
                purchaseInvoiceSchedule.setTaxNoPrice(taxPriceTo);
            }if (!invoiceMoney.equals("")) {
                BigDecimal invoiceMoneyTo = new BigDecimal(invoiceMoney);
                purchaseInvoiceSchedule.setInvoiceMoney(invoiceMoneyTo);
                purchaseInvoiceSchedule.setTaxMoney(invoiceMoneyTo);
                purchaseInvoiceSchedule.setTaxNoMoney(invoiceMoneyTo);
            }if (!orderNumber.equals("")) {
                purchaseInvoiceSchedule.setOrderNumber(orderNumber);
            }if (!invoiceCoding.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCoding(invoiceCoding);
            }if (!invoiceCompany.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCompany(invoiceCompany);
            }
            purchaseInvoiceSchedule.setInvoiceBrand(invoiceBrand);
            purchaseInvoiceSchedule.setInvoiceNumbers(invoiceNumbers);
            purchaseInvoiceSchedule.setInvoiceSubclass(invoiceSubclass);
            baseMapper.savePurchaseInvoiceSchedule(purchaseInvoiceSchedule);
        }
        baseMapper.savePurchaseInvoiceData(purchaseInvoiceData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseInvoice(String invoiceNumbers, String dataTable) throws ParseException {
        baseMapper.deletePurchaseInvoiceSchedule(invoiceNumbers);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseInvoiceSchedule purchaseInvoiceSchedule = new PurchaseInvoiceSchedule();
        for (int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseInvoiceSchedule.setInvoiceNumbers(invoiceNumbers);
            Date invoiceDate = jsonArrayOne.getJSONObject(i).getDate("invoiceDate");
            String invoiceCode = jsonArrayOne.getJSONObject(i).getString("invoiceCode");
            String invoiceUse = jsonArrayOne.getJSONObject(i).getString("invoiceUse");
            String invoiceName = jsonArrayOne.getJSONObject(i).getString("invoiceName");
            String invoiceSpecifications = jsonArrayOne.getJSONObject(i).getString("invoiceSpecifications");
            String invoiceQuantity = jsonArrayOne.getJSONObject(i).getString("invoiceQuantity");
            String taxPrice = jsonArrayOne.getJSONObject(i).getString("taxPrice");
            String invoiceMoney = jsonArrayOne.getJSONObject(i).getString("invoiceMoney");
            String invoiceDeposit = jsonArrayOne.getJSONObject(i).getString("invoiceDeposit");
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String invoiceBrand = jsonArrayOne.getJSONObject(i).getString("invoiceBrand");
            String invoiceCompany = jsonArrayOne.getJSONObject(i).getString("invoiceCompany");
            String invoiceCoding = jsonArrayOne.getJSONObject(i).getString("invoiceCoding");
            String invoiceSubclass = jsonArrayOne.getJSONObject(i).getString("invoiceSubclass");
            BigDecimal invoiceDepositOtn = new BigDecimal(invoiceDeposit);
            purchaseInvoiceSchedule.setInvoiceDeposit(invoiceDepositOtn);
            if (!invoiceDate.equals("")) {
                purchaseInvoiceSchedule.setInvoiceDate(invoiceDate);
            }if (!invoiceCode.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCode(invoiceCode);
            }if (!invoiceUse.equals("")) {
                purchaseInvoiceSchedule.setInvoiceUse(invoiceUse);
            }if (!invoiceName.equals("")) {
                purchaseInvoiceSchedule.setInvoiceName(invoiceName);
            }if (!invoiceSpecifications.equals("")) {
                purchaseInvoiceSchedule.setInvoiceSpecifications(invoiceSpecifications);
            }if (!invoiceQuantity.equals("")) {
                purchaseInvoiceSchedule.setInvoiceQuantity(invoiceQuantity);
                purchaseInvoiceSchedule.setInvoiceAmount(invoiceQuantity);
            }if (!taxPrice.equals("")) {
                BigDecimal taxPriceTo = new BigDecimal(taxPrice);
                purchaseInvoiceSchedule.setTaxPrice(taxPriceTo);
                purchaseInvoiceSchedule.setTaxNoPrice(taxPriceTo);
            }if (!invoiceMoney.equals("")) {
                BigDecimal invoiceMoneyTo = new BigDecimal(invoiceMoney);
                purchaseInvoiceSchedule.setInvoiceMoney(invoiceMoneyTo);
                purchaseInvoiceSchedule.setTaxMoney(invoiceMoneyTo);
                purchaseInvoiceSchedule.setTaxNoMoney(invoiceMoneyTo);
            }if (!orderNumber.equals("")) {
                purchaseInvoiceSchedule.setOrderNumber(orderNumber);
            }if (!invoiceCoding.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCoding(invoiceCoding);
            }if (!invoiceCompany.equals("")) {
                purchaseInvoiceSchedule.setInvoiceCompany(invoiceCompany);
            }
            purchaseInvoiceSchedule.setInvoiceBrand(invoiceBrand);
            purchaseInvoiceSchedule.setInvoiceSubclass(invoiceSubclass);
            baseMapper.savePurchaseInvoiceSchedule(purchaseInvoiceSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseInvoice(String invoiceNumbers) {
        baseMapper.deletePurchaseInvoiceSchedule(invoiceNumbers);
        baseMapper.deletePurchaseInvoice(invoiceNumbers);
	}

    @Override
    public PurchaseInvoice findPurchaseInvoiceQueryPage(String invoiceNumbers) {
        return baseMapper.findPurchaseInvoiceQueryPage(invoiceNumbers);
    }

    @Override
    public List<PurchaseInvoiceSchedule> queryPurchaseInvoiceSchedule(String invoiceNumbers) {
        return baseMapper.queryPurchaseInvoiceSchedule(invoiceNumbers);
    }

    @Override
    public IPage<PurchaseInvoiceSchedule> purchasePaymentAddQuery(QueryRequest request, PurchaseInvoiceSchedule purchaseInvoiceSchedule) {
        Page<PurchaseInvoiceSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchasePaymentAddQuery(purchaseInvoiceSchedule));
        return baseMapper.purchasePaymentAddQuery(page,purchaseInvoiceSchedule);
    }
}

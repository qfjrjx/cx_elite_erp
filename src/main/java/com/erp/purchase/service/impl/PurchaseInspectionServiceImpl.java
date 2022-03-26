package com.erp.purchase.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseInspection;
import com.erp.purchase.entity.PurchaseInspectionSchedule;
import com.erp.purchase.mapper.PurchaseInspectionMapper;
import com.erp.purchase.service.IPurchaseInspectionService;
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
 * 来货检验 Service实现
 *
 * @author qiufeng
 * @date 2022-03-22 09:34:27
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseInspectionServiceImpl extends ServiceImpl<PurchaseInspectionMapper, PurchaseInspection> implements IPurchaseInspectionService {

    private final PurchaseInspectionMapper purchaseInspectionMapper;

    @Override
    public IPage<PurchaseInspection> findPurchaseInspections(QueryRequest request, PurchaseInspection purchaseInspection) {
        Page<PurchaseInspection> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseInspection(purchaseInspection));
        return baseMapper.findPurchaseInspectionPage(page,purchaseInspection);
    }

    @Override
    public List<PurchaseInspection> findPurchaseInspections(PurchaseInspection purchaseInspection) {
	    LambdaQueryWrapper<PurchaseInspection> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseInspection(String purchaseInspection, String dataTable) throws ParseException {
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        PurchaseInspection purchaseInspectionDate = new PurchaseInspection();
        /*单号案例 LLHDJ2203207*/
        String initials = "LLHDJ";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(purchaseInspection);
        String  userName =  object.getString("userName");
        String  purchaseRequisitionDate =  object.getString("purchaseRequisitionDate");
        String  supplierName =  object.getString("supplierName");
        //查询出最后一个工作安排单号
        PurchaseInspection purchaseInspectionOne = null;
        purchaseInspectionOne = baseMapper.queryPurchaseInspection();
        if (purchaseInspectionOne != null){
            String oddNumbers = purchaseInspectionOne.getInspectionNumber();
            /*单号案例 LWLCG22020155*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchaseInspectionDate.setInspectionNumber(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchaseInspectionDate.setInspectionNumber(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        //入库人
        purchaseInspectionDate.setInspectionPreparer(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        purchaseInspectionDate.setInspectionPreparationDate(today);
        //入库日期
        Date purchaseRequisitionDateOne = simpleDateFormatOne.parse(purchaseRequisitionDate);//格式化系统当前时间
        purchaseInspectionDate.setPurchaseRequisitionDate(purchaseRequisitionDateOne);
        purchaseInspectionDate.setSupplierName(supplierName);
        purchaseInspectionDate.setInspectionType("1");
        purchaseInspectionDate.setInspectionState("1");
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseInspectionSchedule purchaseInspectionSchedule = new PurchaseInspectionSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseInspectionSchedule.setInspectionNumber(generateDocNo);
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String materialName = jsonArrayOne.getJSONObject(i).getString("materialName");
            String inspectionrSpecifications = jsonArrayOne.getJSONObject(i).getString("orderSpecifications");
            String inspectionCompany = jsonArrayOne.getJSONObject(i).getString("orderCompany");
            String orderQuantity = jsonArrayOne.getJSONObject(i).getString("orderQuantity");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String inspectionMoney = jsonArrayOne.getJSONObject(i).getString("orderMoney");
            String inspectionRemarks = jsonArrayOne.getJSONObject(i).getString("orderRemarks");
            purchaseInspectionSchedule.setInspectionRemarks(inspectionRemarks);
            BigDecimal inspectionMoneyTo = new BigDecimal(inspectionMoney);
            purchaseInspectionSchedule.setInspectionMoney(inspectionMoneyTo);
            if (!orderNumber.equals("")){
                purchaseInspectionSchedule.setOrderNumbers(orderNumber);
            }
            if (!materialName.equals("")){
                purchaseInspectionSchedule.setMaterialName(materialName);
            }
            if (!inspectionrSpecifications.equals("")){
                purchaseInspectionSchedule.setInspectionrSpecifications(inspectionrSpecifications);
            }
            if (!inspectionCompany.equals("")){
                purchaseInspectionSchedule.setInspectionCompany(inspectionCompany);
            }
            if (!orderQuantity.equals("")){
                purchaseInspectionSchedule.setOrderQuantity(orderQuantity);
            }
            if (!unitPrice.equals("")){
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                purchaseInspectionSchedule.setUnitPrice(unitPriceTo);
            }
            //添加到数据库
            baseMapper.savePurchaseInspectionSchedule(purchaseInspectionSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseInspectionScheduleDate(purchaseInspectionDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseInspection(String inspectionNumber, String dataTable) throws ParseException {
        baseMapper.deletePurchaseInspectionSchedule(inspectionNumber);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseInspectionSchedule purchaseInspectionSchedule = new PurchaseInspectionSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            purchaseInspectionSchedule.setInspectionNumber(inspectionNumber);
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String materialName = jsonArrayOne.getJSONObject(i).getString("materialName");
            String inspectionrSpecifications = jsonArrayOne.getJSONObject(i).getString("inspectionrSpecifications");
            String inspectionCompany = jsonArrayOne.getJSONObject(i).getString("inspectionCompany");
            String orderQuantity = jsonArrayOne.getJSONObject(i).getString("orderQuantity");
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String inspectionMoney = jsonArrayOne.getJSONObject(i).getString("orderMoney");
            String inspectionRemarks = jsonArrayOne.getJSONObject(i).getString("inspectionRemarks");
            purchaseInspectionSchedule.setInspectionRemarks(inspectionRemarks);
            BigDecimal inspectionMoneyTo = new BigDecimal(inspectionMoney);
            purchaseInspectionSchedule.setInspectionMoney(inspectionMoneyTo);
            if (!orderNumber.equals("")){
                purchaseInspectionSchedule.setOrderNumbers(orderNumber);
            }
            if (!materialName.equals("")){
                purchaseInspectionSchedule.setMaterialName(materialName);
            }
            if (!inspectionrSpecifications.equals("")){
                purchaseInspectionSchedule.setInspectionrSpecifications(inspectionrSpecifications);
            }
            if (!inspectionCompany.equals("")){
                purchaseInspectionSchedule.setInspectionCompany(inspectionCompany);
            }
            if (!orderQuantity.equals("")){
                purchaseInspectionSchedule.setOrderQuantity(orderQuantity);
            }
            if (!unitPrice.equals("")){
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                purchaseInspectionSchedule.setUnitPrice(unitPriceTo);
            }
            //添加到数据库
            baseMapper.savePurchaseInspectionSchedule(purchaseInspectionSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseInspection(PurchaseInspection purchaseInspection) {
        LambdaQueryWrapper<PurchaseInspection> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public PurchaseInspectionSchedule findPurchaseInspectionQueryPage(String inspectionNumber) {
        return baseMapper.findPurchaseInspectionQueryPage(inspectionNumber);
    }

    @Override
    public List<PurchaseInspectionSchedule> queryPurchaseInspectionSchedule(String oddNumbers) {


        return baseMapper.queryPurchaseInspectionSchedule(oddNumbers);
    }

    //删除整单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseInspectionNumber(String inspectionNumber) {
        baseMapper.deletePurchaseInspection(inspectionNumber);
        baseMapper.deletePurchaseInspectionSchedule(inspectionNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmPurchaseInspection(String ids) {
        baseMapper.confirmPurchaseInspection(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPurchaseInspection(String ids) {
        baseMapper.cancelPurchaseInspection(ids);
    }
}

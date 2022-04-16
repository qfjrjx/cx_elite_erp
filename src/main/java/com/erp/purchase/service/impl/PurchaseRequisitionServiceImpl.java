package com.erp.purchase.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseRequisition;
import com.erp.purchase.entity.PurchaseRequisitionPositive;
import com.erp.purchase.entity.PurchaseRequisitionSchedule;
import com.erp.purchase.mapper.PurchaseRequisitionMapper;
import com.erp.purchase.service.IPurchaseRequisitionService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 采购申请表 Service实现
 *
 * @author qiufeng
 * @date 2022-01-19 15:29:35
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseRequisitionServiceImpl extends ServiceImpl<PurchaseRequisitionMapper, PurchaseRequisition> implements IPurchaseRequisitionService {

    private final PurchaseRequisitionMapper purchaseRequisitionMapper;

    @Override
    public IPage<PurchaseRequisition> findPurchaseRequisitions(QueryRequest request, PurchaseRequisition purchaseRequisition) {
        Page<PurchaseRequisition> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseRequisition(purchaseRequisition));
        return baseMapper.findPurchaseRequisitionPage(page,purchaseRequisition);
    }

    @Override
    public List<PurchaseRequisition> findPurchaseRequisitions(PurchaseRequisition purchaseRequisition) {
	    LambdaQueryWrapper<PurchaseRequisition> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createPurchaseRequisition(String applyPreparer,String transferData, Long departmentId, String dataTable) throws ParseException {
        PurchaseRequisitionPositive purchaseRequisitionPositive = new PurchaseRequisitionPositive();
        /*单号案例  LCGSQ22020001*/
        String initials = "LCGSQ";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询出最后一个工作安排单号
        PurchaseRequisition purchaseRequisitionOne = null;
        purchaseRequisitionOne = baseMapper.queryPurchaseRequisition();
        if (purchaseRequisitionOne != null){
            String oddNumbers = purchaseRequisitionOne.getOddNumbers();
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            purchaseRequisitionPositive.setOddNumbers(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<1000000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                purchaseRequisitionPositive.setOddNumbers(generateDocNo);
               // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        //制单人
        purchaseRequisitionPositive.setApplyPreparer(applyPreparer);
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date transferDataOne = simpleDateFormatOne.parse(transferData);//格式化日期
        //日期
        purchaseRequisitionPositive.setPurchaseRequisitionDate(transferDataOne);
        //部门
        purchaseRequisitionPositive.setDepartmentId(departmentId);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        //制单日期
        purchaseRequisitionPositive.setApplyPreparationDate(today);//把获取系统当前时间赋值给实体对象
        //PurchaseRequisitionSchedule采购申请表
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseRequisitionSchedule purchaseRequisitionSchedule = new PurchaseRequisitionSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            purchaseRequisitionSchedule.setOddNumbers(generateDocNo);
            String applyCode = jsonArrayOne.getJSONObject(i).getString("applyCode");
            String applyName = jsonArrayOne.getJSONObject(i).getString("applyName");
            String applySpecifications = jsonArrayOne.getJSONObject(i).getString("applySpecifications");
            String applyQuality = jsonArrayOne.getJSONObject(i).getString("applyQuality");
            String applyBrand = jsonArrayOne.getJSONObject(i).getString("applyBrand");
            String applyCompany = jsonArrayOne.getJSONObject(i).getString("applyCompany");
            String applyQuantity = jsonArrayOne.getJSONObject(i).getString("applyQuantity");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryDate");
            String applyRemarks = jsonArrayOne.getJSONObject(i).getString("applyRemarks");
            if (!applyCode.equals("")) {
                purchaseRequisitionSchedule.setApplyCode(applyCode);
            }if (!applyName.equals("")) {
                purchaseRequisitionSchedule.setApplyName(applyName);
            }if (!applySpecifications.equals("")) {
                purchaseRequisitionSchedule.setApplySpecifications(applySpecifications);
            }if (applyQuality != null && applyQuality != "") {
                purchaseRequisitionSchedule.setApplyQuality(applyQuality);
            }if (!applyBrand.equals("")) {
                purchaseRequisitionSchedule.setApplyBrand(applyBrand);
            }if (!applyCompany.equals("")) {
                purchaseRequisitionSchedule.setApplyCompany(applyCompany);
            }if (!applyQuantity.equals("") && !applyQuantity.equals(null)) {
                purchaseRequisitionSchedule.setApplyQuantity(Integer.parseInt(applyQuantity));
            }if (!deliveryDate.equals("")) {
                Date deliveryDateOne = simpleDateFormatOne.parse(deliveryDate);//格式化系统当前时间
                purchaseRequisitionSchedule.setDeliveryDate(deliveryDateOne);
            }if (applyRemarks != null && applyQuantity != "") {
                purchaseRequisitionSchedule.setApplyRemarks(applyRemarks);
            }
            //添加到数据库
            baseMapper.savePurchaseRequisitionSchedule(purchaseRequisitionSchedule);
        }
        //添加到数据库
        baseMapper.savePurchaseRequisitionPositive(purchaseRequisitionPositive);
    }

    @Override
    public void updatePurchaseRequisition(String oddNumbers , String applyPreparer, String transferData, Long departmentId, String dataTable) throws ParseException {
        baseMapper.deletePurchaseRequisitionSchedule(oddNumbers);
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        //PurchaseRequisitionSchedule采购申请表
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        PurchaseRequisitionSchedule purchaseRequisitionSchedule = new PurchaseRequisitionSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            purchaseRequisitionSchedule.setOddNumbers(oddNumbers);
            String applyCode = jsonArrayOne.getJSONObject(i).getString("applyCode");
            String applyName = jsonArrayOne.getJSONObject(i).getString("applyName");
            String applySpecifications = jsonArrayOne.getJSONObject(i).getString("applySpecifications");
            String applyQuality = jsonArrayOne.getJSONObject(i).getString("applyQuality");
            String applyBrand = jsonArrayOne.getJSONObject(i).getString("applyBrand");
            String applyCompany = jsonArrayOne.getJSONObject(i).getString("applyCompany");
            String applyQuantity = jsonArrayOne.getJSONObject(i).getString("applyQuantity");
            String deliveryDate = jsonArrayOne.getJSONObject(i).getString("deliveryDate");
            String applyRemarks = jsonArrayOne.getJSONObject(i).getString("applyRemarks");
            if (!applyCode.equals("")) {
                purchaseRequisitionSchedule.setApplyCode(applyCode);
            }if (!applyName.equals("")) {
                purchaseRequisitionSchedule.setApplyName(applyName);
            }if (!applySpecifications.equals("")) {
                purchaseRequisitionSchedule.setApplySpecifications(applySpecifications);
            }if (applyQuality != null && applyQuality != "") {
                purchaseRequisitionSchedule.setApplyQuality(applyQuality);
            }if (!applyBrand.equals("")) {
                purchaseRequisitionSchedule.setApplyBrand(applyBrand);
            }if (!applyCompany.equals("")) {
                purchaseRequisitionSchedule.setApplyCompany(applyCompany);
            }if (!applyQuantity.equals("") && !applyQuantity.equals(null)) {
                purchaseRequisitionSchedule.setApplyQuantity(Integer.parseInt(applyQuantity));
            }if (!deliveryDate.equals("")) {
                Date deliveryDateOne = simpleDateFormatOne.parse(deliveryDate);//格式化系统当前时间
                purchaseRequisitionSchedule.setDeliveryDate(deliveryDateOne);
            }if (applyRemarks != null && applyQuantity != "") {
                purchaseRequisitionSchedule.setApplyRemarks(applyRemarks);
            }
            //添加到数据库
            baseMapper.savePurchaseRequisitionSchedule(purchaseRequisitionSchedule);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        LambdaQueryWrapper<PurchaseRequisition> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<PurchaseRequisition> queryPurchaseRequisitions(String oddNumbers) {


        return baseMapper.queryPurchaseRequisitions(oddNumbers);
    }

    @Override
    public PurchaseRequisitionPositive findPurchaseRequisitionPositiveById(Long id) {

        return baseMapper.findPurchaseRequisitionPositiveById(id);
    }

    @Override
    public IPage<PurchaseRequisitionSchedule> queryPurchaseRequisitionsList(QueryRequest request,String oddNumbers) {
        Page<PurchaseRequisitionSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        return baseMapper.queryPurchaseRequisitionsList(page,oddNumbers);
    }

    @Override
    public IPage<PurchaseRequisition> findOrderPurchaseRequisitionPage(QueryRequest request, PurchaseRequisition purchaseRequisition) {
        Page<PurchaseRequisition> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseRequisition(purchaseRequisition));
        return baseMapper.findOrderPurchaseRequisitionPage(page,purchaseRequisition);
    }
}

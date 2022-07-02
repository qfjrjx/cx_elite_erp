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
import com.erp.warehouse.entity.WarehouseStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        String userName =  object.getString("userName");
        String purchaseRequisitionDate =  object.getString("purchaseRequisitionDate");
        String supplierName =  object.getString("supplierName");
        String currencyId = object.getString("currencyId");
        String taxRateId = object.getString("taxRateId");
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
        purchaseInspectionDate.setTaxRateId(taxRateId);
        purchaseInspectionDate.setCurrencyId(currencyId);
        purchaseInspectionDate.setInspectionType("1");
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
            String inspectionSubclass = jsonArrayOne.getJSONObject(i).getString("orderSubclass");
            String inspectionCategory = jsonArrayOne.getJSONObject(i).getString("orderCategory");
            String inspectionLocation = jsonArrayOne.getJSONObject(i).getString("orderLocation");
            String inspectionMaterial = jsonArrayOne.getJSONObject(i).getString("orderQuality");
            String inspectionBrand = jsonArrayOne.getJSONObject(i).getString("orderBrand");
            String inspectionCode = jsonArrayOne.getJSONObject(i).getString("orderCode");
            String inspectionDeposit = jsonArrayOne.getJSONObject(i).getString("orderDeposit");
            purchaseInspectionSchedule.setInspectionRemarks(inspectionRemarks);
            BigDecimal inspectionMoneyTo = new BigDecimal(inspectionMoney);
            purchaseInspectionSchedule.setInspectionMoney(inspectionMoneyTo);
            purchaseInspectionSchedule.setInspectionState("1");
            purchaseInspectionSchedule.setInspectionLocation(inspectionLocation);
            purchaseInspectionSchedule.setInspectionMaterial(inspectionMaterial);
            purchaseInspectionSchedule.setInspectionBrand(inspectionBrand);
            purchaseInspectionSchedule.setInspectionCode(inspectionCode);
            BigDecimal inspectionDepositTo = new BigDecimal(inspectionDeposit);
            purchaseInspectionSchedule.setInspectionDeposit(inspectionDepositTo);
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
            }if (!inspectionSubclass.equals("")){
                purchaseInspectionSchedule.setInspectionSubclass(inspectionSubclass);
            }if (!inspectionCategory.equals("")){
                purchaseInspectionSchedule.setInspectionCategory(inspectionCategory);
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
            String inspectionSubclass = jsonArrayOne.getJSONObject(i).getString("orderSubclass");
            String inspectionCategory = jsonArrayOne.getJSONObject(i).getString("orderCategory");
            String inspectionLocation = jsonArrayOne.getJSONObject(i).getString("orderLocation");
            purchaseInspectionSchedule.setInspectionRemarks(inspectionRemarks);
            BigDecimal inspectionMoneyTo = new BigDecimal(inspectionMoney);
            purchaseInspectionSchedule.setInspectionMoney(inspectionMoneyTo);
            purchaseInspectionSchedule.setInspectionState("1");
            purchaseInspectionSchedule.setInspectionLocation(inspectionLocation);
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
            }if (!inspectionSubclass.equals("")){
                purchaseInspectionSchedule.setInspectionSubclass(inspectionSubclass);
            }if (!inspectionCategory.equals("")){
                purchaseInspectionSchedule.setInspectionCategory(inspectionCategory);
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

    @Override
    public PurchaseInspectionSchedule qualityInspectionId(Long ids) {
        return baseMapper.qualityInspectionId(ids);
    }

    @Override
    public void updateQualityInspection(PurchaseInspectionSchedule purchaseInspectionSchedule) throws ParseException {
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        purchaseInspectionSchedule.setInspectionQualityDate(today);
        baseMapper.updateQualityInspection(purchaseInspectionSchedule);
    }

    @Override
    public void cancelInspection(String ids) {
        baseMapper.cancelInspection(ids);
    }

    @Override
    public void confirmOutsourcing(String ids) throws ParseException {
        String[] parts = ids.split(",");
        String userName = parts[0]; // 创建人
        String id = parts[1]; // id
        PurchaseInspectionSchedule purchaseInspectionSchedule = null;
        purchaseInspectionSchedule = baseMapper.confirmOutsourcing(id);
        //采购入库表
        WarehouseStorage warehouseStorage = new WarehouseStorage();
        //来货检验附表
        PurchaseInspectionSchedule purchaseInspectionScheduleData = new PurchaseInspectionSchedule();
        /*单号案例 LLHRK22060017*/
        String initials = "LLHRK";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());//打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询是否有相同的数据
        WarehouseStorage warehouseStorageTwo = null;
        warehouseStorageTwo = baseMapper.queryWarehouseStorageTwo(purchaseInspectionSchedule.getInspectionNumber());
        //查询出最后一个工作安排单号
        WarehouseStorage warehouseStorageOne = null;
        warehouseStorageOne = baseMapper.queryWarehouseStorage();
        if (warehouseStorageOne != null){
            String oddNumbers = warehouseStorageOne.getStorageCoding();
            if (warehouseStorageTwo == null){
                //判断月份是否相同
                String oddNumbersOn = oddNumbers.substring(7,9);
                if (oddNumbersOn.equals(month)){
                    /*单号案例 LLHRK22060017*/
                    String oddNumbersOne = oddNumbers.substring(9,13);
                    int oddNumber = Integer.parseInt(oddNumbersOne);
                    String oddNumberOne = null;
                    if (oddNumber<10000){
                        oddNumberOne = String.format("%04d",oddNumber+1);
                    }
                    generateDocNo = initials+yearLast+month+oddNumberOne;
                    warehouseStorage.setStorageCoding(generateDocNo);
                }else {
                    for (int i=1;i<10000;i++){
                        String oddNumberThree = String.format("%04d",i);
                        generateDocNo = initials+yearLast+month+oddNumberThree;
                        warehouseStorage.setStorageCoding(generateDocNo);
                        break;
                    }
                }
            }else {
                warehouseStorage.setStorageCoding(warehouseStorageTwo.getStorageCoding());
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                warehouseStorage.setStorageCoding(generateDocNo);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        warehouseStorage.setStoragePreparer(userName);
        warehouseStorage.setStoragePreparerDate(today);
        warehouseStorage.setStorageState("1");
        warehouseStorage.setStorageDate(today);
        warehouseStorage.setSupplierName(purchaseInspectionSchedule.getSupplierName());
        warehouseStorage.setStorageLibrary("0");
        warehouseStorage.setStorageQuantity(purchaseInspectionSchedule.getInspectionQualified());
        warehouseStorage.setStorageMoney(purchaseInspectionSchedule.getInspectionMoney());
        warehouseStorage.setCurrencyId(purchaseInspectionSchedule.getCurrencyId());
        warehouseStorage.setTaxRateId(purchaseInspectionSchedule.getTaxRateId());
        warehouseStorage.setOrderNumber(purchaseInspectionSchedule.getOrderNumbers());
        warehouseStorage.setStorageCode(purchaseInspectionSchedule.getInspectionCode());
        warehouseStorage.setStorageName(purchaseInspectionSchedule.getMaterialName());
        warehouseStorage.setStorageSpecifications(purchaseInspectionSchedule.getInspectionrSpecifications());
        warehouseStorage.setStorageQuality(purchaseInspectionSchedule.getInspectionMaterial());
        warehouseStorage.setStorageBrand(purchaseInspectionSchedule.getInspectionBrand());
        warehouseStorage.setStorageCompany(purchaseInspectionSchedule.getInspectionCompany());
        warehouseStorage.setUnitPrice(purchaseInspectionSchedule.getUnitPrice());
        warehouseStorage.setStorageLocation(purchaseInspectionSchedule.getInspectionLocation());
        warehouseStorage.setStorageRemarks(purchaseInspectionSchedule.getInspectionRemarks());
        warehouseStorage.setStorageSubclass(purchaseInspectionSchedule.getInspectionSubclass());
        warehouseStorage.setStorageCategory(purchaseInspectionSchedule.getInspectionCategory());
        warehouseStorage.setStorageNumbers(purchaseInspectionSchedule.getInspectionNumber());
        warehouseStorage.setStorageDeposit(purchaseInspectionSchedule.getInspectionDeposit());
        String uuid = UUID.randomUUID().toString();
        warehouseStorage.setUuid(uuid);
        baseMapper.saveWarehouseStorage(warehouseStorage);
        purchaseInspectionScheduleData.setInspectionLibrary(userName);
        purchaseInspectionScheduleData.setInspectionLibraryDate(today);
        purchaseInspectionScheduleData.setInspectionLibraryCode(generateDocNo);
        purchaseInspectionScheduleData.setUuid(uuid);
        purchaseInspectionScheduleData.setId(Long.valueOf(id).longValue());
        baseMapper.updatePurchaseInspectionSchedule(purchaseInspectionScheduleData);
    }

    @Override
    public void confirmAssets(String ids) throws ParseException {
        String[] parts = ids.split(",");
        String userName = parts[0]; // 创建人
        String id = parts[1]; // id
        PurchaseInspectionSchedule purchaseInspectionSchedule = null;
        purchaseInspectionSchedule = baseMapper.confirmOutsourcing(id);
        //采购入库表
        WarehouseStorage warehouseStorage = new WarehouseStorage();
        //来货检验附表
        PurchaseInspectionSchedule purchaseInspectionScheduleData = new PurchaseInspectionSchedule();
        /*单号案例 LLHRK22060017*/
        String initials = "LLHRK";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());//打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询是否有相同的数据
        WarehouseStorage warehouseStorageTwo = null;
        warehouseStorageTwo = baseMapper.queryWarehouseStorageTwo(purchaseInspectionSchedule.getInspectionNumber());
        //查询出最后一个工作安排单号
        WarehouseStorage warehouseStorageOne = null;
        warehouseStorageOne = baseMapper.queryWarehouseStorage();
        if (warehouseStorageOne != null){
            String oddNumbers = warehouseStorageOne.getStorageCoding();
            if (warehouseStorageTwo == null){
                //判断月份是否相同
                String oddNumbersOn = oddNumbers.substring(7,9);
                if (oddNumbersOn.equals(month)){
                    /*单号案例 LLHRK22060017*/
                    String oddNumbersOne = oddNumbers.substring(9,13);
                    int oddNumber = Integer.parseInt(oddNumbersOne);
                    String oddNumberOne = null;
                    if (oddNumber<10000){
                        oddNumberOne = String.format("%04d",oddNumber+1);
                    }
                    generateDocNo = initials+yearLast+month+oddNumberOne;
                    warehouseStorage.setStorageCoding(generateDocNo);
                }else {
                    for (int i=1;i<10000;i++){
                        String oddNumberThree = String.format("%04d",i);
                        generateDocNo = initials+yearLast+month+oddNumberThree;
                        warehouseStorage.setStorageCoding(generateDocNo);
                        break;
                    }
                }
            }else {
                warehouseStorage.setStorageCoding(warehouseStorageTwo.getStorageCoding());
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                warehouseStorage.setStorageCoding(generateDocNo);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        warehouseStorage.setStoragePreparer(userName);
        warehouseStorage.setStoragePreparerDate(today);
        warehouseStorage.setStorageState("1");
        warehouseStorage.setStorageDate(today);
        warehouseStorage.setSupplierName(purchaseInspectionSchedule.getSupplierName());
        warehouseStorage.setStorageLibrary("5");
        warehouseStorage.setStorageQuantity(purchaseInspectionSchedule.getInspectionQualified());
        warehouseStorage.setStorageMoney(purchaseInspectionSchedule.getInspectionMoney());
        warehouseStorage.setCurrencyId(purchaseInspectionSchedule.getCurrencyId());
        warehouseStorage.setTaxRateId(purchaseInspectionSchedule.getTaxRateId());
        warehouseStorage.setOrderNumber(purchaseInspectionSchedule.getOrderNumbers());
        warehouseStorage.setStorageCode(purchaseInspectionSchedule.getInspectionCode());
        warehouseStorage.setStorageName(purchaseInspectionSchedule.getMaterialName());
        warehouseStorage.setStorageSpecifications(purchaseInspectionSchedule.getInspectionrSpecifications());
        warehouseStorage.setStorageQuality(purchaseInspectionSchedule.getInspectionMaterial());
        warehouseStorage.setStorageBrand(purchaseInspectionSchedule.getInspectionBrand());
        warehouseStorage.setStorageCompany(purchaseInspectionSchedule.getInspectionCompany());
        warehouseStorage.setUnitPrice(purchaseInspectionSchedule.getUnitPrice());
        warehouseStorage.setStorageLocation(purchaseInspectionSchedule.getInspectionLocation());
        warehouseStorage.setStorageRemarks(purchaseInspectionSchedule.getInspectionRemarks());
        warehouseStorage.setStorageSubclass(purchaseInspectionSchedule.getInspectionSubclass());
        warehouseStorage.setStorageCategory(purchaseInspectionSchedule.getInspectionCategory());
        warehouseStorage.setStorageNumbers(purchaseInspectionSchedule.getInspectionNumber());
        warehouseStorage.setStorageDeposit(purchaseInspectionSchedule.getInspectionDeposit());
        String uuid = UUID.randomUUID().toString();
        warehouseStorage.setUuid(uuid);
        baseMapper.saveWarehouseStorage(warehouseStorage);
        purchaseInspectionScheduleData.setInspectionLibrary(userName);
        purchaseInspectionScheduleData.setInspectionLibraryDate(today);
        purchaseInspectionScheduleData.setInspectionLibraryCode(generateDocNo);
        purchaseInspectionScheduleData.setUuid(uuid);
        purchaseInspectionScheduleData.setId(Long.valueOf(id).longValue());
        baseMapper.updatePurchaseInspectionSchedule(purchaseInspectionScheduleData);
    }

    @Override
    public void cancelLibrary(String ids) {
        PurchaseInspectionSchedule purchaseInspectionSchedule = null;
        purchaseInspectionSchedule = baseMapper.confirmOutsourcing(ids);
        baseMapper.updateCancelLibrary(ids);
        baseMapper.deleteWarehouseStorage(purchaseInspectionSchedule.getUuid());
    }

    @Override
    public IPage<PurchaseInspection> purchaseSupplyYieldList(QueryRequest request, PurchaseInspection purchaseInspection) {
        Page<PurchaseInspection> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseSupplyYield(purchaseInspection));
        return baseMapper.findPurchaseSupplyYieldPage(page,purchaseInspection);
    }

}

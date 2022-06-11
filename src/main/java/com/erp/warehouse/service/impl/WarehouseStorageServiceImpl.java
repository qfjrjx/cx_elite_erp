package com.erp.warehouse.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseSettlement;
import com.erp.purchase.entity.PurchaseSettlementSchedule;
import com.erp.purchase.mapper.PurchaseSettlementMapper;
import com.erp.warehouse.entity.WarehouseStorage;
import com.erp.warehouse.mapper.WarehouseStorageMapper;
import com.erp.warehouse.service.IWarehouseStorageService;
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
 * 采购入库 Service实现
 *
 * @author qiufeng
 * @date 2022-06-03 11:33:18
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WarehouseStorageServiceImpl extends ServiceImpl<WarehouseStorageMapper, WarehouseStorage> implements IWarehouseStorageService {

    private final WarehouseStorageMapper warehouseStorageMapper;

    private final PurchaseSettlementMapper purchaseSettlementMapper;

    @Override
    public IPage<WarehouseStorage> findWarehouseStorages(QueryRequest request, WarehouseStorage warehouseStorage) {
        Page<WarehouseStorage> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countWarehouseStorage(warehouseStorage));
        return baseMapper.findWarehouseStoragePage(page,warehouseStorage);
    }

    @Override
    public List<WarehouseStorage> findWarehouseStorages(WarehouseStorage warehouseStorage) {
	    LambdaQueryWrapper<WarehouseStorage> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWarehouseStorage(WarehouseStorage warehouseStorage) {
        this.save(warehouseStorage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWarehouseStorage(WarehouseStorage warehouseStorage) {
        this.saveOrUpdate(warehouseStorage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWarehouseStorage(WarehouseStorage warehouseStorage) {
        LambdaQueryWrapper<WarehouseStorage> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<WarehouseStorage> queryWarehouseStorage(String storageCoding) {
        return baseMapper.queryWarehouseStorage(storageCoding);
    }

    @Override
    public void warehouseStorageStorage(String ids) throws ParseException {
        String[] parts = ids.split(",");
        String id = parts[0]; // id
        String storageCoding = parts[1]; // 单号
        baseMapper.warehouseStorageStorage(id);
        List<WarehouseStorage> warehouseStorages = baseMapper.queryWarehouseStorage(storageCoding);
        JSONArray jsonArrayOne= JSONArray.parseArray(JSON.toJSONString(warehouseStorages));
        PurchaseSettlement purchaseSettlement = new PurchaseSettlement();
        PurchaseSettlementSchedule purchaseSettlementSchedule = new PurchaseSettlementSchedule();

        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        for(int i = 0; i < jsonArrayOne.size(); i++){
            //采购结算主表数据
            String settlementNumbers =  jsonArrayOne.getJSONObject(i).getString("storageCoding");
            String settlementSupplier =  jsonArrayOne.getJSONObject(i).getString("supplierName");
            String settlementLibrary =  jsonArrayOne.getJSONObject(i).getString("storageLibrary");
            String currencyId =  jsonArrayOne.getJSONObject(i).getString("currencyId");
            String taxRateId =  jsonArrayOne.getJSONObject(i).getString("taxRateId");
            //采购结算附表数据
            String settlementCode =  jsonArrayOne.getJSONObject(i).getString("storageCode");
            String settlementName =  jsonArrayOne.getJSONObject(i).getString("storageName");
            String settlementSpecifications =  jsonArrayOne.getJSONObject(i).getString("storageSpecifications");
            String settlementQuantity =  jsonArrayOne.getJSONObject(i).getString("storageQuantity");
            String settlementBrand =  jsonArrayOne.getJSONObject(i).getString("storageBrand");
            String settlementCompany =  jsonArrayOne.getJSONObject(i).getString("storageCompany");
            String settlementPrice =  jsonArrayOne.getJSONObject(i).getString("unitPrice");
            String settlementMoney =  jsonArrayOne.getJSONObject(i).getString("storageMoney");
            String settlementCategory =  jsonArrayOne.getJSONObject(i).getString("storageCategory");
            String settlementSubclass =  jsonArrayOne.getJSONObject(i).getString("storageSubclass");
            String settlementRemarks =  jsonArrayOne.getJSONObject(i).getString("storageRemarks");
            String orderNumber =  jsonArrayOne.getJSONObject(i).getString("orderNumber");
            String settlementQuality =  jsonArrayOne.getJSONObject(i).getString("storageQuality");
            String settlementDeposit =  jsonArrayOne.getJSONObject(i).getString("storageDeposit");
            purchaseSettlement.setSettlementState("2");
            purchaseSettlement.setSettlementDate(today);
            purchaseSettlement.setSettlementNumbers(settlementNumbers);
            purchaseSettlement.setSettlementUse("2");
            purchaseSettlement.setSettlementSupplier(settlementSupplier);
            purchaseSettlement.setSettlementLibrary(settlementLibrary);
            purchaseSettlement.setCurrencyId(Long.parseLong(currencyId));
            purchaseSettlement.setTaxRateId(Long.parseLong(taxRateId));
            purchaseSettlement.setSettlementInvoice(Long.parseLong("1"));
            purchaseSettlementSchedule.setSettlementCode(settlementCode);
            purchaseSettlementSchedule.setSettlementName(settlementName);
            purchaseSettlementSchedule.setSettlementSpecifications(settlementSpecifications);
            purchaseSettlementSchedule.setSettlementQuality(settlementQuality);
            purchaseSettlementSchedule.setSettlementBrand(settlementBrand);
            purchaseSettlementSchedule.setSettlementCompany(settlementCompany);
            purchaseSettlementSchedule.setSettlementQuantity(Long.parseLong(settlementQuantity));
            BigDecimal settlementPriceTo = new BigDecimal(settlementPrice);
            purchaseSettlementSchedule.setSettlementPrice(settlementPriceTo);
            BigDecimal settlementMoneyTo = new BigDecimal(settlementMoney);
            purchaseSettlementSchedule.setSettlementMoney(settlementMoneyTo);
            purchaseSettlementSchedule.setSettlementCategory(settlementCategory);
            purchaseSettlementSchedule.setSettlementSubclass(settlementSubclass);
            purchaseSettlementSchedule.setSettlementRemarks(settlementRemarks);
            purchaseSettlementSchedule.setSettlementNumber(settlementNumbers);
            BigDecimal settlementDepositTo = new BigDecimal(settlementDeposit);
            purchaseSettlementSchedule.setSettlementDeposit(settlementDepositTo);
            purchaseSettlementSchedule.setOrderNumber(orderNumber);
            /*添加到采购结果附表数据*/
            purchaseSettlementMapper.savePurchaseSettlementSchedule(purchaseSettlementSchedule);
        }
        //采购结算
        purchaseSettlementMapper.savePurchaseSettlementDate(purchaseSettlement);
    }

    @Override
    public void cancelWarehouseStorageStorage(String ids) {
        String[] parts = ids.split(",");
        String id = parts[0]; // id
        String storageCoding = parts[1]; // 单号
        baseMapper.cancelWarehouseStorageStorage(id);
        baseMapper.deletePurchaseSettlement(storageCoding);
        baseMapper.deletePurchaseSettlementSchedule(storageCoding);
    }

}

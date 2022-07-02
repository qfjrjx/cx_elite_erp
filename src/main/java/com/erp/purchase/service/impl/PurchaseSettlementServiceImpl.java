package com.erp.purchase.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseInvoiceStatistical;
import com.erp.purchase.entity.PurchaseRefund;
import com.erp.purchase.entity.PurchaseSettlement;
import com.erp.purchase.entity.PurchaseSettlementSchedule;
import com.erp.purchase.mapper.PurchaseSettlementMapper;
import com.erp.purchase.service.IPurchaseSettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购结算 Service实现
 *
 * @author qiufeng
 * @date 2022-04-02 15:02:26
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseSettlementServiceImpl extends ServiceImpl<PurchaseSettlementMapper, PurchaseSettlement> implements IPurchaseSettlementService {

    private final PurchaseSettlementMapper purchaseSettlementMapper;

    @Override
    public IPage<PurchaseSettlement> findPurchaseSettlements(QueryRequest request, PurchaseSettlement purchaseSettlement) {
        Page<PurchaseRefund> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseSettlement(purchaseSettlement));
        return baseMapper.findPurchaseSettlement(page,purchaseSettlement);
    }

    @Override
    public List<PurchaseSettlement> findPurchaseSettlements(PurchaseSettlement purchaseSettlement) {
	    LambdaQueryWrapper<PurchaseSettlement> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseSettlement(PurchaseSettlement purchaseSettlement) {
        this.save(purchaseSettlement);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseSettlement(PurchaseSettlement purchaseSettlement) {
        this.saveOrUpdate(purchaseSettlement);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseSettlement(PurchaseSettlement purchaseSettlement) {
        LambdaQueryWrapper<PurchaseSettlement> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<PurchaseSettlementSchedule> queryPurchaseSettlementSchedule(String oddNumbers) {
        return baseMapper.queryPurchaseSettlementSchedule(oddNumbers);
    }

    @Override
    public void settlementPurchaseSettlement(String ids) {
        String[] parts = ids.split(",");
        String id = parts[0]; // id
        String settlementUse = parts[1]; // 路径1：采购退货2：采购入库
        String settlementNumbers = parts[2]; // 单号
        baseMapper.settlementPurchaseSettlement(id);
        if (settlementUse.equals("1")){
            baseMapper.updatePurchaseRefund(settlementNumbers);
        }else if (settlementUse.equals("2")){
            baseMapper.updateWarehouseStorage(settlementNumbers);
        }
    }

    @Override
    public void cancelPurchaseSettlement(String ids) {
        String[] parts = ids.split(",");
        String id = parts[0]; // id
        String settlementUse = parts[1]; // 路径1：采购退货2：采购入库
        String settlementNumbers = parts[2]; // 单号
        if (settlementUse.equals("1")){
            baseMapper.cancelPurchaseSettlement(id);
            baseMapper.updatePurchaseRefundData(settlementNumbers);
        }else if (settlementUse.equals("2")){
            baseMapper.cancelPurchaseSettlementData(id);
            baseMapper.updateWarehouseStorageData(settlementNumbers);
        }
    }

    @Override
    public IPage<PurchaseSettlementSchedule> purchaseSettlementAddQuery(QueryRequest request, PurchaseSettlementSchedule purchaseSettlementSchedule) {
        Page<PurchaseSettlementSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseSettlementAddQuery(purchaseSettlementSchedule));
        return baseMapper.purchaseSettlementAddQuery(page,purchaseSettlementSchedule);
    }

    /**
     * 收货统计
     * @param request
     * @param purchaseSettlementSchedule
     * @return
     */
    @Override
    public IPage<PurchaseSettlementSchedule> findPurchaseGoodsStatistical(QueryRequest request, PurchaseSettlementSchedule purchaseSettlementSchedule) {
        Page<PurchaseSettlementSchedule> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseGoodsStatistical(purchaseSettlementSchedule));
        return baseMapper.findPurchaseGoodsStatistical(page,purchaseSettlementSchedule);
    }

    @Override
    public List<PurchaseSettlementSchedule> purchaseGoodsExport(PurchaseSettlementSchedule purchaseSettlementSchedule) {
        return baseMapper.purchaseGoodsExport(purchaseSettlementSchedule);
    }

    /**
     * 发票统计
     * @param request
     * @param purchaseInvoiceStatistical
     * @return
     */
    @Override
    public IPage<PurchaseInvoiceStatistical> findPurchaseInvoiceStatistical(QueryRequest request, PurchaseInvoiceStatistical purchaseInvoiceStatistical) {
        Page<PurchaseInvoiceStatistical> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseInvoiceStatistical(purchaseInvoiceStatistical));
        return baseMapper.findPurchaseInvoiceStatistical(page,purchaseInvoiceStatistical);
    }

    @Override
    public List<PurchaseInvoiceStatistical> purchaseInvoiceStatisticalExport(PurchaseInvoiceStatistical purchaseInvoiceStatistical) {
        return baseMapper.purchaseInvoiceStatisticalExport(purchaseInvoiceStatistical);
    }
}

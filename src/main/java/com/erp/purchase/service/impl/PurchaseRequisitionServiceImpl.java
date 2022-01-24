package com.erp.purchase.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseRequisition;
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

import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.save(purchaseRequisition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.saveOrUpdate(purchaseRequisition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        LambdaQueryWrapper<PurchaseRequisition> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

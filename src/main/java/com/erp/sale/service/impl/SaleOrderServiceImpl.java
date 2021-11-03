package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleOrder;
import com.erp.sale.mapper.SaleOrderMapper;
import com.erp.sale.service.ISaleOrderService;
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
 * 销售订单表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-14 09:39:45
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements ISaleOrderService {

    private final SaleOrderMapper saleOrderMapper;

    @Override
    public IPage<SaleOrder> findSaleOrders(QueryRequest request, SaleOrder saleOrder) {
        Page<SaleOrder> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleOrder(saleOrder));
        return baseMapper.findSaleOrderPage(page,saleOrder);
    }

    @Override
    public List<SaleOrder> findSaleOrders(SaleOrder saleOrder) {
	    LambdaQueryWrapper<SaleOrder> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleOrder(SaleOrder saleOrder) {
        this.save(saleOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleOrder(SaleOrder saleOrder) {
        this.saveOrUpdate(saleOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleOrder(SaleOrder saleOrder) {
        LambdaQueryWrapper<SaleOrder> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

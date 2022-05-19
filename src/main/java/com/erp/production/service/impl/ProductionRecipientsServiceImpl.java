package com.erp.production.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.mapper.ProductionRecipientsMapper;
import com.erp.production.service.IProductionRecipientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 生产领用 Service实现
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductionRecipientsServiceImpl extends ServiceImpl<ProductionRecipientsMapper, ProductionRecipients> implements IProductionRecipientsService {

    private final ProductionRecipientsMapper productionRecipientsMapper;

    @Override
    public IPage<ProductionRecipients> findProductionRecipientss(QueryRequest request, ProductionRecipients productionRecipients) {
        LambdaQueryWrapper<ProductionRecipients> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ProductionRecipients> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ProductionRecipients> findProductionRecipientss(ProductionRecipients productionRecipients) {
	    LambdaQueryWrapper<ProductionRecipients> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductionRecipients(ProductionRecipients productionRecipients) {
        this.save(productionRecipients);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductionRecipients(ProductionRecipients productionRecipients) {
        this.saveOrUpdate(productionRecipients);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductionRecipients(ProductionRecipients productionRecipients) {
        LambdaQueryWrapper<ProductionRecipients> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

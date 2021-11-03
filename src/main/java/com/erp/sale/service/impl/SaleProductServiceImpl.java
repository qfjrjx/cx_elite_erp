package com.erp.sale.service.impl;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.common.utils.SortUtil;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.sale.entity.SaleProduct;
import com.erp.sale.entity.SaleProducts;
import com.erp.sale.mapper.SaleProductMapper;
import com.erp.sale.service.ISaleProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 产品表
             Service实现
 *
 * @author qiufeng
 * @date 2021-10-18 17:17:33
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleProductServiceImpl extends ServiceImpl<SaleProductMapper, SaleProduct> implements ISaleProductService {

    private final SaleProductMapper saleProductMapper;

    @Override
    public IPage<SaleProduct> findSaleProducts(QueryRequest request, SaleProduct saleProduct) {
        Page<SaleProduct> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleProducts(saleProduct));
        return baseMapper.findSaleProductsPage(page,saleProduct);
    }

    @Override
    public List<SaleProduct> findSaleProducts(SaleProduct saleProduct) {
	    LambdaQueryWrapper<SaleProduct> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleProduct(SaleProduct saleProduct) {
        this.save(saleProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleProduct(SaleProduct saleProduct) {
        this.saveOrUpdate(saleProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleProduct(SaleProduct saleProduct) {
        LambdaQueryWrapper<SaleProduct> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public IPage<SaleProducts> findSaleProduct(QueryRequest request, SaleProducts saleProducts) {

        Page<SaleProducts> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleProduct(saleProducts));
        return baseMapper.findSaleProductsPages(page,saleProducts);
    }

    @Override
    public void updateSaleProductState(Long id) {
        baseMapper.updateSaleProductState(id);
    }

    @Override
    public void updateSaleProductStates(Long id) {
        baseMapper.updateSaleProductStates(id);
    }


}

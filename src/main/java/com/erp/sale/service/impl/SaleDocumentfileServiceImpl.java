package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleDocumentfile;
import com.erp.sale.mapper.SaleDocumentfileMapper;
import com.erp.sale.service.ISaleDocumentfileService;
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
 * 上传文件表 Service实现
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleDocumentfileServiceImpl extends ServiceImpl<SaleDocumentfileMapper, SaleDocumentfile> implements ISaleDocumentfileService {

    private final SaleDocumentfileMapper saleDocumentfileMapper;

    @Override
    public IPage<SaleDocumentfile> findSaleDocumentfiles(QueryRequest request, SaleDocumentfile saleDocumentfile) {
        LambdaQueryWrapper<SaleDocumentfile> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<SaleDocumentfile> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<SaleDocumentfile> findSaleDocumentfiles(SaleDocumentfile saleDocumentfile) {
	    LambdaQueryWrapper<SaleDocumentfile> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.save(saleDocumentfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        this.saveOrUpdate(saleDocumentfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleDocumentfile(SaleDocumentfile saleDocumentfile) {
        LambdaQueryWrapper<SaleDocumentfile> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public void addDocumentFile(SaleDocumentfile documentFile) {
        this.save(documentFile);
    }

    @Override
    public SaleDocumentfile findSaleDocumentFileByName(String fileName) {

        return baseMapper.findSaleDocumentFileByName(fileName);
    }

    @Override
    public void deleteSaleDocumentFile(String contFile) {
        baseMapper.deleteSaleDocumentFile(contFile);
    }
}

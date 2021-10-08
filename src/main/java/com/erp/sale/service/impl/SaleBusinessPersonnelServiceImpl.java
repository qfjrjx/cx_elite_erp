package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleBusinessPersonnel;
import com.erp.sale.mapper.SaleBusinessPersonnelMapper;
import com.erp.sale.service.ISaleBusinessPersonnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 业务人员表

               数据库表名：                                         对应java表名：
    
               jr_sale_business_personnel                    SaleBusinessPersonnel Service实现
 *
 * @author qiufeng
 * @date 2021-10-08 13:34:13
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleBusinessPersonnelServiceImpl extends ServiceImpl<SaleBusinessPersonnelMapper, SaleBusinessPersonnel> implements ISaleBusinessPersonnelService {

    private final SaleBusinessPersonnelMapper saleBusinessPersonnelMapper;

    @Override
    public IPage<SaleBusinessPersonnel> findSaleBusinessPersonnels(QueryRequest request, SaleBusinessPersonnel saleBusinessPersonnel) {
        Page<PersonnelDormitory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleBusinessPersonnels(saleBusinessPersonnel));
        return baseMapper.findSaleBusinessPersonnelsPage(page,saleBusinessPersonnel);
    }

    @Override
    public List<SaleBusinessPersonnel> findSaleBusinessPersonnels(SaleBusinessPersonnel saleBusinessPersonnel) {
	    LambdaQueryWrapper<SaleBusinessPersonnel> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleBusinessPersonnel(SaleBusinessPersonnel saleBusinessPersonnel) {
        this.save(saleBusinessPersonnel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleBusinessPersonnel(SaleBusinessPersonnel saleBusinessPersonnel) {
        baseMapper.saveOrUpdate(saleBusinessPersonnel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleBusinessPersonnel(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public SaleBusinessPersonnel findSaleBusinessPersonnelById(Long id) {

        return baseMapper.findSaleBusinessPersonnelById(id);
    }
}

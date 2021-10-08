package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleParameters;
import com.erp.sale.mapper.SaleParametersMapper;
import com.erp.sale.service.ISaleParametersService;
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
 * 销售参数表

          数据库表名：                              对应java表名：

          jr_sale_parameters                     SaleParameters Service实现
 *
 * @author qiufeng
 * @date 2021-10-07 10:18:55
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleParametersServiceImpl extends ServiceImpl<SaleParametersMapper, SaleParameters> implements ISaleParametersService {

    private final SaleParametersMapper saleParametersMapper;

    @Override
    public IPage<SaleParameters> findSaleParameterss(QueryRequest request, SaleParameters saleParameters) {
        Page<PersonnelDormitory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleParameterss(saleParameters));
        return baseMapper.findSaleParameterssPage(page,saleParameters);
    }

    @Override
    public List<SaleParameters> findSaleParameterss(SaleParameters saleParameters) {
	    LambdaQueryWrapper<SaleParameters> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleParameters(SaleParameters saleParameters) {
        this.save(saleParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleParameters(SaleParameters saleParameters) {
        this.saveOrUpdate(saleParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleParameters(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}
    //销售参数修改回填
    @Override
    public SaleParameters findSaleParametersById(Long id) {

        return baseMapper.findSaleParametersById(id);
    }
}

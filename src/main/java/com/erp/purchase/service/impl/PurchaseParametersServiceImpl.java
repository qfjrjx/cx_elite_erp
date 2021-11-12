package com.erp.purchase.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.mapper.PurchaseParametersMapper;
import com.erp.purchase.service.IPurchaseParametersService;
import com.erp.technology.entity.TechnologyProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 采购参数表 Service实现
 *
 * @author qiufeng
 * @date 2021-11-09 16:20:36
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseParametersServiceImpl extends ServiceImpl<PurchaseParametersMapper, PurchaseParameters> implements IPurchaseParametersService {

    private final PurchaseParametersMapper purchaseParametersMapper;

    @Override
    public IPage<PurchaseParameters> findPurchaseParameterss(QueryRequest request, PurchaseParameters purchaseParameters) {
        Page<PurchaseParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseParameters(purchaseParameters));
        return baseMapper.findPurchaseParametersPage(page,purchaseParameters);
    }

    @Override
    public List<PurchaseParameters> findPurchaseParameterss(PurchaseParameters purchaseParameters) {
	    LambdaQueryWrapper<PurchaseParameters> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseParameters(PurchaseParameters purchaseParameters) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        purchaseParameters.setCreationTime(today);
        this.save(purchaseParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseParameters(PurchaseParameters purchaseParameters) {
        this.saveOrUpdate(purchaseParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseParameters(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }
    @Override
    public PurchaseParameters findPurchaseParametersById(Long id) {

        return baseMapper.findPurchaseParametersById(id);
    }
    //查询产品材质信息
    @Override
    public List<PurchaseParameters> queryProductMaterial(String purchaseParametersCategory, String parametersState) {
        return baseMapper.queryProductMaterial(purchaseParametersCategory,parametersState);
    }
}

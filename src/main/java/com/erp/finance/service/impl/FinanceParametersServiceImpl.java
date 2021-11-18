package com.erp.finance.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.finance.entity.FinanceParameters;
import com.erp.finance.mapper.FinanceParametersMapper;
import com.erp.finance.service.IFinanceParametersService;
import com.erp.purchase.entity.PurchaseParameters;
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
 * 财务参数表 Service实现
 *
 * @author qiufeng
 * @date 2021-11-17 11:39:32
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FinanceParametersServiceImpl extends ServiceImpl<FinanceParametersMapper, FinanceParameters> implements IFinanceParametersService {

    private final FinanceParametersMapper financeParametersMapper;

    @Override
    public IPage<FinanceParameters> findFinanceParameterss(QueryRequest request, FinanceParameters financeParameters) {
        Page<FinanceParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countFinanceParameters(financeParameters));
        return baseMapper.findFinanceParametersPage(page,financeParameters);
    }

    @Override
    public List<FinanceParameters> findFinanceParameterss(FinanceParameters financeParameters) {
	    LambdaQueryWrapper<FinanceParameters> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFinanceParameters(FinanceParameters financeParameters) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        financeParameters.setFinanceCreationTime(today);
        this.save(financeParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFinanceParameters(FinanceParameters financeParameters) {
        this.saveOrUpdate(financeParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFinanceParameters(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public FinanceParameters financeParametersById(Long id) {

        return baseMapper.financeParametersById(id);
    }
}

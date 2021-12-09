package com.erp.enterprise.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterpriseResourcesParameters;
import com.erp.enterprise.mapper.EnterpriseResourcesParametersMapper;
import com.erp.enterprise.service.IEnterpriseResourcesParametersService;
import com.erp.finance.entity.FinanceParameters;
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
 * 参数设置表 Service实现
 *
 * @author qiufeng
 * @date 2021-12-09 10:22:55
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EnterpriseResourcesParametersServiceImpl extends ServiceImpl<EnterpriseResourcesParametersMapper, EnterpriseResourcesParameters> implements IEnterpriseResourcesParametersService {

    private final EnterpriseResourcesParametersMapper enterpriseResourcesParametersMapper;

    @Override
    public IPage<EnterpriseResourcesParameters> findEnterpriseResourcesParameterss(QueryRequest request, EnterpriseResourcesParameters enterpriseResourcesParameters) {
        Page<FinanceParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countEnterpriseResourcesParameters(enterpriseResourcesParameters));
        return baseMapper.findEnterpriseResourcesParametersPage(page,enterpriseResourcesParameters);
    }

    @Override
    public List<EnterpriseResourcesParameters> findEnterpriseResourcesParameterss(EnterpriseResourcesParameters enterpriseResourcesParameters) {
	    LambdaQueryWrapper<EnterpriseResourcesParameters> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEnterpriseResourcesParameters(EnterpriseResourcesParameters enterpriseResourcesParameters) {
        this.save(enterpriseResourcesParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEnterpriseResourcesParameters(EnterpriseResourcesParameters enterpriseResourcesParameters) {
        this.saveOrUpdate(enterpriseResourcesParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnterpriseResourcesParameters(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public EnterpriseResourcesParameters resourcesParameterById(Long id) {

        return baseMapper.resourcesParameterById(id);
    }
}

package com.erp.monthly.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.monthly.entity.CompletionStatus;
import com.erp.monthly.entity.TaskSettings;
import com.erp.monthly.mapper.CompletionStatusMapper;
import com.erp.monthly.service.ICompletionStatusService;
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
 * 完成情况表 Service实现
 *
 * @author qiufeng
 * @date 2021-12-17 14:06:34
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompletionStatusServiceImpl extends ServiceImpl<CompletionStatusMapper, CompletionStatus> implements ICompletionStatusService {

    private final CompletionStatusMapper completionStatusMapper;

    @Override
    public IPage<CompletionStatus> findCompletionStatuss(QueryRequest request, CompletionStatus completionStatus) {
        Page<CompletionStatus> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countCompletionStatus(completionStatus));
        return baseMapper.findCompletionStatusPage(page,completionStatus);
    }

    @Override
    public List<CompletionStatus> findCompletionStatuss(CompletionStatus completionStatus) {
	    LambdaQueryWrapper<CompletionStatus> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCompletionStatus(CompletionStatus completionStatus) {
        this.save(completionStatus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCompletionStatus(CompletionStatus completionStatus) {
        this.saveOrUpdate(completionStatus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompletionStatus(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}
}

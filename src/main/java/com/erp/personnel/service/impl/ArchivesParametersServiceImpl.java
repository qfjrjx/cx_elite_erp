package com.erp.personnel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.ArchivesParameters;
import com.erp.personnel.mapper.ArchivesParametersMapper;
import com.erp.personnel.service.IArchivesParametersService;
import com.erp.system.entity.UserRole;
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
 *  Service实现
 *
 * @author qiufeng
 * @date 2021-09-11 09:27:44
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArchivesParametersServiceImpl extends ServiceImpl<ArchivesParametersMapper, ArchivesParameters> implements IArchivesParametersService {

    private final ArchivesParametersMapper archivesParametersMapper;

    @Override
    public IPage<ArchivesParameters> findArchivesParameterss(QueryRequest request, ArchivesParameters archivesParameters) {
        LambdaQueryWrapper<ArchivesParameters> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<ArchivesParameters> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<ArchivesParameters> findArchivesParameterss(ArchivesParameters archivesParameters) {
	    LambdaQueryWrapper<ArchivesParameters> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createArchivesParameters(ArchivesParameters archivesParameters) {
        this.save(archivesParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArchivesParameters(ArchivesParameters archivesParameters) {
        this.saveOrUpdate(archivesParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArchivesParameters(ArchivesParameters archivesParameters) {
        LambdaQueryWrapper<ArchivesParameters> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public void deleteArchivesParameter(List<String> archivesId) {
        baseMapper.delete(new QueryWrapper<ArchivesParameters>().lambda().in(ArchivesParameters::getArchivesId, archivesId));
    }
}

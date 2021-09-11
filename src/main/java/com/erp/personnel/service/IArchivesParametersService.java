package com.erp.personnel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.ArchivesParameters;

import java.util.List;

/**
 *  Service接口
 *
 * @author qiufeng
 * @date 2021-09-11 09:27:44
 */
public interface IArchivesParametersService extends IService<ArchivesParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param archivesParameters archivesParameters
     * @return IPage<ArchivesParameters>
     */
    IPage<ArchivesParameters> findArchivesParameterss(QueryRequest request, ArchivesParameters archivesParameters);

    /**
     * 查询（所有）
     *
     * @param archivesParameters archivesParameters
     * @return List<ArchivesParameters>
     */
    List<ArchivesParameters> findArchivesParameterss(ArchivesParameters archivesParameters);

    /**
     * 新增
     *
     * @param archivesParameters archivesParameters
     */
    void createArchivesParameters(ArchivesParameters archivesParameters);

    /**
     * 修改
     *
     * @param archivesParameters archivesParameters
     */
    void updateArchivesParameters(ArchivesParameters archivesParameters);

    /**
     * 删除
     *
     * @param archivesParameters archivesParameters
     */
    void deleteArchivesParameters(ArchivesParameters archivesParameters);

    void deleteArchivesParameter(List<String> archivesId);
}

package com.erp.arrange.service;

import com.erp.arrange.entity.WorkArrange;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.arrange.entity.WorkArrangementStatistics;
import com.erp.common.entity.QueryRequest;

import java.text.ParseException;
import java.util.List;

/**
 * 工作安排表 Service接口
 *
 * @author qiufeng
 * @date 2021-12-20 15:35:45
 */
public interface IWorkArrangeService extends IService<WorkArrange> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param workArrange workArrange
     * @return IPage<WorkArrange>
     */
    IPage<WorkArrange> findWorkArranges(QueryRequest request, WorkArrange workArrange);

    /**
     * 查询（所有）
     *
     * @param workArrange workArrange
     * @return List<WorkArrange>
     */
    List<WorkArrange> findWorkArranges(WorkArrange workArrange);

    /**
     * 新增
     *
     * @param workArrange workArrange
     */
    void createWorkArrange(WorkArrange workArrange) throws ParseException;

    /**
     * 修改
     *
     * @param workArrange workArrange
     */
    void updateWorkArrange(WorkArrange workArrange);

    /**
     * 删除
     *
     * @param ids workArrange
     */
    void deleteWorkArrange(String[] ids);

    WorkArrange findWorkArrangeById(Long id);

    void updateWorkArrangeState(Long id, String stateParam);

    void updateWorkArrangeStateDate(Long id, String stateParam) throws ParseException;

    void workArrangeAssessment(WorkArrange workArrange) throws ParseException;
   /*工作安排统计*/
    IPage<WorkArrangementStatistics> findWorkArrangementStatistics(QueryRequest request, WorkArrangementStatistics workArrangementStatistics);
}

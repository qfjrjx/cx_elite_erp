package com.erp.production.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.entity.SetupBom;
import com.erp.production.entity.SetupBomSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 生产计划 Service接口
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
public interface IProductionPlanService extends IService<ProductionPlan> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param productionPlan productionPlan
     * @return IPage<ProductionPlan>
     */
    IPage<ProductionPlan> findProductionPlans(QueryRequest request, ProductionPlan productionPlan);

    /**
     * 查询（所有）
     *
     * @param productionPlan productionPlan
     * @return List<ProductionPlan>
     */
    List<ProductionPlan> findProductionPlans(ProductionPlan productionPlan);

    /**
     * 新增
     *
     * @param productionPlanSchedule productionPlan
     */
    void createProductionPlan(String userName,ProductionPlanSchedule productionPlanSchedule) throws ParseException;

    /**
     * 修改
     *
     * @param productionPlanSchedule productionPlan
     */
    void updateProductionPlan(ProductionPlanSchedule productionPlanSchedule);

    /**
     * 删除
     *
     * @param planNumber productionPlan
     */
    void deleteProductionPlan(String planCode);

    ProductionPlanSchedule productionPlanScheduleId(Long id);

    void numberProductionPlan(ProductionPlan productionPlan);

    void uploadProductionPlan(ProductionPlan productionPlan);

    ProductionPlan productionPlanId(Long id);

    ProductionPlanSchedule productionPlanPlanNumber(String planCode);

    void createSetupBom(String setupBom, String dataTable) throws ParseException;

    List<SetupBom> queryProductionPlanUpdateBom(String planCode);

    void updateSetupBom(String setupBom, String dataTable);

    List<SetupBomSchedule> queryProductionPlanViewBom(String planCode);

    IPage<SetupBomSchedule> productionRecipientsAddQuery(QueryRequest request, SetupBomSchedule setupBomSchedule);

    void updateProductionStatistical(ProductionPlan productionPlan);

    List<ProductionPlan> productionStatisticalExport(ProductionPlan productionPlan);

    void shipmentProductionPlan(ProductionPlan productionPlan);
}

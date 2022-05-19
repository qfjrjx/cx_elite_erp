package com.erp.production.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.entity.SetupBom;
import com.erp.production.entity.SetupBomSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产计划 Mapper
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
public interface ProductionPlanMapper extends BaseMapper<ProductionPlan> {

    long countProductionPlans(@Param("productionPlan") ProductionPlan productionPlan);

    IPage<ProductionPlan> findProductionPlansPage(Page<ProductionPlan> page,@Param("productionPlan") ProductionPlan productionPlan);

    ProductionPlan queryProductionPlan();

    void saveProductionPlanScheduleDate(ProductionPlanSchedule productionPlanScheduleDate);

    void saveProductionPlan(ProductionPlan productionPlan);

    ProductionPlanSchedule productionPlanScheduleId(@Param("id") Long id);

    void deleteProductionPlanSchedule(@Param("planNumber") String planNumber);

    void updateProductionPlan(@Param("productionPlan") ProductionPlan productionPlan);

    void deleteProductionPlan(@Param("planNumber") String planNumber);

    void numberProductionPlan(@Param("productionPlan") ProductionPlan productionPlan);

    void uploadProductionPlan(@Param("productionPlan") ProductionPlan productionPlan);

    ProductionPlan productionPlanId(@Param("id") Long id);

    ProductionPlanSchedule productionPlanPlanNumber(@Param("planNumber") String planNumber);

    void saveSetupBomSchedule(SetupBomSchedule setupBomSchedule);

    void saveSetupBomData(SetupBom setupBomData);

    void updatePlanMachineBom(@Param("productionPlan") ProductionPlan productionPlan);

    List<SetupBom> queryProductionPlanUpdateBom(@Param("planCode") String planCode);

    void deleteSetupBom(@Param("planCode") String planCode);

    void deleteSetupBomSchedule(@Param("planCode") String planCode);

    List<SetupBomSchedule> queryProductionPlanViewBom(@Param("planCode") String planCode);
}

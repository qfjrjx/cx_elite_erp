package com.erp.production.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.production.entity.ProductionLack;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.entity.ProductionRecipientsSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产领用 Mapper
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
public interface ProductionRecipientsMapper extends BaseMapper<ProductionRecipients> {

    long countProductionRecipients(@Param("productionRecipients") ProductionRecipients productionRecipients);

    IPage<ProductionRecipients> findProductionRecipientsPage(@Param("productionRecipients") Page<ProductionRecipients> page, ProductionRecipients productionRecipients);

    List<ProductionRecipientsSchedule> queryProductionRecipients(@Param("recipientsCode") String recipientsCode);

    ProductionRecipients queryProductionRecipientsOne();

    void saveProductionLack(ProductionLack productionLack);

    void saveProductionRecipientsSchedule(ProductionRecipientsSchedule productionRecipientsSchedule);

    void saveProductionRecipients(ProductionRecipients productionRecipients);

    ProductionRecipients productionRecipientsId(Long id);

    void deleteProductionRecipients(String recipientsCode);

    void cancelProductionRecipients(@Param("ids") String ids);

    void confirmProductionRecipients(@Param("ids") String ids);

    List<ProductionLack> queryProductionRecipientsLack(@Param("recipientsCode") String recipientsCode);

    long countProductionLack(@Param("productionLack") ProductionLack productionLack);

    IPage<ProductionLack> findProductionLackPage(@Param("productionLack") Page<ProductionLack> page, ProductionLack productionLack);
}

package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseInspection;
import com.erp.purchase.entity.PurchaseInspectionSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 来货检验 Mapper
 *
 * @author qiufeng
 * @date 2022-03-22 09:34:27
 */
public interface PurchaseInspectionMapper extends BaseMapper<PurchaseInspection> {

    long countPurchaseInspection(@Param("purchaseInspection") PurchaseInspection purchaseInspection);

    IPage<PurchaseInspection> findPurchaseInspectionPage(Page<PurchaseInspection> page, @Param("purchaseInspection") PurchaseInspection purchaseInspection);

    PurchaseInspectionSchedule findPurchaseInspectionQueryPage(@Param("inspectionNumber") String inspectionNumber);

    List<PurchaseInspectionSchedule> queryPurchaseInspectionSchedule(@Param("oddNumbers") String oddNumbers);

    void deletePurchaseInspection (@Param("inspectionNumber") String inspectionNumber);

    void deletePurchaseInspectionSchedule (@Param("inspectionNumber") String inspectionNumber);

    void savePurchaseInspectionSchedule(PurchaseInspectionSchedule purchaseInspectionSchedule);

    void savePurchaseInspectionScheduleDate (PurchaseInspection purchaseInspectionDate);

    PurchaseInspection queryPurchaseInspection();

    void confirmPurchaseInspection(String ids);

    void cancelPurchaseInspection(String ids);
}

package com.erp.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.purchase.entity.PurchaseInspection;
import com.erp.purchase.entity.PurchaseInspectionSchedule;
import com.erp.warehouse.entity.WarehouseStorage;
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

    void confirmPurchaseInspection(@Param("ids") String ids);

    void cancelPurchaseInspection(@Param("ids") String ids);

    PurchaseInspectionSchedule qualityInspectionId(@Param("ids") Long ids);

    void updateQualityInspection(@Param("purchaseInspectionSchedule") PurchaseInspectionSchedule purchaseInspectionSchedule);

    void cancelInspection(@Param("ids") String ids);

    PurchaseInspectionSchedule confirmOutsourcing(@Param("id") String id);

    WarehouseStorage queryWarehouseStorage();

    void saveWarehouseStorage(WarehouseStorage warehouseStorage);

    void updatePurchaseInspectionSchedule(@Param("purchaseInspectionScheduleData") PurchaseInspectionSchedule purchaseInspectionScheduleData);

    WarehouseStorage queryWarehouseStorageTwo(@Param("inspectionNumber") String inspectionNumber);

    void deleteWarehouseStorage(@Param("uuid") String uuid);

    void updateCancelLibrary(@Param("ids") String ids);

    long countPurchaseSupplyYield(@Param("purchaseInspection") PurchaseInspection purchaseInspection);

    IPage<PurchaseInspection> findPurchaseSupplyYieldPage(Page<PurchaseInspection> page,@Param("purchaseInspection") PurchaseInspection purchaseInspection);
}

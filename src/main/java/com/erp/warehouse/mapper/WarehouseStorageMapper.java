package com.erp.warehouse.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.warehouse.entity.WarehouseStorage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购入库 Mapper
 *
 * @author qiufeng
 * @date 2022-06-03 11:33:18
 */
public interface WarehouseStorageMapper extends BaseMapper<WarehouseStorage> {

    long countWarehouseStorage(@Param("warehouseStorage") WarehouseStorage warehouseStorage);

    IPage<WarehouseStorage> findWarehouseStoragePage(@Param("warehouseStorage") Page<WarehouseStorage> page, WarehouseStorage warehouseStorage);

    List<WarehouseStorage> queryWarehouseStorage(@Param("storageCoding") String storageCoding);

    void warehouseStorageStorage(@Param("ids") String ids);

    void cancelWarehouseStorageStorage(@Param("ids") String ids);

    void deletePurchaseSettlement(@Param("storageCoding") String storageCoding);

    void deletePurchaseSettlementSchedule(@Param("storageCoding") String storageCoding);
}

package com.erp.warehouse.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.warehouse.entity.WarehouseLocation;

import java.text.ParseException;
import java.util.List;

/**
 * 库房区位 Service接口
 *
 * @author qiufeng
 * @date 2022-06-01 10:40:04
 */
public interface IWarehouseLocationService extends IService<WarehouseLocation> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param warehouseLocation warehouseLocation
     * @return IPage<WarehouseLocation>
     */
    IPage<WarehouseLocation> findWarehouseLocations(QueryRequest request, WarehouseLocation warehouseLocation);

    /**
     * 查询（所有）
     *
     * @param warehouseLocation warehouseLocation
     * @return List<WarehouseLocation>
     */
    List<WarehouseLocation> findWarehouseLocations(WarehouseLocation warehouseLocation);

    /**
     * 新增
     *
     * @param warehouseLocation warehouseLocation
     */
    void createWarehouseLocation(WarehouseLocation warehouseLocation) throws ParseException;

    /**
     * 修改
     *
     * @param warehouseLocation warehouseLocation
     */
    void updateWarehouseLocation(WarehouseLocation warehouseLocation) throws ParseException;

    /**
     * 删除
     *
     * @param warehouseLocation warehouseLocation
     */
    void deleteWarehouseLocation(String ids);

    WarehouseLocation warehouseLocationId(Long id);

    List<WarehouseLocation> queryLocationName();
}

package com.erp.warehouse.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.technology.entity.TechnologyBomParameter;
import com.erp.warehouse.entity.WarehouseLocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库房区位 Mapper
 *
 * @author qiufeng
 * @date 2022-06-01 10:40:04
 */
public interface WarehouseLocationMapper extends BaseMapper<WarehouseLocation> {

    long countWarehouseLocations(@Param("warehouseLocation") WarehouseLocation warehouseLocation);

    IPage<WarehouseLocation> findWarehouseLocationsPage(@Param("warehouseLocation") Page<TechnologyBomParameter> page, WarehouseLocation warehouseLocation);

    WarehouseLocation queryWarehouseLocation();

    void saveWarehouseLocation(WarehouseLocation warehouseLocationData);

    WarehouseLocation warehouseLocationId(@Param("id") Long id);

    void deleteWarehouseLocation(@Param("id") Integer id);

    List<WarehouseLocation> queryLocationName();
}

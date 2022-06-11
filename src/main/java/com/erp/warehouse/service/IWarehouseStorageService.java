package com.erp.warehouse.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.warehouse.entity.WarehouseStorage;

import java.text.ParseException;
import java.util.List;

/**
 * 采购入库 Service接口
 *
 * @author qiufeng
 * @date 2022-06-03 11:33:18
 */
public interface IWarehouseStorageService extends IService<WarehouseStorage> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param warehouseStorage warehouseStorage
     * @return IPage<WarehouseStorage>
     */
    IPage<WarehouseStorage> findWarehouseStorages(QueryRequest request, WarehouseStorage warehouseStorage);

    /**
     * 查询（所有）
     *
     * @param warehouseStorage warehouseStorage
     * @return List<WarehouseStorage>
     */
    List<WarehouseStorage> findWarehouseStorages(WarehouseStorage warehouseStorage);

    /**
     * 新增
     *
     * @param warehouseStorage warehouseStorage
     */
    void createWarehouseStorage(WarehouseStorage warehouseStorage);

    /**
     * 修改
     *
     * @param warehouseStorage warehouseStorage
     */
    void updateWarehouseStorage(WarehouseStorage warehouseStorage);

    /**
     * 删除
     *
     * @param warehouseStorage warehouseStorage
     */
    void deleteWarehouseStorage(WarehouseStorage warehouseStorage);

    List<WarehouseStorage> queryWarehouseStorage(String storageCoding);

    void warehouseStorageStorage(String ids) throws ParseException;

    void cancelWarehouseStorageStorage(String ids);
}

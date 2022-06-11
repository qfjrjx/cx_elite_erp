package com.erp.warehouse.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.warehouse.entity.WarehouseParameter;

import java.util.List;

/**
 * 仓库参数 Service接口
 *
 * @author qiufeng
 * @date 2022-06-01 15:53:35
 */
public interface IWarehouseParameterService extends IService<WarehouseParameter> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param warehouseParameter warehouseParameter
     * @return IPage<WarehouseParameter>
     */
    IPage<WarehouseParameter> findWarehouseParameters(QueryRequest request, WarehouseParameter warehouseParameter);

    /**
     * 查询（所有）
     *
     * @param warehouseParameter warehouseParameter
     * @return List<WarehouseParameter>
     */
    List<WarehouseParameter> findWarehouseParameters(WarehouseParameter warehouseParameter);

    /**
     * 新增
     *
     * @param warehouseParameter warehouseParameter
     */
    void createWarehouseParameter(WarehouseParameter warehouseParameter);

    /**
     * 修改
     *
     * @param warehouseParameter warehouseParameter
     */
    void updateWarehouseParameter(WarehouseParameter warehouseParameter);

    /**
     * 删除
     *
     * @param warehouseParameter warehouseParameter
     */
    void deleteWarehouseParameter(String ids);

    WarehouseParameter warehouseParameterId(Long id);

    List<WarehouseParameter> queryParameterValue();
}

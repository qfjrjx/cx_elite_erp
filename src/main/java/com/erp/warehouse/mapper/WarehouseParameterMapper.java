package com.erp.warehouse.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.warehouse.entity.WarehouseParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 仓库参数 Mapper
 *
 * @author qiufeng
 * @date 2022-06-01 15:53:35
 */
public interface WarehouseParameterMapper extends BaseMapper<WarehouseParameter> {

    long countWarehouseParameters(@Param("warehouseParameter") WarehouseParameter warehouseParameter);

    IPage<WarehouseParameter> findWarehouseParametersPage(@Param("warehouseParameter") Page<WarehouseParameter> page, WarehouseParameter warehouseParameter);

    WarehouseParameter warehouseParameterId(Long id);

    void deleteWarehouseParameter(String ids);

    List<WarehouseParameter> queryParameterValue();
}

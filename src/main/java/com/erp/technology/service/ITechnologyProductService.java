package com.erp.technology.service;

import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyProduct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 产品表
             Service接口
 *
 * @author qiufeng
 * @date 2021-11-09 10:30:07
 */
public interface ITechnologyProductService extends IService<TechnologyProduct> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param technologyProduct technologyProduct
     * @return IPage<TechnologyProduct>
     */
    IPage<TechnologyProduct> findTechnologyProducts(QueryRequest request, TechnologyProduct technologyProduct);

    /**
     * 查询（所有）
     *
     * @param technologyProduct technologyProduct
     * @return List<TechnologyProduct>
     */
    List<TechnologyProduct> findTechnologyProducts(TechnologyProduct technologyProduct);

    /**
     * 新增
     *
     * @param technologyProduct technologyProduct
     */
    void createTechnologyProduct(TechnologyProduct technologyProduct) throws ParseException;

    /**
     * 修改
     *
     * @param technologyProduct technologyProduct
     */
    void updateTechnologyProduct(TechnologyProduct technologyProduct);

    /**
     * 删除
     *
     * @param ids technologyProduct
     */
    void deleteTechnologyProduct(String[] ids);

    TechnologyProduct findTechnologyProductById(Long id);
}

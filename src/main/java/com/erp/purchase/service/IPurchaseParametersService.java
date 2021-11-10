package com.erp.purchase.service;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseParameters;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 采购参数表 Service接口
 *
 * @author qiufeng
 * @date 2021-11-09 16:20:36
 */
public interface IPurchaseParametersService extends IService<PurchaseParameters> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseParameters purchaseParameters
     * @return IPage<PurchaseParameters>
     */
    IPage<PurchaseParameters> findPurchaseParameterss(QueryRequest request, PurchaseParameters purchaseParameters);

    /**
     * 查询（所有）
     *
     * @param purchaseParameters purchaseParameters
     * @return List<PurchaseParameters>
     */
    List<PurchaseParameters> findPurchaseParameterss(PurchaseParameters purchaseParameters);

    /**
     * 新增
     *
     * @param purchaseParameters purchaseParameters
     */
    void createPurchaseParameters(PurchaseParameters purchaseParameters) throws ParseException;

    /**
     * 修改
     *
     * @param purchaseParameters purchaseParameters
     */
    void updatePurchaseParameters(PurchaseParameters purchaseParameters);

    /**
     * 删除
     *
     * @param ids purchaseParameters
     */
    void deletePurchaseParameters(String[] ids);

    PurchaseParameters findPurchaseParametersById(Long id);
}

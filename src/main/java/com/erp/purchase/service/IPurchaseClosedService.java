package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseClosed;

import java.text.ParseException;
import java.util.List;

/**
 * 采购收货表 Service接口
 *
 * @author qiufeng
 * @date 2022-03-19 09:44:35
 */
public interface IPurchaseClosedService extends IService<PurchaseClosed> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseClosed purchaseClosed
     * @return IPage<PurchaseClosed>
     */
    IPage<PurchaseClosed> findPurchaseCloseds(QueryRequest request, PurchaseClosed purchaseClosed);

    /**
     * 查询（所有）
     *
     * @param purchaseClosed purchaseClosed
     * @return List<PurchaseClosed>
     */
    List<PurchaseClosed> findPurchaseCloseds(PurchaseClosed purchaseClosed);

    /**
     * 新增
     *
     * @param purchaseClosed purchaseClosed
     */
    void createPurchaseClosed(String purchaseClosed, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param closedNumber dataTable
     */
    void updatePurchaseClosed(String closedNumber, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param purchaseClosed purchaseClosed
     */
    void deletePurchaseClosed(PurchaseClosed purchaseClosed);


    PurchaseClosed queryPurchaseClosedList(Long id);


}

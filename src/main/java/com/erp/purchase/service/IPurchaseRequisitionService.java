package com.erp.purchase.service;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseRequisition;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.purchase.entity.PurchaseRequisitionPositive;
import com.erp.purchase.entity.PurchaseRequisitionSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 采购申请表 Service接口
 *
 * @author qiufeng
 * @date 2022-01-19 15:29:35
 */
public interface IPurchaseRequisitionService extends IService<PurchaseRequisition> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseRequisition purchaseRequisition
     * @return IPage<PurchaseRequisition>
     */
    IPage<PurchaseRequisition> findPurchaseRequisitions(QueryRequest request, PurchaseRequisition purchaseRequisition);

    /**
     * 查询（所有）
     *
     * @param purchaseRequisition purchaseRequisition
     * @return List<PurchaseRequisition>
     */
    List<PurchaseRequisition> findPurchaseRequisitions(PurchaseRequisition purchaseRequisition);

    /**
     * 新增
     *
     * @param applyPreparer transferData departmentId dataTable
     */
    void createPurchaseRequisition(String applyPreparer,String transferData, Long departmentId, String dataTable) throws ParseException;
    /**
     * 修改
     *
     * @param applyPreparer transferData departmentId dataTable
     */
    void updatePurchaseRequisition(String oddNumbers,String applyPreparer, String transferData, Long departmentId, String dataTable) throws ParseException;
    /**
     * 删除
     *
     * @param purchaseRequisition purchaseRequisition
     */
    void deletePurchaseRequisition(PurchaseRequisition purchaseRequisition);

    List<PurchaseRequisition> queryPurchaseRequisitions(String oddNumbers);

    PurchaseRequisitionPositive findPurchaseRequisitionPositiveById(Long id);

    IPage<PurchaseRequisitionSchedule> queryPurchaseRequisitionsList(QueryRequest request, String oddNumbers);

    IPage<PurchaseRequisition> findOrderPurchaseRequisitionPage(QueryRequest request, PurchaseRequisition purchaseRequisition);
}

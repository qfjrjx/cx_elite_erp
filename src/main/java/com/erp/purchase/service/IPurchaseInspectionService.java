package com.erp.purchase.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseInspection;
import com.erp.purchase.entity.PurchaseInspectionSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 来货检验 Service接口
 *
 * @author qiufeng
 * @date 2022-03-22 09:34:27
 */
public interface IPurchaseInspectionService extends IService<PurchaseInspection> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param purchaseInspection purchaseInspection
     * @return IPage<PurchaseInspection>
     */
    IPage<PurchaseInspection> findPurchaseInspections(QueryRequest request, PurchaseInspection purchaseInspection);

    /**
     * 查询（所有）
     *
     * @param purchaseInspection purchaseInspection
     * @return List<PurchaseInspection>
     */
    List<PurchaseInspection> findPurchaseInspections(PurchaseInspection purchaseInspection);

    /**
     * 新增
     *
     * @param purchaseInspection purchaseInspection
     */
    void createPurchaseInspection(String purchaseInspection, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param
     */
    void updatePurchaseInspection(String inspectionNumber, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param purchaseInspection purchaseInspection
     */
    void deletePurchaseInspection(PurchaseInspection purchaseInspection);

    PurchaseInspectionSchedule findPurchaseInspectionQueryPage(String inspectionNumber);

    List<PurchaseInspectionSchedule> queryPurchaseInspectionSchedule(String oddNumbers);

    void deletePurchaseInspectionNumber(String inspectionNumber);

    void confirmPurchaseInspection(String ids);

    void cancelPurchaseInspection(String ids);
}

package com.erp.sale.service;

import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.SaleDocumentfile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 上传文件表 Service接口
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
public interface ISaleDocumentfileService extends IService<SaleDocumentfile> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param saleDocumentfile saleDocumentfile
     * @return IPage<SaleDocumentfile>
     */
    IPage<SaleDocumentfile> findSaleDocumentfiles(QueryRequest request, SaleDocumentfile saleDocumentfile);

    /**
     * 查询（所有）
     *
     * @param saleDocumentfile saleDocumentfile
     * @return List<SaleDocumentfile>
     */
    List<SaleDocumentfile> findSaleDocumentfiles(SaleDocumentfile saleDocumentfile);

    /**
     * 新增
     *
     * @param saleDocumentfile saleDocumentfile
     */
    void createSaleDocumentfile(SaleDocumentfile saleDocumentfile);

    /**
     * 修改
     *
     * @param saleDocumentfile saleDocumentfile
     */
    void updateSaleDocumentfile(SaleDocumentfile saleDocumentfile);

    /**
     * 删除
     *
     * @param saleDocumentfile saleDocumentfile
     */
    void deleteSaleDocumentfile(SaleDocumentfile saleDocumentfile);

    void addDocumentFile(SaleDocumentfile documentFile);

    SaleDocumentfile findSaleDocumentFileByName(String fileName);

    void deleteSaleDocumentFile(String contFile);
}

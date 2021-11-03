package com.erp.sale.mapper;

import com.erp.sale.entity.SaleDocumentfile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;

/**
 * 上传文件表 Mapper
 *
 * @author qiufeng
 * @date 2021-10-15 15:24:27
 */
@Mapper
public interface SaleDocumentfileMapper extends BaseMapper<SaleDocumentfile> {

    SaleDocumentfile findSaleDocumentFileByName(@Param("fileName") String fileName);

    void deleteSaleDocumentFile(@Param("contFile") String contFile);
}

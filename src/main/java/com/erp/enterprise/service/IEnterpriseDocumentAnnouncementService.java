package com.erp.enterprise.service;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * 公文公告表 Service接口
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:08
 */
public interface IEnterpriseDocumentAnnouncementService extends IService<EnterpriseDocumentAnnouncement> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param enterpriseDocumentAnnouncement enterpriseDocumentAnnouncement
     * @return IPage<EnterpriseDocumentAnnouncement>
     */
    IPage<EnterpriseDocumentAnnouncement> findEnterpriseDocumentAnnouncements(QueryRequest request, EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    /**
     * 查询（所有）
     *
     * @param enterpriseDocumentAnnouncement enterpriseDocumentAnnouncement
     * @return List<EnterpriseDocumentAnnouncement>
     */
    List<EnterpriseDocumentAnnouncement> findEnterpriseDocumentAnnouncements(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    /**
     * 新增
     *
     * @param enterpriseDocumentAnnouncement enterpriseDocumentAnnouncement
     */
    void createEnterpriseDocumentAnnouncement(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) throws ParseException;

    /**
     * 修改
     *
     * @param enterpriseDocumentAnnouncement enterpriseDocumentAnnouncement
     */
    void updateEnterpriseDocumentAnnouncement(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    /**
     * 删除
     *
     * @param ids enterpriseDocumentAnnouncement
     */
    void deleteEnterpriseDocumentAnnouncement(String[] ids);


    EnterpriseDocumentAnnouncement documentAnnouncementById(Long id);


}

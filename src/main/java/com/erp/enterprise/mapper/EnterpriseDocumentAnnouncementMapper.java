package com.erp.enterprise.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 公文公告表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:08
 */
@Mapper
public interface EnterpriseDocumentAnnouncementMapper extends BaseMapper<EnterpriseDocumentAnnouncement> {

    long countEnterpriseDocumentAnnouncements(@Param("enterpriseDocumentAnnouncement") EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    IPage<EnterpriseDocumentAnnouncement> findEnterpriseDocumentAnnouncementsPage(Page<EnterpriseDocumentAnnouncement> page,@Param("enterpriseDocumentAnnouncement") EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    EnterpriseDocumentAnnouncement queryEnterpriseDocumentAnnouncement();

    void saveDocumentAnnouncement(@Param("enterpriseDocumentAnnouncement") EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);

    EnterpriseDocumentAnnouncement documentAnnouncementById(@Param("id") Long id);

    void updateEnterpriseDocumentAnnouncement(@Param("enterpriseDocumentAnnouncement") EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement);
}

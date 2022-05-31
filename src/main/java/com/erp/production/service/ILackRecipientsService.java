package com.erp.production.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.LackRecipients;
import com.erp.production.entity.LackRecipientsSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * 缺件领用 Service接口
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:17
 */
public interface ILackRecipientsService extends IService<LackRecipients> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param lackRecipients lackRecipients
     * @return IPage<LackRecipients>
     */
    IPage<LackRecipients> findLackRecipientss(QueryRequest request, LackRecipients lackRecipients);

    /**
     * 查询（所有）
     *
     * @param lackRecipients lackRecipients
     * @return List<LackRecipients>
     */
    List<LackRecipients> findLackRecipientss(LackRecipients lackRecipients);

    /**
     * 新增
     *
     * @param lackRecipients lackRecipients
     */
    void createLackRecipients(String lackRecipients, String dataTable) throws ParseException;

    /**
     * 修改
     *
     * @param lackCode lackRecipients
     */
    void updateLackRecipients(String lackCode, String dataTable) throws ParseException;

    /**
     * 删除
     *
     * @param lackRecipients lackRecipients
     */
    void deleteLackRecipients(LackRecipients lackRecipients);

    List<LackRecipientsSchedule> queryLackRecipients(String lackCode);

    LackRecipients lackRecipientsId(Long id);

    void cancelLackRecipients(String ids);

    void confirmLackRecipients(String ids);
}

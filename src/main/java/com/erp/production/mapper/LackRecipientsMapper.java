package com.erp.production.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.production.entity.LackRecipients;
import com.erp.production.entity.LackRecipientsSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 缺件领用 Mapper
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:17
 */
public interface LackRecipientsMapper extends BaseMapper<LackRecipients> {

    long countLackRecipients(@Param("lackRecipients") LackRecipients lackRecipients);

    IPage<LackRecipients> findLackRecipientsPage(@Param("lackRecipients") Page<LackRecipients> page, LackRecipients lackRecipients);

    List<LackRecipientsSchedule> queryLackRecipients(@Param("lackCode") String lackCode);

    LackRecipients queryLackRecipientsOne();

    void saveLackRecipientsSchedule(LackRecipientsSchedule lackRecipientsSchedule);

    void saveLackRecipients(LackRecipients lackRecipientsData);

    LackRecipients lackRecipientsId(@Param("id") Long id);

    void deleteLackRecipientsSchedule(@Param("lackCodeOne") String lackCodeOne);

    void cancelLackRecipients(@Param("ids") String ids);

    void confirmLackRecipients(@Param("ids") String ids);
}

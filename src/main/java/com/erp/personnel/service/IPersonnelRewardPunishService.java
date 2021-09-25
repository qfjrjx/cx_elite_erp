package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelRewardPunish;

import java.util.List;

/**
 * 奖罚记录 Service接口
 *
 * @author qiufeng
 * @date 2021-09-25 14:06:53
 */
public interface IPersonnelRewardPunishService extends IService<PersonnelRewardPunish> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelRewardPunish personnelRewardPunish
     * @return IPage<PersonnelRewardPunish>
     */
    IPage<PersonnelRewardPunish> findPersonnelRewardPunishs(QueryRequest request, PersonnelRewardPunish personnelRewardPunish);

    /**
     * 查询（所有）
     *
     * @param personnelRewardPunish personnelRewardPunish
     * @return List<PersonnelRewardPunish>
     */
    List<PersonnelRewardPunish> findPersonnelRewardPunishs(PersonnelRewardPunish personnelRewardPunish);

    /**
     * 新增
     *
     * @param personnelRewardPunish personnelRewardPunish
     */
    void createPersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish);

    /**
     * 修改
     *
     * @param personnelRewardPunish personnelRewardPunish
     */
    void updatePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish);

    /**
     * 删除
     *
     * @param personnelRewardPunish personnelRewardPunish
     */
    void deletePersonnelRewardPunish(PersonnelRewardPunish personnelRewardPunish);
}

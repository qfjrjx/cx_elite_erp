package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelRewardPunish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 奖罚记录 Mapper
 *
 * @author qiufeng
 * @date 2021-09-25 14:06:53
 */
@Mapper
public interface PersonnelRewardPunishMapper extends BaseMapper<PersonnelRewardPunish> {

    long countPersonnelRewardPunishs(@Param("personnelRewardPunish") PersonnelRewardPunish personnelRewardPunish);

    IPage<PersonnelRewardPunish> findPersonnelRewardPunishsPage(Page<PersonnelRewardPunish> page,@Param("personnelRewardPunish") PersonnelRewardPunish personnelRewardPunish);
}

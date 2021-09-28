package com.erp.personnel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.personnel.entity.PersonnelDormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 宿舍管理表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-19 09:35:28
 */
@Mapper
public interface PersonnelDormitoryMapper extends BaseMapper<PersonnelDormitory> {

    long countPersonnelDormitorys(@Param("personnelDormitory") PersonnelDormitory personnelDormitory);

    IPage<PersonnelDormitory> findPersonnelDormitorysPage(Page<PersonnelDormitory> page,@Param("personnelDormitory") PersonnelDormitory personnelDormitory);
    //修改员工宿舍已用数量
    void updateDormitory(@Param("dormitoryId") Long dormitoryId, @Param("usedBeds") Integer usedBeds);
    //删除时修改员工管理表宿舍已用数量
    void updateDormitoryPresentNnt(@Param("dormitoryNo") String dormitoryNo,@Param("dormitoryPlace") String dormitoryPlace,@Param("usedBedsCount") int usedBedsCount);
}

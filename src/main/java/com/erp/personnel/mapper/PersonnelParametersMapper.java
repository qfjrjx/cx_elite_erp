package com.erp.personnel.mapper;

import com.erp.personnel.entity.PersonnelParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 人事参数表 Mapper
 *
 * @author qiufeng
 * @date 2021-09-03 15:19:47
 */
@Mapper
public interface PersonnelParametersMapper extends BaseMapper<PersonnelParameters> {

    PersonnelParameters findById(@Param("id")Long id);
}

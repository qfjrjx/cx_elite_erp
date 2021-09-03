package com.erp.monitor.mapper;

import com.erp.monitor.entity.SystemLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrBird
 */
@Mapper
public interface LogMapper extends BaseMapper<SystemLog> {

}

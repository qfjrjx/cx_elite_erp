package com.erp.arrange.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.arrange.entity.WorkArrange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 工作安排表 Mapper
 *
 * @author qiufeng
 * @date 2021-12-20 15:35:45
 */
@Mapper
public interface WorkArrangeMapper extends BaseMapper<WorkArrange> {

    long countWorkArrange(@Param("workArrange") WorkArrange workArrange);

    IPage<WorkArrange> findWorkArrangePage(Page<WorkArrange> page,@Param("workArrange") WorkArrange workArrange);

    WorkArrange queryWorkArrange();

    WorkArrange findWorkArrangeById(@Param("id") Long id);

    void updateWorkArrangeState(@Param("id") Long id,@Param("stateParam") String stateParam);

    void updateWorkArrangeStateDate(@Param("id") Long id,@Param("stateParam") String stateParam,@Param("today") Date today);

    void updateWorkArrangeStateTime(@Param("id") Long id,@Param("stateParam") String stateParam,@Param("today") Date today);
}

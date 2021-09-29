package com.erp.personnel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelMobility;

import java.text.ParseException;
import java.util.List;

/**
 * 调岗记录 Service接口
 *
 * @author qiufeng
 * @date 2021-09-25 08:56:16
 */
public interface IPersonnelMobilityService extends IService<PersonnelMobility> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param personnelMobility personnelMobility
     * @return IPage<PersonnelMobility>
     */
    IPage<PersonnelMobility> findPersonnelMobilitys(QueryRequest request, PersonnelMobility personnelMobility);

    /**
     * 查询（所有）
     *
     * @param personnelMobility personnelMobility
     * @return List<PersonnelMobility>
     */
    List<PersonnelMobility> findPersonnelMobilitys(PersonnelMobility personnelMobility);

    /**
     * 新增
     *
     * @param personnelMobility personnelMobility
     */
    void createPersonnelMobility(PersonnelMobility personnelMobility) throws ParseException;

    /**
     * 修改
     *
     * @param personnelMobility personnelMobility
     */
    void updatePersonnelMobility(PersonnelMobility personnelMobility);

    /**
     * 删除
     *
     * @param ids ids
     */
    void deletePersonnelMobility(String[] ids);

    IPage<PersonnelArchives> findReceiveArchivesMobilityList(PersonnelArchives personnelArchives, QueryRequest request);

    IPage<PersonnelMobility> personnelMobilityUserList(QueryRequest request, String userId);

    /*查询所有调岗记录，根据生效时间生成定时任务。*/
    List<PersonnelMobility> queryPersonnelMobilityList();
    /*根据生效时间,修改部门或者岗位或者职务*/
    void updatePersonnelArchives(PersonnelArchives personnelArchives);
    //查询位置调动记录
    PersonnelMobility personnelMobilityTransfer(Long id);


}

package com.erp.job.task;

import com.erp.common.controller.BaseController;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelContract;
import com.erp.personnel.entity.PersonnelMobility;
import com.erp.personnel.service.IPersonnelMobilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author qiufeng
 */
@Slf4j
@Component
public class SchedulingTask {

    private final IPersonnelMobilityService personnelMobilityService;

    public SchedulingTask(IPersonnelMobilityService personnelMobilityService) {
        this.personnelMobilityService = personnelMobilityService;
    }
    /* 根据调岗记录生效时间每天夜里两点执行，判断当天是否有符合等于零的数据，有的话执行修改，修改部门或者岗位或者职务。*/
    public void mobilitySchedulingTask() throws ParseException {

        List<PersonnelMobility> personnelMobilities = personnelMobilityService.queryPersonnelMobilityList();
        PersonnelArchives personnelArchives = new PersonnelArchives();
        for (PersonnelMobility mobility: personnelMobilities) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            System.out.println(simpleDateFormat.format(new Date()));// new Date()为获取当前系统时间
            String date = simpleDateFormat.format(new Date());//系统当前时间
            System.out.println("系统当前时间："+date);
            long timeStart = simpleDateFormat.parse(date).getTime();//转化系统当前时间为毫秒值
            System.out.println("转化系统当前时间为毫秒值："+timeStart);
            String transferData = simpleDateFormat.format(mobility.getTransferData());//获取生效时间
            System.out.println("获取到期时间："+transferData);
            long timeEnd = simpleDateFormat.parse(transferData).getTime();//转化到期时间为毫秒值
            System.out.println("转化到期时间为毫秒值："+timeEnd);
            long dayCount= (timeEnd-timeStart)/(24*3600*1000);////两个日期想减得到天数
            System.out.println("两个日期想减得到天数："+dayCount);
            if(dayCount==0){
                String userName = mobility.getUserName();
                Long deptId = mobility.getDeptId();
                Long toPositionId =  mobility.getToPositionId();
                Long toGroupingId = mobility.getToGroupingId();
                personnelArchives.setUserId(mobility.getUserId());
                if (deptId!=null){
                    personnelArchives.setDeptId(deptId);
                } if (toPositionId!=null){
                    personnelArchives.setPositionId(toPositionId);
                } if (toGroupingId!=null){
                    personnelArchives.setDutiesId(toGroupingId);
                }
                personnelMobilityService.updatePersonnelArchives(personnelArchives);
                log.info("mobilitySchedulingTask方法，正在被执行"+userName);
            }
        }
    }

    public void testSchedulingTask(String params) {
        log.info("我是带参数的testSchedulingTask方法，正在被执行，参数为：{}", params);
    }


}

package com.erp.personnel.service.impl;

import com.erp.common.entity.FebsConstant;
import com.erp.common.entity.QueryRequest;
import com.erp.common.entity.Strings;
import com.erp.common.utils.SortUtil;
import com.erp.personnel.entity.ArchivesParameters;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelParameters;
import com.erp.personnel.mapper.PersonnelArchivesMapper;
import com.erp.personnel.service.IArchivesParametersService;
import com.erp.personnel.service.IPersonnelArchivesService;
import com.erp.system.entity.User;
import com.erp.system.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户表 Service实现
 *
 * @author qiufeng
 * @date 2021-09-07 10:46:55
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelArchivesServiceImpl extends ServiceImpl<PersonnelArchivesMapper, PersonnelArchives> implements IPersonnelArchivesService {

    private final PersonnelArchivesMapper personnelArchivesMapper;

    private final IArchivesParametersService archivesParametersService;

    @Override
    public IPage<PersonnelArchives> findPersonnelArchivess(QueryRequest request, PersonnelArchives personnelArchives) {
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelArchives(personnelArchives));
        return baseMapper.findPersonnelArchivesPage(page,personnelArchives);
    }

    @Override
    public IPage<PersonnelArchives> findPersonnelArchivesList(PersonnelArchives personnelArchives, QueryRequest request) {
        Page<PersonnelArchives> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelArchives(personnelArchives));
        SortUtil.handlePageSort(request, page, "entryDate", FebsConstant.ORDER_DESC, true);
        return baseMapper.findPersonnelArchivesPage(page,personnelArchives);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonnelArchives(PersonnelArchives personnelArchives) throws ParseException {
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date();
        Date date = new Date();
        //获取当月
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        //格式化当前月
        String month = sdf.format(date);
        //打印当前月
        //System.out.println(month);
        //查询出最后一个员工信息
        PersonnelArchives personnelArchivesOne = personnelArchivesMapper.queryPersonnelArchives();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String createTimeMonth = simpleDateFormat.format(personnelArchivesOne.getCreateDate());
        //打印创建当前月
        //System.out.println(createTimeMonth);
         if(month.equals(createTimeMonth)){
             String jobNumberOne = personnelArchivesOne.getJobNumber();
             String jobNumber = jobNumberOne.substring(4,6);
             int ss = Integer.parseInt(jobNumber);
               if (ss<9){
                   for (int i = ss; i < 100; i++) {
                       String n = "";
                       // 满足条件1
                       if (i < 10) {
                           n = "0" + (i+1);
                       }if ((i + 1) % 10 != 0){
                           personnelArchives.setJobNumber(yearLast+month+n);
                           break;
                       }
                   }
               }else{
                   int ff = ss+1;
                   if(ff < 100){
                       personnelArchives.setJobNumber(yearLast+month+ff);
                   }
               }
         }else if(!month.equals(createTimeMonth)){
             String n = "";
             int i = 0;
             // 满足条件1
             if (i < 10){
                 n = "0" + i;
             }
             if ((i + 1) % 10 != 0){
                 personnelArchives.setJobNumber(yearLast+month+n);
             }
         }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        personnelArchives.setCreateDate(today);//把获取系统当前时间赋值给实体对象

        //添加员工档案信息
        save(personnelArchives);
        // 保存社保信息
        String[] socialSecurity = personnelArchives.getId().split(Strings.COMMA);
       //调用社保添加方法
        setSocialSecurity(personnelArchives, socialSecurity);

        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(personnelArchives.getEntryDate());*/
        //baseMapper.savePersonnelArchives(personnelArchives);
    }
    private void setSocialSecurity(PersonnelArchives personnelArchives, String[] socialSecurity) {
        List<ArchivesParameters> archivesParameters = new ArrayList<>();
        Arrays.stream(socialSecurity).forEach(parametersId -> {
            ArchivesParameters archivesParameter = new ArchivesParameters();
            archivesParameter.setArchivesId(personnelArchives.getUserId());
            archivesParameter.setParametersId(Long.valueOf(parametersId));
            archivesParameters.add(archivesParameter);
        });

        archivesParametersService.saveBatch(archivesParameters);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonnelArchives(PersonnelArchives personnelArchives) {
        String[] archivesId = {String.valueOf(personnelArchives.getUserId())};
        archivesParametersService.deleteArchivesParameter(Arrays.asList(archivesId));
        String[] socialSecurity = StringUtils.splitByWholeSeparatorPreserveAllTokens(personnelArchives.getId(), Strings.COMMA);
        setSocialSecurity(personnelArchives, socialSecurity);

        baseMapper.saveOrUpdate(personnelArchives);
    }

    @Override
    public void deletePersonnelArchives(String[] ids) {
        List<String> list = Arrays.asList(ids);

        archivesParametersService.deleteArchivesParameter(list);

        baseMapper.deleteBatchIds(list);
	}

    @Override
    public PersonnelArchives findArchivesById(Long userId) {
        return baseMapper.findArchivesById(userId);
    }
}

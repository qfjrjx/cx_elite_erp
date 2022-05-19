package com.erp.technology.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyBomConfiguration;
import com.erp.technology.entity.TechnologyBomConfigurationSchedule;
import com.erp.technology.mapper.TechnologyBomConfigurationMapper;
import com.erp.technology.service.ITechnologyBomConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * BOM配置 Service实现
 *
 * @author qiufeng
 * @date 2022-05-09 09:43:35
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TechnologyBomConfigurationServiceImpl extends ServiceImpl<TechnologyBomConfigurationMapper, TechnologyBomConfiguration> implements ITechnologyBomConfigurationService {

    private final TechnologyBomConfigurationMapper technologyBomConfigurationMapper;

    @Override
    public IPage<TechnologyBomConfiguration> findTechnologyBomConfigurations(QueryRequest request, TechnologyBomConfiguration technologyBomConfiguration) {
        Page<TechnologyBomConfiguration> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countTechnologyBomConfiguration(technologyBomConfiguration));
        return baseMapper.findTechnologyBomConfigurationPage(page,technologyBomConfiguration);
    }

    @Override
    public List<TechnologyBomConfiguration> findTechnologyBomConfigurations(TechnologyBomConfiguration technologyBomConfiguration) {
	    LambdaQueryWrapper<TechnologyBomConfiguration> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTechnologyBomConfiguration(String technologyBomConfiguration,String dataTable) throws ParseException {
        TechnologyBomConfiguration technologyBomConfigurationDate = new TechnologyBomConfiguration();
        /*单号案例 BOM-22050001*/
        String initials = "BOM-";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(technologyBomConfiguration);
        String userName = object.getString("userName");
        String parameterName = object.getString("parameterName");
        String parameterState = object.getString("parameterState");
        String productName = object.getString("productName");
        String parameterAttachment = object.getString("parameterAttachment");
        TechnologyBomConfiguration technologyBomConfigurationOne = null;
        technologyBomConfigurationOne = baseMapper.queryTechnologyBomConfiguration();
        if (technologyBomConfigurationOne != null){
            String oddNumbers = technologyBomConfigurationOne.getParameterCode();
            /*单号案例 BOM-22050001*/
            String oddNumbersOne = oddNumbers.substring(8,12);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<1000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            technologyBomConfigurationDate.setParameterCode(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<1000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                technologyBomConfigurationDate.setParameterCode(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        technologyBomConfigurationDate.setParameterInputting(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        technologyBomConfigurationDate.setParameterInputtingDate(today);
        technologyBomConfigurationDate.setParameterState("1");
        technologyBomConfigurationDate.setParameterPart(parameterName);
        technologyBomConfigurationDate.setParameterWith(parameterState);
        technologyBomConfigurationDate.setParameterAttachment(parameterAttachment);
        technologyBomConfigurationDate.setParameterInstructions(productName);

        TechnologyBomConfigurationSchedule technologyBomConfigurationSchedule = new TechnologyBomConfigurationSchedule();
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            technologyBomConfigurationSchedule.setParameterCode(generateDocNo);
            technologyBomConfigurationSchedule.setParameterDate(today);
            String parameterDrawings = jsonArrayOne.getJSONObject(i).getString("parameterDrawings");
            String parameterMaterial = jsonArrayOne.getJSONObject(i).getString("parameterMaterial");
            String parameterSpecifications = jsonArrayOne.getJSONObject(i).getString("parameterSpecifications");
            String parameterUnit = jsonArrayOne.getJSONObject(i).getString("parameterUnit");
            String parameterBrand = jsonArrayOne.getJSONObject(i).getString("parameterBrand");
            String parameterNumber = jsonArrayOne.getJSONObject(i).getString("parameterNumber");
            String parameterReplace = jsonArrayOne.getJSONObject(i).getString("parameterReplace");
            String parameterNote = jsonArrayOne.getJSONObject(i).getString("parameterNote");
            String parameterMaterialCode = jsonArrayOne.getJSONObject(i).getString("parameterMaterialCode");
            String parameterLocation = jsonArrayOne.getJSONObject(i).getString("parameterLocation");

            technologyBomConfigurationSchedule.setParameterDrawings(parameterDrawings);
            technologyBomConfigurationSchedule.setParameterMaterial(parameterMaterial);
            technologyBomConfigurationSchedule.setParameterSpecifications(parameterSpecifications);
            technologyBomConfigurationSchedule.setParameterUnit(parameterUnit);
            technologyBomConfigurationSchedule.setParameterBrand(parameterBrand);
            technologyBomConfigurationSchedule.setParameterNumber(parameterNumber);
            technologyBomConfigurationSchedule.setParameterReplace(parameterReplace);
            technologyBomConfigurationSchedule.setParameterNote(parameterNote);
            technologyBomConfigurationSchedule.setParameterMaterialCode(parameterMaterialCode);
            technologyBomConfigurationSchedule.setParameterLocation(parameterLocation);
            baseMapper.saveTechnologyBomConfigurationSchedule(technologyBomConfigurationSchedule);
        }
        baseMapper.saveTechnologyBomConfigurationDate(technologyBomConfigurationDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTechnologyBomConfiguration(String technologyBomConfiguration,String dataTable) throws ParseException {
        TechnologyBomConfiguration technologyBomConfigurationDate = new TechnologyBomConfiguration();
        JSONObject object = JSON.parseObject(technologyBomConfiguration);
        String userName = object.getString("userName");
        String parameterPart = object.getString("parameterPart");
        String parameterWith = object.getString("parameterWith");
        String parameterInstructions = object.getString("parameterInstructions");
        String parameterCode = object.getString("parameterCode");
        String parameterAttachment = object.getString("parameterAttachment");
        baseMapper.deleteTechnologyBomConfiguration(parameterCode);
        baseMapper.deleteTechnologyBomConfigurationSchedule(parameterCode);

        technologyBomConfigurationDate.setParameterInputting(userName);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        technologyBomConfigurationDate.setParameterInputtingDate(today);
        technologyBomConfigurationDate.setParameterState("1");
        technologyBomConfigurationDate.setParameterPart(parameterPart);
        technologyBomConfigurationDate.setParameterWith(parameterWith);
        technologyBomConfigurationDate.setParameterAttachment(parameterAttachment);
        technologyBomConfigurationDate.setParameterInstructions(parameterInstructions);
        technologyBomConfigurationDate.setParameterCode(parameterCode);

        TechnologyBomConfigurationSchedule technologyBomConfigurationSchedule = new TechnologyBomConfigurationSchedule();
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            technologyBomConfigurationSchedule.setParameterCode(parameterCode);
            technologyBomConfigurationSchedule.setParameterDate(today);
            String parameterDrawings = jsonArrayOne.getJSONObject(i).getString("parameterDrawings");
            String parameterMaterial = jsonArrayOne.getJSONObject(i).getString("parameterMaterial");
            String parameterSpecifications = jsonArrayOne.getJSONObject(i).getString("parameterSpecifications");
            String parameterUnit = jsonArrayOne.getJSONObject(i).getString("parameterUnit");
            String parameterBrand = jsonArrayOne.getJSONObject(i).getString("parameterBrand");
            String parameterNumber = jsonArrayOne.getJSONObject(i).getString("parameterNumber");
            String parameterReplace = jsonArrayOne.getJSONObject(i).getString("parameterReplace");
            String parameterNote = jsonArrayOne.getJSONObject(i).getString("parameterNote");
            String parameterMaterialCode = jsonArrayOne.getJSONObject(i).getString("parameterMaterialCode");
            String parameterLocation = jsonArrayOne.getJSONObject(i).getString("parameterLocation");
            technologyBomConfigurationSchedule.setParameterDrawings(parameterDrawings);
            technologyBomConfigurationSchedule.setParameterMaterial(parameterMaterial);
            technologyBomConfigurationSchedule.setParameterSpecifications(parameterSpecifications);
            technologyBomConfigurationSchedule.setParameterUnit(parameterUnit);
            technologyBomConfigurationSchedule.setParameterBrand(parameterBrand);
            technologyBomConfigurationSchedule.setParameterNumber(parameterNumber);
            technologyBomConfigurationSchedule.setParameterReplace(parameterReplace);
            technologyBomConfigurationSchedule.setParameterNote(parameterNote);
            technologyBomConfigurationSchedule.setParameterMaterialCode(parameterMaterialCode);
            technologyBomConfigurationSchedule.setParameterLocation(parameterLocation);
            baseMapper.saveTechnologyBomConfigurationSchedule(technologyBomConfigurationSchedule);
        }
        baseMapper.saveTechnologyBomConfigurationDate(technologyBomConfigurationDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTechnologyBomConfiguration(String parameterCode) {
        baseMapper.deleteTechnologyBomConfiguration(parameterCode);
        baseMapper.deleteTechnologyBomConfigurationSchedule(parameterCode);
	}

    @Override
    public TechnologyBomConfiguration findTechnologyBomConfigurationModel(String id) {
        return baseMapper.findTechnologyBomConfigurationModel(id);
    }

    @Override
    public List<TechnologyBomConfigurationSchedule> queryConfigurationRefer(String parameterCode) {
        return baseMapper.queryConfigurationRefer(parameterCode);
    }

    @Override
    public void confirmTechnologyBomConfiguration(String ids) {
        baseMapper.confirmTechnologyBomConfiguration(ids);
    }

    @Override
    public void cancelTechnologyBomConfiguration(String ids) {
        baseMapper.cancelTechnologyBomConfiguration(ids);
    }
}

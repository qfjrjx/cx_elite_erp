package com.erp.production.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.LackRecipients;
import com.erp.production.entity.LackRecipientsSchedule;
import com.erp.production.mapper.LackRecipientsMapper;
import com.erp.production.service.ILackRecipientsService;
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
 * 缺件领用 Service实现
 *
 * @author qiufeng
 * @date 2022-05-24 09:30:17
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LackRecipientsServiceImpl extends ServiceImpl<LackRecipientsMapper, LackRecipients> implements ILackRecipientsService {

    private final LackRecipientsMapper lackRecipientsMapper;

    @Override
    public IPage<LackRecipients> findLackRecipientss(QueryRequest request, LackRecipients lackRecipients) {
        Page<LackRecipients> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countLackRecipients(lackRecipients));
        return baseMapper.findLackRecipientsPage(page,lackRecipients);
    }

    @Override
    public List<LackRecipients> findLackRecipientss(LackRecipients lackRecipients) {
	    LambdaQueryWrapper<LackRecipients> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLackRecipients(String lackRecipients ,String dataTable) throws ParseException {
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        LackRecipients lackRecipientsData = new LackRecipients();
        /*单号案例 QLLY22050094*/
        String initials = "QLLY";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(lackRecipients);
        String  recipientsPeople =  object.getString("recipientsPeople");
        String  recipientsWarehouse =  object.getString("recipientsWarehouse");
        String  lackRecipientsDate =  object.getString("lackRecipientsDate");
        String  recipientsDepartment =  object.getString("recipientsDepartment");
        String  userName =  object.getString("userName");
        LackRecipients lackRecipientsOne = null;
        lackRecipientsOne = baseMapper.queryLackRecipientsOne();
        if (lackRecipientsOne != null){
            String oddNumbers = lackRecipientsOne.getLackCode();
            /*单号案例 QLLY22050094*/
            String oddNumbersOne = oddNumbers.substring(8,12);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            lackRecipientsData.setLackCode(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                lackRecipientsData.setLackCode(generateDocNo);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        lackRecipientsData.setLackCreateDate(today);
        lackRecipientsData.setLackCreateName(userName);
        Date lackRecipientsDateOne = simpleDateFormatOne.parse(lackRecipientsDate);
        lackRecipientsData.setLackDate(lackRecipientsDateOne);
        lackRecipientsData.setLackState("1");
        if (!recipientsWarehouse.equals("")) {
            lackRecipientsData.setLackWarehouse(recipientsWarehouse);
        }if (!recipientsDepartment.equals("")) {
            lackRecipientsData.setLackDepartment(recipientsDepartment);
        }if (!recipientsPeople.equals("")) {
            lackRecipientsData.setLackPeople(recipientsPeople);
        }
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        LackRecipientsSchedule lackRecipientsSchedule = new LackRecipientsSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            lackRecipientsSchedule.setLackCode(generateDocNo);
            String recipientsLackCode = jsonArrayOne.getJSONObject(i).getString("recipientsLackCode");
            String lackMachine = jsonArrayOne.getJSONObject(i).getString("lackMachine");
            String lackMaterial = jsonArrayOne.getJSONObject(i).getString("lackMaterial");
            String lackSpecifications = jsonArrayOne.getJSONObject(i).getString("lackSpecifications");
            String lackUnit = jsonArrayOne.getJSONObject(i).getString("lackUnit");
            String inventoryNumber = jsonArrayOne.getJSONObject(i).getString("inventoryNumber");
            String lackNumber = jsonArrayOne.getJSONObject(i).getString("lackNumber");
            String recipientsNumber = jsonArrayOne.getJSONObject(i).getString("recipientsNumber");
            String theNumber = jsonArrayOne.getJSONObject(i).getString("theNumber");
            String lackNote = jsonArrayOne.getJSONObject(i).getString("lackNote");
            String lackLocation = jsonArrayOne.getJSONObject(i).getString("lackLocation");
            String lackCoding = jsonArrayOne.getJSONObject(i).getString("lackCoding");
            lackRecipientsSchedule.setLackSpecifications(lackSpecifications);
            lackRecipientsSchedule.setRecipientsLackCode(recipientsLackCode);
            lackRecipientsSchedule.setLackMachine(lackMachine);
            lackRecipientsSchedule.setLackMaterial(lackMaterial);
            lackRecipientsSchedule.setLackUnit(lackUnit);
            lackRecipientsSchedule.setInventoryNumber(inventoryNumber);
            lackRecipientsSchedule.setLackNumber(lackNumber);
            lackRecipientsSchedule.setRecipientsNumber(recipientsNumber);
            lackRecipientsSchedule.setTheNumber(theNumber);
            lackRecipientsSchedule.setLackNote(lackNote);
            lackRecipientsSchedule.setLackLocation(lackLocation);
            lackRecipientsSchedule.setLackCoding(lackCoding);
            baseMapper.saveLackRecipientsSchedule(lackRecipientsSchedule);
            lackRecipientsData.setLackMachine(lackMachine);
        }
        baseMapper.saveLackRecipients(lackRecipientsData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLackRecipients(String lackCode ,String dataTable) throws ParseException {
        baseMapper.deleteLackRecipientsSchedule(lackCode);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        LackRecipientsSchedule lackRecipientsSchedule = new LackRecipientsSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++) {
            lackRecipientsSchedule.setLackCode(lackCode);
            String recipientsLackCode = jsonArrayOne.getJSONObject(i).getString("recipientsLackCode");
            String lackMachine = jsonArrayOne.getJSONObject(i).getString("lackMachine");
            String lackMaterial = jsonArrayOne.getJSONObject(i).getString("lackMaterial");
            String lackSpecifications = jsonArrayOne.getJSONObject(i).getString("lackSpecifications");
            String lackUnit = jsonArrayOne.getJSONObject(i).getString("lackUnit");
            String inventoryNumber = jsonArrayOne.getJSONObject(i).getString("inventoryNumber");
            String lackNumber = jsonArrayOne.getJSONObject(i).getString("lackNumber");
            String recipientsNumber = jsonArrayOne.getJSONObject(i).getString("recipientsNumber");
            String theNumber = jsonArrayOne.getJSONObject(i).getString("theNumber");
            String lackNote = jsonArrayOne.getJSONObject(i).getString("lackNote");
            String lackLocation = jsonArrayOne.getJSONObject(i).getString("lackLocation");
            String lackCoding = jsonArrayOne.getJSONObject(i).getString("lackCoding");
            lackRecipientsSchedule.setLackSpecifications(lackSpecifications);
            lackRecipientsSchedule.setRecipientsLackCode(recipientsLackCode);
            lackRecipientsSchedule.setLackMachine(lackMachine);
            lackRecipientsSchedule.setLackMaterial(lackMaterial);
            lackRecipientsSchedule.setLackUnit(lackUnit);
            lackRecipientsSchedule.setInventoryNumber(inventoryNumber);
            lackRecipientsSchedule.setLackNumber(lackNumber);
            lackRecipientsSchedule.setRecipientsNumber(recipientsNumber);
            lackRecipientsSchedule.setTheNumber(theNumber);
            lackRecipientsSchedule.setLackNote(lackNote);
            lackRecipientsSchedule.setLackLocation(lackLocation);
            lackRecipientsSchedule.setLackCoding(lackCoding);
            baseMapper.saveLackRecipientsSchedule(lackRecipientsSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLackRecipients(LackRecipients lackRecipients) {
        LambdaQueryWrapper<LackRecipients> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<LackRecipientsSchedule> queryLackRecipients(String lackCode) {
        return baseMapper.queryLackRecipients(lackCode);
    }

    @Override
    public LackRecipients lackRecipientsId(Long id) {
        return baseMapper.lackRecipientsId(id);
    }

    @Override
    public void cancelLackRecipients(String ids) {
        baseMapper.cancelLackRecipients(ids);
    }

    @Override
    public void confirmLackRecipients(String ids) {
        baseMapper.confirmLackRecipients(ids);
    }
}

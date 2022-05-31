package com.erp.production.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionLack;
import com.erp.production.entity.ProductionRecipients;
import com.erp.production.entity.ProductionRecipientsSchedule;
import com.erp.production.mapper.ProductionRecipientsMapper;
import com.erp.production.service.IProductionRecipientsService;
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
 * 生产领用 Service实现
 *
 * @author qiufeng
 * @date 2022-05-19 16:44:59
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductionRecipientsServiceImpl extends ServiceImpl<ProductionRecipientsMapper, ProductionRecipients> implements IProductionRecipientsService {

    private final ProductionRecipientsMapper productionRecipientsMapper;

    @Override
    public IPage<ProductionRecipients> findProductionRecipientss(QueryRequest request, ProductionRecipients productionRecipients) {
        Page<ProductionRecipients> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductionRecipients(productionRecipients));
        return baseMapper.findProductionRecipientsPage(page,productionRecipients);
    }

    @Override
    public List<ProductionRecipients> findProductionRecipientss(ProductionRecipients productionRecipients) {
	    LambdaQueryWrapper<ProductionRecipients> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductionRecipients(String recipients ,String dataTable) throws ParseException {
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        ProductionRecipients productionRecipients = new ProductionRecipients();
        /*单号案例 BOMLY22050263*/
        String initials = "BOMLY";
        String generateDocNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(recipients);
        String  recipientsWarehouse =  object.getString("recipientsWarehouse");
        String  recipientsMachine =  object.getString("recipientsMachine");
        String  recipientsDepartment =  object.getString("recipientsDepartment");
        String  recipientsPeople =  object.getString("recipientsPeople");
        String  recipientsCustomer =  object.getString("recipientsCustomer");
        String  recipientsModel =  object.getString("recipientsModel");
        //查询出最后一个工作安排单号
        ProductionRecipients productionRecipientsOne = null;
        productionRecipientsOne = baseMapper.queryProductionRecipientsOne();
        if (productionRecipientsOne != null){
            String oddNumbers = productionRecipientsOne.getRecipientsCode();
            /*单号案例 BOMLY22050263*/
            String oddNumbersOne = oddNumbers.substring(9,13);
            int oddNumber = Integer.parseInt(oddNumbersOne);
            String oddNumberOne = null;
            if (oddNumber<10000){
                oddNumberOne = String.format("%04d",oddNumber+1);
            }
            generateDocNo = initials+yearLast+month+oddNumberOne;
            productionRecipients.setRecipientsCode(generateDocNo);
            //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                productionRecipients.setRecipientsCode(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        productionRecipients.setRecipientsDate(today);
        productionRecipients.setRecipientsState("1");
        productionRecipients.setRecipientsDepartment(recipientsDepartment);
        productionRecipients.setRecipientsPeople(recipientsPeople);
        productionRecipients.setRecipientsMachine(recipientsMachine);
        productionRecipients.setRecipientsCustomer(recipientsCustomer);
        productionRecipients.setRecipientsModel(recipientsModel);
        productionRecipients.setRecipientsWarehouse(recipientsWarehouse);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        ProductionRecipientsSchedule productionRecipientsSchedule = new ProductionRecipientsSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            productionRecipientsSchedule.setRecipientsCode(generateDocNo);
            String recipientsMaterial = jsonArrayOne.getJSONObject(i).getString("recipientsMaterial");
            String recipientsSpecifications = jsonArrayOne.getJSONObject(i).getString("recipientsSpecifications");
            String recipientsUnit = jsonArrayOne.getJSONObject(i).getString("recipientsUnit");
            String recipientsNumber = jsonArrayOne.getJSONObject(i).getString("recipientsNumber");
            String recipientsNote = jsonArrayOne.getJSONObject(i).getString("recipientsNote");
            String recipientsBrand = jsonArrayOne.getJSONObject(i).getString("recipientsBrand");
            String recipientsMass = jsonArrayOne.getJSONObject(i).getString("recipientsMass");
            String recipientsLocation = jsonArrayOne.getJSONObject(i).getString("recipientsLocation");
            String recipientsLack = jsonArrayOne.getJSONObject(i).getString("recipientsLack");
            String settlementPrice = jsonArrayOne.getJSONObject(i).getString("settlementPrice");
            String lackMaterialCode = jsonArrayOne.getJSONObject(i).getString("lackMaterialCode");
            productionRecipientsSchedule.setRecipientsMaterial(recipientsMaterial);
            productionRecipientsSchedule.setRecipientsSpecifications(recipientsSpecifications);
            productionRecipientsSchedule.setRecipientsUnit(recipientsUnit);
            productionRecipientsSchedule.setRecipientsNumber(recipientsNumber);
            productionRecipientsSchedule.setRecipientsNote(recipientsNote);
            productionRecipientsSchedule.setRecipientsBrand(recipientsBrand);
            productionRecipientsSchedule.setRecipientsMass(recipientsMass);
            productionRecipientsSchedule.setRecipientsLocation(recipientsLocation);
            productionRecipientsSchedule.setRecipientsLack(recipientsLack);
            productionRecipientsSchedule.setRecipientsInventory(settlementPrice);
            if (recipientsLack != null){

            }
            String generateDocNoQ = generateDocNo+"Q";
            if (recipientsLack != null){
                ProductionLack productionLack = new ProductionLack();
                String recipientsLackOne = recipientsLack.substring(1,2);
                productionLack.setRecipientsCode(generateDocNo);
                productionLack.setLackCode(generateDocNoQ);
                productionLack.setLackDate(today);
                productionLack.setLackMaterial(recipientsMaterial);
                productionLack.setLackSpecifications(recipientsSpecifications);
                productionLack.setLackMachine(recipientsMachine);
                productionLack.setLackWarehouse(recipientsWarehouse);
                productionLack.setLackNumber(recipientsLackOne);
                productionLack.setLackUnit(recipientsUnit);
                productionLack.setLackRecipients("2");
                productionLack.setLackMaterialCode(lackMaterialCode);
                baseMapper.saveProductionLack(productionLack);
                productionRecipients.setRecipientsLackCode(generateDocNoQ);
            }
            baseMapper.saveProductionRecipientsSchedule(productionRecipientsSchedule);
        }
        baseMapper.saveProductionRecipients(productionRecipients);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductionRecipients(String recipients ,String dataTable) throws ParseException {
        JSONObject object = JSON.parseObject(recipients);
        String  recipientsWarehouse =  object.getString("recipientsWarehouse");
        String  recipientsCode =  object.getString("recipientsCode");
        String recipientsMachine = object.getString("recipientsMachine");
        baseMapper.deleteProductionRecipients(recipientsCode);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        ProductionRecipientsSchedule productionRecipientsSchedule = new ProductionRecipientsSchedule();
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        for(int i = 0; i < jsonArrayOne.size(); i++){
            productionRecipientsSchedule.setRecipientsCode(recipientsCode);
            String recipientsMaterial = jsonArrayOne.getJSONObject(i).getString("recipientsMaterial");
            String recipientsSpecifications = jsonArrayOne.getJSONObject(i).getString("recipientsSpecifications");
            String recipientsUnit = jsonArrayOne.getJSONObject(i).getString("recipientsUnit");
            String recipientsNumber = jsonArrayOne.getJSONObject(i).getString("recipientsNumber");
            String recipientsNote = jsonArrayOne.getJSONObject(i).getString("recipientsNote");
            String recipientsBrand = jsonArrayOne.getJSONObject(i).getString("recipientsBrand");
            String recipientsMass = jsonArrayOne.getJSONObject(i).getString("recipientsMass");
            String recipientsLocation = jsonArrayOne.getJSONObject(i).getString("recipientsLocation");
            String recipientsLack = jsonArrayOne.getJSONObject(i).getString("recipientsLack");
            String settlementPrice = jsonArrayOne.getJSONObject(i).getString("settlementPrice");
            String lackMaterialCode = jsonArrayOne.getJSONObject(i).getString("lackMaterialCode");
            productionRecipientsSchedule.setRecipientsMaterial(recipientsMaterial);
            productionRecipientsSchedule.setRecipientsSpecifications(recipientsSpecifications);
            productionRecipientsSchedule.setRecipientsUnit(recipientsUnit);
            productionRecipientsSchedule.setRecipientsNumber(recipientsNumber);
            productionRecipientsSchedule.setRecipientsNote(recipientsNote);
            productionRecipientsSchedule.setRecipientsBrand(recipientsBrand);
            productionRecipientsSchedule.setRecipientsMass(recipientsMass);
            productionRecipientsSchedule.setRecipientsLocation(recipientsLocation);
            productionRecipientsSchedule.setRecipientsLack(recipientsLack);
            productionRecipientsSchedule.setRecipientsInventory(settlementPrice);
            if (recipientsLack != null){
                ProductionLack productionLack = new ProductionLack();
                String recipientsLackOne = recipientsLack.substring(1,2);
                productionLack.setRecipientsCode(recipientsCode);
                productionLack.setLackCode(recipientsCode+"Q");
                productionLack.setLackDate(today);
                productionLack.setLackMaterial(recipientsMaterial);
                productionLack.setLackSpecifications(recipientsSpecifications);
                productionLack.setLackMachine(recipientsMachine);
                productionLack.setLackWarehouse(recipientsWarehouse);
                productionLack.setLackNumber(recipientsLackOne);
                productionLack.setLackUnit(recipientsUnit);
                productionLack.setLackRecipients("2");
                productionLack.setLackMaterialCode(lackMaterialCode);
                baseMapper.saveProductionLack(productionLack);
            }
            baseMapper.saveProductionRecipientsSchedule(productionRecipientsSchedule);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductionRecipients(ProductionRecipients productionRecipients) {
        LambdaQueryWrapper<ProductionRecipients> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<ProductionRecipientsSchedule> queryProductionRecipients(String recipientsCode) {
        return baseMapper.queryProductionRecipients(recipientsCode);
    }

    @Override
    public ProductionRecipients productionRecipientsId(Long id) {
        return baseMapper.productionRecipientsId(id);
    }

    @Override
    public void cancelProductionRecipients(String ids) {
        baseMapper.cancelProductionRecipients(ids);
    }

    @Override
    public void confirmProductionRecipients(String ids) {
        baseMapper.confirmProductionRecipients(ids);
    }

    @Override
    public List<ProductionLack> queryProductionRecipientsLack(String recipientsCode) {
        return baseMapper.queryProductionRecipientsLack(recipientsCode);
    }

    @Override
    public IPage<ProductionLack> findProductionLackPage(QueryRequest request, ProductionLack productionLack) {
        Page<ProductionLack> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductionLack(productionLack));
        return baseMapper.findProductionLackPage(page,productionLack);
    }
}

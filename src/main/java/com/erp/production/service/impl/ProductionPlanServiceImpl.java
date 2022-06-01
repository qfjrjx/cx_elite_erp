package com.erp.production.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.production.entity.ProductionPlan;
import com.erp.production.entity.ProductionPlanSchedule;
import com.erp.production.entity.SetupBom;
import com.erp.production.entity.SetupBomSchedule;
import com.erp.production.mapper.ProductionPlanMapper;
import com.erp.production.service.IProductionPlanService;
import com.erp.technology.entity.TechnologyBomConfigurationSchedule;
import com.erp.technology.mapper.TechnologyBomConfigurationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 生产计划 Service实现
 *
 * @author qiufeng
 * @date 2022-04-29 10:54:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductionPlanServiceImpl extends ServiceImpl<ProductionPlanMapper, ProductionPlan> implements IProductionPlanService {

    private final ProductionPlanMapper productionPlanMapper;

    private final TechnologyBomConfigurationMapper technologyBomConfigurationMapper;

    @Override
    public IPage<ProductionPlan> findProductionPlans(QueryRequest request, ProductionPlan productionPlan) {
        Page<ProductionPlan> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductionPlans(productionPlan));
        return baseMapper.findProductionPlansPage(page,productionPlan);
    }

    @Override
    public List<ProductionPlan> findProductionPlans(ProductionPlan productionPlan) {
	    LambdaQueryWrapper<ProductionPlan> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductionPlan(String userName,ProductionPlanSchedule productionPlanSchedule) throws ParseException {
        //生产计划主表
        ProductionPlan productionPlan = new ProductionPlan();
        //生产计划附表
        ProductionPlanSchedule productionPlanScheduleDate = new ProductionPlanSchedule();
        /*单号案例 HT2204060*/
        String initCode = "L";
        String planCodeNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //对象转json
        String jsonString = JSON.toJSONString(productionPlanSchedule);
        JSONObject object = JSON.parseObject(jsonString);
        Date planDate =  object.getDate("planDate");
        String customerName =  object.getString("customerName");
        String planOrder =  object.getString("planOrder");
        String planAmount = object.getString("planAmount");
        String planMachine = object.getString("planMachine");
        String planConfiguration =  object.getString("planConfiguration");
        String planCutting =  object.getString("planCutting");
        String planProcess =  object.getString("planProcess");
        String planOther = object.getString("planOther");
        String planJig = object.getString("planJig");
        String planShape =  object.getString("planShape");
        String planSize =  object.getString("planSize");
        String planNote =  object.getString("planNote");
        String productName =  object.getString("productName");
        String specificationModel =  object.getString("specificationModel");
        String planAttachment =  object.getString("planAttachment");
        String planMoney =  object.getString("planMoney");
        String planUnit =  object.getString("planUnit");
        //查询出最后一个工作安排单号
        ProductionPlan productionPlanOne = null;
        productionPlanOne = baseMapper.queryProductionPlan();
        if (productionPlanOne != null){
            String planCode = productionPlanOne.getPlanCode();
            /*机器码案例 L22040055*/
            String planCodeOne = planCode.substring(5,9);
            int planCodes = Integer.parseInt(planCodeOne);
            String planCodesOne = null;
            if (planCodes<100) {
                planCodesOne = String.format("%04d", planCodes + 1);
            }
            planCodeNo = initCode+yearLast+month+planCodesOne;
            productionPlan.setPlanCode(planCodeNo);
            }else {
                for (int i=1;i<100;i++){
                    //机器码
                    String planCodesThree = String.format("%04d",i);
                    planCodeNo = initCode+yearLast+month+planCodesThree;
                    productionPlan.setPlanCode(planCodeNo);
                    break;
                }
            }
            //主表数据
            productionPlan.setPlanVoucher(userName);
            SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
            Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
            productionPlan.setPlanVoucherDate(today);
            productionPlan.setPlanState("1");
            productionPlan.setPlanDate(planDate);
            productionPlan.setPlanCustomerName(customerName);
            productionPlan.setPlanProductName(productName);
            productionPlan.setPlanSpecifications(specificationModel);
            productionPlan.setPlanNumber(planOrder);
            BigDecimal planMoneyTo = new BigDecimal(planMoney);
            productionPlan.setPlanMoney(planMoneyTo);
            productionPlan.setPlanUnit(planUnit);
            String planConfigurationOnt = null;
            if (planMachine != null){
                String s1 = "机器要求："+planMachine;
                String s2 = "电脑配置："+planConfiguration;
                String s3 = "刀具大小："+planCutting;
                String s4 = "加工工序："+planProcess;
                String s5 = "其他要求："+planOther;
                String s6 = "夹具要求："+planJig;
                String s7 = "产品形状："+planShape;
                String s8 = "产品尺寸："+planSize;
                planConfigurationOnt = s1+s2+s3+s4+s5+s6+s7+s8;
            }
            productionPlan.setPlanConfiguration(planConfigurationOnt);
            productionPlan.setPlanNote(planNote);
            //附表数据
            productionPlanScheduleDate.setPlanOrder(planOrder);
            productionPlanScheduleDate.setPlanNumber(planOrder);
            productionPlanScheduleDate.setPlanAmount(planAmount);
            productionPlanScheduleDate.setPlanMachine(planMachine);
            productionPlanScheduleDate.setPlanConfiguration(planConfiguration);
            productionPlanScheduleDate.setPlanCutting(planCutting);
            productionPlanScheduleDate.setPlanProcess(planProcess);
            productionPlanScheduleDate.setPlanOther(planOther);
            productionPlanScheduleDate.setPlanJig(planJig);
            productionPlanScheduleDate.setPlanShape(planShape);
            productionPlanScheduleDate.setPlanSize(planSize);
            productionPlanScheduleDate.setPlanNote(planNote);
            productionPlanScheduleDate.setPlanDate(planDate);
            productionPlanScheduleDate.setPlanAttachment(planAttachment);
            productionPlanScheduleDate.setPlanCode(planCodeNo);
            baseMapper.saveProductionPlanScheduleDate(productionPlanScheduleDate);
            baseMapper.saveProductionPlan(productionPlan);
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductionPlan(ProductionPlanSchedule productionPlanSchedule) {
        baseMapper.deleteProductionPlanSchedule(productionPlanSchedule.getPlanCode());
        //对象转json
        String s= JSON.toJSONString(productionPlanSchedule);
        JSONObject object = JSON.parseObject(s);
        Date planDate =  object.getDate("planDate");
        String planOrder =  object.getString("planOrder");
        String planAmount = object.getString("planAmount");
        String planMachine = object.getString("planMachine");
        String planConfiguration =  object.getString("planConfiguration");
        String planCutting =  object.getString("planCutting");
        String planProcess =  object.getString("planProcess");
        String planOther = object.getString("planOther");
        String planJig = object.getString("planJig");
        String planShape =  object.getString("planShape");
        String planSize =  object.getString("planSize");
        String planNote =  object.getString("planNote");
        String planNumber =  object.getString("planNumber");
        String planAttachment =  object.getString("planAttachment");
        String planCode =  object.getString("planCode");
        //生产计划附表
        ProductionPlanSchedule productionPlanScheduleDate = new ProductionPlanSchedule();
        //附表数据
        productionPlanScheduleDate.setPlanOrder(planOrder);
        productionPlanScheduleDate.setPlanAmount(planAmount);
        productionPlanScheduleDate.setPlanMachine(planMachine);
        productionPlanScheduleDate.setPlanConfiguration(planConfiguration);
        productionPlanScheduleDate.setPlanCutting(planCutting);
        productionPlanScheduleDate.setPlanProcess(planProcess);
        productionPlanScheduleDate.setPlanOther(planOther);
        productionPlanScheduleDate.setPlanJig(planJig);
        productionPlanScheduleDate.setPlanShape(planShape);
        productionPlanScheduleDate.setPlanSize(planSize);
        productionPlanScheduleDate.setPlanNote(planNote);
        productionPlanScheduleDate.setPlanDate(planDate);
        productionPlanScheduleDate.setPlanNumber(planNumber);
        productionPlanScheduleDate.setPlanAttachment(planAttachment);
        productionPlanScheduleDate.setPlanCode(planCode);
        String planConfigurationOnt = null;
        if (planMachine != null){
            String s1 = "机器要求："+planMachine;
            String s2 = "电脑配置："+planConfiguration;
            String s3 = "刀具大小："+planCutting;
            String s4 = "加工工序："+planProcess;
            String s5 = "其他要求："+planOther;
            String s6 = "夹具要求："+planJig;
            String s7 = "产品形状："+planShape;
            String s8 = "产品尺寸："+planSize;
            planConfigurationOnt = s1+s2+s3+s4+s5+s6+s7+s8;
        }
        //生产计划主表
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.setPlanConfiguration(planConfigurationOnt);
        productionPlan.setPlanNumber(planNumber);
        productionPlan.setPlanNote(planNote);
        baseMapper.saveProductionPlanScheduleDate(productionPlanScheduleDate);
        baseMapper.updateProductionPlan(productionPlan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductionPlan(String planCode) {
        baseMapper.deleteProductionPlanSchedule(planCode);
        baseMapper.deleteProductionPlan(planCode);
	}

    @Override
    public ProductionPlanSchedule productionPlanScheduleId(Long id) {
        return baseMapper.productionPlanScheduleId(id);
    }

    @Override
    public void numberProductionPlan(ProductionPlan productionPlan) {
        baseMapper.numberProductionPlan(productionPlan);
    }

    @Override
    public void uploadProductionPlan(ProductionPlan productionPlan) {
        baseMapper.uploadProductionPlan(productionPlan);
    }

    @Override
    public ProductionPlan productionPlanId(Long id) {
        return baseMapper.productionPlanId(id);
    }

    @Override
    public ProductionPlanSchedule productionPlanPlanNumber(String planCode) {
        return baseMapper.productionPlanPlanNumber(planCode);
    }

    @Override
    public void createSetupBom(String setupBom, String dataTable) throws ParseException {
        ProductionPlan productionPlan = new ProductionPlan();
        String initCode = "LCPBOM";
        String planMachineBomNo = null;
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        JSONObject object = JSON.parseObject(setupBom);
        String  planCode =  object.getString("planCode");
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        SetupBom setupBomData = new SetupBom();
        SetupBomSchedule setupBomSchedule = new SetupBomSchedule();
        //查询出最后一个机器BOM单号
        ProductionPlan productionPlanOne = null;
        productionPlanOne = baseMapper.queryProductionPlanMachine();
        if (productionPlanOne.getPlanMachineBom() != null){
            String planMachineBom = productionPlanOne.getPlanMachineBom();
            /*机器BOM案例 LCPBOM22050014*/
            String planMachineBomOne = planMachineBom.substring(10,14);
            int planMachineBoms = Integer.parseInt(planMachineBomOne);
            String planCodesOne = null;
            if (planMachineBoms<1000) {
                planCodesOne = String.format("%04d", planMachineBoms + 1);
            }
            planMachineBomNo = initCode+yearLast+month+planCodesOne;
            productionPlan.setPlanMachineBom(planMachineBomNo);
        }else {
            for (int i=1;i<100;i++){
                //机器码
                String planCodesThree = String.format("%04d",i);
                planMachineBomNo = initCode+yearLast+month+planCodesThree;
                productionPlan.setPlanMachineBom(planMachineBomNo);
                break;
            }
        }
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String parameterName = jsonArrayOne.getJSONObject(i).getString("parameterName");
            String parameterWith = jsonArrayOne.getJSONObject(i).getString("parameterWith");
            String parameterCode = jsonArrayOne.getJSONObject(i).getString("parameterCode");
            setupBomData.setBomCode(parameterCode);
            setupBomData.setBomPart(parameterName);
            setupBomData.setBomConfiguration(parameterWith);
            setupBomData.setPlanNumber(planCode);
            setupBomData.setTreeId(1);
            if (parameterCode!=null){
                List<TechnologyBomConfigurationSchedule> setupBomSchedules = this.technologyBomConfigurationMapper.queryConfigurationRefer(parameterCode);
                for(int j = 0; j < setupBomSchedules.size(); j++){
                    setupBomSchedule.setBomCode(parameterCode);
                    setupBomSchedule.setBomMaterialCode(setupBomSchedules.get(j).getParameterMaterialCode());
                    setupBomSchedule.setBomMaterialName(setupBomSchedules.get(j).getParameterMaterial());
                    setupBomSchedule.setBomSpecifications(setupBomSchedules.get(j).getParameterSpecifications());
                    setupBomSchedule.setBomBrand(setupBomSchedules.get(j).getParameterBrand());
                    setupBomSchedule.setBomAmount(setupBomSchedules.get(j).getParameterNumber());
                    setupBomSchedule.setBomMaterial(setupBomSchedules.get(j).getParameterReplace());
                    setupBomSchedule.setBomMaterialAmount("0");
                    setupBomSchedule.setBomNote(setupBomSchedules.get(j).getParameterNote());
                    setupBomSchedule.setPlanNumber(planCode);
                    setupBomSchedule.setBomUnit(setupBomSchedules.get(j).getParameterUnit());
                    setupBomSchedule.setTreeSecondaryId(2);
                    setupBomSchedule.setBomLocation(setupBomSchedules.get(j).getParameterLocation());
                    setupBomSchedule.setPlanMachineBom(planMachineBomNo);
                    setupBomSchedule.setPlanQuality(setupBomSchedules.get(j).getParameterQuality());
                    baseMapper.saveSetupBomSchedule(setupBomSchedule);
                }
            }
            baseMapper.saveSetupBomData(setupBomData);
        }
        productionPlan.setPlanCode(planCode);
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        productionPlan.setPlanMachineBomDate(today);
        baseMapper.updatePlanMachineBom(productionPlan);
    }

    @Override
    public List<SetupBom> queryProductionPlanUpdateBom(String planCode) {
        return baseMapper.queryProductionPlanUpdateBom(planCode);
    }

    @Override
    public void updateSetupBom(String setupBom, String dataTable) {
        JSONObject object = JSON.parseObject(setupBom);
        String  planCode =  object.getString("planCode");
        ProductionPlan productionPlanData = baseMapper.queryPlanMachineBom(planCode);
        baseMapper.deleteSetupBom(planCode);
        baseMapper.deleteSetupBomSchedule(planCode);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        SetupBom setupBomData = new SetupBom();
        SetupBomSchedule setupBomSchedule = new SetupBomSchedule();
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String parameterName = jsonArrayOne.getJSONObject(i).getString("bomPart");
            String parameterWith = jsonArrayOne.getJSONObject(i).getString("bomConfiguration");
            String parameterCode = jsonArrayOne.getJSONObject(i).getString("bomCode");
            setupBomData.setBomCode(parameterCode);
            setupBomData.setBomPart(parameterName);
            setupBomData.setBomConfiguration(parameterWith);
            setupBomData.setPlanNumber(planCode);
            setupBomData.setTreeId(1);
            if (parameterCode!=null){
                List<TechnologyBomConfigurationSchedule> setupBomSchedules = this.technologyBomConfigurationMapper.queryConfigurationRefer(parameterCode);
                for(int j = 0; j < setupBomSchedules.size(); j++){
                    setupBomSchedule.setBomCode(parameterCode);
                    setupBomSchedule.setBomMaterialCode(setupBomSchedules.get(j).getParameterMaterialCode());
                    setupBomSchedule.setBomMaterialName(setupBomSchedules.get(j).getParameterMaterial());
                    setupBomSchedule.setBomSpecifications(setupBomSchedules.get(j).getParameterSpecifications());
                    setupBomSchedule.setBomBrand(setupBomSchedules.get(j).getParameterBrand());
                    setupBomSchedule.setBomAmount(setupBomSchedules.get(j).getParameterNumber());
                    setupBomSchedule.setBomMaterial(setupBomSchedules.get(j).getParameterReplace());
                    setupBomSchedule.setBomMaterialAmount("0");
                    setupBomSchedule.setBomNote(setupBomSchedules.get(j).getParameterNote());
                    setupBomSchedule.setPlanNumber(planCode);
                    setupBomSchedule.setBomUnit(setupBomSchedules.get(j).getParameterUnit());
                    setupBomSchedule.setTreeSecondaryId(2);
                    setupBomSchedule.setBomLocation(setupBomSchedules.get(j).getParameterLocation());
                    setupBomSchedule.setPlanMachineBom(productionPlanData.getPlanMachineBom());
                    setupBomSchedule.setPlanQuality(setupBomSchedules.get(j).getParameterQuality());
                    baseMapper.saveSetupBomSchedule(setupBomSchedule);
                }
            }
            baseMapper.saveSetupBomData(setupBomData);
        }
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.setPlanMachineBom(productionPlanData.getPlanMachineBom());
        productionPlan.setPlanCode(planCode);
        baseMapper.updatePlanMachineBom(productionPlan);
    }

    @Override
    public List<SetupBomSchedule> queryProductionPlanViewBom(String planCode) {
        return baseMapper.queryProductionPlanViewBom(planCode);
    }

    @Override
    public IPage<SetupBomSchedule> productionRecipientsAddQuery(QueryRequest request, SetupBomSchedule setupBomSchedule) {
        Page<ProductionPlan> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductionRecipientsAddQuery(setupBomSchedule));
        return baseMapper.queryProductionRecipientsAddQuery(page,setupBomSchedule);
    }

    @Override
    public void updateProductionStatistical(ProductionPlan productionPlan) {
        baseMapper.updateProductionStatistical(productionPlan);
    }

    @Override
    public List<ProductionPlan> productionStatisticalExport(ProductionPlan productionPlan) {
        return baseMapper.productionStatisticalExport(productionPlan);
    }

    @Override
    public void shipmentProductionPlan(ProductionPlan productionPlan) {
        baseMapper.shipmentProductionPlan(productionPlan);
    }
}

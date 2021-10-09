package com.erp.sale.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.personnel.entity.PersonnelArchives;
import com.erp.personnel.entity.PersonnelDormitory;
import com.erp.sale.entity.SaleCustomerProfile;
import com.erp.sale.mapper.SaleCustomerProfileMapper;
import com.erp.sale.service.ISaleCustomerProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 客户档案表

             数据库表名：                                             对应java表名：

             jr_sale_customer_profile                            SaleCustomerProfile Service实现
 *
 * @author qiufeng
 * @date 2021-10-08 16:51:04
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleCustomerProfileServiceImpl extends ServiceImpl<SaleCustomerProfileMapper, SaleCustomerProfile> implements ISaleCustomerProfileService {

    private final SaleCustomerProfileMapper saleCustomerProfileMapper;

    @Override
    public IPage<SaleCustomerProfile> findSaleCustomerProfiles(QueryRequest request, SaleCustomerProfile saleCustomerProfile) {
        Page<PersonnelDormitory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleCustomerProfiles(saleCustomerProfile));
        return baseMapper.findSaleCustomerProfilesPage(page,saleCustomerProfile);
    }

    @Override
    public List<SaleCustomerProfile> findSaleCustomerProfiles(SaleCustomerProfile saleCustomerProfile) {
	    LambdaQueryWrapper<SaleCustomerProfile> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createSaleCustomerProfile(SaleCustomerProfile saleCustomerProfile) {
        //定义客户编号前两位；
        String oneCustomerCode = "LX";
        //定义当前系统时间
        Date date = new Date();
        //获取当月
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        //格式化当前月
        String month = sdf.format(date);
        //打印当前月
        //System.out.println("当前月为："+month);
        //查询出最后一个员工信息
        SaleCustomerProfile saleCustomerProfileOne = baseMapper.querySaleCustomerProfile();
        //格式化查询出的最后一个员工信息时间只保留月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String createTimeMonth = null;
        if (saleCustomerProfileOne!=null){
             createTimeMonth = simpleDateFormat.format(saleCustomerProfileOne.getCreatedDate());
           // System.out.println(createTimeMonth);
        }
        //判断当前月和查询出的最后一个员工信息的月份是否相等
        if(month.equals(createTimeMonth) && createTimeMonth !=null){
            //获取是否有客户编码
            String customerCodeOne = saleCustomerProfileOne.getCustomerCode();
            //如果有只保留后两位
            String customerCode = customerCodeOne.substring(4,6);
            //把获取到的客户编码转化为int类型
            int ss = Integer.parseInt(customerCode);
            //判断获取到的后两位客户编码是否小于9
            if (ss<9){
                for (int i = ss; i < 100; i++) {
                    String n = "";
                    // 满足条件1
                    if (i < 10) {
                        n = "0" + (i+1);
                    }if ((i + 1) % 10 != 0){
                        saleCustomerProfile.setCustomerCode(oneCustomerCode+month+n);
                        break;
                    }
                }
            }else{
                int ff = ss+1;
                if(ff < 100){
                    saleCustomerProfile.setCustomerCode(oneCustomerCode+month+ff);
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
                saleCustomerProfile.setCustomerCode(oneCustomerCode+month+n);
            }
        }
        this.save(saleCustomerProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleCustomerProfile(SaleCustomerProfile saleCustomerProfile) {
        this.saveOrUpdate(saleCustomerProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleCustomerProfile(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public SaleCustomerProfile findSaleCustomerProfileById(Long id) {

        return  baseMapper.findSaleCustomerProfileById(id);
    }
}

package com.erp.purchase.service.impl;

import com.erp.arrange.entity.WorkArrange;
import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseParameters;
import com.erp.purchase.entity.PurchaseSupplier;
import com.erp.purchase.mapper.PurchaseSupplierMapper;
import com.erp.purchase.service.IPurchaseSupplierService;
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
 * 供货单位表 Service实现
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:05
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseSupplierServiceImpl extends ServiceImpl<PurchaseSupplierMapper, PurchaseSupplier> implements IPurchaseSupplierService {

    private final PurchaseSupplierMapper purchaseSupplierMapper;

    @Override
    public IPage<PurchaseSupplier> findPurchaseSuppliers(QueryRequest request, PurchaseSupplier purchaseSupplier) {
        Page<PurchaseSupplier> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseSuppliers(purchaseSupplier));
        return baseMapper.findPurchaseSuppliersPage(page,purchaseSupplier);
    }

    @Override
    public List<PurchaseSupplier> findPurchaseSuppliers(PurchaseSupplier purchaseSupplier) {
	    LambdaQueryWrapper<PurchaseSupplier> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseSupplier(PurchaseSupplier purchaseSupplier) throws ParseException {

        /*单号案例  LAP21110001*/
        String initials = "LAP";
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询出最后一个工作安排单号
        PurchaseSupplier purchaseSupplierOne = null;
        purchaseSupplierOne = baseMapper.queryPurchaseSupplier();
        String createTimeMonth = "";
        if (purchaseSupplierOne != null){
            createTimeMonth = simpleDateFormat.format(purchaseSupplierOne.getCreationTime());
            if (month.equals(createTimeMonth)){//supplierCode
                String oddNumbers = purchaseSupplierOne.getSupplierCode();
                String oddNumbersOne = oddNumbers.substring(7,11);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = String.format("%04d",oddNumber+1);
                purchaseSupplier.setSupplierCode(initials+yearLast+month+oddNumberOne);
                System.out.println(initials+yearLast+month+oddNumberOne);

            }else if(!month.equals(createTimeMonth)){
                for (int i=1;i<10000;i++){
                    String oddNumberTwo = String.format("%04d",i);
                    purchaseSupplier.setSupplierCode(initials+yearLast+month+oddNumberTwo);
                    System.out.println(initials+yearLast+month+oddNumberTwo);
                    break;
                }
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                purchaseSupplier.setSupplierCode(initials+yearLast+month+oddNumberThree);
                System.out.println(initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        purchaseSupplier.setCreationTime(today);//把获取系统当前时间赋值给实体对象

        baseMapper.savePurchaseSupplier(purchaseSupplier);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseSupplier(PurchaseSupplier purchaseSupplier) {
        this.saveOrUpdate(purchaseSupplier);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseSupplier(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public PurchaseSupplier findPurchaseSupplierById(Long id) {

        return baseMapper.findPurchaseSupplierById(id);
    }
}

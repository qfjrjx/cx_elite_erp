package com.erp.purchase.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.purchase.entity.PurchaseMaterialCategory;
import com.erp.purchase.entity.PurchaseMaterialFile;
import com.erp.purchase.entity.PurchaseSupplier;
import com.erp.purchase.mapper.PurchaseMaterialFileMapper;
import com.erp.purchase.service.IPurchaseMaterialFileService;
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
 * 物料档案表 Service实现
 *
 * @author qiufeng
 * @date 2022-01-08 11:20:19
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PurchaseMaterialFileServiceImpl extends ServiceImpl<PurchaseMaterialFileMapper, PurchaseMaterialFile> implements IPurchaseMaterialFileService {

    private final PurchaseMaterialFileMapper purchaseMaterialFileMapper;

    @Override
    public IPage<PurchaseMaterialFile> findPurchaseMaterialFiles(QueryRequest request, PurchaseMaterialFile purchaseMaterialFile) {
        Page<PurchaseMaterialFile> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPurchaseMaterialFiles(purchaseMaterialFile));
        return baseMapper.findPurchaseMaterialFilesPage(page,purchaseMaterialFile);
    }

    @Override
    public List<PurchaseMaterialFile> findPurchaseMaterialFiles(PurchaseMaterialFile purchaseMaterialFile) {
	    LambdaQueryWrapper<PurchaseMaterialFile> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPurchaseMaterialFile(PurchaseMaterialFile purchaseMaterialFile) throws ParseException {
        /*单号案例  LWL000001*/
        String initials = "LWL";
        //查询出最后一个工作安排单号
        PurchaseMaterialFile purchaseMaterialFileOne = null;
        purchaseMaterialFileOne = baseMapper.queryPurchaseMaterialFile();
        if (purchaseMaterialFileOne != null){
                String oddNumbers = purchaseMaterialFileOne.getMaterialCode();
                String oddNumbersOne = oddNumbers.substring(3,9);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = null;
                if (oddNumber<1000000){
                     oddNumberOne = String.format("%06d",oddNumber+1);
                }
                purchaseMaterialFile.setMaterialCode(initials+oddNumberOne);
                System.out.println(initials+oddNumberOne);
        }else {
            for (int i=1;i<1000000;i++){
                String oddNumberThree = String.format("%06d",i);
                purchaseMaterialFile.setMaterialCode(initials+oddNumberThree);
                System.out.println(initials+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        purchaseMaterialFile.setMaterialCreatedDate(today);//把获取系统当前时间赋值给实体对象
        this.save(purchaseMaterialFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePurchaseMaterialFile(PurchaseMaterialFile purchaseMaterialFile) {
        baseMapper.updatePurchaseMaterialFile(purchaseMaterialFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePurchaseMaterialFile(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public PurchaseMaterialFile findPurchaseMaterialFileById(Long id) {

        return baseMapper.findPurchaseMaterialFileById(id);
    }

    @Override
    public PurchaseMaterialFile findPurchaseMaterialFileCopyById(Long id) {
        return baseMapper.findPurchaseMaterialFileCopyById(id);
    }
}

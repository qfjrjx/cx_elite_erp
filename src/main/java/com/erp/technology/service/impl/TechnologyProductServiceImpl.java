package com.erp.technology.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyProduct;
import com.erp.technology.entity.TechnologyProductCategory;
import com.erp.technology.mapper.TechnologyProductMapper;
import com.erp.technology.service.ITechnologyProductService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 产品表
             Service实现
 *
 * @author qiufeng
 * @date 2021-11-09 10:30:07
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TechnologyProductServiceImpl extends ServiceImpl<TechnologyProductMapper, TechnologyProduct> implements ITechnologyProductService {

    private final TechnologyProductMapper technologyProductMapper;

    @Override
    public IPage<TechnologyProduct> findTechnologyProducts(QueryRequest request, TechnologyProduct technologyProduct) {
        Page<TechnologyProduct> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countTechnologyProduct(technologyProduct));
        return baseMapper.findTechnologyProductPage(page,technologyProduct);
    }

    @Override
    public List<TechnologyProduct> findTechnologyProducts(TechnologyProduct technologyProduct) {
	    LambdaQueryWrapper<TechnologyProduct> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTechnologyProduct(TechnologyProduct technologyProduct) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        TechnologyProduct technologyProductOne = null;
        technologyProductOne = baseMapper.queryProductCode();
        //String createTimeMonth = simpleDateFormat.format(technologyProductOne.getProductCreatedDate());
        if (technologyProductOne == null){
            for (int i=0;i<1000000;i++){
                String productCodeOne = String.format("%06d",i);
                technologyProduct.setProductCode("LCP"+productCodeOne);
                //System.out.println(productCodeOne);
                break;
            }
        }else {
            String productCode = technologyProductOne.getProductCode();
            String jobNumber = productCode.substring(3,9);
            int code = Integer.parseInt(jobNumber);
            for (int i=code;code<1000000;code++){
                String productCodeTwo = String.format("%06d",i+1);
                technologyProduct.setProductCode("LCP"+productCodeTwo);
                //System.out.println("LCP"+productCodeTwo);
                break;
            }
        }
        String date = simpleDateFormat.format(new Date());//系统当前时间
        Date today = simpleDateFormat.parse(date);
        technologyProduct.setProductCreatedDate(today);
        baseMapper.saveTechnologyProduct(technologyProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTechnologyProduct(TechnologyProduct technologyProduct) {
        baseMapper.updateTechnologyProduct(technologyProduct);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTechnologyProduct(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public TechnologyProduct findTechnologyProductById(Long id) {
        return baseMapper.findTechnologyProductById(id);
    }

}

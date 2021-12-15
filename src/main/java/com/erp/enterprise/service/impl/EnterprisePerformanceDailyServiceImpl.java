package com.erp.enterprise.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;
import com.erp.enterprise.mapper.EnterprisePerformanceDailyMapper;
import com.erp.enterprise.service.IEnterprisePerformanceDailyService;
import com.erp.sale.entity.SaleApplication;
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
 * 业绩日报表  Service实现
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:11
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EnterprisePerformanceDailyServiceImpl extends ServiceImpl<EnterprisePerformanceDailyMapper, EnterprisePerformanceDaily> implements IEnterprisePerformanceDailyService {

    private final EnterprisePerformanceDailyMapper enterprisePerformanceDailyMapper;

    @Override
    public IPage<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(QueryRequest request, EnterprisePerformanceDaily enterprisePerformanceDaily) {
        Page<EnterprisePerformanceDaily> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countEnterprisePerformanceDaily(enterprisePerformanceDaily));
        return baseMapper.findEnterprisePerformanceDailyPage(page,enterprisePerformanceDaily);
    }

    @Override
    public List<EnterprisePerformanceDaily> findEnterprisePerformanceDailys(EnterprisePerformanceDaily enterprisePerformanceDaily) {
	    LambdaQueryWrapper<EnterprisePerformanceDaily> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) throws ParseException {
        /*单号案例：LRB190101*/
        String initials = "LRB";
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询出最后一个销售申请单号
        EnterprisePerformanceDaily enterprisePerformanceDailyOne = null;
        enterprisePerformanceDailyOne = baseMapper.queryEnterprisePerformanceDaily();
        String createTimeMonth = "";
        if (enterprisePerformanceDailyOne != null){
            createTimeMonth = simpleDateFormat.format(enterprisePerformanceDailyOne.getCreateDate());
            if (month.equals(createTimeMonth)){
                String oddNumbers = enterprisePerformanceDailyOne.getOddNumbers();
                String oddNumbersOne = oddNumbers.substring(7,9);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = String.format("%02d",oddNumber+1);
                enterprisePerformanceDaily.setOddNumbers(initials+yearLast+month+oddNumberOne);
                System.out.println(initials+yearLast+month+oddNumberOne);

            }else if(!month.equals(createTimeMonth)){
                for (int i=1;i<100;i++){
                    String oddNumberTwo = String.format("%02d",i);
                    enterprisePerformanceDaily.setOddNumbers(initials+yearLast+month+oddNumberTwo);
                    System.out.println(initials+yearLast+month+oddNumberTwo);
                    break;
                }
            }
        }else {
            for (int i=1;i<100;i++){
                String oddNumberThree = String.format("%02d",i);
                enterprisePerformanceDaily.setOddNumbers(initials+yearLast+month+oddNumberThree);
                System.out.println(initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        enterprisePerformanceDaily.setCreateDate(today);//把获取系统当前时间赋值给实体对象
        this.save(enterprisePerformanceDaily);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEnterprisePerformanceDaily(EnterprisePerformanceDaily enterprisePerformanceDaily) {
        this.saveOrUpdate(enterprisePerformanceDaily);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnterprisePerformanceDaily(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public EnterprisePerformanceDaily enterprisePerformanceDailyById(Long id) {

        return baseMapper.enterprisePerformanceDailyById(id);
    }
}

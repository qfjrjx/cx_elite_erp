package com.erp.arrange.service.impl;

import com.erp.arrange.entity.WorkArrange;
import com.erp.arrange.mapper.WorkArrangeMapper;
import com.erp.arrange.service.IWorkArrangeService;
import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterprisePerformanceDaily;
import com.erp.finance.entity.FinanceParameters;
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
 * 工作安排表 Service实现
 *
 * @author qiufeng
 * @date 2021-12-20 15:35:45
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WorkArrangeServiceImpl extends ServiceImpl<WorkArrangeMapper, WorkArrange> implements IWorkArrangeService {

    private final WorkArrangeMapper workArrangeMapper;

    @Override
    public IPage<WorkArrange> findWorkArranges(QueryRequest request, WorkArrange workArrange) {
        Page<WorkArrange> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countWorkArrange(workArrange));
        return baseMapper.findWorkArrangePage(page,workArrange);
    }

    @Override
    public List<WorkArrange> findWorkArranges(WorkArrange workArrange) {
	    LambdaQueryWrapper<WorkArrange> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWorkArrange(WorkArrange workArrange) throws ParseException {
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
        WorkArrange workArrangeOne = null;
        workArrangeOne = baseMapper.queryWorkArrange();
        String createTimeMonth = "";
        if (workArrangeOne != null){
            createTimeMonth = simpleDateFormat.format(workArrangeOne.getCreationTime());
            if (month.equals(createTimeMonth)){
                String oddNumbers = workArrangeOne.getOddNumbers();
                String oddNumbersOne = oddNumbers.substring(7,11);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = String.format("%04d",oddNumber+1);
                workArrange.setOddNumbers(initials+yearLast+month+oddNumberOne);
                System.out.println(initials+yearLast+month+oddNumberOne);

            }else if(!month.equals(createTimeMonth)){
                for (int i=1;i<10000;i++){
                    String oddNumberTwo = String.format("%04d",i);
                    workArrange.setOddNumbers(initials+yearLast+month+oddNumberTwo);
                    System.out.println(initials+yearLast+month+oddNumberTwo);
                    break;
                }
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                workArrange.setOddNumbers(initials+yearLast+month+oddNumberThree);
                System.out.println(initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        workArrange.setCreationTime(today);//把获取系统当前时间赋值给实体对象

        this.save(workArrange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWorkArrange(WorkArrange workArrange) {
        this.saveOrUpdate(workArrange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWorkArrange(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}
    /*修改回填*/
    @Override
    public WorkArrange findWorkArrangeById(Long id) {
        return baseMapper.findWorkArrangeById(id);
    }

    @Override
    public void updateWorkArrangeState(Long id,String stateParam) {

        baseMapper.updateWorkArrangeState(id,stateParam);
    }

    @Override
    public void updateWorkArrangeStateDate(Long id, String stateParam) throws ParseException {

        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        if (stateParam.equals("3")){
            baseMapper.updateWorkArrangeStateDate(id,stateParam,today);
        }else if (stateParam.equals("4")){
            baseMapper.updateWorkArrangeStateTime(id,stateParam,today);
        }

    }

    @Override
    public void workArrangeAssessment(WorkArrange workArrange) throws ParseException {
         SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         String dates = simpleDateFormatOne.format(new Date());//系统当前时间
         Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
         workArrange.setAssessmentTime(today);//把获取系统当前时间赋值给实体对象
         workArrange.setArrangeState(WorkArrange.STATE_ASSESSMENT);
        this.saveOrUpdate(workArrange);
    }
}

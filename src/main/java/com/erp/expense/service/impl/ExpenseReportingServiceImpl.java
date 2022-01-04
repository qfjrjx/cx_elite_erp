package com.erp.expense.service.impl;

import com.erp.arrange.entity.WorkArrange;
import com.erp.common.entity.QueryRequest;
import com.erp.expense.entity.ExpenseReporting;
import com.erp.expense.mapper.ExpenseReportingMapper;
import com.erp.expense.service.IExpenseReportingService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 费用报支表 Service实现
 *
 * @author qiufeng
 * @date 2021-12-29 16:39:19
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ExpenseReportingServiceImpl extends ServiceImpl<ExpenseReportingMapper, ExpenseReporting> implements IExpenseReportingService {

    private final ExpenseReportingMapper expenseReportingMapper;

    @Override
    public IPage<ExpenseReporting> findExpenseReportings(QueryRequest request, ExpenseReporting expenseReporting) {
        Page<ExpenseReporting> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countExpenseReporting(expenseReporting));
        return baseMapper.findExpenseReportingPage(page,expenseReporting);
    }

    @Override
    public List<ExpenseReporting> findExpenseReportings(ExpenseReporting expenseReporting) {
	    LambdaQueryWrapper<ExpenseReporting> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createExpenseReporting(ExpenseReporting expenseReporting) throws ParseException {

        /*单号案例  LFYBZ22010001*/
        String initials = "LFYBZ";
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
        ExpenseReporting expenseReportingOne = null;
        expenseReportingOne = baseMapper.queryExpenseReporting();
        String createTimeMonth = "";
        if (expenseReportingOne != null){
            createTimeMonth = simpleDateFormat.format(expenseReportingOne.getCreationTime());
            if (month.equals(createTimeMonth)){
                String oddNumbers = expenseReportingOne.getOddNumbers();
                String oddNumbersOne = oddNumbers.substring(9,13);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = String.format("%04d",oddNumber+1);
                expenseReporting.setOddNumbers(initials+yearLast+month+oddNumberOne);
                System.out.println(initials+yearLast+month+oddNumberOne);

            }else if(!month.equals(createTimeMonth)){
                for (int i=1;i<10000;i++){
                    String oddNumberTwo = String.format("%04d",i);
                    expenseReporting.setOddNumbers(initials+yearLast+month+oddNumberTwo);
                    System.out.println(initials+yearLast+month+oddNumberTwo);
                    break;
                }
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                expenseReporting.setOddNumbers(initials+yearLast+month+oddNumberThree);
                System.out.println(initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        expenseReporting.setCreationTime(today);//把获取系统当前时间赋值给实体对象

        this.save(expenseReporting);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateExpenseReporting(ExpenseReporting expenseReporting) {
        this.saveOrUpdate(expenseReporting);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteExpenseReporting(ExpenseReporting expenseReporting) {
        LambdaQueryWrapper<ExpenseReporting> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}

package com.erp.enterprise.service.impl;

import com.erp.common.entity.QueryRequest;
import com.erp.enterprise.entity.EnterpriseDocumentAnnouncement;
import com.erp.enterprise.mapper.EnterpriseDocumentAnnouncementMapper;
import com.erp.enterprise.service.IEnterpriseDocumentAnnouncementService;
import com.erp.finance.entity.FinanceParameters;
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
 * 公文公告表 Service实现
 *
 * @author qiufeng
 * @date 2021-12-09 10:23:08
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EnterpriseDocumentAnnouncementServiceImpl extends ServiceImpl<EnterpriseDocumentAnnouncementMapper, EnterpriseDocumentAnnouncement> implements IEnterpriseDocumentAnnouncementService {

    private final EnterpriseDocumentAnnouncementMapper enterpriseDocumentAnnouncementMapper;

    @Override
    public IPage<EnterpriseDocumentAnnouncement> findEnterpriseDocumentAnnouncements(QueryRequest request, EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
        Page<EnterpriseDocumentAnnouncement> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countEnterpriseDocumentAnnouncements(enterpriseDocumentAnnouncement));
        return baseMapper.findEnterpriseDocumentAnnouncementsPage(page,enterpriseDocumentAnnouncement);
    }

    @Override
    public List<EnterpriseDocumentAnnouncement> findEnterpriseDocumentAnnouncements(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
	    LambdaQueryWrapper<EnterpriseDocumentAnnouncement> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEnterpriseDocumentAnnouncement(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) throws ParseException {

        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);LGG21120001
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //查询出最后一个销售申请单号
        EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncementOne = null;
        enterpriseDocumentAnnouncementOne = baseMapper.queryEnterpriseDocumentAnnouncement();
        String createTimeMonth = "";
        if (enterpriseDocumentAnnouncementOne != null){
            createTimeMonth = simpleDateFormat.format(enterpriseDocumentAnnouncementOne.getCreateDate());
            if (month.equals(createTimeMonth)){
                String oddNumbers = enterpriseDocumentAnnouncementOne.getOddNumbers();
                String oddNumbersOne = oddNumbers.substring(7,11);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                    String oddNumberOne = String.format("%04d",oddNumber+1);
                    enterpriseDocumentAnnouncement.setOddNumbers("LGG"+yearLast+month+oddNumberOne);
                    System.out.println("LGG"+yearLast+month+oddNumberOne);

            }else if(!month.equals(createTimeMonth)){
                for (int i=1;i<10000;i++){
                    String oddNumberTwo = String.format("%04d",i);
                    enterpriseDocumentAnnouncement.setOddNumbers("LGG"+yearLast+month+oddNumberTwo);
                    System.out.println("LGG"+yearLast+month+oddNumberTwo);
                    break;
                }
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                enterpriseDocumentAnnouncement.setOddNumbers("LGG"+yearLast+month+oddNumberThree);
                System.out.println("LGG"+yearLast+month+oddNumberThree);
                break;
            }
        }
        String fileName = "";
        fileName = enterpriseDocumentAnnouncement.getAnnouncementEnclosure();
        if (!fileName.equals("")){
            String str1 = fileName.substring(0, fileName.indexOf("."));
            enterpriseDocumentAnnouncement.setAnnouncementEnclosure(str1);
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        enterpriseDocumentAnnouncement.setCreateDate(today);//把获取系统当前时间赋值给实体对象
        baseMapper.saveDocumentAnnouncement(enterpriseDocumentAnnouncement);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEnterpriseDocumentAnnouncement(EnterpriseDocumentAnnouncement enterpriseDocumentAnnouncement) {
        baseMapper.updateEnterpriseDocumentAnnouncement(enterpriseDocumentAnnouncement);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnterpriseDocumentAnnouncement(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
	}

    @Override
    public EnterpriseDocumentAnnouncement documentAnnouncementById(Long id) {

        return baseMapper.documentAnnouncementById(id);
    }
}

package com.erp.sale.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erp.common.entity.QueryRequest;
import com.erp.sale.entity.*;
import com.erp.sale.mapper.SaleDeliveryNoticeMapper;
import com.erp.sale.service.ISaleDeliveryNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 发货通知 Service实现
 *
 * @author qiufeng
 * @date 2022-05-13 10:13:33
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SaleDeliveryNoticeServiceImpl extends ServiceImpl<SaleDeliveryNoticeMapper, SaleDeliveryNoticeAll> implements ISaleDeliveryNoticeService {

    private final SaleDeliveryNoticeMapper saleDeliveryNoticeMapper;

    @Override
    public IPage<SaleDeliveryNoticeAll> findSaleDeliveryNotices(QueryRequest request, SaleDeliveryNoticeAll saleDeliveryNotice) {
        Page<SaleDeliveryNoticeAll> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countSaleDeliveryNotice(saleDeliveryNotice));
        return baseMapper.findSaleDeliveryNoticePage(page,saleDeliveryNotice);
    }

    @Override
    public List<SaleDeliveryNoticeAll> findSaleDeliveryNotices(SaleDeliveryNoticeAll saleDeliveryNotice) {
	    LambdaQueryWrapper<SaleDeliveryNoticeAll> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createSaleDeliveryNotice(String saleDeliveryNoticeData, String dataTable) throws ParseException {
        SaleDeliveryNotice saleDeliveryNotice = new SaleDeliveryNotice();
        SaleDeliveryNoticeSchedule saleDeliveryNoticeSchedule = new SaleDeliveryNoticeSchedule();
        String initials = "L";
        String generateDocNo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //获取当前年
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());Date d = new Date(); //打印当前月
        //打印当前年
        //System.out.println(yearLast);
        //获取当月
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        //格式化当前月
        String month = simpleDateFormat.format(date);
        //this.save(saleOrder);
        //查询出最后一个销售申请单号
        SaleDeliveryNotice saleDeliveryNoticeOne = null;
        saleDeliveryNoticeOne = baseMapper.querySaleDeliveryNotice();
        String createTimeMonth = "";
        if (saleDeliveryNoticeOne != null) {
            createTimeMonth = simpleDateFormat.format(saleDeliveryNoticeOne.getCreateDate());
            if (month.equals(createTimeMonth)) {
                String shipmentNo = saleDeliveryNoticeOne.getShipmentNo();
                /*单号案例 L22050001*/
                String oddNumbersOne = shipmentNo.substring(5, 9);
                int oddNumber = Integer.parseInt(oddNumbersOne);
                String oddNumberOne = null;
                if (oddNumber < 10000) {
                    oddNumberOne = String.format("%04d", oddNumber + 1);
                }
                generateDocNo = initials + yearLast + month + oddNumberOne;
                saleDeliveryNotice.setShipmentNo(generateDocNo);
                //System.out.println("第一"+initials+yearLast+month+oddNumberOne);
            }else {
                for (int i=1;i<10000;i++){
                    String oddNumberThree = String.format("%04d",i);
                    generateDocNo = initials+yearLast+month+oddNumberThree;
                    saleDeliveryNotice.setShipmentNo(generateDocNo);
                    // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                    break;
                }
            }
        }else {
            for (int i=1;i<10000;i++){
                String oddNumberThree = String.format("%04d",i);
                generateDocNo = initials+yearLast+month+oddNumberThree;
                saleDeliveryNotice.setShipmentNo(generateDocNo);
                // System.out.println("第二"+initials+yearLast+month+oddNumberThree);
                break;
            }
        }
        JSONObject object = JSON.parseObject(saleDeliveryNoticeData);
        String  deliveryDate =  object.getString("deliveryDate");
        String  customerId =  object.getString("customerId");
        String  customerName =  object.getString("customerName");
        String  salesmanName =  object.getString("salesmanName");
        String  orderType =  object.getString("orderType");
        String  storageRoom =  object.getString("storageRoom");
        String  consigneeName =  object.getString("consigneeName");
        String  receivingPhone =  object.getString("receivingPhone");
        String  receivingAddress =  object.getString("receivingAddress");
        String  afterSalesClerk =  object.getString("afterSalesClerk");
        String  orderBookkeeping =  object.getString("orderBookkeeping");
        String  preparerName =  object.getString("preparerName");
        if (!deliveryDate.equals("")) {
            Date orderDateOne = sdf.parse(deliveryDate);//格式化数据，取当前时间结果
            saleDeliveryNotice.setDeliveryDate(orderDateOne);
        }if (!customerId.equals("")) {
            saleDeliveryNotice.setCustomerId(Long.parseLong(customerId));
        }if (!customerName.equals("")) {
            saleDeliveryNotice.setCustomerName(customerName);
        }if (!salesmanName.equals("")) {
            saleDeliveryNotice.setSalesmanName(salesmanName);
        }if (!orderType.equals("")) {
            saleDeliveryNotice.setOrderType(orderType);
        }if (!storageRoom.equals("")) {
            saleDeliveryNotice.setStorageRoom(storageRoom);
        }if (!consigneeName.equals("")) {
            saleDeliveryNotice.setConsigneeName(consigneeName);
        }if (!receivingPhone.equals("")) {
            saleDeliveryNotice.setReceivingPhone(receivingPhone);
        }if (!receivingAddress.equals("")) {
            saleDeliveryNotice.setReceivingAddress(receivingAddress);
        }if (!afterSalesClerk.equals("")) {
            saleDeliveryNotice.setAfterSalesClerk(afterSalesClerk);
        }if (!orderBookkeeping.equals("")) {
            saleDeliveryNotice.setOrderBookkeeping(orderBookkeeping);
        }if (!preparerName.equals("")) {
            saleDeliveryNotice.setPreparerName(preparerName);
        }
        SimpleDateFormat simpleDateFormatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatOne.format(new Date());//系统当前时间
        Date today = simpleDateFormatOne.parse(dates);//格式化系统当前时间
        saleDeliveryNotice.setCreateDate(today);
        JSONArray jsonArrayOne = JSONArray.parseArray(dataTable);
        for(int i = 0; i < jsonArrayOne.size(); i++){
            String orderScheduleId = jsonArrayOne.getJSONObject(i).getString("orderScheduleId");//订单附表id
            String storageRoomOne = jsonArrayOne.getJSONObject(i).getString("storageRoom");//库房
            String productName = jsonArrayOne.getJSONObject(i).getString("productName");//产品名称
            String specificationModel = jsonArrayOne.getJSONObject(i).getString("specificationModel");//规格型号
            String machineConfiguration = jsonArrayOne.getJSONObject(i).getString("machineConfiguration");//机器配置
            String invoiceNot = jsonArrayOne.getJSONObject(i).getString("invoiceNot");//是否开票
            String orderCount = jsonArrayOne.getJSONObject(i).getString("orderCount");//订单数
            String numberSent = jsonArrayOne.getJSONObject(i).getString("numberSent");//已发数
            String unitPrice = jsonArrayOne.getJSONObject(i).getString("unitPrice");//单价
            String thisShipment = jsonArrayOne.getJSONObject(i).getString("thisShipment");//本次发货（发货数量）
            String currencyName = jsonArrayOne.getJSONObject(i).getString("currencyName");//币种
            String orderNumber = jsonArrayOne.getJSONObject(i).getString("orderNumber");//订单号
            String categoryName = jsonArrayOne.getJSONObject(i).getString("categoryName");//类别
            String preferentialAmount = jsonArrayOne.getJSONObject(i).getString("preferentialAmount");//优惠金额
            String orderPostage = jsonArrayOne.getJSONObject(i).getString("orderPostage");//邮费
            String machineCode = jsonArrayOne.getJSONObject(i).getString("machineCode");//机器码
            String shippingNoticeRemarks = jsonArrayOne.getJSONObject(i).getString("shippingNoticeRemarks");//备注
            if (!orderScheduleId.equals("")) {
                saleDeliveryNoticeSchedule.setOrderScheduleId(Long.parseLong(orderScheduleId));
            }if (!storageRoomOne.equals("")) {
                saleDeliveryNoticeSchedule.setScheduleStorageRoom(storageRoomOne);
            }if (!productName.equals("")) {
                saleDeliveryNoticeSchedule.setProductName(productName);
            }if (!specificationModel.equals("")) {
                saleDeliveryNoticeSchedule.setSpecificationModel(specificationModel);
            }if (!machineConfiguration.equals("")) {
                saleDeliveryNoticeSchedule.setMachineConfiguration(machineConfiguration);
            }if (!invoiceNot.equals("")) {
                saleDeliveryNoticeSchedule.setInvoiceNot(invoiceNot);
            }if (!orderCount.equals("")) {
                saleDeliveryNoticeSchedule.setOrderCount(Integer.parseInt(orderCount));
            }if (numberSent != null) {
                saleDeliveryNoticeSchedule.setNumberSent(Integer.parseInt(numberSent));
            }if (!thisShipment.equals("")) {
                saleDeliveryNoticeSchedule.setThisShipment(Integer.parseInt(thisShipment));
            }if (!unitPrice.equals("")) {
                BigDecimal unitPriceTo = new BigDecimal(unitPrice);
                saleDeliveryNoticeSchedule.setUnitPrice(unitPriceTo);
            }if (!currencyName.equals("")) {
                saleDeliveryNoticeSchedule.setCurrencyName(currencyName);
            }if (!orderNumber.equals("")) {
                saleDeliveryNoticeSchedule.setOrderNumber(orderNumber);
            }if (!categoryName.equals("")) {
                saleDeliveryNoticeSchedule.setCategoryName(categoryName);
            }if (!preferentialAmount.equals("")) {
                BigDecimal preferentialAmountTo = new BigDecimal(preferentialAmount);
                saleDeliveryNoticeSchedule.setPreferentialAmount(preferentialAmountTo);
            }if (!orderPostage.equals("")) {
                BigDecimal orderPostageTo = new BigDecimal(unitPrice);
                saleDeliveryNoticeSchedule.setOrderPostage(orderPostageTo);
            }if (machineCode != null) {
                saleDeliveryNoticeSchedule.setMachineCode(machineCode);
            }if (!shippingNoticeRemarks.equals("")) {
                saleDeliveryNoticeSchedule.setShippingNoticeRemarks(shippingNoticeRemarks);
            }
            saleDeliveryNoticeSchedule.setShipmentNo(generateDocNo);
            //添加到数据库附表
            baseMapper.addSaleDeliveryNoticeSchedule(saleDeliveryNoticeSchedule);
        }
        //添加到数据库附表
        baseMapper.addSaleDeliveryNotice(saleDeliveryNotice);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice) {
        this.saveOrUpdate(saleDeliveryNotice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleDeliveryNotice(SaleDeliveryNoticeAll saleDeliveryNotice) {
        LambdaQueryWrapper<SaleDeliveryNoticeAll> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    @Override
    public List<SaleDeliveryNoticeAll> saleDeliveryNoticeList(Long customerData) {

        return baseMapper.saleDeliveryNoticeList(customerData);
    }
}

package com.erp.warehouse.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.common.entity.QueryRequest;
import com.erp.technology.entity.TechnologyBomParameter;
import com.erp.warehouse.entity.WarehouseLocation;
import com.erp.warehouse.mapper.WarehouseLocationMapper;
import com.erp.warehouse.service.IWarehouseLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 库房区位 Service实现
 *
 * @author qiufeng
 * @date 2022-06-01 10:40:04
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WarehouseLocationServiceImpl extends ServiceImpl<WarehouseLocationMapper, WarehouseLocation> implements IWarehouseLocationService {

    private final WarehouseLocationMapper warehouseLocationMapper;

    @Override
    public IPage<WarehouseLocation> findWarehouseLocations(QueryRequest request, WarehouseLocation warehouseLocation) {
        Page<TechnologyBomParameter> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countWarehouseLocations(warehouseLocation));
        return baseMapper.findWarehouseLocationsPage(page,warehouseLocation);
    }

    @Override
    public List<WarehouseLocation> findWarehouseLocations(WarehouseLocation warehouseLocation) {
	    LambdaQueryWrapper<WarehouseLocation> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWarehouseLocation(WarehouseLocation warehouseLocation) throws ParseException {
        //库房区位
        WarehouseLocation warehouseLocationData = new WarehouseLocation();
        /*单号案例 L01*/
        String initCode = "L";
        String locationCodeNo = null;
        //对象转json
        String jsonString = JSON.toJSONString(warehouseLocation);
        JSONObject object = JSON.parseObject(jsonString);
        String locationType = object.getString("locationType");
        String locationName = object.getString("locationName");
        String locationSorting = object.getString("locationSorting");
        String locationNote = object.getString("locationNote");
        String locationState = object.getString("locationState");
        String locationCreate = object.getString("locationCreate");
        WarehouseLocation warehouseLocationOne = null;
        warehouseLocationOne = baseMapper.queryWarehouseLocation();
        if (warehouseLocationOne != null){
            String locationCode = warehouseLocationOne.getLocationCode();
            /*库位编码案例 L01*/
            String locationCodeOne = locationCode.substring(1,3);
            int locationCodes = Integer.parseInt(locationCodeOne);
            String locationCodesOne = null;
            if (locationCodes<100) {
                locationCodesOne = String.format("%02d", locationCodes + 1);
            }
            locationCodeNo = initCode+locationCodesOne;
            warehouseLocationData.setLocationCode(locationCodeNo);
        }else {
            for (int i=1;i<100;i++){
                String planCodesThree = String.format("%04d",i);
                locationCodeNo = initCode+planCodesThree;
                warehouseLocationData.setLocationCode(locationCodeNo);
                break;
            }
        }
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        warehouseLocationData.setLocationCreateDate(today);
        warehouseLocationData.setLocationCreate(locationCreate);
        warehouseLocationData.setLocationState(locationState);
        warehouseLocationData.setLocationNote(locationNote);
        warehouseLocationData.setLocationSorting(locationSorting);
        warehouseLocationData.setLocationName(locationName);
        warehouseLocationData.setLocationType(locationType);
        baseMapper.saveWarehouseLocation(warehouseLocationData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWarehouseLocation(WarehouseLocation warehouseLocation) throws ParseException {
        baseMapper.deleteWarehouseLocation(warehouseLocation.getId());
        //库房区位
        WarehouseLocation warehouseLocationData = new WarehouseLocation();
        //对象转json
        String jsonString = JSON.toJSONString(warehouseLocation);
        JSONObject object = JSON.parseObject(jsonString);
        String locationType = object.getString("locationType");
        String locationName = object.getString("locationName");
        String locationSorting = object.getString("locationSorting");
        String locationNote = object.getString("locationNote");
        String locationState = object.getString("locationState");
        String locationCreate = object.getString("locationCreate");
        String locationCode = object.getString("locationCode");
        SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dates = simpleDateFormatTwo.format(new Date());//系统当前时间
        Date today = simpleDateFormatTwo.parse(dates);//格式化系统当前时间
        warehouseLocationData.setLocationCreateDate(today);
        warehouseLocationData.setLocationCreate(locationCreate);
        warehouseLocationData.setLocationState(locationState);
        warehouseLocationData.setLocationNote(locationNote);
        warehouseLocationData.setLocationSorting(locationSorting);
        warehouseLocationData.setLocationName(locationName);
        warehouseLocationData.setLocationType(locationType);
        warehouseLocationData.setLocationCode(locationCode);
        baseMapper.saveWarehouseLocation(warehouseLocationData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWarehouseLocation(String ids) {
        baseMapper.deleteWarehouseLocation(Integer.parseInt(ids));
	}

    @Override
    public WarehouseLocation warehouseLocationId(Long id) {
        return baseMapper.warehouseLocationId(id);
    }

    @Override
    public List<WarehouseLocation> queryLocationName() {
        return baseMapper.queryLocationName();
    }
}

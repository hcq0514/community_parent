package com.comm.base.service.impl;

import com.comm.base.dao.CityDao;
import com.comm.base.service.CityService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.BaseExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.base.City;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author : hcq
 * @date : 2019/6/12
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

    @Override
    public City getById(String id) {
        Optional<City> optional = cityDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(City city) {
        City base = cityDao.save(city);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(City city) {
        Optional<City> cityOptional = cityDao.findById(city.getId());
        if (!cityOptional.isPresent()) {
            ExceptionCast.cast(BaseExceptionCode.CITY_NOT_EXIST);
        }
        City update = cityDao.save(city);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<City> cityOptional = cityDao.findById(pageId);
        if (!cityOptional.isPresent()) {
            ExceptionCast.cast(BaseExceptionCode.CITY_ALREADY_EXIST);
        }
        cityDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return cityDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String name, Boolean hot) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        City city = new City();
        if (StringUtils.isNotEmpty(id)) {
            city.setId(id);
        }
        if (StringUtils.isNotEmpty(name)) {
            city.setName(name);
        }
        if (hot != null) {
            city.setHot(hot);
        }
        //创建条件实例
        Example<City> example = Example.of(city, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<City> all = cityDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}

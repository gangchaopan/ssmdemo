package com.aisino.service.Impl;

import com.aisino.mapper.CarMapper;
import com.aisino.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gangchaopan on 2017/5/25.
 */
@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarMapper carMapper;

    public String getCar() {
        return carMapper.getCar();
    }
}

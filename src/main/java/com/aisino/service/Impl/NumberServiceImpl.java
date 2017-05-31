package com.aisino.service.Impl;

import com.aisino.mapper.CarMapper;
import com.aisino.mapper.NumberMapper;
import com.aisino.service.CarService;
import com.aisino.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/5/25.
 */
@Service
public class NumberServiceImpl implements NumberService{
    @Autowired
    private NumberMapper numberMapper ;

    public void  save(int number) {
//        Map numberMap = new HashMap();
//        numberMap.put("num",number);
//        numberMapper.save(numberMap);
    }
}

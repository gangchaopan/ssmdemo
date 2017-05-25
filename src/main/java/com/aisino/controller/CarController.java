package com.aisino.controller;


import com.aisino.service.CarService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by gangchaopan on 2017/5/25.
 */

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    Logger log = LoggerFactory.getLogger(CarController.class);

    @ApiOperation(value = "测试跳转到test页面", httpMethod = "GET", notes = "测试程序是否正常启动")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String testUrl(HttpServletRequest request, HttpServletResponse respons){
        log.debug("访问函数{}","TestController.testUrl");
        return "test";
    }
    @ApiOperation(value = "获取汽车信息",httpMethod = "GET",notes = "测试程序是否可以联系数据库，dao，service层是否正常")
    @RequestMapping(value = "car",method = RequestMethod.GET)
    public  String getCar(HttpServletRequest request,HttpServletResponse response){
        log.debug("查询汽车{}","CarController.getCar");
        log.debug("Service:{}",carService);
        String carName = carService.getCar();
        log.debug("车名：{}",carName);
        return "test";
    }

}

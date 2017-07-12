package com.aisino.controller;


import com.aisino.service.CarService;
import com.wordnik.swagger.annotations.ApiOperation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by gangchaopan on 2017/5/25.
 */

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private OkHttpClient client;

    Logger log = LoggerFactory.getLogger(CarController.class);

    @ApiOperation(value = "测试跳转到test页面", httpMethod = "GET", notes = "测试程序是否正常启动")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String testUrl(HttpServletRequest request, HttpServletResponse respons){
        log.debug("访问函数{}","TestController.testUrl");
        return "test";
    }
    @ApiOperation(value = "获取汽车信息",httpMethod = "GET",notes = "测试程序是否可以联系数据库，dao，service层是否正常")
    @RequestMapping(value = "car",method = RequestMethod.GET)
    public  String setCar(HttpServletRequest request,HttpServletResponse response){

        Request request1 = new Request.Builder().url("http://localhost:8080/getCar").get().build();
        okhttp3.Response response1= null;
        String carName2 = null;
        try {
            response1 = client.newCall(request1).execute();
        }catch (Exception e){

        }

        log.debug("查询汽车{}","CarController.getCar");
        log.debug("Service:{}",carService);
        String carName = carService.getCar();
        request.getSession().setAttribute("carName","test_car_name");
        log.debug("车名：{}",carName);
        return "test";
    }

    @RequestMapping(value = "getCar")
    @ResponseBody
    public String getCar(HttpServletRequest request,HttpServletResponse response){
       String car=(String) request.getSession().getAttribute("carName");
       return car;
    }

}

package com.aisino.test;

import com.aisino.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gangchaopan on 2017/5/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/Spring-application.xml","classpath:spring/Spring-mvc.xml","classpath:spring/Spring-mybatis.xml"})
public class CarServiceTest {
    Logger log = LoggerFactory.getLogger(CarServiceTest.class);

    @Autowired
    private CarService carService;
    @Test
    public void testCar(){
      String name =   carService.getCar();
      log.debug("获取车名:{}",name);
    }
}

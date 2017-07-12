package com.aisino.test;

import com.aisino.tools.JsonWebTokenBuild;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/12.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/Spring-application.xml","classpath:spring/Spring-mvc.xml","classpath:spring/Spring-mybatis.xml"})
public class JsonWebTokenTest {

    public static  final Logger logger = LoggerFactory.getLogger(JsonWebTokenTest.class);

    public static String token;
    public static  Date exp;
    public static Map map ;

     static {
        exp = JsonWebTokenBuild.buildTime("2017-07-12 16:32:00");
        map = new HashMap<String,Object>();
        map.put("uid","1111");
        map.put("isAdmin","0");

    }


    // 1.测试无效的token
    @Test
    public void testErrorToken(){
        token="11111&^T&*^*&Y*";
        //String token = JsonWebTokenBuild.buildJWT(exp,map);
        try {
          String result =   JsonWebTokenBuild.verify(token);
          logger.info("测试结果{}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // 2.测试过期的token
    @Test
    public void testExpToken(){
        exp = JsonWebTokenBuild.buildTime("2017-07-12 16:32:00");
        String token = JsonWebTokenBuild.buildJWT(exp,map);
        try {
            String result =   JsonWebTokenBuild.verify(token);
            logger.info("测试结果{}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3.测试token为空值
    @Test
    public void testNullToken(){
        try {

            String result =   JsonWebTokenBuild.verify(null);
            logger.info("测试结果{}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //"token异常，请重新登录\"


    // 4.测试正常的token值
    @Test
    public void testSuccessToken(){
        exp = JsonWebTokenBuild.buildTime("2019-07-12 16:32:00");
        String token = JsonWebTokenBuild.buildJWT(exp,map);
        try {
            String result =   JsonWebTokenBuild.verify(token);
            logger.info("测试结果{}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // \"校验toke成功\"

    //token值eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMTExIiwibmJmIjoxNDk5ODU0OTc3LCJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOiIwIiwiZXhwIjoxNTYyOTIwMzIwLCJpYXQiOjE0OTk4NTQ5Nzd9.otNt_jqmhiQ0NJXL7Bm-7FcO8CtiQ70tXwQzrZj47HQ
    //5.4.测试已经重新生成的token值，原token失效，改方案修改系统时间
    @Test
    public void testOldToken(){
      //  exp = JsonWebTokenBuild.buildTime("2019-07-12 16:32:00");
      //  String token = JsonWebTokenBuild.buildJWT(exp,map);
        try {
            String result =   JsonWebTokenBuild.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMTExIiwiaXNzIjoiYXV0aDAiLCJpc0FkbWluIjoiMCIsImV4cCI6MTU2MjkyMDMyMCwiaWF0IjoxNDk5ODU1MTcxfQ.QPCIRBYYGdOquVpwp6-PLubBTgPZcNz_iO0ju45RyDU");
            logger.info("测试结果{}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //"测试结果\"token非法，请重新登录\""
}

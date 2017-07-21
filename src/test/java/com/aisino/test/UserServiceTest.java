package com.aisino.test;

import com.aisino.model.UserSecurityVO;
import com.aisino.service.UserService;
import com.aisino.tools.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by gangchaopan on 2017/7/14.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/Spring-application.xml","classpath:spring/Spring-mvc.xml","classpath:spring/Spring-mybatis.xml"})
public class UserServiceTest {

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByUsername(){
        String username="test";
        UserSecurityVO user = userService.findUserByUsername(username);
        logger.info(user.toString());
    }

    @Test
    public void testVerifyByPwd() {
        String username = "test";
        String str = "test";
        UserSecurityVO user = userService.findUserByUsername(username);
        String pwd = user.getPassword();

       byte[] md5str1 = DigestUtils.md5(Base64.encode(str.getBytes()));
       byte[] basest2 = Base64.encode(md5str1);

       String password = new String(basest2);

        if (password.equals(pwd)) {
            logger.info("密码匹配,输入密码：{} 数据库密码:{}",password,pwd);
        } else{
            logger.info("密码不匹配,输入密码：{} 数据库密码:{}",password,pwd);
         }

        logger.info(user.toString());
    }
}

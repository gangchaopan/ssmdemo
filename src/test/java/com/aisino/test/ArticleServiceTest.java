package com.aisino.test;

import com.aisino.model.UserSecurityVO;
import com.aisino.service.ArticleService;
import com.aisino.service.UserService;
import com.aisino.tools.Base64;
import com.aisino.tools.JacksonTools;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/14.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/Spring-application.xml","classpath:spring/Spring-mvc.xml","classpath:spring/Spring-mybatis.xml"})
public class ArticleServiceTest {

    Logger logger = LoggerFactory.getLogger(ArticleServiceTest.class);
    @Autowired
    private ArticleService articleService;
    @Test
    public void testVerifyByPwd() {
        Map map = new HashMap<String,Object>();
        map.put("title","标题");
        map.put("content","文章内容");
        map.put("userid","1");
        map.put("author","人民日报");
        boolean  is_ok = articleService.save(map);
        if(is_ok){
            logger.info("发表文章正常");
        }else{
            logger.info("发表文章失败");
        }
    }


    @Test
    public  void testList(){

        String jsonstr = articleService.listArticle(1,5);
        logger.info("获取文章信息{}",jsonstr);

    }

    @Test
    public void testArticleById(){
       String article =  articleService.getArticleByArticleId(1);
        System.out.println(article);
    }
}

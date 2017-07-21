package com.aisino.tools;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/17.
 */
public class JacksonTools {

    private static  Logger logger = LoggerFactory.getLogger(JacksonTools.class);

    public static String getJson(Map<String,Object> map){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(map);
            return json;
        } catch (IOException e) {
            logger.error("生成json错误", e);
            return null;
        }
    }



    public static String getJson(String object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (IOException e) {
            logger.error("生成json错误", e);
            return null;
        }
    }

}

package com.aisino.controller;

import com.aisino.model.UserSecurityVo;
import com.aisino.service.UserService;
import com.aisino.tools.Base64;
import com.aisino.tools.JsonWebTokenBuild;

import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/13.
 */
@Api(value = "user",description = "用户管理",produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login.json",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ApiOperation(value = "用户登录",httpMethod = "post",notes = "登录成功返回token")
    @ApiParam(value="账号",name="username",required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "登录成功",response = String.class),
            @ApiResponse(code = 201 , message = "账号不存在",response = String.class),
            @ApiResponse(code = 202 , message = "密码不匹配",response = String.class),
            @ApiResponse(code = 203 , message = "token创建失败,重新登录",response = String.class),
            @ApiResponse(code = 204 , message = "账号或密码为空",response = String.class),
            @ApiResponse(code = 205 , message = "创建token异常",response = String.class)
    })
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request, HttpServletResponse response) {


        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            map.put("status", "204");
            map.put("token", null);
            map.put("message", "账号或密码为空");
            map.put("uid",0);
            return getJson(map);
        }

        //登录
        UserSecurityVo user = userService.findUserByUsername(username);
        //账号不存在
        if (user == null) {
            map.put("status", "201");
            map.put("token", null);
            map.put("message", "账号不存在");
            map.put("uid",0);
            return getJson(map);
        }
        String sqlPassword = user.getPassword();
        password = new String(Base64.encode(DigestUtils.md5(Base64.encode(password.getBytes()))));
        //密码不存在
        if (!password.equals(sqlPassword)) {
            map.put("status", "202");
            map.put("token", null);
            map.put("message", "密码不存在");
            map.put("uid",0);
            return getJson(map);
        }

        //获取uid
        long uid = user.getUserId();
        Date exp = JsonWebTokenBuild.buildTime("2017-10-10 00:00:00");
        String token = JsonWebTokenBuild.buildJWT(exp, map);
        map.put("uid",uid);
        map.put("token", token);
        if(token==null){
            map.put("status", "205");
            map.put("message", "构建token异常，检查后台程序");
        }else{
            map.put("status", "200");
            map.put("message", "登录成功");
        }
        return getJson(map);

    }


    private String getJson(Map<String,Object> map){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(map);
            return json;
        } catch (IOException e) {
            logger.error("生成json错误", e);
            return null;
        }
    }






}

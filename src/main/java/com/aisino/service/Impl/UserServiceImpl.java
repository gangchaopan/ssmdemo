package com.aisino.service.Impl;

import com.aisino.mapper.UserMapper;
import com.aisino.model.UserCommonVo;
import com.aisino.model.UserPO;
import com.aisino.model.UserSecurityVo;
import com.aisino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账号查找用户，一般用在登录
     *
     * @param username
     * @return
     */
    public UserSecurityVo findUserByUsername(String username) {
        if(username==null) {
            throw new IllegalArgumentException("用户名不能为空 cannot be null --UserServiceImpl");
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username",username);

        UserPO  userPO= userMapper.getUser(map);

        UserSecurityVo securityVo = new UserSecurityVo();
        securityVo.setUserId(userPO.getUserId());
        securityVo.setUsername(userPO.getUsername());
        securityVo.setPassword(userPO.getPassword());

        return securityVo;
    }

    /**
     * 根据用户id查找用户
     *
     * @param uid
     * @return
     */
    public UserCommonVo findUserByUserId(int uid) {
        return null;
    }
}

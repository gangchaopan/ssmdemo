package com.aisino.service;

import com.aisino.model.UserCommonVo;
import com.aisino.model.UserPO;
import com.aisino.model.UserSecurityVo;

import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/14.
 */
public interface UserService {

    /**
     * 根据账号查找用户，一般用在登录
     * @param username
     * @return
     */
    UserSecurityVo findUserByUsername(String username);

    /**
     * 根据用户id查找用户
     * @param uid
     * @return
     */
    UserCommonVo   findUserByUserId(int uid);
}

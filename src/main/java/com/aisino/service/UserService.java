package com.aisino.service;

import com.aisino.model.UserCommonVO;
import com.aisino.model.UserSecurityVO;

/**
 * Created by gangchaopan on 2017/7/14.
 */
public interface UserService {

    /**
     * 根据账号查找用户，一般用在登录
     * @param username
     * @return
     */
    UserSecurityVO findUserByUsername(String username);

    /**
     * 根据用户id查找用户
     * @param uid
     * @return
     */
    UserCommonVO findUserByUserId(int uid);
}

package com.aisino.mapper;

import com.aisino.model.UserPO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by gangchaopan on 2017/7/13.
 */
@Repository
public interface UserMapper {

    /**
     *
     * @param param
     * @return
     */
    UserPO getUser(Map<String,Object> param);
}

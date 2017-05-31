package com.aisino.mapper;


import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by gangchaopan on 2017/5/24.
 */
@Repository
public interface NumberMapper {

    void  save(Map number);
}

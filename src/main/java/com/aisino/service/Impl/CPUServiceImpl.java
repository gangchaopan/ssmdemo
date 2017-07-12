package com.aisino.service.Impl;

import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by gangchaopan on 2017/6/7.
 */
@Service("CPUService")
public class CPUServiceImpl implements CPUService {

    /**
     * 获取cup信息
     * @return
     */
    public String getCPUUsage() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://112.124.100.28:1988/page/cpu/usage")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        String cpuInfo = response.readEntity(String.class);
        cpuInfo= cpuInfo.replace("[[","[").replace("]]","]");
        return cpuInfo;
    }
}

package com.aisino.service.Impl;

import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by gangchaopan on 2017/6/8.
 */
@Service("DiskUsageService")
public class DiskUsageServiceImpl implements DiskUsageService {

    public String getDiskUsage() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://192.168.15.43:1988/page/df")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        int  status = response.getStatus();
        if(status==200){
          return  response.readEntity(String.class);
        }

        return null;
    }
}

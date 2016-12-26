package com.cdboo.business.service;

import com.cdboo.business.util.YamlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houyi on 2016/12/26.
 */
@Service
public class UserService {

    public boolean checkUser(){

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", "thinkgem");
        params.put("password", "12345");

        String result = restTemplate.postForObject(
                YamlUtils.getValue("url.cdboo.server.ip") + YamlUtils.getValue("url.cdboo.server.checkUser"), params, String.class);

        return true;
    }
}

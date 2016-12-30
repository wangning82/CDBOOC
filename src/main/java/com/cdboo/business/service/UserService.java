package com.cdboo.business.service;

import com.cdboo.business.common.YamlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houyi on 2016/12/26.
 */
@Service
public class UserService {

    public String checkUser(String username, String password) {
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        params.put("password", password);
        String url = YamlUtils.getValue("url.cdboo.server.ip") + YamlUtils.getValue("url.cdboo.server.checkUser");
        ResponseEntity<String> entity = template.postForEntity(url, params, String.class);
        return entity.getBody();
    }

    public static void main(String[] args) {

    }

    public boolean updateUserData(){
        return false;
    }
}

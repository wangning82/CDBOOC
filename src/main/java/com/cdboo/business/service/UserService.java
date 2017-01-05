package com.cdboo.business.service;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houyi on 2016/12/26.
 */
@Service
public class UserService {

    @Autowired
    private PlanService planService;

    public String checkUser(String username, String password) {
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        params.put("password", password);
        String url = YamlUtils.getValue("url.cdboo.server.ip") + YamlUtils.getValue("url.cdboo.server.checkUser");
        ResponseEntity<String> entity = template.postForEntity(url, params, String.class);
        return entity.getBody();
    }

    public RestModel getUserData(String username){
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        String url = YamlUtils.getValue("url.cdboo.server.ip") + YamlUtils.getValue("url.cdboo.server.getUserData");
        ResponseEntity<RestModel> entity = template.postForEntity(url, params, RestModel.class);
        return entity.getBody();
    }

    @Transactional(readOnly = false)
    public void saveUserData(RestModel model){
        Config config = Config.getConfigInstance();
        config.setUserName(model.getUserName());
        config.setShopOwnerName(model.getShopOwnerName());
        config.setAddress(model.getAddress());
        config.setPhoto(model.getPhoto());
        config.setBusinessHoursBegin(model.getBusinessHoursBegin());
        config.setBusinessHoursEnd(model.getBusinessHoursEnd());
        config.setServiceTimeBegin(model.getServiceTimeBegin());
        config.setServiceTimeEnd(model.getServiceTimeEnd());
        config.saveUserData();

        planService.deleteAll();
        for(PlanModel planModel : model.getPlanModelList()){
            planService.save(planModel);
        }
    }
}

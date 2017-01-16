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
        String url = YamlUtils.getValue("url.cdboo.server.ip");
        url += YamlUtils.getValue("url.cdboo.server.checkUser");
        url += "/" + username + "/" + password;
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        return entity.getBody();
    }

    public RestModel getUserData(String username){
        RestTemplate template = new RestTemplate();
        String url = YamlUtils.getValue("url.cdboo.server.ip");
        url += YamlUtils.getValue("url.cdboo.server.getUserData");
        url += "/" + username;
        ResponseEntity<RestModel> entity = template.getForEntity(url, RestModel.class);
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
            // TODO 下载文件
            planService.save(planModel);
        }
    }
}

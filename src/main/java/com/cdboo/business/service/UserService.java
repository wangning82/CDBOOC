package com.cdboo.business.service;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.Constants;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.model.RestModel;
import com.cdboo.system.spring.PropsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by houyi on 2016/12/26.
 */
@Service
public class UserService {

    @Autowired
    private PlanService planService;

    @Autowired
    private PropsConfig propsConfig;

    private static int BUFFER_SIZE = 10240;

    private static final String SERVER_IP = YamlUtils.getValue("url.cdboo.server.ip");

    public String checkUser(String username, String password) {
        RestTemplate template = new RestTemplate();
        String url = SERVER_IP + YamlUtils.getValue("url.cdboo.server.checkUser");
        url += "/" + username + "/" + password;
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        return entity.getBody();
    }

    public RestModel getUserData(String username) {
        RestTemplate template = new RestTemplate();
        String url = YamlUtils.getValue("url.cdboo.server.ip");
        url += YamlUtils.getValue("url.cdboo.server.getUserData");
        url += "/" + username;
        ResponseEntity<RestModel> entity = template.getForEntity(url, RestModel.class);
        return entity.getBody();
    }

    @Transactional(readOnly = false)
    public void saveUserData(RestModel model) {
        Config config = Config.getConfigInstance();
        config.setUserName(model.getUserName());
        config.setShopOwnerName(model.getShopOwnerName());
        config.setAddress(model.getAddress());
        config.setPhoto(getImagePath(model.getPhoto()));
        config.setBusinessHoursBegin(model.getBusinessHoursBegin());
        config.setBusinessHoursEnd(model.getBusinessHoursEnd());
        config.setServiceTimeBegin(model.getServiceTimeBegin());
        config.setServiceTimeEnd(model.getServiceTimeEnd());
        config.saveUserData();

        planService.deleteAll();
        for (PlanModel planModel : model.getPlanModelList()) {
            planModel.getChannel().setPhotoPath(getImagePath(planModel.getChannel().getPhotoPath()));
            for (RestMusic restMusic : planModel.getChannel().getMusicList()) {
                restMusic.setPath(getMusicPath(restMusic.getPath()));
            }
            planService.save(planModel);
        }
    }

    private String getImagePath(String source) {
        String filename = source.substring(source.lastIndexOf("/"));
        try{
            saveToFile(SERVER_IP + source, propsConfig.getImages() + filename);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Constants.URL_IMAGES + filename;
    }

    private String getMusicPath(String source) {
        String filename = source.substring(source.lastIndexOf("/"));
        try{
            saveToFile(SERVER_IP + source, propsConfig.getMusic() + filename);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Constants.URL_MUSIC + filename;
    }

    private void saveToFile(String destUrl, String fileName) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        URL url = new URL(destUrl);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.connect();
        BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
        FileOutputStream fos = new FileOutputStream(fileName);

        // 保存文件
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }
}

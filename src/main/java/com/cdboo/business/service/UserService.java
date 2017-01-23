package com.cdboo.business.service;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.Constants;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.common.download.GeneralDownloadInfo;
import com.cdboo.business.common.download.HttpDownloader;
import com.cdboo.business.common.download.RemoteLocalPair;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.model.RestModel;
import com.cdboo.system.spring.PropsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2016/12/26.
 */
@Service
public class UserService {

    @Autowired
    private PlanService planService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private PropsConfig propsConfig;

    private static int BUFFER_SIZE = 10240;

    private List<String> musicList; // 服务器音乐地址

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
        config.setPhoneNumber(model.getPhoneNumber());
        config.setAddress(model.getAddress());

        String userPhoto = model.getPhoto().substring(model.getPhoto().lastIndexOf("/") + 1);
        saveToFile(SERVER_IP + model.getPhoto(), propsConfig.getImages() + userPhoto);
        config.setPhoto(propsConfig.getImages() + userPhoto);

        config.setBusinessHoursBegin(model.getBusinessHoursBegin());
        config.setBusinessHoursEnd(model.getBusinessHoursEnd());
        config.setServiceTimeBegin(model.getServiceTimeBegin());
        config.setServiceTimeEnd(model.getServiceTimeEnd());
        config.saveUserData();

        if(!new File(propsConfig.getMusic()).exists()){
            new File(propsConfig.getMusic()).mkdirs();
        }
        if(!new File(propsConfig.getImages()).exists()){
            new File(propsConfig.getImages()).mkdirs();
        }

        deleteAll();
        musicList = new ArrayList<>(); // 音乐单独下载

        for (PlanModel planModel : model.getPlanModelList()) {
            planModel.setSceneImg(getImagePath(planModel.getSceneImg()));

            saveChannel(planModel.getChannel());
            if(Constants.CHANNEL_TYPE_GROUP.equals(planModel.getChannel().getChannelType())){
                for(RestChannel channel : planModel.getChannel().getChildChannelList()){
                    saveChannel(channel);
                }
            }
            planService.save(planModel);
        }

        // 多线程下载歌曲
        new Thread(() -> {
            for(String source : musicList){
                String filename = source.substring(source.lastIndexOf("/") + 1);
                RemoteLocalPair pair = new RemoteLocalPair(SERVER_IP + source.replaceAll(" ", "%20"), propsConfig.getMusic(), filename);
                GeneralDownloadInfo info = new GeneralDownloadInfo(pair);
                HttpDownloader downloader = new HttpDownloader(info, 3);
                downloader.run();
            }
        }).start();
    }

    private void deleteAll(){
        musicService.deleteAll();
        periodService.deleteAll();
        channelService.deleteAll();
        planService.deleteAll();
    }

    private void saveChannel(RestChannel channel){
        channel.setPhotoPath(getImagePath(channel.getPhotoPath()));
        for (RestMusic restMusic : channel.getMusicList()) {
            if(!musicList.contains(restMusic.getPath())){
                musicList.add(restMusic.getPath());
            }
            restMusic.setPath(getMusicPath(restMusic.getPath()));
        }
    }

    private String getImagePath(String source) {
        if(!StringUtils.isEmpty(source)){
            String filename = source.substring(source.lastIndexOf("/") + 1);
            saveToFile(SERVER_IP + source, propsConfig.getImages() + filename);
            return Constants.URL_IMAGES + filename;
        }else{
            return null;
        }
    }

    private String getMusicPath(String source) {
        if(!StringUtils.isEmpty(source)){
            String filename = source.substring(source.lastIndexOf("/") + 1);
            //saveToFile(SERVER_IP + source, propsConfig.getMusic() + filename);
            return Constants.URL_MUSIC + filename;
        }else {
            return null;
        }
    }

    private void saveToFile(String destUrl, String fileName) {
        if(!new File(fileName).exists()){
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            try{
                byte[] buf = new byte[BUFFER_SIZE];
                int size = 0;

                URL url = new URL(destUrl.replaceAll(" ", "%20"));
                HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                fos = new FileOutputStream(fileName);
                // 保存文件
                while ((size = bis.read(buf)) != -1){
                    fos.write(buf, 0, size);
                }
                httpUrl.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(bis != null){
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}

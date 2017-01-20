package com.cdboo.business.web;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.BaseEntity;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.service.ChannelService;
import com.cdboo.business.service.MusicService;
import com.cdboo.business.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyi on 2016/12/20.
 */
@Controller
@RequestMapping("/")
public class WebViewController {
    private static final Logger logger = LoggerFactory.getLogger(WebViewController.class);

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PlanService planService;

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("config", Config.getConfigInstance());
        model.addAttribute("sceneList", planService.findSceneList()); // 场景业态
        model.addAttribute("festivalList", planService.findFestivalList()); // 查询节日
        return "index";
    }

    /**
     * 我的收藏
     * @param model
     * @return
     */
    @RequestMapping(value = "favorite")
    @ResponseBody
    public Map<String, List<? extends BaseEntity>> favorite(Model model){
        Map<String, List<? extends BaseEntity>> result = new HashMap<>();
        result.put("channelList", channelService.findFavoriteList());
        result.put("musicList", musicService.findFavoriteList());
        return result;
    }

    /**
     * 收藏频道
     * @param channelId
     */
    @RequestMapping(value = "doFavoriteChannel")
    @ResponseBody
    public void doFavoriteChannel(String channelId){
        channelService.updateFavorite(Long.parseLong(channelId), Constants.FAVORITE_YES);
    }

    /**
     * 取消收藏频道
     * @param channelId
     */
    @RequestMapping(value = "doNotFavoriteChannel")
    @ResponseBody
    public void doNotFavoriteChannel(String channelId){
        channelService.updateFavorite(Long.parseLong(channelId), Constants.FAVORITE_DEFAULT);
    }

    /**
     * 收藏音乐
     * @param musicId
     */
    @RequestMapping(value = "doFavoriteMusic")
    @ResponseBody
    public void doFavoriteMusic(String musicId){
        musicService.updateFavorite(Long.parseLong(musicId), Constants.FAVORITE_YES);
    }

    /**
     * 取消收藏音乐
     * @param musicId
     */
    @RequestMapping(value = "doNotFavoriteMusic")
    @ResponseBody
    public void doNotFavoriteMusic(String musicId){
        musicService.updateFavorite(Long.parseLong(musicId), Constants.FAVORITE_DEFAULT);
    }

    /**
     * 查询频道列表
     * @param scene
     * @param model
     * @return
     */
    @RequestMapping(value = "channel")
    @ResponseBody
    public Map<String, List<RestChannel>> channel(String scene, Model model){
        Map<String, List<RestChannel>> result = new HashMap<>();
        if(scene == null){
            result.put("festival", channelService.findChannelList(Constants.MUSIC_FESTIVAL, null));
            result.put("theme", channelService.findChannelList(Constants.MUSIC_THEME, null));
            result.put("manner", channelService.findChannelList(Constants.MUSIC_MANNER, null));
        }else{
            result.put("festival", channelService.findChannelList(Constants.MUSIC_FESTIVAL, scene));
            result.put("theme", channelService.findChannelList(Constants.MUSIC_THEME, scene));
            result.put("manner", channelService.findChannelList(Constants.MUSIC_MANNER, scene));
        }
        return result;
    }

    /**
     * 获取频道下音乐
     * @param channelId
     * @return
     */
    @RequestMapping(value = "music")
    @ResponseBody
    public Map<String, Object> music(String channelId){
        Map<String, Object> result = new HashMap<>();
        result.put("channel", channelService.findChannelById(Long.parseLong(channelId)));
        result.put("musicList", musicService.findMusicByChannel(Long.parseLong(channelId)));
        return result;
    }

    /**
     * 获取播放计划
     * @param model
     * @return
     */
    @RequestMapping(value = "plan")
    @ResponseBody
    public Map<String, Iterable<PlanModel>> plan(String festival, Model model){
        Map<String, Iterable<PlanModel>> result = new HashMap<>();
        result.put("festival", planService.findFestivalPlan(festival));
        result.put("theme", planService.findPlanByStyle(Constants.MUSIC_THEME));
        result.put("manner", planService.findPlanByStyle(Constants.MUSIC_MANNER));
        return result;
    }

    /**
     * 获取节日计划
     * @param model
     * @return
     */
    @RequestMapping(value = "festivalPlan")
    @ResponseBody
    public Iterable<PlanModel> festivalPlan(String festival, Model model){
        return planService.findFestivalPlan(festival);
    }

    /**
     * 获取插播计划
     * @param model
     * @return
     */
    @RequestMapping(value = "spotPlan")
    @ResponseBody
    public Iterable<PlanModel> spotPlan(Model model){
        return planService.findSpotPlan();
    }

    /**
     * 获取计划音乐
     * @return
     */
    @RequestMapping(value = "planMusic")
    @ResponseBody
    public List<RestMusic> planMusic(){
        return musicService.findPlanMusic();
    }

    /**
     * 获取插播音乐
     * @return
     */
    @RequestMapping(value = "spotMusic")
    @ResponseBody
    public RestMusic spotMusic(){
        RestMusic result = null;
        try{
            result = musicService.findSpotMusic();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
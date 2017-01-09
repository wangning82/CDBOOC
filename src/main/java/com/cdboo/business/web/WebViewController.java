package com.cdboo.business.web;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.BaseEntity;
import com.cdboo.business.entity.RestChannel;
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
        model.addAttribute("sceneList", planService.findSceneList());
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
            result.put("style", channelService.findChannelList(Constants.MUSIC_STYLE, null));
        }else{
            result.put("festival", channelService.findChannelList(Constants.MUSIC_FESTIVAL, scene));
            result.put("theme", channelService.findChannelList(Constants.MUSIC_THEME, scene));
            result.put("style", channelService.findChannelList(Constants.MUSIC_STYLE, scene));
        }
        return result;
    }

    @RequestMapping(value = "plan")
    public String plan(Model model){
        return "plan";
    }

    @RequestMapping(value = "spot")
    public String spot(Model model){
        return "spot";
    }
}

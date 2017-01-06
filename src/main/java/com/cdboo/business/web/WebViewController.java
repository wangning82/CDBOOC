package com.cdboo.business.web;

import com.cdboo.business.common.Config;
import com.cdboo.business.entity.BaseEntity;
import com.cdboo.business.service.ChannelService;
import com.cdboo.business.service.MusicService;
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
    private MusicService musicService;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("config", Config.getConfigInstance());
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

    @RequestMapping(value = "channel")
    public String channel(Model model){
        return "channel";
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

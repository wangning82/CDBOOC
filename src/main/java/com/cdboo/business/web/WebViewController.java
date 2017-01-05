package com.cdboo.business.web;

import com.cdboo.business.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by houyi on 2016/12/20.
 */
@Controller
@RequestMapping("/")
public class WebViewController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("config", Config.getConfigInstance());
        return "index";
    }

    @RequestMapping(value = "favorite")
    public String favorite(Model model){
        return "favorite";
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

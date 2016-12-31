package com.cdboo.business.web;

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
        return "index";
    }

    @RequestMapping(value = "myshop")
    public String myshop(Model model){
        return "myshop";
    }

    @RequestMapping(value = "favorite")
    public String favorite(Model model){
        return "favorite";
    }

    @RequestMapping(value = "channel")
    public String channel(Model model){
        return "channel";
    }

}

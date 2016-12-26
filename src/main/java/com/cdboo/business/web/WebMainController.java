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
public class WebMainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "")
    public String index(Model model){
        return "index";
    }



}

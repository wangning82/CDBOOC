package com.cdboo.business.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by houyi on 2016/12/20.
 */
@Controller
@RequestMapping("/")
public class WebMainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "")
    public ModelAndView index() {
        System.out.println("进入控制器===");
        return new ModelAndView("view");
    }
}

package com.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by meng on 2017/8/25.
 */
@Controller
@RequestMapping("/gather")
public class GatherController {

    @RequestMapping("/list")
    public ModelAndView getGatherList(){
        ModelAndView modelAndView = new ModelAndView("/jsp/gather/gatherList.jsp");

        return modelAndView;
    }


}

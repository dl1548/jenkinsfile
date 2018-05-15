package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController{
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("welcome");//模板文件的名称，不需要指定后缀
        mv.addObject("key","hellofg");
        return mv;
    }
}

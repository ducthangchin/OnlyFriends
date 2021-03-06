package com.ducthangchin.controllers;

import com.ducthangchin.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PageController {

    @Autowired
    private StatusUpdateService statusUpdateService;

    @Value("${message.error.forbidden}")
    private String messsage;

    @RequestMapping({ "/home"})
    ModelAndView home(ModelAndView modelAndView) {


        modelAndView.setViewName("app.homepage");

        return modelAndView;
    }

    @RequestMapping("/about")
    String about() {
        return "app.about";
    }

    @RequestMapping("/admin")
    String admin() {return "app.admin";}

    @RequestMapping("/403")
    ModelAndView accessDenied(ModelAndView modelAndView) {

        modelAndView.getModel().put("message", messsage);
        modelAndView.setViewName("app.message");
        return modelAndView;

    }

    @RequestMapping("/")
    ModelAndView init(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/viewstatus");
        return modelAndView;
    }


}

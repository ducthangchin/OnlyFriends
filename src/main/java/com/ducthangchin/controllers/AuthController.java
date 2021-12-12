package com.ducthangchin.controllers;


import com.ducthangchin.model.WebUser;
import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    WebUserService webUserService;

    @RequestMapping("/login")
    String admin() {
        return "app.login";
    }


    @RequestMapping(value="/register", method= RequestMethod.GET)
    ModelAndView register(ModelAndView modelAndView) {

        WebUser user = new WebUser();

        modelAndView.getModel().put("user", user);

        modelAndView.setViewName("app.register");

        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    ModelAndView register(ModelAndView modelAndView, @Valid WebUser user, BindingResult result) {

        modelAndView.setViewName("app.register");

        if (!result.hasErrors()) {
            webUserService.register(user);
            modelAndView.setViewName("redirect:/viewstatus");
        }

        return modelAndView;

    }
}

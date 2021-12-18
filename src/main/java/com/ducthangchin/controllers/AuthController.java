package com.ducthangchin.controllers;


import com.ducthangchin.model.WebUser;
import com.ducthangchin.service.EmailService;
import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    WebUserService webUserService;

    @Autowired
    EmailService emailService;



    @RequestMapping("/login")
    String admin() {
        return "app.login";
    }

    @RequestMapping("/register")
    ModelAndView register(ModelAndView modelAndView) {

        WebUser user = new WebUser();

        modelAndView.getModel().put("user", user);

        modelAndView.setViewName("app.register");

        return modelAndView;

    }


    @RequestMapping(value="/register", method= RequestMethod.POST)
    ModelAndView register (ModelAndView modelAndView, @ModelAttribute(value="user") @Valid WebUser user, BindingResult result) {

        modelAndView.setViewName("app.register");


        if(!result.hasErrors()) {

            webUserService.register(user);

            emailService.sendVerificationEmail(user.getEmail(), user.getUsername());

            modelAndView.setViewName("redirect:/verify");
        }

        return modelAndView;
    }

    @RequestMapping("/verify")
    public String verify() {
        return "app.verify";
    }





}

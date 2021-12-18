package com.ducthangchin.controllers;


import com.ducthangchin.model.VerificationToken;
import com.ducthangchin.model.WebUser;
import com.ducthangchin.service.EmailService;
import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private EmailService emailService;

    @Value("${message.registration.confirmed}")
    private String registrationConfirmedMessage;

    @Value("${message.invalid.user}")
    private String invalidUserMessage;

    @Value("${message.expired.token}")
    private String expiredTokenMessage;




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

            String token = webUserService.createEmailVerificationToken(user);

            emailService.sendVerificationEmail(user.getEmail(), user.getUsername(), token);

            modelAndView.setViewName("redirect:/verify");
        }

        return modelAndView;
    }

    @RequestMapping("/verify")
    String verify() {
        return "app.verify";
    }


    @RequestMapping("/confirmregister" )
    ModelAndView registration(ModelAndView modelAndView, @RequestParam("t") String tokenString) {

        VerificationToken token = webUserService.getVerification(tokenString);

        if (token == null) {
            modelAndView.setViewName("redirect:/invaliduser");
            return modelAndView;
        }

        Date expiryDate = token.getExpiry();
        if (expiryDate.before(new Date())) {
            modelAndView.setViewName("redirect:/expiredtoken");
            webUserService.deleteToken(token);
            return modelAndView;
        }

        WebUser user = token.getUser();

        if (user == null) {
            modelAndView.setViewName("redirect:/invaliduser");
            webUserService.deleteToken(token);
            return modelAndView;
        }

        webUserService.deleteToken(token);
        user.setEnabled(true);
        webUserService.save(user);

        modelAndView.getModel().put("message", registrationConfirmedMessage);
        modelAndView.setViewName("app.message");

        return modelAndView;
    }

    @RequestMapping("/invaliduser" )
    ModelAndView invalidUser(ModelAndView modelAndView) {


        modelAndView.getModel().put("message", invalidUserMessage);
        modelAndView.setViewName("app.message");

        return modelAndView;
    }

    @RequestMapping("/expiredtoken" )
    ModelAndView expiredToken(ModelAndView modelAndView) {


        modelAndView.getModel().put("message", expiredTokenMessage);
        modelAndView.setViewName("app.message");

        return modelAndView;
    }




}

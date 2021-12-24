package com.ducthangchin.controllers;


import com.ducthangchin.model.Comment;
import com.ducthangchin.model.Profile;
import com.ducthangchin.model.StatusUpdate;
import com.ducthangchin.model.WebUser;
import com.ducthangchin.service.CommentService;
import com.ducthangchin.service.ProfileService;
import com.ducthangchin.service.StatusUpdateService;
import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {


    @Autowired
    private StatusUpdateService statusUpdateService;

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CommentService commentService;

    private WebUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        WebUser user = webUserService.findUser(email);

        return user;
    }

    @RequestMapping("/post-management")
    ModelAndView postManagement(ModelAndView modelAndView, @RequestParam(name = "p", defaultValue = "1") int pageNumber) {

        Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);

        modelAndView.getModel().put("page", page);
        modelAndView.getModel().put("user", profileService.findProfile(getUser()));

        List<StatusUpdate> posts = page.getContent();
        Map<Long, List<Comment>> postComments = new HashMap<>();

        posts.stream()
                .forEach(statusUpdate -> {
                    postComments.put(statusUpdate.getId(), commentService.getCommentsByPost(statusUpdate));
                });

        modelAndView.getModel().put("postComments", postComments);

        modelAndView.setViewName("app.viewStatus");

        return modelAndView;
    }

    @RequestMapping("/user-management")
    ModelAndView userManagement(ModelAndView modelAndView){

        List<Profile> profiles = profileService.getAll();

        modelAndView.getModel().put("profiles", profiles);

        modelAndView.setViewName("app.user-management");

        return modelAndView;
    }

    @RequestMapping(value="/changeauth", method = RequestMethod.GET)
    ModelAndView changeAuthority(ModelAndView modelAndView, @RequestParam("id") Long userId) {

        WebUser user = webUserService.findById(userId);

        user.setEnabled(!user.getEnabled());

        webUserService.save(user);

        modelAndView.setViewName("redirect:/user-management");

        return modelAndView;
    }


    @RequestMapping(value="/changerole", method = RequestMethod.GET)
    ModelAndView changeRole(ModelAndView modelAndView, @RequestParam("id") Long userId) {

        WebUser user = webUserService.findById(userId);

        if (user.getRole().equals("ROLE_ADMIN")){
            user.setRole("ROLE_USER");
        }
        else {
            user.setRole("ROLE_ADMIN");
        }

        webUserService.save(user);

        modelAndView.setViewName("redirect:/user-management");
        return modelAndView;
    }







}

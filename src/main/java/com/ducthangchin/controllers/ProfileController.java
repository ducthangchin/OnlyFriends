package com.ducthangchin.controllers;


import com.ducthangchin.model.Profile;
import com.ducthangchin.model.WebUser;
import com.ducthangchin.service.ProfileService;
import com.ducthangchin.service.StatusUpdateService;
import com.ducthangchin.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private StatusUpdateService statusUpdateService;

    @Value("${photo.upload.avatar.directory}")
    private String photoUploadDirectory;

    private WebUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        WebUser user = webUserService.findUser(email);
        return user;
    }


    private ModelAndView showProfile(WebUser user) {
        ModelAndView modelAndView = new ModelAndView();

        if (user == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Profile profile = profileService.findProfile(user);
        modelAndView.getModel().put("profile", profile);


        modelAndView.setViewName("app.profile");
        modelAndView.getModel().put("user", getUser());
        return modelAndView;
    }


    @RequestMapping(value="/profile")
    public ModelAndView showProfile() {
        WebUser webUser = getUser();


        return showProfile(webUser);
    }

    @RequestMapping(value="/profile/{id}")
    public ModelAndView showProfile(@PathVariable("id") Long id) {
        WebUser webUser = webUserService.findById(id);
        return showProfile(webUser);
    }


    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public ModelAndView editProfile(ModelAndView modelAndView, @ModelAttribute("editProfile") Profile editProfile) {

        modelAndView.setViewName("app.editProfile");
        WebUser user = getUser();
        Profile profile = profileService.findProfile(user);

        modelAndView.getModel().put("profile", profile);

        return modelAndView;
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    ModelAndView editProfile(ModelAndView modelAndView, @Valid Profile profile, BindingResult result) {

        modelAndView.setViewName("app.editProfile");


        if (!result.hasErrors()) {
            WebUser user = getUser();

            Profile currentProfile = profileService.findProfile(user);

            if (profile.getFullname() != ""){
                currentProfile.setFullname(profile.getFullname());
            }
            if (profile.getAbout() != ""){
                currentProfile.setAbout(profile.getAbout());
            }
            if (profile.getPhone() != ""){
                currentProfile.setPhone(profile.getPhone());
            }
            if (profile.getAddress() != ""){
                currentProfile.setAddress(profile.getAddress());
            }

            profileService.save(currentProfile);
            modelAndView.setViewName("redirect:/profile");
        }

        return modelAndView;
    }

    @RequestMapping(value = "upload-profile-photo", method=RequestMethod.GET)
    public ModelAndView photoUpload(ModelAndView modelAndView) {

        modelAndView.setViewName("app.uploadProfilePhoto");

        return  modelAndView;
    }


    @RequestMapping(value = "upload-profile-photo", method=RequestMethod.POST)
    public ModelAndView handlePhotoUpload(ModelAndView modelAndView, @RequestParam("file")MultipartFile file) {
        modelAndView.setViewName("redirect:/profile");

        Path outputFilePath = Paths.get(photoUploadDirectory, file.getOriginalFilename());

        try {
            Files.deleteIfExists(outputFilePath);
            Files.copy(file.getInputStream(), outputFilePath);
        }
        catch (IOException e)  {
            e.printStackTrace();
        }

        WebUser user = getUser();
        Profile profile = profileService.findProfile(user);

        profile.setAvatarURL("/avatar/"+file.getOriginalFilename());
        profileService.save(profile);

        return modelAndView;

    }






}

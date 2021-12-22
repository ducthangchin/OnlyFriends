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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatusUpdateController {
    @Autowired
    private StatusUpdateService statusUpdateService;

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CommentService commentService;

    @Value("${photo.upload.status.directory}")
    private String photoUploadDirectory;

    private WebUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        WebUser user = webUserService.findUser(email);

        return user;
    }

    @RequestMapping(value = "/viewstatus", method = RequestMethod.GET)
    ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name = "p", defaultValue = "1") int pageNumber) {

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

    @RequestMapping(value = "/addstatus", method = RequestMethod.GET)
    ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {

        modelAndView.setViewName("app.addStatus");


        return modelAndView;
    }

    @RequestMapping(value = "/addstatus", method = RequestMethod.POST)
    ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {



        modelAndView.setViewName("app.addStatus");

        if (!result.hasErrors()) {
            WebUser user = getUser();


            statusUpdate.setOwner(profileService.findProfile(user));
            statusUpdateService.save(statusUpdate);

            modelAndView.getModel().put("statusUpdate", statusUpdate);
            modelAndView.setViewName("redirect:/viewstatus");

        }

        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
        modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

        return modelAndView;

    }

    @RequestMapping(value = "/deletestatus", method = RequestMethod.GET)
    ModelAndView deleteStatus(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {

        modelAndView.setViewName("redirect:/mystatus");


        if (getUser().getId() == statusUpdateService.getStatus(id).getOwner().getId()) {
            statusUpdateService.delete(id);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    ModelAndView editStatus(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {

        modelAndView.setViewName("app.editStatus");

        StatusUpdate statusUpdate = statusUpdateService.getStatus(id);

        if (getUser().getId() != statusUpdate.getOwner().getUser().getId()) {
            modelAndView.setViewName("redirect:/mystatus");
        }

        modelAndView.getModel().put("statusToEdit", statusUpdate);

        return modelAndView;
    }

    @RequestMapping(value = "/editstatus", method = RequestMethod.POST)
    ModelAndView editStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {

        modelAndView.setViewName("app.editStatus");


        if (!result.hasErrors()) {
            statusUpdateService.save(statusUpdate);
            modelAndView.setViewName("redirect:/mystatus");
        }

        return modelAndView;
    }

    @RequestMapping("/mystatus")
    ModelAndView myStatus(ModelAndView modelAndView) {

        Profile profile = profileService.findProfile(getUser());
        List<StatusUpdate> statuses = statusUpdateService.getByOwner(profile);
        modelAndView.getModel().put("statusUpdates", statuses);
        modelAndView.getModel().put("user", profile);
        modelAndView.setViewName("app.myStatus");

        return modelAndView;
    }

    @RequestMapping(value="/add-img-to-status/{id}")
    public ModelAndView addImage(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView();

        WebUser user = getUser();
        StatusUpdate statusUpdate = statusUpdateService.getStatus(id);
        Long ownerId = statusUpdate.getOwner().getUser().getId();

        System.out.println(ownerId);
        System.out.println(user.getId());
        if (user.getId() != ownerId) {
            modelAndView.setViewName("/redirect:viewstatus");
            return modelAndView;
        }

        modelAndView.getModel().put("id", id);
        modelAndView.setViewName("app.addImgToStatus");
        return  modelAndView;

    }



    @RequestMapping(value = "/add-img-to-status", method=RequestMethod.POST)
    public ModelAndView addImage(ModelAndView modelAndView, @RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        modelAndView.setViewName("redirect:/mystatus");

        Path outputFilePath = Paths.get(photoUploadDirectory, file.getOriginalFilename());

        try {
            Files.deleteIfExists(outputFilePath);
            Files.copy(file.getInputStream(), outputFilePath);
        }
        catch (IOException e)  {
            e.printStackTrace();
        }

        StatusUpdate statusUpdate = statusUpdateService.getStatus(id);
        statusUpdate.setImgURL("/status/" + file.getOriginalFilename());

        statusUpdateService.save(statusUpdate);

        return modelAndView;
    }

    @RequestMapping(value="/delete-status-img/{id}")
    ModelAndView deleteImg(ModelAndView modelAndView, @PathVariable("id") Long id) {
        WebUser user = getUser();
        StatusUpdate statusUpdate = statusUpdateService.getStatus(id);
        Long ownerId = statusUpdate.getOwner().getUser().getId();

        modelAndView.setViewName("redirect:/mystatus");

        if (user.getId() == ownerId) {
            statusUpdate.setImgURL(null);
            statusUpdateService.save(statusUpdate);
        }

        return modelAndView;
    }
}

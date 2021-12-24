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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private StatusUpdateService statusUpdateService;

    @Autowired
    private WebUserService webUserService;

    private WebUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        WebUser user = webUserService.findUser(email);

        return user;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(ModelAndView modelAndView,
                                   @RequestParam("text") String text,
                                   @RequestParam("postId") Long postId){


        WebUser user = getUser();

        Profile commenter = profileService.findProfile(user);

        StatusUpdate post = statusUpdateService.getStatus(postId);

        Comment comment = new Comment(commenter, post, text);

        commentService.save(comment);


        modelAndView.setViewName("redirect:/viewstatus");

        return modelAndView;
    }


    @RequestMapping(value = "/deletecomment")
    ModelAndView deleteComment(ModelAndView modelAndView, @RequestParam("id") Long commentId) {

        modelAndView.setViewName("redirect:/viewstatus");

        Comment comment = commentService.getCommentById(commentId);

        Long commenterID = comment.getCommenter().getUser().getId();


        if (commenterID == getUser().getId() || getUser().getRole().equals("ROLE_ADMIN")) {
            commentService.deleteComment(comment);
        }

        return  modelAndView;
    }
}

package com.ducthangchin.service;

import com.ducthangchin.model.Comment;
import com.ducthangchin.model.CommentDao;
import com.ducthangchin.model.Profile;
import com.ducthangchin.model.StatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentDao commentDao;


    public List<Comment> getCommentsByPost(StatusUpdate statusUpdate) {

        List<Comment> comments = new ArrayList<>();

        commentDao.findCommentsByPostOrderByAddedDesc(statusUpdate)
                .forEach(comment->{comments.add(comment);});

        return comments;

    }


    public Comment getCommentById(Long id) {

        return commentDao.findById(id).get();

    }

    public void save(Comment comment) {

        commentDao.save(comment);
    }



}

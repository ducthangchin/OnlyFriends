package com.ducthangchin.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(nullable = false)
    Profile commenter;

    @ManyToOne(targetEntity = StatusUpdate.class)
    @JoinColumn(nullable = false)
    StatusUpdate post;

    String text;

    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;

    @PrePersist
    protected void onCreate() {
        if (added == null) {
            added = new Date();
        }
    }


    public Comment() {
    }

    public Comment(Profile commenter, StatusUpdate post, String text) {
        this.commenter = commenter;
        this.post = post;
        this.text = text;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Profile getCommenter() {
        return commenter;
    }

    public void setCommenter(Profile commenter) {
        this.commenter = commenter;
    }

    public StatusUpdate getPost() {
        return post;
    }

    public void setPost(StatusUpdate post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

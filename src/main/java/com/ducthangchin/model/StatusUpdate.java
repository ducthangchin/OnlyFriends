package com.ducthangchin.model;


import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;



@Entity
@Table(name="post")
public class StatusUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max=6000, message="{addstatus.text.size}")
    private String text;

    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(nullable = true)
    Profile owner;

    String imgURL;

    @PrePersist
    protected void onCreate() {
        if (added == null) {
            added = new Date();
        }
    }

    public StatusUpdate() {

    }

    public StatusUpdate(String text) {
        this.text = text;
    }

    public StatusUpdate(String text, Date added) {
        this.text = text;
        this.added = added;
    }


    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "StatusUpdate{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", added=" + added +
                '}';
    }


}

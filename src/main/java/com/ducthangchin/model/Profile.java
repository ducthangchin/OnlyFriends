package com.ducthangchin.model;


import javax.persistence.*;

@Entity
@Table
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = WebUser.class)
    @JoinColumn(nullable = false)
    private WebUser user;


    @Column(length=1000)
    private String about;



    String address;

    String avatarURL;


    String phone;

    String fullname;


    @PrePersist
    protected void onCreate() {
        if (avatarURL == null) {
            avatarURL = "/avatar/default.png";
        }

    }

    public Profile(WebUser user) {
        this.user = user;
        this.fullname = user.getUsername();
    }

    public Profile() {

    }


    public Long getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}

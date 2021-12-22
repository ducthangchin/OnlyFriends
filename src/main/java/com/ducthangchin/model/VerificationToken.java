package com.ducthangchin.model;



import jdk.nashorn.internal.parser.Token;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = WebUser.class)
    @JoinColumn(nullable = false)
    private WebUser user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType type;



    @PrePersist
    private void setDate() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 24);
        expiry = c.getTime();

    }


    public VerificationToken() {
    }

    public VerificationToken(String token, WebUser user, TokenType type) {
        this.token = token;
        this.user = user;
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", expiry=" + expiry +
                ", type=" + type +
                '}';
    }
}

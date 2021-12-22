package com.ducthangchin.service;


import com.ducthangchin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WebUserService implements UserDetailsService {

    @Autowired
    private WebUserDao webUserDao;

    @Autowired
    private VerificationTokenDao verificationTokenDao;


    public WebUser findUser(String email) {
        return webUserDao.findByEmail(email);
    }

    public WebUser findById(Long id) {return webUserDao.findById(id).get();}


    public void register(WebUser user) {

        user.setRole("ROLE_USER");

        webUserDao.save(user);
    }

    public void save(WebUser user) {
        webUserDao.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        WebUser user = webUserDao.findByEmail(email);

        if (user == null) {

            System.out.println("The username " + user.getEmail() + " does not exist.");

            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

        String password = user.getPassword();

        Boolean enabled = user.getEnabled();

        return new User(email, password, enabled, true, true, true, auth);
    }

    public String createEmailVerificationToken(WebUser user) {
        VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user, TokenType.REGISTRATION);

        verificationTokenDao.save(token);
        System.out.println(token.getToken());
        System.out.println("Token saved!");

        return token.getToken();
    }

    public VerificationToken getVerification(String token) {
        return verificationTokenDao.findByToken(token);
    }

    public void deleteToken(VerificationToken token){
        verificationTokenDao.delete(token);
    }
}

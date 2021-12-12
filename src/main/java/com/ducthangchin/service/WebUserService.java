package com.ducthangchin.service;


import com.ducthangchin.model.WebUser;
import com.ducthangchin.model.WebUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebUserService implements UserDetailsService {

    @Autowired
    private WebUserDao webUserDao;

    public void register(WebUser user) {
        webUserDao.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        WebUser user = webUserDao.findByEmail(email);

        if (user == null) {
            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String password = user.getPassword();

        return new User(email, password, auth);

    }
}




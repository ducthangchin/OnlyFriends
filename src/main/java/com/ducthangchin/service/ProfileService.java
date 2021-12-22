package com.ducthangchin.service;

import com.ducthangchin.model.Profile;
import com.ducthangchin.model.ProfileDao;
import com.ducthangchin.model.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileDao profileDao;

    public Profile findProfile(WebUser user) {
        return profileDao.findByUser(user);
    }

    public void save(Profile profile) {
        profileDao.save(profile);
    }




}

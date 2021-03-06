package com.ducthangchin.model;

import org.springframework.data.repository.CrudRepository;

public interface ProfileDao  extends CrudRepository<Profile, Long> {

    Profile findByUser(WebUser user);
}

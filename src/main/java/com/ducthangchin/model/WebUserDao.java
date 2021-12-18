package com.ducthangchin.model;

import org.springframework.data.repository.CrudRepository;

public interface WebUserDao extends CrudRepository<WebUser, Long> {

    WebUser findByEmail(String email);


}

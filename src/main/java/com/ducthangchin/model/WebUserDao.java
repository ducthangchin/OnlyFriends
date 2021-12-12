package com.ducthangchin.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserDao extends CrudRepository<WebUser, Long> {
    WebUser findByEmail(String email);

}

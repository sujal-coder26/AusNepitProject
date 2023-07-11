package com.microservices.userservice.service;

import com.microservices.userservice.entities.User;

import java.util.List;

public interface UserService {
    User saveUser( User user);
    List<User> getAllUser();
    User getUserById(String userId);
}

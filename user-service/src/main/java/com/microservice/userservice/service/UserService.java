package com.microservice.userservice.service;

import com.microservice.model.UserDto;
import com.microservice.userservice.domain.User;

public interface UserService {
    void sendUserDetails(UserDto userDto);
    User saveUser(UserDto userDto);
}

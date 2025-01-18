package com.microservice.user.registration.Service;

import com.microservice.user.registration.controller.model.UserDto;

public interface NotificationService {
    void sendUserDetails(UserDto userDto);
}

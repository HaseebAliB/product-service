package com.microservice.notificationservice.service;

import com.microservice.model.UserDto;

public interface NotificationService {
void sendUserDetails(UserDto userDto);
void sendEmail(UserDto userDto);
void notifyUser(UserDto userDto);

}

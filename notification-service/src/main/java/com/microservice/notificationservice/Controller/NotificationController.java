package com.microservice.notificationservice.Controller;

import com.microservice.model.UserDto;
import com.microservice.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/push/user")
    public ResponseEntity<?> sendUserRequest(@RequestBody UserDto userDto){
        notificationService.sendUserDetails(userDto);
        return  new ResponseEntity<>("User added in queue", HttpStatus.OK);
    }



}

package com.microservice.user.registration.Service;

import com.microservice.user.registration.controller.model.UserDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final RestTemplate restTemplate;
    public static final String SERVICE_HOST = "http://localhost:8083";

    public NotificationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void sendUserDetails(UserDto userDto) {
        restTemplate.postForEntity(SERVICE_HOST+"/notification/push/user",userDto, ResponseEntity.class);
    }
}

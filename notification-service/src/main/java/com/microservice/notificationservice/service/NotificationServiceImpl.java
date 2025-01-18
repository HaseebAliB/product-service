package com.microservice.notificationservice.service;

import com.microservice.notificationservice.Config.JmsConfig;
import com.microservice.model.UserDto;
import com.microservice.model.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{
    private final JmsTemplate jmsTemplate;
    private final RestTemplate restTemplate;
    private final EmailService emailService;


    @Override
    public void sendUserDetails(UserDto userDto) {
        if(userDto.getUserStatus().equals(UserStatus.VERIFIED)){
            log.debug("Sending user object to queue");
           jmsTemplate.convertAndSend(JmsConfig.USER_QUEUE,userDto, message -> {
               // Setting custom JMS message headers (properties)
               message.setStringProperty("status", userDto.getUserStatus().toString());
               return message;
           });
            log.debug("user object sent!");
        }
        if(userDto.getUserStatus().equals(UserStatus.FAILED)){
            log.debug("User verification failed sending email to user "+ userDto.getEmail());
            sendEmail(userDto);
        }
    }

    @Override
    public void sendEmail(UserDto userDto) {
     emailService.sendSimpleEmail(userDto.getEmail(),EmailService.SUBJECT,"User Add Status :"+ userDto.getUserStatus());
    }

    @Override
    @JmsListener(destination = JmsConfig.USER_QUEUE,selector = "status= 'ADDED'")
    public void notifyUser( UserDto userDto) {
        log.debug("Listening user " + userDto.getEmail());
        log.debug("sending email to user");
        sendEmail(userDto);
    }
}

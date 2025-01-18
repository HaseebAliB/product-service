package com.microservice.userservice.service;

import com.microservice.model.UserDto;
import com.microservice.model.UserStatus;
import com.microservice.userservice.domain.User;
import com.microservice.userservice.config.JmsConfig;
import com.microservice.userservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public UserServiceImpl(JmsTemplate jmsTemplate, UserRepository userRepository) {
        this.jmsTemplate = jmsTemplate;
        this.userRepository = userRepository;
    }

    private final JmsTemplate jmsTemplate;
    private UserRepository userRepository;


    @Override
    @JmsListener(destination = JmsConfig.USER_QUEUE,selector = "status= 'VERIFIED'")
    public void sendUserDetails(UserDto userDto) {
        log.debug("listening user from userservice");
        if (userDto.getUserStatus().equals(UserStatus.VERIFIED)) {
            userDto.setUserStatus(UserStatus.ADDED);
            log.debug("Saving user");
            saveUser(userDto);
            log.debug("user Saved!");
            log.debug("pushing user in queue!");
            jmsTemplate.convertAndSend(JmsConfig.USER_QUEUE,userDto,message -> {
                // Setting custom JMS message headers (properties)
                message.setStringProperty("status", userDto.getUserStatus().toString());
                return message;
            });
            log.debug("user pushed in queue!");
        }


    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = User.builder().name(userDto.getName())
                .email(userDto.getEmail()).userStatus(UserStatus.ADDED)
                .userRole(userDto.getUserRole())
                .build();

        userRepository.save(user);
      return user;
    }
}

package com.microservice.userservice.bootstrap;

import com.microservice.userservice.model.User;
import com.microservice.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLoader implements CommandLineRunner {

    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) loadUsers();
    }



    private void loadUsers(){
        User u = User.builder().name("GKing").build();
        userRepository.save(u);
    }
}

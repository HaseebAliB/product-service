package com.microservice.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return null;
   // return new ResponseEntity(new UserDto(id,"landed User"), HttpStatus.OK);
}

}

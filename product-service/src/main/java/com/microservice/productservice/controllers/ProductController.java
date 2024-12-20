package com.microservice.productservice.controllers;

import com.microservice.productservice.dto.UserDto;
import com.microservice.productservice.services.ProductService;
import com.microservice.productservice.utilities.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("product/api/v1")
public class ProductController {
 @Autowired
private RestClient restClient;
    @GetMapping("/userdata")
  public ResponseEntity<?> get(){

        return new ResponseEntity(restClient.getUser(UUID.randomUUID().toString()),HttpStatus.OK);
    }

}

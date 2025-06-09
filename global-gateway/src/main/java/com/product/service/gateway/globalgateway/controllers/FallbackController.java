package com.product.service.gateway.globalgateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping(value = "/user-fallback",method = RequestMethod.GET)
public ResponseEntity<?> userFallback(){
        return ResponseEntity
                .status(503) // 503 Service Unavailable
                .body("User Service is currently unavailable. Please try again later.");
}

    @RequestMapping(value = "/product-fallback",method = RequestMethod.GET)
    public ResponseEntity<?> productFallback(){
        return ResponseEntity
                .status(503) // 503 Service Unavailable
                .body("Product Service is currently unavailable. Please try again later.");
    }
}

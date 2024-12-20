package com.microservice.productservice.utilities;

import com.microservice.productservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestClient {

 @Value("${app.user.service.apihost}")
 private String apihost;
 public final String USER_FETCH_PATH = "user/";

 private final RestTemplate restTemplate ;


 public RestClient(RestTemplateBuilder restTemplateBuilder) {
  this.restTemplate = restTemplateBuilder.build();
 }


 public UserDto getUser(String id){
   return restTemplate.getForObject(apihost+USER_FETCH_PATH+id,UserDto.class);
 }

 public String getApihost() {
  return apihost;
 }

 public void setApihost(String apihost) {
  this.apihost = apihost;
 }
}

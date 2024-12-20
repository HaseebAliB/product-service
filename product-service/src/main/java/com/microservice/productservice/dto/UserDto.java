package com.microservice.productservice.dto;

public class UserDto {

private String uuId;
private String name;

    public UserDto(String uuId, String name) {
        this.uuId = uuId;
        this.name = name;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

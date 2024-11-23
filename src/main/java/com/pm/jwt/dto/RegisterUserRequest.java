package com.pm.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest {

    private String fullName;
    private String username;
    private String password;

    public RegisterUserRequest(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

}

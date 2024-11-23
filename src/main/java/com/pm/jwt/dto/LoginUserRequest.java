package com.pm.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserRequest {

    private String username;
    private String password;

    public LoginUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

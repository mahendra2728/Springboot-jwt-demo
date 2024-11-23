package com.pm.jwt.controller;

import com.pm.jwt.dto.LoginResponse;
import com.pm.jwt.dto.LoginUserRequest;
import com.pm.jwt.dto.RegisterUserRequest;
import com.pm.jwt.model.User;
import com.pm.jwt.service.AuthenticationService;
import com.pm.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    private User signUp(@RequestBody RegisterUserRequest registerUserRequest){
        return authenticationService.signUp(registerUserRequest);
    }

    @PostMapping("/authenticate")
    private LoginResponse authenticateUser(@RequestBody LoginUserRequest loginUserRequest){
         User authenticatedUser = authenticationService.authenticate(loginUserRequest);
         String jwtToken = jwtService.generateToken(authenticatedUser);
         return new LoginResponse(jwtToken,jwtService.getExpirationTime());
    }
}

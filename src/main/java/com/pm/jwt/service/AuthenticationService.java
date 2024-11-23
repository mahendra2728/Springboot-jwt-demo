package com.pm.jwt.service;

import com.pm.jwt.dto.LoginUserRequest;
import com.pm.jwt.dto.RegisterUserRequest;
import com.pm.jwt.model.User;
import com.pm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    public User signUp(RegisterUserRequest registerUserRequest){
        User user = new User();
        user.setFullName(registerUserRequest.getFullName());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(LoginUserRequest loginUserRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getUsername(),
                        loginUserRequest.getPassword())
        );
        return userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}

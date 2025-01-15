package com.url.shortener.controller;

import com.url.shortener.dto.LoginRequest;
import com.url.shortener.dto.ResgisterRequest;
import com.url.shortener.model.User;
import com.url.shortener.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody ResgisterRequest resgisterRequest){
        User user = new User();
        user.setUsername(resgisterRequest.getUsername());
        user.setEmail(resgisterRequest.getEmail());
        user.setPassword(resgisterRequest.getPassword());
        user.setRole("ROLE_USER");

        if(userService.registerUser(user)){
            return ResponseEntity.ok("User registered successfully");
        }

        return ResponseEntity.ok("User registered failled");
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }
}

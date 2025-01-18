package com.url.shortener.service;

import com.url.shortener.dto.LoginRequest;
import com.url.shortener.model.User;
import com.url.shortener.repository.UserRespository;
import com.url.shortener.security.jwt.JwtAuthenticationResponse;
import com.url.shortener.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRespository userRespository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public Boolean registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // check the user is already existing in the database by email
        if(userRespository.existsUsersByUsername(user.getUsername())){
            return false;
        }

        userRespository.save(user);
        return true;
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails =(UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    public User getByUsername(String name) {
        return userRespository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("Username not found " + name)
        );
    }
}

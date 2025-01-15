package com.url.shortener.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ResgisterRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
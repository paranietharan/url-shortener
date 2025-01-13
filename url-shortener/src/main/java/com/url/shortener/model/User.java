package com.url.shortener.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "user")
    private List<UrlMapping> urlMappings;
}

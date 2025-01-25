package com.url.shortener.controller;

import com.url.shortener.dto.UrlMappingDto;
import com.url.shortener.model.User;
import com.url.shortener.service.UrlMappingService;
import com.url.shortener.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<UrlMappingDto> createShortUrl(@RequestBody Map<String, String> request,
                                                        Principal principal){
        String oringinalUrl = request.get("oringinalUrl");
        User user = userService.getByUsername(principal.getName());

        // call service
        UrlMappingDto urlMappingDto = urlMappingService.createShortUrl(oringinalUrl, user);
        return ResponseEntity.ok(urlMappingDto);
    }

    @GetMapping("/myurls")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<List<UrlMappingDto>> getMyUrl(Principal principal){
        User user = userService.getByUsername(principal.getName());
        List<UrlMappingDto> urlMappingDtos = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urlMappingDtos);
    }
}

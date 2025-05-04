package com.url.shortener.controller;

import com.url.shortener.model.UrlMapping;
import com.url.shortener.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RedirectController {

    private UrlMappingService urlMappingService;

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        UrlMapping urlMapping = urlMappingService.getUrlByShortUrl(shortUrl);
        if (urlMapping != null) {
            String originalUrl = urlMapping.getOringinalUrl();
            if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
                originalUrl = "http://" + originalUrl;
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", originalUrl);
            return ResponseEntity.status(302).headers(headers).build();
        }
        return ResponseEntity.notFound().build();
    }
}

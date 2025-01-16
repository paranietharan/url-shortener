package com.url.shortener.service;

import com.url.shortener.dto.UrlMappingDto;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;
import com.url.shortener.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Service
public class UrlMappingService {

    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDto createShortUrl(String oringinalUrl, User user) {
        String shortUrl = generateShortUrl();

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOringinalUrl(oringinalUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMapping.setShortUrl(shortUrl);

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);

        return toUrlMappingDto(savedUrlMapping);
    }

    private UrlMappingDto toUrlMappingDto(UrlMapping savedUrlMapping) {
        UrlMappingDto urlMappingDto = new UrlMappingDto();

        urlMappingDto.setOringinalUrl(savedUrlMapping.getOringinalUrl());
        urlMappingDto.setShortUrl(savedUrlMapping.getShortUrl());
        urlMappingDto.setCreatedDate(savedUrlMapping.getCreatedDate());
        urlMappingDto.setUsername(savedUrlMapping.getUser().getUsername());
        urlMappingDto.setClickCount(savedUrlMapping.getClickCount());

        return urlMappingDto;
    }

    private String generateShortUrl() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);

        for(int i=0; i < 8; i++){
            shortUrl.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return shortUrl.toString();
    }
}

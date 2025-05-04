package com.url.shortener.service;

import com.url.shortener.dto.ClickEventDTO;
import com.url.shortener.dto.UrlMappingDto;
import com.url.shortener.model.ClickEvent;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;
import com.url.shortener.repository.ClickEventRepository;
import com.url.shortener.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UrlMappingService {

    private UrlMappingRepository urlMappingRepository;
    private ClickEventRepository clickEventRepository;

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

    private String generateShortUrl() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);

        for(int i=0; i < 8; i++){
            shortUrl.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return shortUrl.toString();
    }

    public List<UrlMappingDto> getUrlsByUser(User user) {
        List<UrlMapping> urls = urlMappingRepository.findByUser(user);

        return urls.stream()
                .map(this::toUrlMappingDto)
                .collect(Collectors.toList());
    }

    // method to UrlMapping from UrlMappingDto
    private UrlMappingDto toUrlMappingDto(UrlMapping savedUrlMapping) {
        UrlMappingDto urlMappingDto = new UrlMappingDto();

        urlMappingDto.setOringinalUrl(savedUrlMapping.getOringinalUrl());
        urlMappingDto.setShortUrl(savedUrlMapping.getShortUrl());
        urlMappingDto.setCreatedDate(savedUrlMapping.getCreatedDate());
        urlMappingDto.setUsername(savedUrlMapping.getUser().getUsername());
        urlMappingDto.setClickCount(savedUrlMapping.getClickCount());

        return urlMappingDto;
    }

    public List<ClickEventDTO> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);

        if(urlMapping != null){
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end).stream()
                    .collect(Collectors.groupingBy(clickEvent -> clickEvent.getClickDate().toLocalDate(), Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        ClickEventDTO clickEventDTO = new ClickEventDTO();
                        clickEventDTO.setClickDate(entry.getKey());
                        clickEventDTO.setCount(entry.getValue());
                        return clickEventDTO;
                    })
                    .collect(Collectors.toList());
        }

        return null;
    }

    public UrlMapping getUrlByShortUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            urlMapping.setClickCount(urlMapping.getClickCount() + 1);
            urlMappingRepository.save(urlMapping);

            // Record the click event
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setUrlMapping(urlMapping);
            clickEvent.setClickDate(LocalDateTime.now());
            clickEventRepository.save(clickEvent);

            return urlMapping;
        } else {
            return null;
        }
    }
}

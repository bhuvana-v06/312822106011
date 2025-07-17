
package com.example.urlshortener.controller;

import com.example.urlshortener.entity.ShortUrl;
import com.example.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shorturls")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> createShortUrl(@RequestBody Map<String, Object> body) {
        String url = (String) body.get("url");
        Integer validity = body.get("validity") != null ? (Integer) body.get("validity") : null;
        String shortcode = (String) body.get("shortcode");

        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "URL is required"));
        }

        ShortUrl shortUrl = service.createShortUrl(url, validity, shortcode);

        String shortlink = "http://localhost:8080/" + shortUrl.getShortcode();
        String expiry = shortUrl.getExpiry().format(DateTimeFormatter.ISO_DATE_TIME);

        Map<String, String> response = new HashMap<>();
        response.put("shortlink", shortlink);
        response.put("expiry", expiry);

        return ResponseEntity.status(201).body(response);
    }
}
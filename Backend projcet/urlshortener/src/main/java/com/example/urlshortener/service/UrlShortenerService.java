package com.example.urlshortener.service;

import com.example.urlshortener.entity.ShortUrl;
import com.example.urlshortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {

    @Autowired
    private ShortUrlRepository repo;

    public ShortUrl createShortUrl(String url, Integer validity, String shortcode) {
        if (shortcode != null && repo.findByShortcode(shortcode).isPresent()) {
            throw new RuntimeException("Shortcode already exists");
        }

        if (shortcode == null || shortcode.isEmpty()) {
            shortcode = UUID.randomUUID().toString().substring(0, 6);
        }

        int minutes = (validity != null) ? validity : 30;

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(url);
        shortUrl.setShortcode(shortcode);
        shortUrl.setExpiry(LocalDateTime.now().plusMinutes(minutes));

        return repo.save(shortUrl);
    }

    public Optional<ShortUrl> findByShortcode(String shortcode) {
        return repo.findByShortcode(shortcode);
    }
}


package com.example.urlshortener.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String shortcode;
    private LocalDateTime expiry;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
}
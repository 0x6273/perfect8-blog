package com.perfect8.blog.dto;

import java.time.LocalDateTime;

/// A post sent from backend to frontend.
public record PostDto(String title, String body, LocalDateTime publishedAt) {
}

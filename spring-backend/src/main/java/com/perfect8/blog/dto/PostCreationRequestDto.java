package com.perfect8.blog.dto;

/// A request to create a new post, send from frontend to backend.
public record PostCreationRequestDto(String title, String body) {
}

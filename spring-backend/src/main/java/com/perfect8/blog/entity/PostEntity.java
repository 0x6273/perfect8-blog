package com.perfect8.blog.entity;

import java.time.LocalDateTime;

// TODO: make this a database entity
public class PostEntity {
    /// The database ID of the post
    private final long id;
    /// The URL slug
    private final String slug;
    /// The title of the post
    private final String title;
    /// The body text
    private final String body;
    /// When the post was published
    private final LocalDateTime publishedAt;

    public PostEntity(long id, String slug, String title, String body, LocalDateTime publishedAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.publishedAt = publishedAt;
    }

    public long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}

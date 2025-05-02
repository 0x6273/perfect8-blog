package com.perfect8.blog.dto.mapper;

import com.perfect8.blog.dto.PostDto;
import com.perfect8.blog.entity.PostEntity;
import org.jetbrains.annotations.NotNull;

public class PostDtoMapper {
    public static @NotNull PostDto toDto(@NotNull PostEntity entity) {
        return new PostDto(entity.getTitle(), entity.getBody(), entity.getPublishedAt());
    }
}

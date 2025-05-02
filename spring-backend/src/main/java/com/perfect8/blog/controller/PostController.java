package com.perfect8.blog.controller;

import com.perfect8.blog.dto.PostCreationRequestDto;
import com.perfect8.blog.dto.PostCreationResponseDto;
import com.perfect8.blog.dto.PostDto;
import com.perfect8.blog.dto.mapper.PostDtoMapper;
import com.perfect8.blog.service.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /// Get a post by its unique URL slug.
    /// @param slug     The post's unique URL slug
    /// @return         An Optional<PostDto> that is present if the post exists
    @GetMapping("/{slug}")
    public Optional<PostDto> getPost(@NotNull @PathVariable String slug) {
        return postService.getPostBySlug(slug)
                .map(PostDtoMapper::toDto);
    }

    /// Create a new post.
    /// @param postCreationRequestDto   The post to be created
    /// @return                         PostCreationResponseDto
    @PostMapping("/")
    public @NotNull PostCreationResponseDto createNewPost(@NotNull @RequestBody PostCreationRequestDto postCreationRequestDto) {
        var post = postService.createNewPost(postCreationRequestDto.title(), postCreationRequestDto.body());

        return new PostCreationResponseDto(post.getSlug());
    }
}

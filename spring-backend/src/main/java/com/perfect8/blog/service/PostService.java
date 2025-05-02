package com.perfect8.blog.service;

import com.perfect8.blog.entity.PostEntity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {
    Logger logger = LoggerFactory.getLogger(PostService.class);

    /// Get a post by its unique URL slug.
    /// @param slug     The post's unique URL slug
    /// @return         An Optional<PostEntity> that is present if the post exists
    public @NotNull Optional<PostEntity> getPostBySlug(@NotNull String slug) {
        //TODO get from repository when we have a database
        var post = new PostEntity(
                1,
                "my-blog-post",
                "My blog post",
                """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                deserunt mollit anim id est laborum.
                """,
                LocalDateTime.now()
        );

        return slug.equals(post.getSlug()) ? Optional.of(post) : Optional.empty();
    }

    /// Create a new post.
    /// @param title    The title of the post
    /// @param body     The post body
    public @NotNull PostEntity createNewPost(@NotNull String title, @NotNull String body) {
        //TODO: actually put the post in the database
        var post = new PostEntity(
                1,
                generateSlug(title),
                title,
                body,
                LocalDateTime.now()
        );

        logger.info("Created a new post with title \"{}\" and slug \"{}\"", post.getTitle(), post.getSlug());

        return post;
    }

    /// Create a slug from a title.
    /// @param title    The title
    /// @return         Slug
    public @NotNull String generateSlug(@NotNull String title) {
        // Code taken from this blog post: https://glaforge.dev/posts/2024/01/08/url-slug-or-how-to-remove-accents-in-java/
        //TODO maybe use a library instead (Slugify?)
        return Normalizer.normalize(title, Normalizer.Form.NFD)
                .toLowerCase()
                .replaceAll("\\p{IsM}+", "")
                .replaceAll("\\p{IsP}+", " ")
                .trim()
                .replaceAll("\\s+", "-");
    }
}

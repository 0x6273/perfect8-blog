package com.perfect8.blog;

import com.perfect8.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Test
    void contextLoads() {
        assertThat(postService)
                .isNotNull();
    }

    /// Test if the slug generator returns the expected slugs.
    @Test
    void slugGeneratorTest() {
        assertThat(postService.generateSlug("Hello world!"))
                .as("English title")
                .isEqualTo("hello-world");

        assertThat(postService.generateSlug("Hallå världen!"))
                .as("Swedish title")
                .isEqualTo("halla-varlden");
    }

    /// Test if a post can be created and it contains the expected values.
    /// This does not test if it's created in the database, it only tests the service.
    @Test
    void createPostTest() {
        var post = postService.createNewPost("Hello world!", "Lorem ipsum");

        assertThat(post.getTitle())
                .as("Title")
                .isEqualTo("Hello world!");

        assertThat(post.getBody())
                .as("Body")
                .isEqualTo("Lorem ipsum");

        assertThat(post.getPublishedAt())
                .as("Published at")
                .isNotNull();

        assertThat(post.getSlug())
                .as("Slug")
                .isEqualTo("hello-world");

        assertThat(post.getId())
                .as("ID")
                .isNotNegative();
    }

    /// Test getting a post from the service
    @Test
    void getPostTest() {
        //TODO use a mock repository
        assertThat(postService.getPostBySlug("my-blog-post"))
                .as("Existing post")
                .isPresent();

        assertThat(postService.getPostBySlug("this-post-does-not-exist"))
                .as("Nonexistent post")
                .isNotPresent();
    }
}

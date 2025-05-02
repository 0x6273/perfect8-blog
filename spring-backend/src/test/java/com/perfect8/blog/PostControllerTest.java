package com.perfect8.blog;

import com.perfect8.blog.controller.PostController;
import com.perfect8.blog.dto.PostCreationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostControllerTest {
    @Autowired
    private PostController postController;

    @Test
    void contextLoads() {
        assertThat(postController)
                .isNotNull();
    }

    @Test
    void getPost() {
        //TODO use a mock repository
        assertThat(postController.getPost("my-blog-post"))
                .as("Existing post")
                .isPresent();

        assertThat(postController.getPost("this-post-does-not-exist"))
                .as("Nonexistent post")
                .isNotPresent();
    }

    @Test
    void createPostTest() {
        var postCreationRequestDto = new PostCreationRequestDto("Hello world!", "Lorem ipsum");
        var postCreationResponseDto = postController.createNewPost(postCreationRequestDto);
        assertThat(postCreationResponseDto.slug())
                .as("Post slug")
                .isEqualTo("hello-world");
    }
}

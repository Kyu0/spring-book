package com.kyu0.springbook.domain.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//책에서는 RunWith annotation을 사용, JUnit4 = RunWith(SpringRunner.class) / JUnit5 = ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsrepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //책에서는 After annotation을 사용, JUnit4 = After / JUnit5 = AfterEach, AfterAll 등... After annotation 지원 X
    @AfterEach            
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_save_load() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("kyu0")
            .build());
        
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        
    }

    @Test
    public void registry_BaseTimeEntity() {
        LocalDateTime now = LocalDateTime.of(2021, 9, 16, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate=" + posts.getCreatedDate()
                        + "modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
    
}

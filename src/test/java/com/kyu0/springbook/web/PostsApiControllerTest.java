package com.kyu0.springbook.web;

import com.kyu0.springbook.domain.posts.Posts;
import com.kyu0.springbook.domain.posts.PostsRepository;
import com.kyu0.springbook.web.dto.PostsSaveRequestDto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/*
    Entity 클래스를 Request/Response (DTO) 클래스로 이용해서는 절대 안됨
    Entity 클래스 = DB Layer / DTO 클래스 = View Layer
    DTO 클래스는 View Layer에 속해 있다고 취급하며 View Layer는 변경이 잦은 점이 있는데,
    Entity 클래스가 변경되면 여러 클래스에 영향을 끼치기 때문에 비용이 너무 크다.
    -> 따라서, DB / View Layer를 철저히 분리하여 운용하는 것이 좋다.
*/

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void registry_Posts() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                        .title(title)
                                        .content(content)
                                        .author("kyu0")
                                        .build();
        
        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}

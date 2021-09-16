package com.kyu0.springbook.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.kyu0.springbook.web.dto.PostsSaveRequestDto;
import com.kyu0.springbook.service.posts.PostsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
public class PostsApiController {
    
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
    
}

package com.kyu0.springbook.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.kyu0.springbook.web.dto.PostsSaveRequestDto;
import com.kyu0.springbook.web.dto.PostsUpdateRequestDto;
import com.kyu0.springbook.service.posts.PostsService;
import com.kyu0.springbook.web.dto.PostsResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RestController
public class PostsApiController {
    
    private final PostsService postsService;

    @PostMapping("/api/v1/posts/{id}")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }
    
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}

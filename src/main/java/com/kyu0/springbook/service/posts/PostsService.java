package com.kyu0.springbook.service.posts;

import com.kyu0.springbook.domain.posts.PostsRepository;
import com.kyu0.springbook.web.dto.PostsResponseDto;
import com.kyu0.springbook.web.dto.PostsSaveRequestDto;
import com.kyu0.springbook.web.dto.PostsUpdateRequestDto;
import com.kyu0.springbook.web.dto.PostsListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import com.kyu0.springbook.domain.posts.Posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(new StringBuilder()
                                                                .append("해당 게시글이 없습니다. id=")
                                                                .append(id).toString()));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
            .map(PostsListResponseDto::new)
            .collect(Collectors.toList());
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(new StringBuilder()
                                                    .append("해당 게시글이 없습니다. id=")
                                                    .append(id).toString()));
        
        return new PostsResponseDto(entity);
    }
}

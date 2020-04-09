package org.boot.study.springboot.service;


import lombok.RequiredArgsConstructor;
import org.boot.study.springboot.domain.posts.Posts;
import org.boot.study.springboot.domain.posts.PostsRepository;
import org.boot.study.springboot.web.dto.PostsListResponseDto;
import org.boot.study.springboot.web.dto.PostsResponseDto;
import org.boot.study.springboot.web.dto.PostsSaveRequestDto;
import org.boot.study.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 트랜잭션,도메인 간 순서 보장의 역할

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
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        return new PostsResponseDto(entity);
    }


    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(Posts))
                .collect(Collectors.toList());
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        postsRepository.delete(posts);
    }
}

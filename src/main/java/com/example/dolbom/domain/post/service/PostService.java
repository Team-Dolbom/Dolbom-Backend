package com.example.dolbom.domain.post.service;

import com.example.dolbom.domain.post.domain.Post;
import com.example.dolbom.domain.post.domain.repository.PostRepository;
import com.example.dolbom.domain.post.exception.PostAccessDeniedException;
import com.example.dolbom.domain.post.exception.PostNotFoundException;
import com.example.dolbom.domain.post.present.dto.request.PostRequest;
import com.example.dolbom.domain.post.present.dto.response.PostListResponse;
import com.example.dolbom.domain.post.present.dto.response.PostResponse;
import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public PostListResponse findAllDesc() {
        List<PostResponse> postsList = postRepository.findAllByOrderByIdDesc().stream()
                .map(posts -> new PostResponse(
                        posts.getId(),
                        posts.getCategory(),
                        posts.getTitle(),
                        posts.getContent().substring(0, Math.min(posts.getContent().length(), 40)),
                        posts.getRegion(),
                        posts.getView()
                )).collect(Collectors.toList());

        return new PostListResponse(postsList);
    }

    @Transactional(readOnly = true)
    public Post postDetail(Long id){
        Post post = postRepository.findPostsById(id)
                .orElseThrow(()-> PostNotFoundException.EXCEPTION);
        return post;
    }

    @Transactional
    public void save(PostRequest request){
        postRepository.save(
                Post.builder()
                        .category(request.getCategory())
                        .title(request.getTitle())
                        .content(request.getContent())
                        .author(userFacade.getCurrentUser().getAccountId())
                        .build()
        );
    }

    @Transactional
    public void update(Long id, PostRequest request){
        Post post = postRepository.findPostsById(id)
                .orElseThrow(()-> PostNotFoundException.EXCEPTION);

        log.info("check");

        User currentUser = userFacade.getCurrentUser();

        if(!currentUser.getAccountId().equals(post.getAuthor())){
            throw PostAccessDeniedException.EXCEPTION;
        }

        post.update(request.getCategory(), request.getTitle(), request.getContent());
    }


    @Transactional
    public void deleteById(Long id){
        Post posts = postRepository.findPostsById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        User currentUser = userFacade.getCurrentUser();

        if(!currentUser.getAccountId().equals(posts.getAuthor())) {
            throw PostAccessDeniedException.EXCEPTION;
        }

        postRepository.deletePostById(id);
    }

    @Transactional
    public void updateView(Long id){
        postRepository.updateView(id);
    }

}

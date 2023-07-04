package com.example.dolbom.domain.post.domain.repository;

import com.example.dolbom.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
    Optional<Post> findPostsById(Long id);
    Optional<Post> deletePostById(Long id);
}

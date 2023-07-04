package com.example.dolbom.domain.post.present;


import com.example.dolbom.domain.post.domain.Post;
import com.example.dolbom.domain.post.present.dto.request.PostRequest;
import com.example.dolbom.domain.post.present.dto.response.PostListResponse;
import com.example.dolbom.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public PostListResponse get() {
        return postService.findAllDesc();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.postDetail(id);
    }

    @PostMapping("/create")
    public void createPost(@RequestBody PostRequest request){
        postService.save(request);
    }

    @PutMapping("/{id}")
    public void updatePostById(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id){
        postService.deleteById(id);
    }
}

package com.sau.ornekwebapp.Controllers;

import com.sau.ornekwebapp.Models.Post;
import com.sau.ornekwebapp.Requests.PostCreateRequest;
import com.sau.ornekwebapp.Requests.PostUpdateRequest;
import com.sau.ornekwebapp.Responses.PostResponse;
import com.sau.ornekwebapp.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getPosts(userId);
    }

    @GetMapping(value = "/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getOnePost(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePost(id, updatePost);
    }
    @DeleteMapping("/{id}")
    public void deletePosts(@PathVariable Long id){
         postService.deleteOnePost(id);
    }
}

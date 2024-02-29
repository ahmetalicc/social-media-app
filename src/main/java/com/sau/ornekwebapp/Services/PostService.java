package com.sau.ornekwebapp.Services;

import com.sau.ornekwebapp.Models.Like;
import com.sau.ornekwebapp.Models.Post;
import com.sau.ornekwebapp.Models.User;
import com.sau.ornekwebapp.Repositories.LikeRepository;
import com.sau.ornekwebapp.Repositories.PostRepository;
import com.sau.ornekwebapp.Requests.PostCreateRequest;
import com.sau.ornekwebapp.Requests.PostUpdateRequest;
import com.sau.ornekwebapp.Responses.LikeResponse;
import com.sau.ornekwebapp.Responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;
    public PostService(PostRepository postRepository, UserService userService,LikeRepository likeRepository)
    {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }


    public List<LikeResponse> getLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public List<PostResponse> getPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = getLikes(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }


    public Post getOnePost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());
        if(user == null)
            return null;
        Post toPost = new Post();
        toPost.setId(newPostRequest.getId());
        toPost.setText(newPostRequest.getText());
        toPost.setTitle(newPostRequest.getTitle());
        toPost.setUser(user);
        toPost.setCreate_date(new Date());
        return postRepository.save(toPost);
    }


    public void deleteOnePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post updateOnePost(Long id, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

}

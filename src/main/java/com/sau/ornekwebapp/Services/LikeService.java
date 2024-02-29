package com.sau.ornekwebapp.Services;

import com.sau.ornekwebapp.Models.Like;
import com.sau.ornekwebapp.Models.Post;
import com.sau.ornekwebapp.Models.User;
import com.sau.ornekwebapp.Repositories.LikeRepository;
import com.sau.ornekwebapp.Requests.LikeCreateRequest;
import com.sau.ornekwebapp.Responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    private final UserService userService;

    private final PostService postService;


    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService){
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
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

    public Like createOneLike(LikeCreateRequest newLikeRequest) {
        User user = userService.getOneUser(newLikeRequest.getUserId());
        Post post = postService.getOnePost(newLikeRequest.getPostId());
        if(user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(newLikeRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }else
        return null;
    }

    public Like getOneLike(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public void deleteOneLike(Long id) {
        likeRepository.deleteById(id);
    }
}

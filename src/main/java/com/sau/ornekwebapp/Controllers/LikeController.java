package com.sau.ornekwebapp.Controllers;

import com.sau.ornekwebapp.Models.Like;
import com.sau.ornekwebapp.Requests.LikeCreateRequest;
import com.sau.ornekwebapp.Responses.LikeResponse;
import com.sau.ornekwebapp.Services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")

public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getLikes(userId, postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest newLikeRequest){
        return likeService.createOneLike(newLikeRequest);
    }

    @GetMapping("/{id}")
    public Like getLike(@PathVariable Long id){
        return likeService.getOneLike(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Long id){
        likeService.deleteOneLike(id);
    }
}

package com.sau.ornekwebapp.Controllers;

import com.sau.ornekwebapp.Models.Comment;
import com.sau.ornekwebapp.Requests.CommentCreateRequest;
import com.sau.ornekwebapp.Requests.CommentUpdateRequest;
import com.sau.ornekwebapp.Services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long>  userId){
        return commentService.getComments(postId, userId);
    }
    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest newCommentRequest){
        return commentService.createOneComment(newCommentRequest);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id){
        return commentService.getOneComment(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest updateComment){
        return commentService.updateOneComment(id, updateComment);
    }

    @DeleteMapping("/{id}")
    public void  deleteComment(@PathVariable Long id){
        commentService.deleteOneComment(id);
    }

}

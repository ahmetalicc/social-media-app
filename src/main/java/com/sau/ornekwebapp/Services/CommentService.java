package com.sau.ornekwebapp.Services;

import com.sau.ornekwebapp.Models.Comment;
import com.sau.ornekwebapp.Models.Post;
import com.sau.ornekwebapp.Models.User;
import com.sau.ornekwebapp.Repositories.CommentRepository;
import com.sau.ornekwebapp.Requests.CommentCreateRequest;
import com.sau.ornekwebapp.Requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }




    public List<Comment> getComments(Optional<Long> postId, Optional<Long> userId) {
        if(userId.isPresent() && postId.isPresent() ){
            return commentRepository.findByUserIdAndPostId(postId.get(), userId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        else
            return commentRepository.findAll();
    }

    public Comment getOneComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }


    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userService.getOneUser(newCommentRequest.getUserId());
        Post post = postService.getOnePost(newCommentRequest.getPostId());
        if(user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentRequest.getId());
            commentToSave.setText(newCommentRequest.getText());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            commentToSave.setCreate_date(new Date());
            return commentRepository.save(commentToSave);
        }else
            return null;

    }


    public Comment updateOneComment(Long id, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(updateComment.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneComment(Long id) {
        commentRepository.deleteById(id);
    }
}

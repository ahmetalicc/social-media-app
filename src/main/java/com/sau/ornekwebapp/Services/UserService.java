package com.sau.ornekwebapp.Services;

import com.sau.ornekwebapp.Models.User;
import com.sau.ornekwebapp.Repositories.CommentRepository;
import com.sau.ornekwebapp.Repositories.LikeRepository;
import com.sau.ornekwebapp.Repositories.PostRepository;
import com.sau.ornekwebapp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public UserService(UserRepository userRepository, LikeRepository likeRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }



    public User getOneUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateOneUser(Long id, User newUser) {
        Optional<User>  user = userRepository.findById(id);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword((newUser.getPassword()));
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }

    public void deleteOneUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getOneUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty())
            return null;
        System.out.println(commentRepository.findUserCommentsByPostId(postIds));
        return null;
    }
}

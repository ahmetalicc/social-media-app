package com.sau.ornekwebapp.Controllers;

import com.sau.ornekwebapp.Models.User;
import com.sau.ornekwebapp.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){

        return userService.getUsers();
    }

    @PostMapping
    public User CreateUser(@RequestBody User newUser){
        return userService.createOneUser(newUser);
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){

        //custom exception
        return userService.getOneUser(id);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
      return userService.updateOneUser(id, newUser);
    }

    @DeleteMapping(name = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteOneUser(id);
    }

    @GetMapping(value = "/activity/{userId}")
    public List<Object> getUserActivity(@PathVariable Long userId){
        return userService.getUserActivity(userId);
    }
}

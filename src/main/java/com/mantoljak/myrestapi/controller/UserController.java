package com.mantoljak.myrestapi.controller;

import com.mantoljak.myrestapi.exceptions.UserNotFoundException;
import com.mantoljak.myrestapi.model.Post;
import com.mantoljak.myrestapi.model.UserInfo;
import com.mantoljak.myrestapi.repository.PostRepository;
import com.mantoljak.myrestapi.repository.UserInfoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserInfoRepository userInfoRepository;
    private final PostRepository postRepository;

    public UserController(UserInfoRepository userInfoRepository, PostRepository postRepository) {
        this.userInfoRepository = userInfoRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<UserInfo> createUser(@RequestBody @Valid UserInfo userInfo) {
        var newUser = userInfoRepository.save(userInfo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public UserInfo getUserById(@PathVariable int id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("User with id %d doesn't exist yet.", id));

        return userInfo;
    }

    @DeleteMapping("/users/{id}")
    public UserInfo deleteUserById(@PathVariable int id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("Cannot delete. User with id %d doesn't exist yet.", id));

        userInfoRepository.delete(userInfo);
        return userInfo;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("User with id %d doesn't exist yet.", id));

        return userInfo.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@RequestBody @Valid Post post, @PathVariable int id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("User with id %d doesn't exist yet.", id));

        post.setAuthor(userInfo);
        var savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{id}")
    public UserInfo updateUser(@RequestBody UserInfo user, @PathVariable int id) {
        UserInfo updatedUser = userInfoRepository.findById(id).orElse(null);

        if (updatedUser == null)
            throw new UserNotFoundException(String.format("User with id %d doesn't exist yet.", id));

        updatedUser.setUsername(user.getUsername());
        updatedUser.setBirthDate(user.getBirthDate());

        userInfoRepository.save(updatedUser);

        return updatedUser;
    }
}

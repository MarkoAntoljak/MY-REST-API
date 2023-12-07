package com.mantoljak.myrestapi.controller;

import com.mantoljak.myrestapi.exceptions.UserNotFoundException;
import com.mantoljak.myrestapi.model.UserInfo;
import com.mantoljak.myrestapi.repository.UserInfoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserInfoRepository repository;

    @Autowired
    public UserController(UserInfoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<UserInfo> createUser(@RequestBody @Valid UserInfo userInfo) {
        var newUser = repository.save(userInfo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public UserInfo getUserById(@PathVariable int id) {
        UserInfo userInfo = repository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("User with id %d doesn't exist yet.", id));

        return userInfo;
    }

    @DeleteMapping("/users/{id}")
    public UserInfo deleteUserById(@PathVariable int id) {
        UserInfo userInfo = repository.findById(id).orElse(null);

        if (userInfo == null)
            throw new UserNotFoundException(String.format("Cannot delete. User with id %d doesn't exist yet.", id));

        repository.delete(userInfo);
        return userInfo;
    }
}

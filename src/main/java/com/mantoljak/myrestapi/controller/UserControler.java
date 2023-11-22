package com.mantoljak.myrestapi.controller;

import com.mantoljak.myrestapi.exceptions.CustomResponseEntityExceptionHandler;
import com.mantoljak.myrestapi.model.UserInfo;
import com.mantoljak.myrestapi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserControler {

    private final UserInfoService userInfoService;

    @Autowired
    public UserControler(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userInfoService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserInfo getUserById(@PathVariable int id) {
        UserInfo userInfo = userInfoService.findUser(id);
        return userInfo;
    }

    @PostMapping("/users")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        var newUser = userInfoService.createUser(userInfo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}

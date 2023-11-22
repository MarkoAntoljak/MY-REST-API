package com.mantoljak.myrestapi.service;

import com.mantoljak.myrestapi.model.UserInfo;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class UserInfoService {

    private static List<UserInfo> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new UserInfo(++usersCount,"mantoljak", LocalDate.now()));
        users.add(new UserInfo(++usersCount,"perica", LocalDate.now()));
        users.add(new UserInfo(++usersCount,"tomica", LocalDate.now()));
    }

    public List<UserInfo> findAll() {
        return users;
    }

    public UserInfo findUser(int id) {
        Predicate<? super UserInfo> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setId(usersCount++);
        users.add(userInfo);
        return userInfo;
    }
}

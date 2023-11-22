package com.mantoljak.myrestapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

public class UserInfo {

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @NonNull
//    private UUID id;

    private int id;

    private String username;

    private LocalDate birthDate;

    public UserInfo(int id, String username, LocalDate birthDate) {
        this.id = id;
        this.username = username;
        this.birthDate = birthDate;
    }

    public UserInfo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

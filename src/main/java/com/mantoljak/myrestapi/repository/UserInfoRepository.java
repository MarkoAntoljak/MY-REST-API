package com.mantoljak.myrestapi.repository;

import com.mantoljak.myrestapi.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {}

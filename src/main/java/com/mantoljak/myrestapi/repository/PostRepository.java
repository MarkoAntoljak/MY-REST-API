package com.mantoljak.myrestapi.repository;

import com.mantoljak.myrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {}

package com.SpringBootBlogApi.repository;

import com.SpringBootBlogApi.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloggerRepository extends JpaRepository<Blogger,Long> {
    boolean existsByUsername(String username);


    Blogger findByUsernameEquals(String username);
}

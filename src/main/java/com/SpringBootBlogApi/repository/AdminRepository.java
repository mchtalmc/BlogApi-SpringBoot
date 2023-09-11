package com.SpringBootBlogApi.repository;

import com.SpringBootBlogApi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    boolean existsByUsername(String username);


    Admin findByUsernameEquals(String username);
}

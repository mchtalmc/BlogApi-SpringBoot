package com.SpringBootBlogApi.repository;

import com.SpringBootBlogApi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}

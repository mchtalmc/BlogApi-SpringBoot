package com.SpringBootBlogApi.payload.response;

import com.SpringBootBlogApi.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentResponse {
    private Post post;
    private Long id;
    private String name;
    private String email;
    private String rating;
}

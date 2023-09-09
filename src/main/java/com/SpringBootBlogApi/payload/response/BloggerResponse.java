package com.SpringBootBlogApi.payload.response;

import com.SpringBootBlogApi.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BloggerResponse {

    private Long userId;
    private String username;
    private String name;
    private String surname;
    private String password;
    private Gender gender;
}

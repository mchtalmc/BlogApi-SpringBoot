package com.SpringBootBlogApi.payload.response;

import com.SpringBootBlogApi.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdminResponse {

    private Long id;
    private String username;
    private String name;
    private String password;
    private String email;
    private Gender gender;
}

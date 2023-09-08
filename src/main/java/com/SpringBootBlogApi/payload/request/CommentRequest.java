package com.SpringBootBlogApi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentRequest {


    @NotNull(message = "Please enter your name")
    @Size(min=4,max=16, message="Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull(message = "Please enter your email")
    @Email(message = "Please enter valid email")
    @Size(min = 5, max = 50, message = "Your email should be between 5 and 50 chars")
    private String email;

    @NotNull(message = "Please enter your rating")
    @Size(min = 5, max = 50, message = "Your rating should be between 5 and 50 chars")
    private String rating;
}

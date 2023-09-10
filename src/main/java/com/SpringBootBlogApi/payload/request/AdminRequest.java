package com.SpringBootBlogApi.payload.request;

import com.SpringBootBlogApi.entity.enums.Gender;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdminRequest {

    @NotNull
    @Size(min=4,max=16, message="Your username should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your username must consist of the characters .")
    private String username;

    @NotNull
    @Size(min=4,max=16, message="Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull
    @Size(min=8,max=60, message="Your password should be at least 8 chars")
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Gender gender;


}

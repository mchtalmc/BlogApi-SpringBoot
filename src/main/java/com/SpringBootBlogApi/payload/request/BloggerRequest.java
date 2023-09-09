package com.SpringBootBlogApi.payload.request;

import com.SpringBootBlogApi.entity.enums.Gender;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BloggerRequest {
    @NotNull
    @Size(min=4,max=16, message="Your username should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your username must consist of the characters .")
    private String username;

    @NotNull
    @Size(min=4,max=16, message="Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull
    @Size(min=4,max=16, message="Your surname should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your surname must consist of the characters .")
    private String surname;

    @NotNull
    @Size(min=8,max=60, message="Your password should be at least 8 chars")
    private String password; //

    @NotNull
    @Size(min=12,max=12, message="Your phoneNumber should be 12 chars long")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your phoneNumber must consist of the characters .")
    private String phoneNumber;

    @NotNull
    private Gender gender;
}

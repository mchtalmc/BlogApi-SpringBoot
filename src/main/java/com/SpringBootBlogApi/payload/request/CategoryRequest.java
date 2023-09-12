package com.SpringBootBlogApi.payload.request;

import com.SpringBootBlogApi.entity.enums.Gender;
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
public class CategoryRequest {
    @NotNull(message = "The name cannot be empty")
    @Size(min=4,max=16, message="Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull(message = "The description cannot be empty")
    @Size(min=2,max=60, message="Your description should be at least 8 chars")
    private String description;


}

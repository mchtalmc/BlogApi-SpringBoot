package com.SpringBootBlogApi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostRequest implements Serializable {

    @NotNull

    private Long id;
    @NotNull
    @Size(min = 4,max = 30, message = "Your title must be between 4 and 30")
    private String title;

    @NotNull
    @Size(min = 10,max = 250, message = "Your title must be between 10 and 250")
    private String message;
    @NotNull
    @Size(min = 5,max = 50, message = "Your title must be between 5 and 50")
    private String contents;
}
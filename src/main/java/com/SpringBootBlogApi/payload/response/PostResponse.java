package com.SpringBootBlogApi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostResponse {

    private Long id;

    private String title;

    private String message;

    private String contents;
}

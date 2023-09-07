package com.SpringBootBlogApi.payload.message;

public class ErrorMessage {
    private ErrorMessage() {
    }

    public static final String POST_ID_ALREADY_EXISTS= "Error: Post Id already exists in DB %s";
}

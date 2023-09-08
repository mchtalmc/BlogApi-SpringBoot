package com.SpringBootBlogApi.payload.message;

public class ErrorMessage {
    private ErrorMessage() {
    }

    public static final String POST_ID_ALREADY_EXISTS = "Error: Post Id already exists in DB %s";

    public static final String NOT_FOUND_POST = "Error: There is a post with this %s ";
    public static final String NOT_FOUND_COMMENT = "Error: There is a comment with this %s ";
}
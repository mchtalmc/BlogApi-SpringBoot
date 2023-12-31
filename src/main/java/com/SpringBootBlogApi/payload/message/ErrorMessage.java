package com.SpringBootBlogApi.payload.message;

public class ErrorMessage {
    private ErrorMessage() {
    }

    public static final String POST_ID_ALREADY_EXISTS = "Error: Post Id already exists in DB %s";

    public static final String NOT_FOUND_POST = "Error: There is a post with this %s ";
    public static final String NOT_FOUND_COMMENT = "Error: There is a comment with this %s ";


    public static final String NOT_FOUND_ADMIN = "Error: There is a Admin with this %s ";


    public static final String USED_USERNAME = "Error: This username has been taken before ";
    public static final String NOT_FOUND_BLOGGER_THIS_ID = "Error: There is no Blogger with this %s ";


    public static final String ALREADY_ROLE = "Error: This named UserRole exists ";

    public static final String NOT_FOUND_ROLE = "Error: No role with this name  ";
    public static final String NOT_PERMITTED_METHOD_MESSAGE = "Error: This Admin not DELETED  ";
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: This Email Already Exist  ";
    public static final String ALREADY_CATEGORY_NAME = "Error: There is a category with this %s  ";
    public static final String NOT_FOUND_CATEGORY = "Error: There is no category with this %s  ";



}
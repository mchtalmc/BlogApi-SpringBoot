package com.SpringBootBlogApi.payload.message;

public class SuccesMessage {
    private SuccesMessage() {
    }

    public static final String POST_FOUND = "Post is Found Successfully";
    public static final String POST_UPDATE = "Post is Update Successfully";
    public static final String POST_DELETE = "Post is Delete Successfully";


    public static final String SAVE_COMMENT = "Comment is Save Successfully";
    public static final String UPDATE_COMMENT = "Comment is Update Successfully";
    public static final String DELETE_COMMENT = "Comment is dELETE Successfully";



    public static final String SAVE_ADMIN = "Admin is Save Successfully";
    public static final String DELETE_ADMIN = "Admin is Deleted Successfully";
    public static final String SAVE_BLOGGER = "Blogger is Save Successfully";


    public static final String UPDATE_BLOGGER_FIELD = "Blogger is Update Successfully";
    public static final String FIND_BLOGGER_BY_ID = "Blogger with this %s was found";
    public static final String DELETE_BLOGGER_BY_ID = "Blogger is Deleted Successfully";


}

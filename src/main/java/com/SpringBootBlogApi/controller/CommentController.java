package com.SpringBootBlogApi.controller;

import com.SpringBootBlogApi.entity.Comment;
import com.SpringBootBlogApi.payload.request.CommentRequest;
import com.SpringBootBlogApi.payload.response.CommentResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseMessage<CommentResponse> createComment(@RequestBody CommentRequest commentRequest,
                                                             @PathVariable(value = "postId") Long postId){
        return commentService.save(commentRequest,postId);

    }

    @GetMapping("/posts/{postId}/getAllComments")
    public Page<CommentResponse> getAllComment(
            @PathVariable(value = "postId") Long postId,
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "email")String sort,
            @RequestParam(value = "type",defaultValue = "desc")String type){

        return commentService.findAll(page,size,sort,type,postId);

    }
    @GetMapping("/posts/{postId}/comments/{commentsId}")
    public Page<CommentResponse> getAllCommentById(
            @PathVariable(value = "commentsId")Long commentsId,
            @PathVariable(value = "postId") Long postId,
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10") int size) {

        return commentService.findAll(page,size, postId,commentsId);
    }






}

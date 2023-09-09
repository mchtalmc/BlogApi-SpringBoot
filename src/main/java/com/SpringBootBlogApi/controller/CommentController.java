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
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseMessage<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest,
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

    @PutMapping("/posts/{postId}/comments/{commentsId}")
    public ResponseMessage<CommentResponse> updateCommentById(
            @Valid
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentsId") Long commentsId,
            @RequestBody CommentRequest commentRequest
    ){
        return commentService.updateCommentById(postId,commentsId,commentRequest);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseMessage<?> deleteComment (
                                 @PathVariable(value = "postId") Long postId,
                                 @PathVariable(value = "commentsId")Long commentId){
        return commentService.deleteCommentById(postId,commentId);

    }




}

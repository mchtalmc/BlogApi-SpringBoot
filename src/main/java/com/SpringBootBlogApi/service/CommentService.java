package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.Comment;
import com.SpringBootBlogApi.exception.ResourceNotFoundException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.payload.message.SuccesMessage;
import com.SpringBootBlogApi.payload.request.CommentRequest;
import com.SpringBootBlogApi.payload.response.CommentResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.repository.CommentRepository;
import com.SpringBootBlogApi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public ResponseMessage<CommentResponse> save(CommentRequest commentRequest, Long postId) {
        if (isPostExist(postId)) {
            Comment comment = mapToCommentRespontToComment(commentRequest);
            Comment savedComment = commentRepository.save(comment);
            return ResponseMessage.<CommentResponse>builder()
                    .httpStatus(HttpStatus.CREATED)
                    .message(SuccesMessage.SAVE_COMMENT)
                    .object(mapToCommentToCommentResponse(savedComment))
                    .build();
        } else {
            throw new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_POST,postId));
        }
    }


    //Are there any posts with this id
    private boolean isPostExist(Long postId) {
        return postRepository.existsById(postId);
    }
    //Are there any comment with this id
    private boolean isCommentExist(Long commentId){
        return commentRepository.existsById(commentId);
    }


    //Create DTO
    private Comment mapToCommentRespontToComment(CommentRequest commentRequest) {
        return Comment.builder()
                .name(commentRequest.getName())
                .email(commentRequest.getEmail())
                .rating(commentRequest.getRating())
                .build();
    }

    //Create Response
    private CommentResponse mapToCommentToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .name(comment.getName())
                .email(comment.getEmail())
                .rating(comment.getRating())
                .build();
    }



    public Page<CommentResponse> findAll(int page, int size, String sort, String type, Long postId) {
        if (!isPostExist(postId)){
            throw new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_POST,postId));
        }
        Pageable pageable=PageRequest.of(page,size,Sort.by(sort).ascending());
        if (Objects.equals("type","desc")){
            pageable=PageRequest.of(page,size,Sort.by(sort).descending());
        }
        return commentRepository.findAll(pageable).map(this::mapToCommentToCommentResponse);
    }


    public Page<CommentResponse> findAll(int page, int size, Long postId, Long commentsId) {
        if (!isPostExist(postId)){
            throw new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_POST,postId));
        }
        if (!isCommentExist(commentsId)){
            throw new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_COMMENT,commentsId));
        }
        Pageable pageable=PageRequest.of(page,size);
        return commentRepository.findAll(pageable).map(this::mapToCommentToCommentResponse);
    }
}
package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.Post;
import com.SpringBootBlogApi.exception.ResourceNotFoundException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.payload.message.SuccesMessage;
import com.SpringBootBlogApi.payload.request.PostRequest;
import com.SpringBootBlogApi.payload.response.PostResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
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
public class PostService {
    private final PostRepository postRepository;

    public ResponseMessage<PostResponse> save(PostRequest postRequest) {
    Post post=mapPostRequestToPost(postRequest);
    Post savedPost=postRepository.save(post);

    return ResponseMessage.<PostResponse>builder()
            .message("Save Post")
            .object(mapPostToPostResponse(savedPost))
            .httpStatus(HttpStatus.CREATED)
            .build();


    }

    //Create POJO
    private Post mapPostRequestToPost(PostRequest postRequest){
       return Post.builder()
                .title(postRequest.getTitle())
                .message(postRequest.getMessage())
                .contents(postRequest.getContents())
                .build();
    }
    //Create POJO
    private PostResponse mapPostToPostResponse(Post post){
        return PostResponse.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .message(post.getMessage())
                .build();
    }

    public Page<PostResponse> getAllPost(int page, int size, String sort, String type) {
        Pageable pageable=PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals("type","desc")){
            pageable=PageRequest.of(page,size,Sort.by(sort).descending());
        }
        return postRepository.findAll(pageable).map(this::mapPostToPostResponse);
    }

    public ResponseMessage<PostResponse> getPostById(Long postId) {
        return ResponseMessage.<PostResponse>builder()
                .message(SuccesMessage.POST_FOUND)
                .httpStatus(HttpStatus.OK)
                .object(mapPostToPostResponse(isPostExist(postId)))
                .build();

    }

    //Is there any post with this id
    private Post isPostExist(Long postId){
        return postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.POST_ID_ALREADY_EXISTS, postId)));
    }
}
















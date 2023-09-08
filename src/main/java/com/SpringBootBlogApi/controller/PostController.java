package com.SpringBootBlogApi.controller;
import com.SpringBootBlogApi.payload.request.PostRequest;
import com.SpringBootBlogApi.payload.response.PostResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseMessage<PostResponse> createPost(@Valid
                                                        @RequestBody PostRequest postRequest){

        return postService.save(postRequest);
    }


    @GetMapping("/getAllPost")
    public Page<PostResponse> getAllPostWithPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "title")String sort,
            @RequestParam(value = "type", defaultValue = "desc")String type
    ){
        return postService.getAllPost(page,size,sort,type);
    }


    @GetMapping("/getById/{postId}")
    public ResponseMessage<PostResponse> getPostById(@PathVariable Long postId ){

        return postService.getPostById(postId);
    }

    @PutMapping("/updatePostById/{postId}")
    public ResponseMessage<PostResponse> updatePost(@RequestBody PostRequest postRequest,
                                                    @PathVariable Long postId){
        return  postService.update(postRequest,postId);

    }

    @DeleteMapping("/deletePostById/{postId}")
    public ResponseMessage<?> deletePostById(@PathVariable Long postId){

        return postService.deletePostById(postId);

    }



}





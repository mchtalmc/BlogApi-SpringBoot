package com.SpringBootBlogApi.controller;

import com.SpringBootBlogApi.payload.request.BloggerRequest;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.payload.response.BloggerResponse;
import com.SpringBootBlogApi.service.BloggerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class BloggerController {
    private final BloggerService bloggerService;

    //save
    @PostMapping("/saveUser")
    public ResponseMessage<BloggerResponse> createUser(@Valid
                                                    @RequestBody BloggerRequest bloggerRequest){
        return bloggerService.save(bloggerRequest);
    }

    //update
    @PutMapping("/updateBlogger/{bloggerId}")
    public ResponseMessage<BloggerResponse> updateBlogger(@PathVariable Long bloggerId,
                                                          @RequestBody   @Valid BloggerRequest  bloggerRequest){
        return  bloggerService.updateBlogger(bloggerId,bloggerRequest);
    }
    //getBloggerWithId
    @GetMapping("getBloggerById")
    public ResponseMessage<BloggerResponse> getBloggerById(@PathVariable Long bloggerId){

        return bloggerService.findById(bloggerId);
    }
    //getAllWithPage
    @GetMapping("/getBloggerWithPage")
    public Page<BloggerResponse> getAllBloggerWithPage (
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "size",defaultValue = "10")int size){
        return bloggerService.findAll(page,size);
    }
    //delete

    @DeleteMapping("/deleteBloggerById/{bloggerId}")
    public String deleteBloggerById(@PathVariable Long bloggerId){
        return bloggerService.deleteBloggerById(bloggerId);
    }







}

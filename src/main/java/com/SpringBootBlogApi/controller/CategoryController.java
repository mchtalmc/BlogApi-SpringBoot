package com.SpringBootBlogApi.controller;

import com.SpringBootBlogApi.payload.request.CategoryRequest;
import com.SpringBootBlogApi.payload.response.CategoryResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseMessage<CategoryResponse> addCategory(@RequestBody @Valid
                                                             CategoryRequest categoryRequest){
        return categoryService.save(categoryRequest);
    }

    @GetMapping("/getCategory/{name}")
    public ResponseMessage<CategoryResponse> getCategoryByName(@PathVariable Long categoryId){

        return categoryService.findCategoryByName(categoryId);
    }
}

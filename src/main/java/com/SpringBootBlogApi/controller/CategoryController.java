package com.SpringBootBlogApi.controller;

import com.SpringBootBlogApi.payload.request.CategoryRequest;
import com.SpringBootBlogApi.payload.response.CategoryResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/addCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'BLOGGER')")
    public ResponseMessage<CategoryResponse> addCategory(@RequestBody @Valid
                                                             CategoryRequest categoryRequest){
        return categoryService.save(categoryRequest);
    }

    @GetMapping("/getCategory/{categoryId}")
    public ResponseMessage<CategoryResponse> getCategoryById(@PathVariable Long categoryId){

        return categoryService.findCategoryById(categoryId);
    }

    @GetMapping("/getAllCategory")
    public Page<CategoryResponse> getAllCategoryWithPage(
      @RequestParam(value = "page",defaultValue = "0")int page,
      @RequestParam(value = "size",defaultValue = "10")int size
    ){
        return categoryService.getAllCategory(page,size);
    }
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseMessage<CategoryResponse> updateCategoryWithId(
            @PathVariable Long categoryId,
            @Valid
            @RequestBody CategoryRequest categoryRequest
    ){
        return categoryService.updateCategory(categoryId,categoryRequest);
    }

}

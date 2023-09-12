package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.Category;
import com.SpringBootBlogApi.exception.ConflictException;
import com.SpringBootBlogApi.exception.ResourceNotFoundException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.payload.message.SuccesMessage;
import com.SpringBootBlogApi.payload.request.CategoryRequest;
import com.SpringBootBlogApi.payload.response.CategoryResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseMessage<CategoryResponse> save(CategoryRequest categoryRequest) {

        if (categoryRepository.existsByName(categoryRequest.getName())){
            throw new ConflictException(String.format(ErrorMessage.ALREADY_CATEGORY_NAME,categoryRequest.getName()));
        }
        Category category=maptoCategoryRequestToCategory(categoryRequest);
        Category savedCategory= categoryRepository.save(category);
        return ResponseMessage.<CategoryResponse>builder()
                .object(mapToCategoryToCategoryResponse(savedCategory))
                .httpStatus(HttpStatus.CREATED)
                .message(SuccesMessage.CATEGORY_CREATED)
                .build();
    }

    private Category maptoCategoryRequestToCategory(CategoryRequest categoryRequest){
       return Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
    }
    private CategoryResponse mapToCategoryToCategoryResponse(Category category){
        return  CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }


    public ResponseMessage<CategoryResponse> findCategoryByName(Long categoryId) {

       Category category= categoryRepository.findById(categoryId)
               .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_CATEGORY,categoryId)));

      CategoryResponse mapCategory= mapToCategoryToCategoryResponse(category);
       return ResponseMessage.<CategoryResponse>builder()
               .object(mapCategory)
               .httpStatus(HttpStatus.OK)
               .build();
    }

}

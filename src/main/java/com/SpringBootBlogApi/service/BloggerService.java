package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.Blogger;
import com.SpringBootBlogApi.exception.ResourceNotFoundException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.payload.message.SuccesMessage;
import com.SpringBootBlogApi.payload.request.BloggerRequest;
import com.SpringBootBlogApi.payload.response.BloggerResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.repository.BloggerRepository;
import com.SpringBootBlogApi.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.valueOf;

@Service
@RequiredArgsConstructor
public class BloggerService {
    private final BloggerRepository bloggerRepository;
    private final UniquePropertyValidator uniquePropertyValidator;


    public ResponseMessage<BloggerResponse> save(BloggerRequest bloggerRequest) {
        uniquePropertyValidator.checkDuplicate(bloggerRequest.getUsername());

        Blogger blogger=mapBloggerRequestToBlogger(bloggerRequest);
        Blogger savedBlogger=bloggerRepository.save(blogger);
        return ResponseMessage.<BloggerResponse>builder()
                .httpStatus(HttpStatus.CREATED)
                .object(mapBloggerToBloggerResponse(savedBlogger))
                .message(SuccesMessage.SAVE_BLOGGER)
                .build();

    }

    private Blogger mapBloggerRequestToBlogger(BloggerRequest bloggerRequest){
       return Blogger.builder()
                .name(bloggerRequest.getName())
                .username(bloggerRequest.getUsername())
                .gender(bloggerRequest.getGender())
                .surname(bloggerRequest.getSurname())
                .password(bloggerRequest.getPassword())
                .build();
    }
    private BloggerResponse mapBloggerToBloggerResponse(Blogger blogger){
        return BloggerResponse.builder()
                .userId(blogger.getId())
                .name(blogger.getName())
                .username(blogger.getUsername())
                .surname(blogger.getSurname())
                .password(blogger.getPassword())
                .gender(blogger.getGender())
                .build();
    }

    public ResponseMessage<BloggerResponse> updateBlogger(Long bloggerId, BloggerRequest bloggerRequest) {
        Blogger blogger= isBloggerExistById(bloggerId);
        checkUniqueProperties(blogger,bloggerRequest);

        Blogger mapBlogger=mapBloggerRequestToBlogger(bloggerRequest);
        Blogger savedUpdateBlogger=bloggerRepository.save(mapBlogger);

        return ResponseMessage.<BloggerResponse>builder()
                .object(mapBloggerToBloggerResponse(savedUpdateBlogger))
                .httpStatus(HttpStatus.OK)
                .message(SuccesMessage.UPDATE_BLOGGER_FIELD)
                .build();

    }



    public Blogger isBloggerExistById(Long bloggerId){
        return bloggerRepository.findById(bloggerId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_BLOGGER_THIS_ID,bloggerId)));
    }


    //Change field
    private void checkUniqueProperties (Blogger blogger, BloggerRequest bloggerRequest){
        String updatedUsername="";
        String updatedName="";
        String updatedSurname="";

        if (!blogger.getUsername().equalsIgnoreCase(bloggerRequest.getUsername())){
            updatedUsername= bloggerRequest.getUsername();
        }
        if (blogger.getName().equalsIgnoreCase(blogger.getName())){
            updatedName= bloggerRequest.getName();
        }
        if (blogger.getSurname().equalsIgnoreCase(bloggerRequest.getSurname())){
            updatedSurname= bloggerRequest.getSurname();
        }


    }

    public ResponseMessage<BloggerResponse> findById(Long bloggerId) {
        isBloggerExistById(bloggerId);
        return ResponseMessage.<BloggerResponse>builder()
                .message(String.format(SuccesMessage.FIND_BLOGGER_BY_ID,bloggerId))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public Page<BloggerResponse> findAll(int page, int size) {

        Pageable pageable= PageRequest.of(page,size);

        return bloggerRepository.findAll(pageable).map(this::mapBloggerToBloggerResponse);


    }

    public String deleteBloggerById(Long bloggerId) {

       Optional<Blogger> blogger= bloggerRepository.findById(bloggerId);
       if (!blogger.isEmpty()){
           throw new ResourceNotFoundException(ErrorMessage.NOT_FOUND_BLOGGER_THIS_ID);
       }
       bloggerRepository.deleteById(bloggerId);
       return SuccesMessage.DELETE_BLOGGER_BY_ID;
    }
}

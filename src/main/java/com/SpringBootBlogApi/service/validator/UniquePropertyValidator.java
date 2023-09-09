package com.SpringBootBlogApi.service.validator;

import com.SpringBootBlogApi.exception.ConflictException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.repository.AdminRepository;
import com.SpringBootBlogApi.repository.BloggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePropertyValidator {
    private final AdminRepository adminRepository;
    private final BloggerRepository bloggerRepository;

    public void checkDuplicate(String username){
        if (!adminRepository.existsByUsername(username)&&bloggerRepository.existsByUsername(username)){
            throw new ConflictException(ErrorMessage.USED_USERNAME);
        }


    }
}

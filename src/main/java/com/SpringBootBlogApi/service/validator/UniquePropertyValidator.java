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

    public void checkDuplicate(String... values) {

        String username = values[0];
        String email = values[1];
        if (adminRepository.existsByUsername(username) || bloggerRepository.existsByUsername(username)) {
            throw new ConflictException(ErrorMessage.USED_USERNAME);
        } else if (adminRepository.existsByEmail(email) || bloggerRepository.existsByEmail(email)) {
            throw new ConflictException(String.format(ErrorMessage.ALREADY_REGISTER_MESSAGE_EMAIL, email));
        }
    }
}
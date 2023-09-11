package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.UserRole;
import com.SpringBootBlogApi.entity.enums.RoleType;
import com.SpringBootBlogApi.exception.ConflictException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataException;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole(RoleType roleType){
        return userRoleRepository.findByEnumRoleEquals(roleType).orElseThrow(() ->
                new ConflictException(ErrorMessage.NOT_FOUND_ROLE));
    }

    public List<UserRole> getAllUserRole(){
        return userRoleRepository.findAll();
    }

    public UserRole save(RoleType roleType){
        if (userRoleRepository.existsByEnumRoleEquals(roleType)){
            throw new ConflictException(ErrorMessage.ALREADY_ROLE);
        }
        UserRole userRole=UserRole.builder()
                .roleType(roleType)
                .build();
        return userRoleRepository.save(userRole);
    }
}

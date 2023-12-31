package com.SpringBootBlogApi.service;

import com.SpringBootBlogApi.entity.Admin;
import com.SpringBootBlogApi.entity.enums.RoleType;
import com.SpringBootBlogApi.exception.ConflictException;
import com.SpringBootBlogApi.exception.ResourceNotFoundException;
import com.SpringBootBlogApi.payload.message.ErrorMessage;
import com.SpringBootBlogApi.payload.message.SuccesMessage;
import com.SpringBootBlogApi.payload.request.AdminRequest;
import com.SpringBootBlogApi.payload.response.AdminResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.repository.AdminRepository;
import com.SpringBootBlogApi.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public ResponseMessage<AdminResponse> saveAdmin(AdminRequest adminRequest) {
        uniquePropertyValidator.checkDuplicate(adminRequest.getUsername(),adminRequest.getEmail());

        Admin admin=mapToAdminRequestToAdmin(adminRequest);
        admin.setBuilt_in(false);
        if (Objects.equals(adminRequest.getUsername(),"mchtalmc")){
            admin.setBuilt_in(true);
        }
        admin.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        System.out.println(admin.getPassword());

        Admin saveAdmin=adminRepository.save(admin);
        return ResponseMessage.<AdminResponse>builder()
                .httpStatus(HttpStatus.CREATED)
                .object(mapToAdminToAdminResponse(saveAdmin))
                .message(SuccesMessage.SAVE_ADMIN)
                .build();
    }

    //Create Dto

    private Admin mapToAdminRequestToAdmin(AdminRequest adminRequest){
       return Admin.builder()
                .email(adminRequest.getEmail())
                .username(adminRequest.getUsername())
                .name(adminRequest.getName())
                .password(adminRequest.getPassword())
               .gender(adminRequest.getGender())
                .build();

    }
    //Create Response
    private AdminResponse mapToAdminToAdminResponse(Admin admin){
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .username(admin.getUsername())
                .name(admin.getName())
                .gender(admin.getGender())
                .build();
    }

    public Page<Admin> getAllAdminsByPage(int page, int size, String sort, String type) {
        Pageable pageable= PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals("type","desc")){
            pageable=PageRequest.of(page,size,Sort.by(sort).descending());
        }

        return adminRepository.findAll(pageable);
    }

    public String deleteAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.NOT_FOUND_ADMIN,id));
        }else if (admin.get().isBuilt_in()) {
            throw new ConflictException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }
        adminRepository.deleteById(id);
        return SuccesMessage.DELETE_ADMIN;
    }

    public long countAllAdmins() {
        return adminRepository.count();
    }
}

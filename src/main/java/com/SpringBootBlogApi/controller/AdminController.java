package com.SpringBootBlogApi.controller;

import com.SpringBootBlogApi.entity.Admin;
import com.SpringBootBlogApi.payload.request.AdminRequest;
import com.SpringBootBlogApi.payload.response.AdminResponse;
import com.SpringBootBlogApi.payload.response.ResponseMessage;
import com.SpringBootBlogApi.service.AdminService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;


    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<AdminResponse>> saveAdmin(@RequestBody @Valid
                                                                    AdminRequest adminRequest){
        return ResponseEntity.ok(adminService.saveAdmin(adminRequest));
    }

    // Not : getAll() **********************************************************
    @GetMapping("/getAllAdminsByPage") // best practice , donen nesnelerin DTO olmasidir

    public ResponseEntity<Page<Admin>> getAllAdminsByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "name") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        Page<Admin> admins = adminService.getAllAdminsByPage(page, size, sort, type);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Not : delete() **********************************************************
    @DeleteMapping("/delete/{id}") // admins/delete/1

    public ResponseEntity<String> deleteAdminById(@PathVariable Long id){

        return ResponseEntity.ok(adminService.deleteAdminById(id));
    }
}

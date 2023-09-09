package com.SpringBootBlogApi.repository;

import com.SpringBootBlogApi.entity.UserRole;

import com.SpringBootBlogApi.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByEnumRoleEquals(RoleType roleType);

    boolean existsByEnumRoleEquals(RoleType roleType);
}

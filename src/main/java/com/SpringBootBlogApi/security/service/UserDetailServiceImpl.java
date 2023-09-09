package com.SpringBootBlogApi.security.service;

import com.SpringBootBlogApi.entity.Admin;
import com.SpringBootBlogApi.entity.Blogger;
import com.SpringBootBlogApi.repository.AdminRepository;
import com.SpringBootBlogApi.repository.BloggerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final BloggerRepository bloggerRepository;

    public UserDetailServiceImpl(AdminRepository adminRepository, BloggerRepository bloggerRepository) {
        this.adminRepository = adminRepository;
        this.bloggerRepository = bloggerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin= adminRepository.findByUsernameEquals(username);
        Blogger blogger=bloggerRepository.findByUsernameEquals(username);

        if (admin !=null){
            return new UserDetailsImpl(
                    admin.getId(),
                    admin.getUsername(),
                    admin.getEmail(),
                    admin.getName(),
                    blogger.getGender(), admin.getPassword(),
                    admin.getUserRole().getRoleType().name()
            );
        }
        if (blogger!=null){
            return new UserDetailsImpl(
                    blogger.getId(),
                    blogger.getUsername(),
                    blogger.getName(),
                    blogger.getSurname(),
                    blogger.getGender(),
                    blogger.getPassword(),
                    blogger.getUserRole().getRoleType().name()
            )
        }


        throw new UsernameNotFoundException("User " + username + " not found");
    }
}

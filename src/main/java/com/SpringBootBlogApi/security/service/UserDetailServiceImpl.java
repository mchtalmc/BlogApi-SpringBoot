package com.SpringBootBlogApi.security.service;
import com.SpringBootBlogApi.entity.Admin;
import com.SpringBootBlogApi.entity.Blogger;
import com.SpringBootBlogApi.repository.AdminRepository;
import com.SpringBootBlogApi.repository.BloggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final BloggerRepository bloggerRepository;
    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRepository.findByUsernameEquals(username);
        Blogger blogger=bloggerRepository.findByUsernameEquals(username);
        if (blogger!=null){
            return new UserDetailsImpl(
                    blogger.getId(),
                    blogger.getUsername(),
                    blogger.getName(),
                    blogger.getPassword(),
                    blogger.getUserRole().getRoleType().name()
            );
        } else if (admin!=null){
            return new UserDetailsImpl(
                    admin.getId(),
                    admin.getUsername(),
                    admin.getName(),
                    admin.getPassword(),
                    admin.getUserRole().getRoleType().name()
            );
        }
        throw new UsernameNotFoundException("User "+ username +" NOT FOUND");
    }
}

package com.debatemanagement.demo.security;

import com.debatemanagement.demo.entity.User;
import com.debatemanagement.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByName(username);

        if(user == null) throw new UsernameNotFoundException("invalid userName");
        else return new CustomUserDetails(user);

    }
}
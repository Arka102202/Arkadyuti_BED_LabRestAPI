package com.debatemanagement.demo.service;

import com.debatemanagement.demo.entity.User;
import com.debatemanagement.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public User findUserByName(String username) {
        return userRepo.findUserByName(username);
    }
}

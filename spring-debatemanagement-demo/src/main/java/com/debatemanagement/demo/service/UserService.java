package com.debatemanagement.demo.service;

import com.debatemanagement.demo.entity.User;

public interface UserService {
    User findUserByName(String username);
}

package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }
}

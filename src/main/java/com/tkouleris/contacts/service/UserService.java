package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}

package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}

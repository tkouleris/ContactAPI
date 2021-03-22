package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);

        return new CustomUserDetails(user);
    }

    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

}

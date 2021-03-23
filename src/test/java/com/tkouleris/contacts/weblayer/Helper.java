package com.tkouleris.contacts.weblayer;

import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.service.CustomUserDetails;
import com.tkouleris.contacts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Helper {

    @Autowired
    IContactsRepository contactsRepository;

    @Autowired
    IUserRepository userRepository;

    public void cleanAll()
    {
        this.contactsRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    public User initializeUser(){
        User u = new User();
        u.setUsername("tkouleris");
        u.setPassword("password");
        return this.userRepository.save(u);
    }

    public String getToken(){
        User user = initializeUser();
        UserDetails userDetails = new CustomUserDetails(user);
        JwtUtil jwt = new JwtUtil();
        return jwt.generateToken(userDetails);
    }
}

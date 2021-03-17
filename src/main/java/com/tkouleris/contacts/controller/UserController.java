package com.tkouleris.contacts.controller;

import com.tkouleris.contacts.dto.response.ApiResponse;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<Object> createContact(@RequestBody User user){
        User registeredUser = this.userService.createUser(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(registeredUser);
        apiResponse.setMessage("user is registered");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }
}

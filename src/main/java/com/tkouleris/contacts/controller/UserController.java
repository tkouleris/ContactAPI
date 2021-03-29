package com.tkouleris.contacts.controller;

import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.dto.response.ApiResponse;
import com.tkouleris.contacts.dto.response.LoginDto;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.service.CustomUserDetailsService;
import com.tkouleris.contacts.service.UserService;
import com.tkouleris.contacts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    IUserRepository userRepository;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<Object> createContact(@RequestBody User user){
        User registeredUser = this.userService.createUser(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(registeredUser);
        apiResponse.setMessage("user is registered");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticateUser(@RequestBody User user)
    {
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User LoggedInUser = userRepository.findByUsername(user.getUsername());
        LoginDto loginDto = new LoginDto();
        loginDto.setJwt(jwt);
        loginDto.setUsername(user.getUsername());
        loginDto.setUserid(LoggedInUser.getId());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Auth Token!");
        apiResponse.setData(loginDto);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }
}

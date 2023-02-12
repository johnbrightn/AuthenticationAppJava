package com.jbntech.auth.AuthenticationApp2.controller;

import com.jbntech.auth.AuthenticationApp2.dto.UserDto;
import com.jbntech.auth.AuthenticationApp2.entity.User;
import com.jbntech.auth.AuthenticationApp2.service.JwtService;
import com.jbntech.auth.AuthenticationApp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User addUser(@RequestBody UserDto user){

        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userService.retrieveUsers();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User getUser(@PathVariable Long id) throws Exception {
        return userService.retrieveUser(id);
    }

    @PostMapping("/auth")
    public String authenticateAndGetToken(@RequestBody String username, @RequestBody String password){
        return jwtService.generateToken(username);
    }
}

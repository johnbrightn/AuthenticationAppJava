package com.jbntech.auth.AuthenticationApp2.controller;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.jbntech.auth.AuthenticationApp2.dto.AuthDto;
import com.jbntech.auth.AuthenticationApp2.dto.UserDto;
import com.jbntech.auth.AuthenticationApp2.entity.User;
import com.jbntech.auth.AuthenticationApp2.service.JwtService;
import com.jbntech.auth.AuthenticationApp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

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

    @PostMapping(value = "/auth")
    public String authenticateAndGetToken(@RequestBody AuthDto authDto){
        Authentication authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        if(authentication.isAuthenticated()){

        return jwtService.generateToken(authDto.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid username or password!");
        }

    }
}

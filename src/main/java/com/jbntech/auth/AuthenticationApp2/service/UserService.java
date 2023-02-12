package com.jbntech.auth.AuthenticationApp2.service;

import com.jbntech.auth.AuthenticationApp2.dto.UserDto;
import com.jbntech.auth.AuthenticationApp2.entity.User;
import com.jbntech.auth.AuthenticationApp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User addUser(UserDto user){

        User userBuild = User.builder()
                .fullName(user.getFullName())
                .password(passwordEncoder.encode(user.getPassword()))
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();

      return   userRepository.save(userBuild);
    }

    public List<User> retrieveUsers(){
        return userRepository.findAll();
    }

    public User retrieveUser(Long id) throws Exception {
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()){
            throw new Exception("User not found");
        }

        return user.get();
    }
}

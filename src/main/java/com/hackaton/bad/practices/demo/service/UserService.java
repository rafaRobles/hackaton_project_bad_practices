package com.hackaton.bad.practices.demo.service;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users createUser(final Users newUser) {
        Users user = new Users();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(hashPassword(newUser.getPassword()));
        return this.userRepository.save(user);
    }

    public String hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            return new String(md.digest(password.getBytes()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

}

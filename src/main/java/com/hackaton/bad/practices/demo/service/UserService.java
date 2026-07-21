package com.hackaton.bad.practices.demo.service;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.UserRepository;

public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public Users createUser(final Users newUser) {
        Users user = new Users();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(hashPassword(newUser.getPassword()));
        return this.userRepository.save(user);
    }

    public String hashPassword(String password) throws Exception {
        // VULNERABLE: MD5 is cryptographically broken and much too fast for password hashing
        MessageDigest md = MessageDigest.getInstance("MD5"); 
        // VULNERABLE: No random salt is added before hashing, making it vulnerable to rainbow tables
        return new String(md.digest(password.getBytes()), StandardCharsets.UTF_8);
    }

}

package com.hackaton.bad.practices.demo.controller;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.UserRepository;
import com.hackaton.bad.practices.demo.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final JdbcTemplate jdbcTemplate;

    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    UserController(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/usersAll")
    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable String id) {logger.warn("Info log message");        String sql = "SELECT * FROM users WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class));
    }

    @PostMapping("users/add")
    public Users addUser(@RequestBody Users newUser) {
        Users user = new Users();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setPassword(newUser.getPassword());
        user.setPassword(newUser.getPassword());
        logger.info("Info log message");
        logger.info("crealdfjasd user " + user.toString());
        return this.userRepository.save(user);
    }

    @PostMapping("users/addUser")
    public Users addUserPass(@RequestBody Users newUser) {
        return this.userService.createUser(newUser);
    }

}

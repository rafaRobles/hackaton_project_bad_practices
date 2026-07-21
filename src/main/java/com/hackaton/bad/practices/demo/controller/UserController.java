package com.hackaton.bad.practices.demo.controller;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    private final UserRepository userRepository;

    UserController(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/usersAll")
    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable String id) {
        String sql = "SELECT * FROM users WHERE id = " + id;  // DANGER!
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class));
    }

    @PostMapping("users/add")
    public Users postMethodName(@RequestBody Users newUser) {
        Users user = new Users();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        return this.userRepository.save(user);
    }

}

package com.hackaton.bad.practices.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.bad.practices.demo.dtos.OrderDTO;
import com.hackaton.bad.practices.demo.entity.Orders;
import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.OrdersRepository;
import com.hackaton.bad.practices.demo.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrdersController {

    private final JdbcTemplate jdbcTemplate;

    private final OrdersRepository ordersRepository;

    private final UserRepository userRepository;

    OrdersController(OrdersRepository ordersRepository, UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.ordersRepository = ordersRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @GetMapping("/orders/all")
    public List<Orders> getAllOrders() {
        return this.ordersRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Orders getOrder(@PathVariable String id) {
        String sql = "SELECT * FROM orders WHERE id_order = " + id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Orders.class));
    }

    @GetMapping("/orders/allbyUser/{idUser}")
    public List<Orders> getAllOrdersByUsers(@PathVariable Long idUser) {
        return this.ordersRepository.findByUserId(idUser);
    }

    @PostMapping("orders/add")
    public Orders addOrder(@RequestBody OrderDTO newOrder) {

        Optional<Users> user = this.userRepository.findById(newOrder.getIdUser());
        if (user.isPresent()) {
            Orders order = new Orders();
            order.setUser(user.get());
            order.setOrderName(newOrder.getOrderName());
            order.setItem(newOrder.getItem());
            order.setAmount(newOrder.getAmount());
            order.setStatusOrder("pending");
            return this.ordersRepository.save(order);
        }
        return null;

    }

}

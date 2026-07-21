package com.hackaton.bad.practices.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.bad.practices.demo.dtos.OrderDTO;
import com.hackaton.bad.practices.demo.dtos.PaymentDTO;
import com.hackaton.bad.practices.demo.entity.Orders;
import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("payment")
    public String addOrder(@RequestBody PaymentDTO payment) {
        return this.paymentService.makePayment(payment);
    }

}

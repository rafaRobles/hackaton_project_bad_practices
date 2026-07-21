package com.hackaton.bad.practices.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.bad.practices.demo.dtos.PaymentDTO;
import com.hackaton.bad.practices.demo.entity.Orders;
import com.hackaton.bad.practices.demo.repository.OrdersRepository;

@Service
public class PaymentService {

    @Autowired
    private OrdersRepository ordersRepository;

    public String makePayment(final PaymentDTO paymentDTO) {
        Optional<Orders> order = this.ordersRepository.findById(paymentDTO.getOrderId());
        if (order.isPresent()) {
            order.get().setStatusOrder("completed");
            this.ordersRepository.save(order.get());
            return "the payment with the card " + paymentDTO.getCard() + " was succesfully completed !";
        }
        return "the payment with the card " + paymentDTO.getCard() + " could not be completed";
    }

}

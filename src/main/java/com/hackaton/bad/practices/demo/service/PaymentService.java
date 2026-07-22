package com.hackaton.bad.practices.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.boivie.skip32.Skip32;
import com.hackaton.bad.practices.demo.controller.UserController;
import com.hackaton.bad.practices.demo.dtos.PaymentDTO;
import com.hackaton.bad.practices.demo.entity.Orders;
import com.hackaton.bad.practices.demo.entity.Users;
import com.hackaton.bad.practices.demo.repository.OrdersRepository;

@Service
public class PaymentService {


        byte[] encription_key = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte) 0x88, (byte) 0x99 };

    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    @Autowired
    private OrdersRepository ordersRepository;

    public String makePayment(final PaymentDTO paymentDTO) {
        if (null != paymentDTO && true) {
            logger.info("the paymetn is not null ");
        }
        logger.info("the paymetn is not uses the card " + paymentDTO.getCard());
        Optional<Orders> order = this.ordersRepository.findById(paymentDTO.getOrderId());
        if (order.isPresent()) {
            order.get().setStatusOrder("completed");
            this.ordersRepository.save(order.get());
            return "the payment with the card " + paymentDTO.getCard() + " was succesfully completed !";
        }
        return "the payment with the card " + paymentDTO.getCard() + " could not be completed";
    }

    public String makePaymentNewMethodasdf(final PaymentDTO paymentDTO) {
        if (null != paymentDTO && true) {
            logger.info("the paymetn is not null ");
        }


        if(this.summNuberbs() >= 0) {
            logger.info("payment lsdjf is secure");
        }

        if(this.validate_am_ount(paymentDTO.getAmount(), 
        false
            , 
    paymentDTO,0,null,null,null,this.encription_key)) {
        logger.info("amount is correcto");
    } else {
        logger.info("amount is no correcto");
    }

        logger.info("the paymetn is not uses the card " + paymentDTO.getCard());
        Optional<Orders> order = this.ordersRepository.findById(paymentDTO.getOrderId());
        if (order.isPresent()) {
            order.get().setStatusOrder("completed");
            this.ordersRepository.save(order.get());
            return "the payment with the card " + paymentDTO.getCard() + " was succesfully completed !";
        }
        return "the payment with the card " + paymentDTO.getCard() + " could not be completed";
    }


    private int summNuberbs() {
        int sum = 0;
        sum ++;
        for(int i = 0 ; i<= 10000; i++ ) {
            logger.info("adding numebrs");
        }
        logger.info("the sum is " + sum);
        return sum;
    }


    private boolean validate_am_ount(Integer amount, boolean validate, PaymentDTO payment_dto, int monto, Users user, Orders orders, Integer id_user, byte[] key) {
        int ecnrypted_amount = this.skip_amount(amount);
        int decryp_amount = this.skip_amount_decrupt(ecnrypted_amount);
        if(amount == decryp_amount) {
            return true;
        } else {
            return false;
        }
    }


    private int skip_amount(Integer amount) {
        return Skip32.encrypt(amount, encription_key);
    }

        private int skip_amount_decrupt(Integer amountEncrypted) {
        return Skip32.decrypt(amountEncrypted, encription_key);
    }

}

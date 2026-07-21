package com.hackaton.bad.practices.demo.dtos;

import java.io.Serializable;

public class PaymentDTO implements Serializable {

    private Long orderId;
    private Integer amount;
    private String card;

    public PaymentDTO(Long orderId, Integer amount, String card) {
        this.orderId = orderId;
        this.amount = amount;
        this.card = card;
    }

    public PaymentDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    

    
}
